package fr.xebia.blog.jfx;

import fr.xebia.blog.service.YahooService;
import fr.xebia.blog.service.dtos.HistoricQuote;
import fr.xebia.blog.service.dtos.YahooResponse;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by romainn on 13/08/2014.
 */
public class Controller implements Initializable {

    @FXML
    private TextField code;

    @FXML
    private ChoiceBox duration;

    @FXML
    private TableView<HistoricQuote> tableView;

    @FXML
    private TableColumn columnDate;

    @FXML
    private HBox hboxTable;

    @FXML
    private HBox hboxGraph;

    private YahooService yahooService;

    private ExecutorService executorService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        yahooService = new YahooService();
        columnDate.setCellValueFactory(value -> new SimpleStringProperty(new SimpleDateFormat("dd-MM-yyyy").format(((HistoricQuote) ((TableColumn.CellDataFeatures) value).getValue()).getDate())));
    }

    public void run(ActionEvent event) {

        final Stage progressBar = openLoadingWindow();

        executorService = Executors.newSingleThreadExecutor();

        Task<YahooResponse> task = new Task<YahooResponse>() {
            @Override
            protected YahooResponse call() throws Exception {
                return yahooService.getHistoric(code.getText(), (Integer) duration.getValue());
            }
        };
        task.setOnFailed(workerStateEvent -> getResponse(progressBar, false));
        task.setOnSucceeded(workerStateEvent -> {
            YahooResponse yahooResponse = (YahooResponse) workerStateEvent.getSource().getValue();
            if (yahooResponse != null && yahooResponse.getQuery().getCount() > 0) {
                getResponse(progressBar, true);
                List<HistoricQuote> quotes = yahooResponse.getQuery().getResults().getQuote();
                fillTableView(quotes);

                quotes.sort((h1, h2) -> {
                    if (h1.getDate().getTime() == h2.getDate().getTime())
                        return 0;
                    return h1.getDate().getTime() < h2.getDate().getTime() ? -1 : 1;
                });

                fillGraph(quotes);
            } else
                getResponse(progressBar, false);
        }

        );
        executorService.submit(task);
        executorService.shutdown();
    }

    private void fillGraph(List<HistoricQuote> quotes) {
        ObservableList<XYChart.Series<String, Float>> lineChartData = FXCollections
                .observableArrayList();
        final XYChart.Series<String, Float> series = createSerie(quotes);
        lineChartData.add(series);

        NumberAxis yAxis = createYAxis(quotes);

        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Temps");
        LineChart chart = new LineChart(xAxis, yAxis, lineChartData);
        chart.setPrefWidth(1010);
        chart.setPrefHeight(400);

        hboxGraph.getChildren().clear();
        hboxGraph.getChildren().add(chart);
    }

    private NumberAxis createYAxis(List<HistoricQuote> quotes) {
        Optional<HistoricQuote> max = quotes.stream().max((h1, h2) -> {
            if (h1.getHigh() == h2.getHigh())
                return 0;
            return h1.getHigh() < h2.getHigh() ? -1 : 1;
        });
        Optional<HistoricQuote> min = quotes.stream().min((h1, h2) -> {
            if (h1.getLow() == h2.getLow())
                return 0;
            return h1.getHigh() < h2.getHigh() ? -1 : 1;
        });
        return new NumberAxis("Variation", min.get().getLow(), max.get().getHigh(), 0.2);
    }

    private XYChart.Series<String, Float> createSerie(List<HistoricQuote> quotes) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        final ObservableList<XYChart.Data<String, Float>> observableList = FXCollections
                .observableArrayList();
        quotes.stream().forEach(historic -> {
            XYChart.Data<String, Float> data = new XYChart.Data<String, Float>(
                    dateFormat.format(historic.getDate()),
                    historic.getClose());
            observableList.add(data);
        });
        return new XYChart.Series<String, Float>(
                "Evolution du cours", observableList);
    }

    private void fillTableView(List<HistoricQuote> quotes) {
        final ObservableList<HistoricQuote> items = FXCollections
                .observableArrayList();
        quotes.stream().limit(5l).forEach(historic -> items.add(historic));
        tableView.setItems(items);
    }

    private Stage openLoadingWindow() {
        final Stage progressBar = new Stage();
        progressBar.initModality(Modality.WINDOW_MODAL);
        progressBar.initOwner(code.getScene().getWindow());
        progressBar.setScene(new Scene(new Group(JfxUtils.loadFxml("/fr/xebia/blog/fxml/loading.fxml"))));
        progressBar.show();
        return progressBar;
    }

    private void getResponse(Stage progressBar, boolean visible) {
        progressBar.close();
        hboxTable.setVisible(visible);
        hboxGraph.setVisible(visible);
    }

}


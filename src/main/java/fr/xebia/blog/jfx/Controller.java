package fr.xebia.blog.jfx;

import fr.xebia.blog.service.YahooService;
import fr.xebia.blog.service.dtos.YahooResponse;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by romainn on 13/08/2014.
 */
public class Controller implements Initializable {

    @FXML
    private TextField code;

    @FXML
    private ChoiceBox duration;

    @FXML
    private TableView tableView;

    @FXML
    private HBox hboxTable;

    @FXML
    private HBox hboxGraph;

    @FXML
    private LineChart graph;

    private YahooService yahooService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        yahooService = new YahooService();
    }

    public void run(ActionEvent event) {

        final Stage progressBar = new Stage();
        progressBar.initModality(Modality.WINDOW_MODAL);
        progressBar.initOwner(code.getScene().getWindow());
        progressBar.setScene(new Scene(new Group(JfxUtils.loadFxml("/fr/xebia/blog/fxml/loading.fxml"))));

        Task<YahooResponse> task = new Task<YahooResponse>() {
            @Override
            protected YahooResponse call() throws Exception {
                return yahooService.getHistoric(code.getText(), (Integer) duration.getValue());
            }
        };// -> ;
        task.setOnFailed(workerStateEvent -> {
            progressBar.close();
            visible(false);
        });
        task.setOnSucceeded(workerStateEvent -> {
            progressBar.close();
            workerStateEvent.getSource().getValue();
            visible(true);
        });
task.run();
//        YahooResponse historic =

    }

    private void visible(boolean visible) {
        hboxTable.setVisible(visible);
        hboxGraph.setVisible(visible);
    }

}

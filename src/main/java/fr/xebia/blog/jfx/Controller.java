package fr.xebia.blog.jfx;

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

    private Stage progressBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void run(ActionEvent event) {

        progressBar = new Stage();
        progressBar.initModality(Modality.WINDOW_MODAL);
        progressBar.initOwner(code.getScene().getWindow());
        progressBar.setScene(new Scene(new Group(JfxUtils.loadFxml("/fr/xebia/blog/fxml/loading.fxml"))));
        progressBar.show();


    }
}

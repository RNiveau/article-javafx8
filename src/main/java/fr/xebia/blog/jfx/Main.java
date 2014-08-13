package fr.xebia.blog.jfx;


/**
 * Created by romainn on 11/08/2014.
 */

import fr.xebia.blog.service.dtos.YahooResponse;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setWidth(1024);
        primaryStage.setHeight(968);
        primaryStage.setTitle("JavaFX Xebia");
        primaryStage.setScene(new Scene((Parent) JfxUtils.loadFxml("/fr/xebia/blog/fxml/screen.fxml"), 1024, 968));
        primaryStage.show();
    }
}

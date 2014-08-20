package fr.xebia.blog.jfx;


/**
 * Created by romainn on 11/08/2014.
 */

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

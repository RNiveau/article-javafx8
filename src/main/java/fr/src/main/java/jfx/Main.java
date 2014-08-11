package fr.xebia.blog.jfx;/**
 * Created by romainn on 11/08/2014.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        WeldContainer weldContainer = new Weld().initialize();

        primaryStage.setWidth(1024);
        primaryStage.setHeight(968);
        primaryStage.setTitle("JavaFX Xebia");
        primaryStage.setScene(new Scene(new AnchorPane(), 1024, 768));
        primaryStage.show();
    }
}

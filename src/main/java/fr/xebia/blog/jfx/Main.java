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

        Client client = ClientBuilder.newClient();

        String query = "select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22"
                + "KN"
                + ".PA%22%20and%20startDate%20%3D%20%22"
                + "2014-03-24"
                + "%22%20and%20endDate%20%3D%20%22"
                + "2014-06-14" + "%22";
        WebTarget target = client.target("http://query.yahooapis.com").path("v1/public/yql")
                .queryParam("q", query)
                .queryParam("format", "json")
                .queryParam("env", "store%3A%2F%2Fdatatables.org%2Falltableswithkeys");

        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        System.out.println(response.getStatus());
        String json = response.readEntity(String.class);
        System.out.println(json);

        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        try {
            YahooResponse yahooResponse = mapper.readValue(json,
                    new TypeReference<YahooResponse>() {
                    });
            System.out.println(yahooResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        if (response.getStatus() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : "
//                    + response.getStatus());
//        }

//        Object output = response.getEntity();
        primaryStage.setWidth(1024);
        primaryStage.setHeight(968);
        primaryStage.setTitle("JavaFX Xebia");
        primaryStage.setScene(new Scene((Parent) JfxUtils.loadFxml("/fr/xebia/blog/fxml/screen.fxml"), 1024, 968));
        primaryStage.show();
    }
}

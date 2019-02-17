package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        Label pointsNumberLabel = new Label("Точек: ");
        Label clusterNumberLabel = new Label("Классов: ");
        TextField pointsNumber = new TextField();
        TextField clusterNumber = new TextField();
        Button button = new Button("Calculate");

        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().add(pointsNumberLabel);
        flowPane.getChildren().add(pointsNumber);
        flowPane.getChildren().add(clusterNumberLabel);
        flowPane.getChildren().add(clusterNumber);
        flowPane.getChildren().add(button);

        root.setTop(flowPane);

        Group group = new Group();

        Color[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW,
                Color.MAGENTA, Color.BLACK, Color.SIENNA, Color.SLATEGRAY,
                Color.SEAGREEN, Color.VIOLET, Color.AQUA, Color.CRIMSON,
                Color.DARKBLUE, Color.CHARTREUSE, Color.DARKORANGE, Color.BLUEVIOLET,
                Color.SALMON, Color.DARKGOLDENROD, Color.ROYALBLUE, Color.SPRINGGREEN};
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                group.getChildren().clear();
                Kmeans kmeans = new Kmeans(Integer.valueOf(pointsNumber.getCharacters().toString()), Integer.valueOf(clusterNumber.getCharacters().toString()));
//                Kmeans kmeans = new Kmeans(100000, 20);
                List<Cluster> clusters = kmeans.calculate();
                for (int i = 0; i < clusters.size(); ++i) {
                    for (Point2D point : clusters.get(i).getPointsInCluster()) {
                        Circle circle = new Circle(point.getX(), point.getY(), 1);
                        circle.setStroke(colors[i]);
                        circle.setFill(colors[i]);
                        group.getChildren().add(circle);
                    }
                }
                System.out.println('1');
            }
        });
        root.setCenter(group);

        Scene scene = new Scene(root, 900, 500);
        stage.setTitle("К-средних Соболевская");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

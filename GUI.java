package sps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

// JavaFX screen for Smart Parking System.
public class GUI extends Application {

    private TextField idField;
    private TextField plateField;
    private TextField ownerField;
    private TextField hoursField;
    private TextArea outputArea;

    @Override
    public void start(Stage stage) {
        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setStyle("-fx-background-color: #eef6f2; -fx-padding: 15;");

        Label titleLabel = new Label("Smart Parking System");
        Label idLabel = new Label("Vehicle ID");
        Label plateLabel = new Label("Plate");
        Label ownerLabel = new Label("Owner");
        Label hoursLabel = new Label("Hours");

        idField = new TextField();
        plateField = new TextField();
        ownerField = new TextField();
        hoursField = new TextField();
        outputArea = new TextArea();

        Button addButton = new Button("Add");
        Button showButton = new Button("Show");

        pane.add(titleLabel, 0, 0, 2, 1);
        pane.add(idLabel, 0, 1);
        pane.add(idField, 1, 1);
        pane.add(plateLabel, 0, 2);
        pane.add(plateField, 1, 2);
        pane.add(ownerLabel, 0, 3);
        pane.add(ownerField, 1, 3);
        pane.add(hoursLabel, 0, 4);
        pane.add(hoursField, 1, 4);
        pane.add(addButton, 0, 5);
        pane.add(showButton, 1, 5);
        pane.add(outputArea, 0, 6, 2, 1);

        addButton.setOnAction(e -> {
            try {
                String id = idField.getText().trim();
                String plate = plateField.getText().trim();
                String owner = ownerField.getText().trim();
                int hours = Integer.parseInt(hoursField.getText().trim());

                Main.system.addVehicle(new Car(id, plate, owner, hours, "", "SPS", 4, false));
                outputArea.setText("Vehicle added");
            } catch (Exception ex) {
                outputArea.setText(ex.getMessage());
            }
        });

        showButton.setOnAction(e -> {
            outputArea.setText(Main.system.displayAllVehicles()
                    + "\n------------------\n"
                    + Main.system.displayAllTickets());
        });

        Scene scene = new Scene(pane, 400, 500);
        stage.setTitle("Smart Parking System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

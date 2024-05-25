package main;

import customer.CustomerLogin;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import staffs.StaffLogin;

public class Identify extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label lblchoose = new Label("Welcome to our Website!!! \nChoose one option");
        Label identify = new Label("");
        lblchoose.setStyle("-fx-font-weight: bold;");
        identify.setStyle("-fx-font-weight: bold;");

        Slider sl = new Slider(0, 1, 0.5);
        sl.setShowTickLabels(true);
        sl.setShowTickMarks(true);
        sl.setMajorTickUnit(1);
        sl.setMinorTickCount(0);

        Button btnnext = new Button("Next");
        btnnext.setStyle("-fx-background-color: pink; -fx-text-fill: black");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(lblchoose, identify, sl, btnnext);
        vbox.setStyle("-fx-background-image: url('file:Login.jpg'); " +
                "-fx-background-size: cover;");

        sl.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() < 0.5) {
                identify.setText("Customer");
            } else {
                identify.setText("Staff");
            }
        });

        btnnext.setOnAction((event) -> {
            if (sl.getValue() == 0) {
                GotoCustomerLogin(primaryStage);
            } else if (sl.getValue() == 1) {
                GotoStaffLogin(primaryStage);
            }
        });

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Identify Person");
        primaryStage.setHeight(250);
        primaryStage.setWidth(400);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private static void GotoCustomerLogin(Stage primaryStage) {
        CustomerLogin cuslog = new CustomerLogin();
        try {
            cuslog.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void GotoStaffLogin(Stage primaryStage) {
        StaffLogin cusreg = new StaffLogin();
        try {
            cusreg.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

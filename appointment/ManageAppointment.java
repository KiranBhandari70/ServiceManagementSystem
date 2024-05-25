package appointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import appointment.Appointment.Status;
import homepage.CustomerMainPage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ManageAppointment extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label lblA = new Label("Appointment ID");
        Label lblC = new Label("Customer ID");
        Label lblW = new Label("Worker ID");
        Label lblS = new Label("Service ID");
        Label lblD = new Label("Appointment Date");
        Label lblT = new Label("Appointment Time");
        Label lblSs = new Label("Status");

        TextField txtA = new TextField();
        txtA.setPromptText("Appointment ID");

        TextField txtC= new TextField();
        txtC.setPromptText("Customer ID*");

        TextField txtW = new TextField();
        txtW.setPromptText("Worker ID*");

        TextField txtS = new TextField();
        txtS.setPromptText("Service ID*");

        TextField txtD = new TextField();
        txtD.setPromptText("Date*");

        TextField txtT = new TextField();
        txtT.setPromptText("Time*");

        ComboBox<String> statusComboBox = new ComboBox<>();
        List<String> status = new ArrayList<>();
        status.add("Fixed");
        status.add("Cancel");
        status.add("Completed");
        statusComboBox.getItems().addAll(status);

        Button btnS = new Button("Search");
        btnS.setOnAction((event) -> {
            try {
                int A = Integer.parseInt(txtA.getText());
                Appointment appointment = Find(A);
                if (appointment != null) {

                    txtC.setText(Integer.toString(appointment.getCustomerID()));
                	txtW.setText(Integer.toString(appointment.getWorkerID()));
                	txtS.setText(Integer.toString(appointment.getServiceID()));

                    txtD.setText(appointment.getAppointmentDate());
                    txtT.setText(appointment.getTime());

                    Status state = appointment.getStatus();

                    switch (state) {
                        case Fixed:
                            statusComboBox.getSelectionModel().select(0);
                            break;
                        case Cancel:
                            statusComboBox.getSelectionModel().select(1);
                            break;
                        case Completed:
                            statusComboBox.getSelectionModel().select(2);
                            break;
                    }

                    System.out.println("Record found");
                } else {
                	  Alert alert = new Alert(AlertType.WARNING);
                      alert.setTitle("Appointment");
                      alert.setContentText("Error searching Appointment. Please try again later.");
                      alert.show();
                    System.out.println("Record not found");
                }
            } catch (NumberFormatException e) {
            }
        });

        Button btnUpdate = new Button("Update");
        btnUpdate.setOnAction((event) -> {
            try {
                int aid = Integer.parseInt(txtA.getText());
                int cid = Integer.parseInt(txtC.getText());
                int wid = Integer.parseInt(txtW.getText());
                int sid = Integer.parseInt(txtS.getText());

                String date = txtD.getText();
                String time = txtT.getText();
                String stat = statusComboBox.getValue();

                Appointment appointment = new Appointment(aid,cid, wid, sid, date, time, stat);
                boolean res = refactor(appointment);
                if (res) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Appointment");
                    alert.setContentText("Your appointment has been updated.");
                    alert.show();
                    System.out.println("Record updated");
                } else {
                	  Alert alert = new Alert(AlertType.WARNING);
                      alert.setTitle("Appiointment");
                      alert.setContentText("Error updating appointment. Please try again later.");
                      alert.show();
                    System.out.println("Error: to update record");
                }
            } catch (NumberFormatException e) {
            }
        });

        Button btnback = new Button("Back");
		btnback.setOnAction((event)->{
			GotoCustomermain(primaryStage);
		});
		
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        gridPane.add(lblA, 0, 0);
        gridPane.add(txtA, 1, 0);
        gridPane.add(lblC, 0, 1);
        gridPane.add(txtC, 1, 1);
        gridPane.add(lblW, 0, 2);
        gridPane.add(txtW, 1, 2);
        gridPane.add(lblS, 0, 3);
        gridPane.add(txtS, 1, 3);
        gridPane.add(lblD, 0, 4);
        gridPane.add(txtD, 1, 4);
        gridPane.add(lblT, 0, 5);
        gridPane.add(txtT, 1, 5);
        gridPane.add(lblSs, 0, 6);
        gridPane.add(statusComboBox, 1, 6);
        gridPane.add(btnS, 0, 7);
        gridPane.add(btnUpdate, 1, 7);
        gridPane.add(btnback, 2, 7);


        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("Manage Appointments");
    	primaryStage.setHeight(600);
		primaryStage.setWidth(800);
		primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
	
	private void GotoCustomermain (Stage primaryStage) {
		CustomerMainPage cusmainpage = new CustomerMainPage();
		try {
			cusmainpage.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
    
    public boolean refactor(Appointment appointment) {
        boolean result = false;
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String HOST = "localhost";
        int PORT = 3306;
        String DATABASE = "ServiceSystem";
        String DBUSER = "root";
        String DBPASS = "Kiran@#123";
        String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
        String sql = "UPDATE Appointment SET CustomerID=?,WorkerID=?,ServiceID=?, AppointmentDate=?, ATime=?,State=? WHERE AppointmentID=?";
        try {
            Class.forName(DRIVER);
            try (Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
                 PreparedStatement pstat = conn.prepareStatement(sql)) {
                pstat.setInt(1, appointment.getCustomerID());
                pstat.setInt(2, appointment.getWorkerID());
                pstat.setInt(3, appointment.getServiceID());
                pstat.setString(4, appointment.getAppointmentDate());
                pstat.setString(5, appointment.getTime());
                pstat.setString(6, appointment.getStatus().toString());
                pstat.setInt(7, appointment.getAppointmentID());
                pstat.executeUpdate();
            }
            result = true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
        return result;
    }

    public Appointment Find (int aid) {
        Appointment appointment = null;
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String HOST = "localhost";
        int PORT = 3306;
        String DATABASE = "ServiceSystem";
        String DBUSER = "root";
        String DBPASS = "Kiran@#123";
        String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
        String sql = "SELECT * FROM Appointment WHERE AppointmentID=?";
        try {
            Class.forName(DRIVER);
            try (Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
                 PreparedStatement pstat = conn.prepareStatement(sql)) {
                pstat.setInt(1, aid);
                try (ResultSet rs = pstat.executeQuery()) {
                    if (rs.next()) {
                        int cid = rs.getInt("CustomerID");
                        int wid = rs.getInt("WorkerID");
                        int sid = rs.getInt("ServiceID");
                        String date = rs.getString("AppointmentDate");
                        String time = rs.getString("ATime");
                        String state = rs.getString("State");
                        appointment = new Appointment(aid,cid, wid, sid, date, time, state);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
        return appointment;

    }
}


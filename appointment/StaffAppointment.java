package appointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import appointment.Appointment.Status;
import homepage.StaffMainPage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StaffAppointment extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	  Label lbla = new Label("Appointment ID");
	  Label lblc = new Label("Customer ID");
      Label lblw = new Label("Worker ID");
      Label lbls = new Label("Service ID");
	  Label lbld = new Label("Appintment Date");
	  Label lblt = new Label("Appoint Time");
	  Label lblSs = new Label("Status");
	  
	  
	  TextField txta = new TextField();
      txta.setPromptText("Appointment ID");

      TextField txtc = new TextField();
      txtc.setPromptText("Customer id*");

      TextField txtw = new TextField();
      txtw.setPromptText("Worker ID* ");

      TextField txts = new TextField();
      txts.setPromptText("Service id*");

      TextField txtd = new TextField();
      txtd.setPromptText("Date* ");

      TextField txtt = new TextField();
      txtt.setPromptText("Time*");
      
      ComboBox<Status> statusComboBox = new ComboBox<>(); 
      List<Status> statuses = new ArrayList<>();
      statuses.add(Status.Completed);
      statuses.add(Status.Fixed);
      statuses.add(Status.Cancel);
      statusComboBox.getItems().addAll(statuses);

      Button btnsave = new Button("Appoint");
      btnsave.setOnAction((event) -> {
          try {
              int cusid = Integer.parseInt(txtc.getText());
              int wid = Integer.parseInt(txtw.getText());
              int serid = Integer.parseInt(txts.getText());
              String appdate = txtd.getText();
              String aptime = txtt.getText();
              Status stats = statusComboBox.getValue();

              Appointment appointment = new Appointment(cusid, wid, serid, appdate, aptime, stats);

              boolean outcome = Appoint(appointment, cusid, wid, serid);
              if (outcome) {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Appointment");
                  alert.setContentText("Your appointment has been booked.");
                  alert.show();
                  System.out.println("Appointment booked");
              } else {
                  Alert alert = new Alert(Alert.AlertType.WARNING);
                  alert.setTitle("Appointment");
                  alert.setContentText("Error appointing. Please try again later.");
                  alert.show();
                  System.out.println("Error booking appointment.");
              }
          } catch (NumberFormatException e) {
              e.printStackTrace();
          }
      });
      
 	 Button btnback = new Button("Back");
	 btnback.setOnAction((event)->{
		 BackLogin (primaryStage);
	 });
	


      
      GridPane pane = new GridPane();
      pane.setVgap(10);
      pane.setHgap(10);
      pane.setPadding(new Insets(20, 20, 20, 20));

      GridPane.setConstraints(lbla, 0, 0);
      GridPane.setConstraints(txta, 1, 0);
      GridPane.setConstraints(lblc, 0, 1);
      GridPane.setConstraints(txtc, 1, 1);
      GridPane.setConstraints(lblw, 0, 2);
      GridPane.setConstraints(txtw, 1, 2);
      GridPane.setConstraints(lbls, 0, 3);
      GridPane.setConstraints(txts, 1, 3);
      GridPane.setConstraints(lbld, 0, 4);
      GridPane.setConstraints(txtd, 1, 4);
      GridPane.setConstraints(lblt, 0,5);
      GridPane.setConstraints(txtt, 1, 5);
      GridPane.setConstraints(lblSs, 0,6);
      GridPane.setConstraints(statusComboBox, 1, 6);
      GridPane.setConstraints(btnsave, 1, 7);
      GridPane.setConstraints(btnback, 2, 7);
      
      pane.getChildren().addAll(lbla, lblc, lblw, lbls, lbld, lblt, lblSs,
              txta, txtc, txtw, txts,txtd,txtt,statusComboBox,
              btnsave,btnback);

      Scene scene = new Scene(pane);
      primaryStage.setScene(scene);
      primaryStage.setTitle("Appointment Page");
      primaryStage.setHeight(400);
      primaryStage.setWidth(400);
      primaryStage.setResizable(true);
      primaryStage.show();
		
	}
	
	private void BackLogin (Stage primaryStage) {
		StaffMainPage cuslog = new StaffMainPage();
		try {
			cuslog.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
    public boolean Appoint(Appointment appointment,int customerid,int workerId, int serviceId) {
        boolean result = false;
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String HOST = "localhost";
        int PORT = 3306;
        String DATABASE = "ServiceSystem";
        String DBUSER = "root";
        String DBPASS = "Kiran@#123";
        String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
        String sql = "INSERT INTO Appointment (CustomerID,WorkerID,ServiceID, AppointmentDate, ATime,State) VALUES (?, ?, ?, ?,?,?)";
        System.out.println(sql);

        try {
        	 Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
        	 PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setInt(1, appointment.getCustomerID());
            pstat.setInt(2, appointment.getWorkerID());
            pstat.setInt(3, appointment.getServiceID());
            pstat.setString(4, appointment.getAppointmentDate());
            pstat.setString(5, appointment.getTime());
            pstat.setString(6, appointment.getStatus().toString());

         
            pstat.executeUpdate();
            result = true;

        } catch (SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
        return result;
    }

}

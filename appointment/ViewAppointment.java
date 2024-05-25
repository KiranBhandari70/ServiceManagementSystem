package appointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewAppointment extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		

		 TableView<Appointment>tblappoint= new TableView<Appointment>();
			tblappoint.setPrefWidth(1200);
			tblappoint.setPrefHeight(150);
		
			TableColumn<Appointment, Integer> colqid = new TableColumn<>("Appointment ID");
			colqid.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
			colqid.setMinWidth(50);
			
			TableColumn<Appointment, Integer> colsid = new TableColumn<>("Customer ID");
			colsid.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
			colsid.setMinWidth(50);
			
			TableColumn<Appointment, Integer> colcid = new TableColumn<>("Worker ID");
			colcid.setCellValueFactory(new PropertyValueFactory<>("WorkerID"));
			colcid.setMinWidth(50);
			
			TableColumn<Appointment, Integer> coldesc = new TableColumn<>("ServiceID");
			coldesc.setCellValueFactory(new PropertyValueFactory<>("ServiceID")); 
	        coldesc.setMinWidth(50);
			
			TableColumn<Appointment, String> colbud = new TableColumn<>("Appointment Dates");
			colbud.setCellValueFactory(new PropertyValueFactory<>("AppointmentDate"));
			colbud.setMinWidth(50);
			

			TableColumn<Appointment, String> colbu = new TableColumn<>("Appointment Time");
			colbu.setCellValueFactory(new PropertyValueFactory<>("Time"));
			colbu.setMinWidth(50);
			

	        TableColumn<Appointment, Appointment.Status> colstat = new TableColumn<>("Status");
	        colstat.setCellValueFactory(new PropertyValueFactory<>("Status")); 
	        colstat.setMinWidth(50);
			
			tblappoint.getColumns().addAll(colqid, colsid,colcid, coldesc, colbud,colbu, colstat);
			
			List<Appointment> appointment = new ArrayList<Appointment>();
			appointment = AllData();
			for(Appointment appointments: appointment) {
				tblappoint.getItems().add(appointments);
				
			}
			
			VBox pane = new VBox();
			pane.setSpacing(15);
		        pane.setPadding(new Insets(20, 20, 20, 20));

			pane.getChildren().addAll(tblappoint);
			
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("List All Appointment");
			primaryStage.setHeight(600);
			primaryStage.setWidth(800);
			primaryStage.setResizable(false);
			primaryStage.show();
			
	}
	
	public ArrayList AllData() {				
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		String DRIVER ="com.mysql.cj.jdbc.Driver";
		String HOST ="localhost";
		int PORT=3306;
		String DATABASE ="ServiceSystem";
		String DBUSER="root";
		String DBPASS="Kiran@#123";
		String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
		String sql="SELECT * FROM Appointment where State= 'Completed' ;";
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
			PreparedStatement pstat = conn.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			while(rs.next()) {
				int qid = rs.getInt("AppointmentID");
				int sid = rs.getInt("CustomerID");
				int cid = rs.getInt("WorkerID");
				int Detail = rs.getInt("ServiceID");
				String Cost = rs.getString("AppointmentDate");
				String time = rs.getString("ATime");
				String stats = rs.getString("State");
				
				Appointment appointment = new Appointment(qid, sid,cid, Detail, Cost,time,stats);				
				appointments.add(appointment);
				System.out.println(appointment);
			}
			pstat.close();
			conn.close();			
		}
		catch(Exception ex) {
			System.out.println("Error : "+ex.getMessage());
		}
		return appointments;
	}

}

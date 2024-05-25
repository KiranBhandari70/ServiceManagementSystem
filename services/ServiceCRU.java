package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import homepage.StaffMainPage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ServiceCRU extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
        Label lblsid = new Label("Service ID: ");
			Label lblsn = new Label("Service Name: ");
			Label lbldesc = new Label("Description: ");
			Label lblestdur = new Label("Estimated Duration: ");
			Label lblcost = new Label("Cost: ");
		
			TextField txtsid = new TextField("");
			txtsid.setPromptText("service ID");
			
			TextField txtsn = new TextField("");
			txtsn.setPromptText("Name of service ");

			TextField txtdesc = new TextField("");
			txtdesc.setPromptText("description of service ");

			TextField txtestdur = new TextField("");
			txtestdur.setPromptText("estimated time of work");
			
			TextField txtcost = new TextField("");
			txtcost.setPromptText("cost of work");
			
			   // Button to save new services
			 Button btnsign = new Button("Add");
			 btnsign.setOnAction((event)-> {
				 int ServiceID = Integer.parseInt(txtsid.getText());  
				 String ServiceName = txtsn.getText();
				 String Description = txtdesc.getText();
				 String EstimatedTime = txtestdur.getText();
				 String Cost = txtcost.getText();
			
			 	 
		 Service service =new Service(ServiceID,ServiceName,Description,EstimatedTime,Cost);	
			boolean outcome = InsertService(service);
			if(outcome==true) {			
			Alert alertin = new Alert(AlertType.INFORMATION);
	    	alertin.setTitle("Registered Information");
		    alertin.setContentText("The service is added successfully.");
			alertin.show();
			Timeline entryTime = new Timeline(
		     	new KeyFrame(Duration.seconds(4),e ->alertin.close()));
					entryTime.play();
						System.out.println("Service Saved");
				}else {
			Alert alertout= new Alert(AlertType.WARNING);
		    alertout.setTitle("Registered Information");
		    alertout.setContentText("Error to add new services.Please fill up details properly.");
		    alertout.show();
			Timeline outgo = new Timeline(
				new KeyFrame(Duration.seconds(4),e ->alertout.close()));
						outgo.play();
						System.out.println("Error: to save services.");
					}   
				});
			
			 // button to search existing services
			Button btnSearch=new Button("Search");
			btnSearch.setOnAction((event)->{
				int sid = Integer.parseInt(txtsid.getText());
				Service service= searchService(sid);						
				if(service != null) {
				
					txtsn.setText(service.getServiceName());
					txtdesc.setText(service.getDetails());
					txtestdur.setText(service.getEstimatedDuration());
					txtcost.setText(service.getCost());
				
					System.out.println("Record found");
				}
				else {
					System.out.println("Record not found");
				}
			});
			
			// btn to update service records
			Button btnUpdate=new Button("Refactor");
			btnUpdate.setOnAction((event)->{
				int sid = Integer.parseInt(txtsid.getText());
				String servicename = txtsn.getText();
				String description = txtdesc.getText();
				String duration = txtestdur.getText();
				String cost = txtcost.getText();
				
				Service service =new Service (sid,servicename,description,duration,cost);			
				boolean res = UpdateServices(service);
				if(res==true) { 
					System.out.println("Record Saved");
					Alert alertin = new Alert(AlertType.INFORMATION);
					 alertin.setTitle("Refactored Information");
					 alertin.setHeaderText(null);
					 alertin.setContentText("The service is updated successfully.");
					 alertin.show();
					Timeline entryTime = new Timeline(
							new KeyFrame(Duration.seconds(4),e ->alertin.close())
							);
					entryTime.play();
					System.out.println("Record Saved");
				}
				else {
					System.out.println("Error: to save record");
					Alert alertin = new Alert(AlertType.WARNING);
					 alertin.setTitle("Refactored Information");
					 alertin.setHeaderText(null);
					 alertin.setContentText("The service update is not possible.");
					 alertin.show();
					Timeline entryTime = new Timeline(
							new KeyFrame(Duration.seconds(4),e ->alertin.close())
							);
					entryTime.play();
				}
			});
			
			 Button btnback = new Button("Back");
			 btnback.setOnAction((event)->{
				 BackLogin (primaryStage);
			 });
			
			
			GridPane pane = new GridPane ();
			pane.setVgap(10);
			pane.setHgap(10);
			pane.setPadding(new Insets(20,20,20,20));
			
	  GridPane.setConstraints(lblsid,0,0);
	  GridPane.setConstraints(txtsid,1,0);
	  GridPane.setConstraints(lblsn,0,1);
	  GridPane.setConstraints(txtsn,1,1);
	  GridPane.setConstraints(lbldesc,0,2);
	  GridPane.setConstraints(txtdesc,1,2);
	  GridPane.setConstraints(lblestdur,0,3);
	  GridPane.setConstraints(txtestdur,1,3);
	  GridPane.setConstraints(lblcost,0,4);
	  GridPane.setConstraints(txtcost,1,4);
	  GridPane.setConstraints(btnsign,0,5);
	  GridPane.setConstraints(btnSearch,1,5);
	  GridPane.setConstraints(btnUpdate,2,5);
	  GridPane.setConstraints(btnback,3,5);
	  
	  pane.setStyle("-fx-background-image: url('file:Login.jpg'); " +
              "-fx-background-size: cover;");

		pane.getChildren().addAll(lblsid,lblsn,lbldesc,lblestdur,lblcost,
                                  txtsid, txtsn,txtdesc,txtestdur,txtcost,
                                  btnSearch,btnUpdate,btnsign,btnback);
		
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Staff Home Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(800);
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

	 public Service searchService (int sid) {
			Service service = null;
			String DRIVER = "com.mysql.cj.jdbc.Driver";
			String HOST = "localhost";
			int PORT = 3306;
			String DATABASE = "ServiceSystem";
			String DBUSER = "root";
			String DBPASS = "Kiran@#123";
			String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
			String sql = "select * from Service where ServiceID=? ;";
			System.out.println(sql);
			
			try {
				Class.forName(DRIVER);
				Connection conn = DriverManager.getConnection(URL,DBUSER,DBPASS);
				PreparedStatement pstat = conn.prepareStatement(sql);
				pstat.setInt(1,sid);
				ResultSet rs = pstat.executeQuery();
				while(rs.next()) {	
				String ServiceName=rs.getString("ServiceName");
				String Detail=rs.getString("Detail");
				String EstimatedDuration=rs.getString("EstimatedDuration");
				String Cost=rs.getString("Cost");
			    service =new Service(sid,ServiceName,Detail,EstimatedDuration,Cost);		
				
				}
				pstat.close();
				conn.close();

			}
			catch (Exception ex) {
				System.out.println("Error : "+ex.getMessage());
			}
					return service;
		}
		
		//updating a service
		public boolean UpdateServices (Service service) {
			boolean result = false;
			String DRIVER = "com.mysql.cj.jdbc.Driver";
			String HOST = "localhost";
			int PORT = 3306;
			String DATABASE = "ServiceSystem";
			String DBUSER = "root";
			String DBPASS = "Kiran@#123";
			String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
			String sql = "Update Service SET ServiceName=?, Detail=?, EstimatedDuration=?, Cost=? Where ServiceID=? ;";
			System.out.println(sql);
			
			try {
				Class.forName(DRIVER);
				Connection conn = DriverManager.getConnection(URL,DBUSER,DBPASS);
				PreparedStatement pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, service.getServiceName());
				pstat.setString(2, service.getDetails());
				pstat.setString(3, service.getEstimatedDuration());
				pstat.setString(4, service.getCost());
				pstat.setInt(5, service.getServiceID());

				pstat.executeUpdate();
				pstat.close();
				conn.close();
				result=true;
			
				pstat.close();
				conn.close();
			}
			catch (Exception ex) {
				System.out.println("Error : "+ex.getMessage());
			}
					return result;
		}
		

		public boolean InsertService (Service service) {
			boolean result = false;
			String DRIVER = "com.mysql.cj.jdbc.Driver";
			String HOST = "localhost";
			int PORT = 3306;
			String DATABASE = "ServiceSystem";
			String DBUSER = "root";
			String DBPASS = "Kiran@#123";
			String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
			String sql = "INSERT INTO Service (ServiceID,ServiceName,Detail, EstimatedDuration, Cost) VALUES (?,? ,?, ?, ?) ;";
			System.out.println(sql);
			
			try {
				Class.forName(DRIVER);
				Connection conn = DriverManager.getConnection(URL,DBUSER,DBPASS);
				PreparedStatement pstat = conn.prepareStatement(sql);
				pstat.setInt(1, service.getServiceID());
				pstat.setString(2, service.getServiceName());
				pstat.setString(3, service.getDetails());
				pstat.setString(4, service.getEstimatedDuration());
				pstat.setString(5, service.getCost());
			
				pstat.executeUpdate();
				pstat.close();
				conn.close();
				result=true;
			
				pstat.close();
				conn.close();
			}
			catch (Exception ex) {
				System.out.println("Error : "+ex.getMessage());
			}
					return result;
		}
		
		
	}


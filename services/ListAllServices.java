package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import homepage.CustomerMainPage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListAllServices extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		 TableView<Service> table1= new TableView<Service>();
			table1.setPrefWidth(2000);
			table1.setPrefHeight(150);
			
			//Table Columns of service table
			TableColumn<Service, Integer> colsid = new TableColumn<>("ServiceID");
			colsid.setCellValueFactory(new PropertyValueFactory<>("ServiceID"));
			colsid.setMinWidth(50);
			
			TableColumn<Service, String> colname = new TableColumn<>("Service Name");
			colname.setCellValueFactory(new PropertyValueFactory<>("ServiceName"));
			colname.setMinWidth(50);
			
			TableColumn<Service, String> coldesc = new TableColumn<>("Description");
			coldesc.setCellValueFactory(new PropertyValueFactory<>("Details"));
			coldesc.setMinWidth(950);
			
			TableColumn<Service, String> colestimate = new TableColumn<>("Duration");
			colestimate.setCellValueFactory(new PropertyValueFactory<>("EstimatedDuration"));
			colestimate.setMinWidth(50);
			
			TableColumn<Service, String> colcost = new TableColumn<>("Cost");
			colcost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
			colcost.setMinWidth(50);
			
			table1.getColumns().addAll(colsid, colname, coldesc, colestimate, colcost);
			
			List<Service> service = new ArrayList<Service>();
			service = AllData();
			for(Service services: service) {
				table1.getItems().add(services);
			}
			
			Button btnback = new Button("Back");
			btnback.setOnAction((event)->{
				GotoCustomermain(primaryStage);
			});
			
			VBox pane = new VBox();
			pane.setSpacing(15);
		        pane.setPadding(new Insets(20, 20, 20, 20));

		 pane.setStyle("-fx-background-image: url('file:Login.jpg'); " +
		                "-fx-background-size: cover;");
		pane.getChildren().addAll(table1,btnback);
		
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("List All Services");
		primaryStage.setHeight(600);
		primaryStage.setWidth(800);
		primaryStage.setResizable(false);
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
	
	public ArrayList AllData() {				
		ArrayList<Service> services = new ArrayList<Service>();
		String DRIVER ="com.mysql.cj.jdbc.Driver";
		String HOST ="localhost";
		int PORT=3306;
		String DATABASE ="ServiceSystem";
		String DBUSER="root";
		String DBPASS="Kiran@#123";
		String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
		String sql="SELECT * FROM Service";
		try {
			Class.forName(DRIVER); //loading driver
			Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
			PreparedStatement pstat = conn.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			while(rs.next()) {
				int sid = rs.getInt("ServiceID");
				String ServiceName = rs.getString("ServiceName");
				String Descript = rs.getString("Detail");
				String Estimate = rs.getString("EstimatedDuration");
				String cost=rs.getString("Cost");
				
				Service service = new Service(sid, ServiceName, Descript, Estimate, cost);				
				services.add(service);
				System.out.println(service);
			}
			pstat.close();
			conn.close();			
		}
		catch(Exception ex) {
			System.out.println("Error : "+ex.getMessage());
		}
		return services;
	}


}

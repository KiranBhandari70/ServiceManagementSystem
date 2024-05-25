package quote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import homepage.StaffMainPage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListQuote extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		 TableView<Quote>tblquote= new TableView<Quote>();
			tblquote.setPrefWidth(1200);
			tblquote.setPrefHeight(150);
		
			TableColumn<Quote, Integer> colqid = new TableColumn<>("Quote ID");
			colqid.setCellValueFactory(new PropertyValueFactory<>("QuoteID"));
			colqid.setMinWidth(50);
			
			TableColumn<Quote, Integer> colsid = new TableColumn<>("Service ID");
			colsid.setCellValueFactory(new PropertyValueFactory<>("ServiceID"));
			colsid.setMinWidth(50);
			
			TableColumn<Quote, Integer> colcid = new TableColumn<>("Customer ID");
			colcid.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
			colcid.setMinWidth(50);
			
			TableColumn<Quote, String> coldesc = new TableColumn<>("Details");
			coldesc.setCellValueFactory(new PropertyValueFactory<>("Description")); 
	        coldesc.setMinWidth(300);
			
			TableColumn<Quote, String> colbud = new TableColumn<>("Budget");
			colbud.setCellValueFactory(new PropertyValueFactory<>("Budget"));
			colbud.setMinWidth(100);
			

	        TableColumn<Quote, Quote.Status> colstat = new TableColumn<>("Status");
	        colstat.setCellValueFactory(new PropertyValueFactory<>("Status")); 
	        colstat.setMinWidth(50);
			
			tblquote.getColumns().addAll(colqid, colsid,colcid, coldesc, colbud, colstat);
			
			List<Quote> quote = new ArrayList<Quote>();
			quote = AllData();
			for(Quote quotes: quote) {
				tblquote.getItems().add(quotes);
			}

			Button btnnext = new Button("Manage Quote");
			btnnext.setOnAction((event)->{ 
				GotoQuoteStaff(primaryStage);
			});
			
			 Button btnback = new Button("Back");
			 btnback.setOnAction((event)->{
				 BackLogin (primaryStage);
			 });
			
			
			VBox pane = new VBox();
			pane.setSpacing(15);
		        pane.setPadding(new Insets(20, 20, 20, 20));

			pane.getChildren().addAll(tblquote,btnnext,btnback);
			 pane.setStyle("-fx-background-image: url('file:Login.jpg'); " +
		                "-fx-background-size: cover;");
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("List All Services");
			primaryStage.setHeight(600);
			primaryStage.setWidth(800);
			primaryStage.setResizable(false);
			primaryStage.show();
			
	}
	
	public ArrayList AllData() {				
		ArrayList<Quote> quotes = new ArrayList<Quote>();
		String DRIVER ="com.mysql.cj.jdbc.Driver";
		String HOST ="localhost";
		int PORT=3306;
		String DATABASE ="ServiceSystem";
		String DBUSER="root";
		String DBPASS="Kiran@#123";
		String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
		String sql="SELECT * FROM Quote where State = 'Pending' ;";
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
			PreparedStatement pstat = conn.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			while(rs.next()) {
				int qid = rs.getInt("QuoteID");
				int sid = rs.getInt("ServiceID");
				int cid = rs.getInt("CustomerID");
				String Detail = rs.getString("Details");
				String Cost = rs.getString("Budget");
				String stats = rs.getString("State");
				
				Quote quote = new Quote(qid, sid,cid, Detail, Cost,stats);				
				quotes.add(quote);
				System.out.println(quote);
			}
			pstat.close();
			conn.close();			
		}
		catch(Exception ex) {
			System.out.println("Error : "+ex.getMessage());
		}
		return quotes;
	}

	private void GotoQuoteStaff (Stage primaryStage) {
		QuoteStaff cusreg = new QuoteStaff();
		try {
			cusreg.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	private void BackLogin (Stage primaryStage) {
		StaffMainPage cuslog = new StaffMainPage();
		try {
			cuslog.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

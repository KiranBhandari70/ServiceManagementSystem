package staffs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import homepage.StaffMainPage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StaffLogin extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		
		Label lblaccess = new Label("Staff Access :");
		Label lblpswrd = new Label("Password :");
		
		lblaccess.setStyle("-fx-font-weight: bold;");
		lblpswrd.setStyle("-fx-font-weight: bold;");
		
		TextField txtaccess = new TextField();
		txtaccess.setPromptText("username");
		
		PasswordField pswrd = new PasswordField();
		pswrd.setPromptText("password");
		
		Button btnaccess = new Button("Login");
		btnaccess.setOnAction((event)->{
			String Login = txtaccess.getText();
			String pass = pswrd.getText();
			boolean result = loginStaff(Login,pass);
			if (result==true) {
				GotoStaffMainPage(primaryStage);
			} else {
				Alert alertfail = new Alert(AlertType.WARNING);
				alertfail.setTitle("Invalid Credentials");
			    alertfail.setContentText("Invalid user name or password.");
			    alertfail.showAndWait();
			}
			
		});
		
		btnaccess.setStyle("-fx-background-color: pink; -fx-text-fill: black");
		
		
		GridPane pane = new GridPane();
		pane.setVgap(7);
		pane.setHgap(7);
		pane.setPadding(new Insets(18,18,18,18));
		
		GridPane.setConstraints(lblaccess,0,0);
		GridPane.setConstraints(txtaccess,1,0);
		GridPane.setConstraints(lblpswrd,0,1);
		GridPane.setConstraints(pswrd,1,1);
		GridPane.setConstraints(btnaccess,1,2);
		
		pane.setStyle("-fx-background-image: url('file:Login.jpg'); " +
	                "-fx-background-size: cover;");

		pane.getChildren().addAll(lblaccess,lblpswrd,
				                  txtaccess,
				                  pswrd,
				                  btnaccess);
	
		pane.setStyle("-fx-background-image: url('file:Login.jpg'); " +
                "-fx-background-size: cover;");
		 
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Staff Login Page");
		primaryStage.setHeight(250);
		primaryStage.setWidth(400);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public boolean loginStaff (String usrname,String password) {
		boolean result = false;
		String DRIVER = "com.mysql.cj.jdbc.Driver"; 
		String HOST = "localhost";
		int PORT = 3306;
		String DATABASE = "ServiceSystem"; 
		String DBUSER = "root";
		String DBPASS = "Kiran@#123";
		String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
		String sql = "SELECT * FROM Staff WHERE UserName=? and PassKey=?;";
		System.out.println(sql);
		
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL,DBUSER,DBPASS);
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1,usrname);
			pstat.setString(2,password);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				result=true;
			}
			pstat.close();
			conn.close();
		}
		catch (Exception ex) {
			System.out.println("Error : "+ex.getMessage());
		}
				return result;
	}
	
	// to divert back to staff mainpage
	private void GotoStaffMainPage (Stage primaryStage) {
		StaffMainPage stfmainpage = new StaffMainPage();
		try {
			stfmainpage.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


}

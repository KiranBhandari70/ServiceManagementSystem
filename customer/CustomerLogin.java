package customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import homepage.CustomerMainPage;
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


public class CustomerLogin extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		
		Label lblaccess = new Label("User Access :");
		Label lblpswrd = new Label("Password :");
		Label lblnewentry = new Label("Don't have an account?? Click Below!!");
		
		lblaccess.setStyle("-fx-font-weight: bold;");
		lblpswrd.setStyle("-fx-font-weight: bold;");
		lblnewentry.setStyle("-fx-font-weight: bold;");

		
		TextField txtaccess = new TextField();
		txtaccess.setPromptText("username");
		
		PasswordField pswrd = new PasswordField();
		pswrd.setPromptText("password");
		
		Button btnaccess = new Button("Login");
		btnaccess.setOnAction((event)->{
			String Login = txtaccess.getText();
			String pass = pswrd.getText();
		   Boolean result = loginCustomer(Login,pass);
			if (result==true) {
				GotoCustomerMainPage(primaryStage);
			} else {
				Alert alertfail = new Alert(AlertType.WARNING);
				alertfail.setTitle("Invalid Credentials");
			    alertfail.setContentText("Invalid user name or password. Please try again with authentic credentials.");
			    alertfail.showAndWait();
			}
			
		});
		
		btnaccess.setStyle("-fx-background-color: pink; -fx-text-fill: black");
		
		Button btnapply = new Button("Create an Account");
		btnapply.setOnAction((event)-> {
			GotoCustomerRegistration(primaryStage);
		});
		
		btnapply.setStyle("-fx-background-color: pink; -fx-text-fill: black");

		GridPane pane = new GridPane();
		pane.setVgap(8);
		pane.setHgap(8);
		pane.setPadding(new Insets(15,15,15,15));
		
		GridPane.setConstraints(lblaccess,0,0);
		GridPane.setConstraints(txtaccess,1,0);
		GridPane.setConstraints(lblpswrd,0,1);
		GridPane.setConstraints(pswrd,1,1);
		GridPane.setConstraints(btnaccess,1,2);
		GridPane.setConstraints(lblnewentry,1,3);
		GridPane.setConstraints(btnapply,1,4);
		
		pane.getChildren().addAll(lblaccess,lblpswrd,lblnewentry,
				                  txtaccess,
				                  pswrd,
				                  btnaccess,btnapply);
		
		pane.setStyle("-fx-background-image: url('file:Login.jpg'); " +
                "-fx-background-size: cover;");
		 
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Customer Login Page");
		primaryStage.setHeight(250);
		primaryStage.setWidth(400);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public Boolean loginCustomer (String usrname,String password) {
		boolean result = false;
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String HOST = "localhost";
		int PORT = 3306;
		String DATABASE = "ServiceSystem";
		String DBUSER = "root";
		String DBPASS = "Kiran@#123";
		String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
		String sql = "SELECT * FROM Customer WHERE UserName=? and PassKey=?;";
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
	
	private void GotoCustomerMainPage (Stage primaryStage) {
		CustomerMainPage cusmainpage = new CustomerMainPage();
		try {
			cusmainpage.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void GotoCustomerRegistration (Stage primaryStage) {
		CustomerRegistration cusreg = new CustomerRegistration();
		try {
			cusreg.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}


package workers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import homepage.StaffMainPage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WorkerRegistration extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		
		Label lblfn = new Label("First Name: ");
		Label lblmn = new Label("Middle Name: ");
		Label lblln = new Label("Surname: ");
		Label lblemail = new Label("Email: ");
		Label lblmobile = new Label("Contact: ");
		Label lbladdress = new Label("Address : ");
		Label lblgender = new Label("Gender: ");
		Label lblduty = new Label("Duty ID: ");
		
		TextField txtfn = new TextField("");
		txtfn.setPromptText("First name ");

		TextField txtmn = new TextField("");
		txtmn.setPromptText("middle name ");

		TextField txtln = new TextField("");
		txtln.setPromptText("surname");

		TextField txtmobile = new TextField("");
		txtmobile.setPromptText("Number");
		
		// warning for entering number upto 10 digits and no alphabet shall be used.
		Label lblnumwarn = new Label();
		lblnumwarn.setTextFill(Color.RED);
		txtmobile.textProperty().addListener((observable,oldvalue,newvalue)->{
			if(!newvalue.matches("\\d{0,10}")) {
				txtmobile.setText(oldvalue);
				lblnumwarn.setText("Please enter number upto 10 digits.");
			} else {
				lblnumwarn.setText("");
			}
		});

		
		TextField txtemail = new TextField("");
		txtemail.setPromptText("eg:abc@gmail.com");
		// warning for having an @ while entering email
		Label lblewarn = new Label();
		lblewarn.setTextFill(Color.RED);
		txtemail.textProperty().addListener((observable,oldvalue,newvalue)->{
			if(!correctemail(newvalue)) {
				lblewarn.setText("Please use correct format.");
			} else {
				lblewarn.setText("");
			}
		});
		
		TextField txtaddress = new TextField("");
		txtaddress.setPromptText("address");
		
	
		RadioButton rboy = new RadioButton("Male");
		RadioButton rgirl = new RadioButton("Female");
		RadioButton rboth = new RadioButton("Prefer Not to Say");
		rboth.setSelected(true); //default selection 
		
		 ToggleGroup gdrgrp = new ToggleGroup(); // for automatic changing of button selection after clicking 
		 rboy.setToggleGroup(gdrgrp);
		 rgirl.setToggleGroup(gdrgrp);
		 rboth.setToggleGroup(gdrgrp);
		 
		 TextField txtduty = new TextField("");
			txtduty.setPromptText("duty");

			// adding a new worker in database
		 Button btnsign = new Button("Enroll");
		 btnsign.setOnAction((event)-> {
			 String FirstName = txtfn.getText();
			 String MiddleName = txtmn.getText();
			 String Surname = txtln.getText();
			 String Email = txtemail.getText();
			 String Contact = txtmobile.getText();
			 String Address = txtaddress.getText();
		
			 String Gender = "Prefer Not to Say";
			 if(rboy.isSelected()==true) {
					Gender="Male";
				}	
			 if(rgirl.isSelected()==true) {
					Gender="Female";
				}	
			 
			 String Duty = txtduty.getText();
			
			 Worker worker=new Worker(FirstName,MiddleName,Surname,Email,Contact,Address,Gender,Duty);	
				boolean outcome = RegisterWorker(worker);
				if(outcome==true) {			
					Alert alertin = new Alert(AlertType.INFORMATION);
					 alertin.setTitle("Registered Information");
					 alertin.setHeaderText(null);
					 alertin.setContentText("The worker is registered successfully.");
					 alertin.show();
					Timeline entryTime = new Timeline(
							new KeyFrame(Duration.seconds(4),e ->alertin.close())
							);
					entryTime.play();
					
					System.out.println("Record Saved");
					}
				else {
					Alert alertout= new Alert(AlertType.WARNING);
					 alertout.setTitle("Registered Information");
					 alertout.setHeaderText(null);
					 alertout.setContentText("Error to register worker.Please fill up details properly.");
					 alertout.show();
					Timeline outgo = new Timeline(
							new KeyFrame(Duration.seconds(4),e ->alertout.close())
							);
					outgo.play();
					System.out.println("Error: to save record");
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
			
			GridPane.setConstraints(lblfn,0,1);
			GridPane.setConstraints(lblmn,2,1);
			GridPane.setConstraints(lblln,4,1);
			GridPane.setConstraints(lblemail,0,2);
			GridPane.setConstraints(lblmobile,0,3);
			GridPane.setConstraints(lbladdress,0,4);
			GridPane.setConstraints(lblgender,0,6);
			GridPane.setConstraints(lblduty,0,7);
			GridPane.setConstraints(txtfn,1,1);
			GridPane.setConstraints(txtmn,3,1);
			GridPane.setConstraints(txtln,5,1);
			GridPane.setConstraints(txtemail,1,2);
			GridPane.setConstraints(lblewarn,2,2);
			GridPane.setConstraints(txtmobile,1,3);
			GridPane.setConstraints(lblnumwarn,2,3);
			GridPane.setConstraints(txtaddress,1,4);
			GridPane.setConstraints(txtduty,1,7);
			GridPane.setConstraints(rboy,1,6);
			GridPane.setConstraints(rgirl,2,6);
			GridPane.setConstraints(rboth,3,6);
			GridPane.setConstraints(btnsign,2,9);
			GridPane.setConstraints(btnback,4,9);
			
			pane.setStyle("-fx-background-image: url('file:Login.jpg'); " +
	                "-fx-background-size: cover;");
				
			pane.getChildren().addAll(lblfn,lblmn,lblln,lblemail,lblmobile,lbladdress,lblgender,lblduty,
	                  txtfn,txtmn,txtln,txtmobile,txtemail,txtaddress,txtduty,
	                  lblnumwarn, lblewarn,
	                  rboy,rgirl,rboth,
	                  btnsign,btnback
	                  );
		
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Worker Registration");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	 private boolean correctemail(String email) {
	        String emailvalid= "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	        return email.matches(emailvalid);
	    }
	
	private void BackLogin (Stage primaryStage) {
		StaffMainPage stfpg = new StaffMainPage();
		try {
			stfpg.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean RegisterWorker (Worker worker) {
		boolean result = false;
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String HOST = "localhost";
		int PORT = 3306;
		String DATABASE = "ServiceSystem";
		String DBUSER = "root";
		String DBPASS = "Kiran@#123";
		String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
		String sql = "INSERT INTO Worker  (FirstName, MiddleName, Surname, Email, Contact,Address, Gender,Duty) VALUES (? ,?, ?, ?, ?, ?, ?, ?) ;";
		System.out.println(sql);
		
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL,DBUSER,DBPASS);
			PreparedStatement pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, worker.getFirstName());
			pstat.setString(2, worker.getMiddleName());
			pstat.setString(3, worker.getSurname());
			pstat.setString(4, worker.getEmail());
			pstat.setString(5, worker.getPhone());
			pstat.setString(6, worker.getAddress());
			pstat.setString(7, worker.getGender());
			pstat.setString(8, worker.getDuty());

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

package customer;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ManageCusByStaff extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
        Label lblcid = new Label("CustomerID: ");
		Label lblfn = new Label("First Name: ");
		Label lblmn = new Label("Middle Name: ");
		Label lblln = new Label("Surname: ");
		Label lblemail = new Label("Email: ");
		Label lblmobile = new Label("Contact: ");
		Label lbladdress = new Label("Address : ");
		Label lblgender = new Label("Gender: ");
		Label lbllogname = new Label("UserName: ");
		Label lblpswrd = new Label("Password: ");
		
		TextField txtcid = new TextField("");
		txtcid.setPromptText("customerId");
		
		TextField txtfn = new TextField("");
		txtfn.setPromptText("First name ");

		TextField txtmn = new TextField("");
		txtmn.setPromptText("middle name ");

		TextField txtln = new TextField("");
		txtln.setPromptText("surname");

		TextField txtmobile = new TextField("");
		txtmobile.setPromptText("Number");
		//warning for digits length
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
		//warning for email structure
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
		rboth.setSelected(true); 
		ToggleGroup gdrgrp = new ToggleGroup();
		 rboy.setToggleGroup(gdrgrp);
		 rgirl.setToggleGroup(gdrgrp);
		 rboth.setToggleGroup(gdrgrp);
		 
			TextField txtlogname = new TextField("");
			txtlogname.setPromptText("username ");
			
			PasswordField pswrd = new PasswordField();
			Label lblpwarn = new Label();
			//warning for passwords
			lblpwarn.setTextFill(Color.RED);
			pswrd.textProperty().addListener((observable,oldvalue,newvalue)->{
				if(!correctpassword(newvalue)) {
					lblpwarn.setText("password must be 8 characters with atleast a digit.");
				} else {
					lblpwarn.setText("");
				}
			});
			
          // Button to save new customers
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
				 
				 String Username = txtlogname.getText();
				 String PassKey = pswrd.getText();
				 
			 	 
		 Customer customer=new Customer(FirstName,MiddleName,Surname,Email,Contact,Address,Gender,Username,PassKey);	
			boolean outcome = RegisterCustomer(customer);
			if(outcome==true) {			
			Alert alertin = new Alert(AlertType.INFORMATION);
	    	alertin.setTitle("Registered Information");
		    alertin.setHeaderText(null);
		    alertin.setContentText("The customer is registered successfully.");
			alertin.show();
			Timeline entryTime = new Timeline(
		     	new KeyFrame(Duration.seconds(4),e ->alertin.close()));
					entryTime.play();
						System.out.println("Record Saved");
				}else {
			Alert alertout= new Alert(AlertType.WARNING);
		    alertout.setTitle("Registered Information");
		    alertout.setHeaderText(null);
		    alertout.setContentText("Error to register customer.Please fill up details properly.");
		    alertout.show();
			Timeline outgo = new Timeline(
				new KeyFrame(Duration.seconds(4),e ->alertout.close()));
						outgo.play();
						System.out.println("Error: to save record");
					}   
				});
			
			 // button to search existing customer
			Button btnSearch=new Button("Search");
			btnSearch.setOnAction((event)->{
				int cid = Integer.parseInt(txtcid.getText());
				Customer customer= searchcustomer(cid);						
				if(customer != null) {
				
					txtfn.setText(customer.getFirstName());
					txtmn.setText(customer.getMiddleName());
					txtln.setText(customer.getSurname());
					txtemail.setText(customer.getEmail());
					txtmobile.setText(customer.getContact());
					txtaddress.setText(customer.getAddress());
				
					if(customer.getGender().equals("Male")) {
						rboy.setSelected(true);
					}
					else if (customer.getGender().equals("Female")) {
						rgirl.setSelected(true);
					}		
					else {
						rboth.setSelected(true);
					}
					
					txtlogname.setText(customer.getUserName());
					pswrd.setText(customer.getPassKey());
					
					System.out.println("Record found");
				}
				else {
					System.out.println("Record not found");
				}
			});
			
			// btn to update customer records
			Button btnUpdate=new Button("Refactor");
			btnUpdate.setOnAction((event)->{
				int cid = Integer.parseInt(txtcid.getText());
				String firstname = txtfn.getText();
				String middlename = txtmn.getText();
				String lastname = txtln.getText();
				String email = txtemail.getText();
				String Contact=txtmobile.getText();
				String address = txtaddress.getText();
				
				String gender ="Prefer Not to Say";
				if(rboy.isSelected()==true) {
					gender="Male";}
				if(rgirl.isSelected()== true) {
					gender="Female";
				}
			
				String loginName = txtlogname.getText();
				String loginPass = pswrd.getText();
				
				Customer customer=new Customer(cid,firstname,middlename,lastname,email,Contact, address, gender, loginName, loginPass);			
				boolean res = Refactorcustomer(customer);
				if(res==true) { 
					System.out.println("Record Saved");
					Alert alertin = new Alert(AlertType.INFORMATION);
					 alertin.setTitle("Refactored Information");
					 alertin.setHeaderText(null);
					 alertin.setContentText("The customer is updated successfully.");
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
					 alertin.setContentText("The customer update is not possible.");
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
				
				GridPane.setConstraints(lblcid,0,0);
				GridPane.setConstraints(lblfn,0,1);
				GridPane.setConstraints(lblmn,2,1);
				GridPane.setConstraints(lblln,4,1);
				GridPane.setConstraints(lblemail,0,2);
				GridPane.setConstraints(lblmobile,0,3);
				GridPane.setConstraints(lbladdress,0,4);
				GridPane.setConstraints(lblgender,0,6);
				GridPane.setConstraints(lbllogname,0,7);
				GridPane.setConstraints(lblpswrd,0,8);
				GridPane.setConstraints(txtcid,1,0);
				GridPane.setConstraints(txtfn,1,1);
				GridPane.setConstraints(txtmn,3,1);
				GridPane.setConstraints(txtln,5,1);
				GridPane.setConstraints(txtemail,1,2);
				GridPane.setConstraints(txtmobile,1,3);	
				GridPane.setConstraints(txtaddress,1,4);
				GridPane.setConstraints(txtlogname,1,7);
				GridPane.setConstraints(pswrd,1,8);
				GridPane.setConstraints(rboy,1,6);
				GridPane.setConstraints(rgirl,2,6);
				GridPane.setConstraints(rboth,3,6);
				GridPane.setConstraints(btnsign,2,9);
				GridPane.setConstraints(lblpwarn,1,9);
				GridPane.setConstraints(lblnumwarn,2,3);
				GridPane.setConstraints(lblewarn,2,2);
				GridPane.setConstraints(btnSearch,3,9);
				GridPane.setConstraints(btnUpdate,4,9);
				GridPane.setConstraints(btnback,5,9);
				
				pane.setStyle("-fx-background-image: url('file:Login.jpg'); " +
		                "-fx-background-size: cover;");
				
				pane.getChildren().addAll(lblcid,lblfn,lblmn,lblln,lblemail,lblmobile,lbladdress,lblgender,lbllogname,lblpswrd,
		                 txtcid, txtfn,txtmn,txtln,txtmobile,txtemail,txtaddress,txtlogname,
		                  rboy,rgirl,rboth,
		                  btnSearch,btnUpdate,btnsign,
		                  lblnumwarn, lblewarn,lblpwarn,btnback,
		                  pswrd);
				
				Scene scene = new Scene(pane);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Manage Customer Details");
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

	 private boolean correctemail(String email) {
	        String emailvalid= "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	        return email.matches(emailvalid);
	    }
	 //protocol for password
	 private boolean correctpassword(String password) {
	        return password.length() >= 8 && password.matches(".*\\d.*");
	  }
	
	// searching customer
	public Customer searchcustomer (int cid) {
		Customer customer = null;
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String HOST = "localhost";
		int PORT = 3306;
		String DATABASE = "ServiceSystem";
		String DBUSER = "root";
		String DBPASS = "Kiran@#123";
		String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
		String sql = "select * from customer where CustomerID=? ;";
		System.out.println(sql);
		
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL,DBUSER,DBPASS);
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setInt(1,cid);
			ResultSet rs = pstat.executeQuery();
			while(rs.next()) {	
			String FirstName=rs.getString("FirstName");
			String MiddleName=rs.getString("MiddleName");
			String Surname=rs.getString("Surname");
			String Email=rs.getString("Email");
			String Contact=rs.getString("Contact");
			String Address=rs.getString("Address");
			String Gender=rs.getString("Gender");
			String UserName=rs.getString("UserName");
			String PassKey=rs.getString("PassKey");
		    customer=new Customer(FirstName,MiddleName,Surname,Email,Contact, Address, Gender,UserName,PassKey);		
			
			}
			pstat.close();
			conn.close();

		}
		catch (Exception ex) {
			System.out.println("Error : "+ex.getMessage());
		}
				return customer;
	}
	
	//updating a customer
	public boolean Refactorcustomer (Customer customer) {
		boolean result = false;
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String HOST = "localhost";
		int PORT = 3306;
		String DATABASE = "ServiceSystem";
		String DBUSER = "root";
		String DBPASS = "Kiran@#123";
		String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
		String sql = "Update Customer SET FirstName=?, MiddleName=?, Surname=?, Email=?, Contact=?,Address=?, Gender=? , UserName=?, PassKey=? Where CustomerID=? ;";
		System.out.println(sql);
		
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL,DBUSER,DBPASS);
			PreparedStatement pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, customer.getFirstName());
			pstat.setString(2, customer.getMiddleName());
			pstat.setString(3, customer.getSurname());
			pstat.setString(4, customer.getEmail());
			pstat.setString(5, customer.getContact());
			pstat.setString(6, customer.getAddress());
			pstat.setString(7, customer.getGender());
			pstat.setString(8, customer.getUserName());
			pstat.setString(9, customer.getPassKey());
			pstat.setInt(10, customer.getCustomerID());

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
	
	//Inserting a new customer
	public boolean RegisterCustomer (Customer customer) {
		boolean result = false;
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String HOST = "localhost";
		int PORT = 3306;
		String DATABASE = "ServiceSystem";
		String DBUSER = "root";
		String DBPASS = "Kiran@#123";
		String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
		String sql = "INSERT INTO Customer  (FirstName, MiddleName, Surname, Email, Contact,Address, Gender, UserName, PassKey) VALUES (? ,?, ?, ?, ?, ?, ?, ?, ?) ;";
		System.out.println(sql);
		
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL,DBUSER,DBPASS);
			PreparedStatement pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, customer.getFirstName());
			pstat.setString(2, customer.getMiddleName());
			pstat.setString(3, customer.getSurname());
			pstat.setString(4, customer.getEmail());
			pstat.setString(5, customer.getContact());
			pstat.setString(6, customer.getAddress());
			pstat.setString(7, customer.getGender());
			pstat.setString(8, customer.getUserName());
			pstat.setString(9, customer.getPassKey());
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

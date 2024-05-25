package homepage;

import appointment.ManageAppointment;
import appointment.ViewAppointment;
import customer.CustomerRegistration;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import quote.QuoteCustomer;
import services.ListAllServices;


public class CustomerMainPage extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
	
		TabPane tabpane = new TabPane();
       
		Tab Abouttab = new Tab("Home");
		Abouttab.setClosable(false);
		Label lblmsg = new Label("A warm welcome to our website.\n" 
				                 +"\n We are providing different services related to household since a year.\n"
			                     + "\n Our services are used for both commercial and non-commercial purpose.\n"
				                 + "\n Booking a service can never be easy to you before. Just a Click away.\n"
			                     + "\n Book a service now!!! And make your life easy!!!");
		lblmsg.setPadding(new Insets(20));
		Abouttab.setContent(lblmsg);
		
		Tab contacttab = new Tab("Contact Us");
		contacttab.setClosable(false);
		Label lblcontact = new Label("For Contacting Us: \n "
                + "\n Email : ServiceProviders@gmail.com \n"
                + "\n Phone: +977 9345611234\n"
                + "\n You can contact us via email and phone or through our website.\n"
                );
       lblcontact.setPadding(new Insets(20));
       contacttab.setContent(lblcontact);
       
		Tab servicetab = new Tab("Main Page");
		servicetab.setClosable(false);
		
		 VBox vbox = new VBox(15);
		  vbox.setPadding(new Insets(20));
	        Button button1 = new Button("Manage Self");
	        button1.setOnAction((event)-> {
	        	GotoRegistration(primaryStage);
	            
	        });
	        Button button2 = new Button("List All Services");
	        button2.setOnAction((event)-> { 
	        	GotolistServices(primaryStage);
	        });
	        
	        Button button3 = new Button("QuoteCustomer");
	        button3.setOnAction((event)-> { 
	        	Gotoquote(primaryStage);
	        });
	        
	        Button button4 = new Button("View Appointment");
	        button4.setOnAction((event)-> { 
	        	GotoManageAppointment(primaryStage);
	        });
	        
	        Button button5 = new Button("Appointment History");
	        button5.setOnAction((event)-> { 
	        	GotoHistory(primaryStage);
	        });
	        
	        vbox.getChildren().addAll(button1, button2,button3,button4,button5);
	        servicetab.setContent(vbox);
		
		
	        tabpane.setStyle("-fx-background-image: url('file:Login.jpg'); " +
	                "-fx-background-size: cover;");
		tabpane.getTabs().addAll(Abouttab,servicetab,contacttab);
		
		Scene scene = new Scene(tabpane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Customer Home Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(800);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	

	private void GotoRegistration (Stage primaryStage) {
		CustomerRegistration cusmainpage = new CustomerRegistration();
		try {
			cusmainpage.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void GotolistServices (Stage primaryStage) {
	  ListAllServices cusmainpage = new ListAllServices();
		try {
			cusmainpage.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void Gotoquote (Stage primaryStage) {
		QuoteCustomer cusmainpage = new QuoteCustomer ();
		try {
			cusmainpage.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void GotoManageAppointment (Stage primaryStage) {
		ManageAppointment cusmainpage = new ManageAppointment ();
		try {
			cusmainpage.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void GotoHistory(Stage primaryStage) {
	 ViewAppointment cusmainpage = new ViewAppointment();
		try {
			cusmainpage.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

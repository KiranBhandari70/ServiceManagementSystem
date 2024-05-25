package homepage;

import appointment.ListAppointment;

import appointment.StaffAppointment;
import customer.ManageCusByStaff;
import services.ListAllServices2;
import services.ServiceCRU;
import workers.WorkerRegistration;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import quote.ListQuote;
import quote.ListQuote2All;
import quote.QuoteStaff;

public class StaffMainPage extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		TabPane tabpane = new TabPane();

		Tab Abouttab = new Tab("Home");
		Abouttab.setClosable(false);
		Label lblmsg = new Label("\n A warm welcome to our website.\n"
				                 + "\n We are providing different services related to household since a year.\n"
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
       
       Tab listservice = new Tab("Main Page");
		listservice.setClosable(false);
		
		  VBox vbox = new VBox(15);
		  vbox.setPadding(new Insets(20));
	 
	        Button button2 = new Button("Manage Customer");
	        button2.setOnAction((event)-> { 
	        	GotoManageCustomer(primaryStage);
	        });
	        
	        Button button3 = new Button("List All Services");
	        button3.setOnAction((event)-> { 
	        	Gotolistservice(primaryStage);
	        });
	        
	        Button button4 = new Button("Manage Services");
	        button4.setOnAction((event)-> { 
	        	GotoManageService(primaryStage);
	        });
	        
	        Button button5 = new Button("List All Quotes");
	        button5.setOnAction((event)-> { 
	        	Gotolistquotes(primaryStage);
	        });
	        
	        Button button6 = new Button("Quote Management");
	        button6.setOnAction((event)-> { 
	        	GotoquoteManagement(primaryStage);
	        });
	        
	        Button button7 = new Button(" Schedule Appointment");
	        button7.setOnAction((event)-> { 
	        	GotoStaffAppointment(primaryStage);
	        });
	        
	        Button button8 = new Button("Appointment Data");
	        button8.setOnAction((event)->{
	        	GotoListAppointment(primaryStage);
	        });
	        
	        Button button9 = new Button(" Check All Quote");
	        button9.setOnAction((event)->{
	        	Gotoallquote(primaryStage);
	        });
	        
	        Button button10 = new Button(" Register Worker");
	        button10.setOnAction((event)->{
	        	GotoWorkerRegistration(primaryStage);
	        });
	        
	        vbox.getChildren().addAll(button2,button3,button4,button5,button6,button7,button8, button9,button10);
	        listservice.setContent(vbox);
	        
	        tabpane.setStyle("-fx-background-image: url('file:Login.jpg'); " +
	                "-fx-background-size: cover;");
		tabpane.getTabs().addAll(Abouttab,listservice,contacttab);
		
		Scene scene = new Scene(tabpane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Staff Home Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(800);
		primaryStage.setResizable(true);
		primaryStage.show();
		
	}
	
	private void GotoManageCustomer (Stage primaryStage) {
		ManageCusByStaff cusmainpage = new ManageCusByStaff();
		try {
			cusmainpage.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void Gotolistservice (Stage primaryStage) {
		ListAllServices2 cusmainpage = new ListAllServices2();
		try {
			cusmainpage.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void GotoManageService (Stage primaryStage) {
		ServiceCRU cusmainpage = new ServiceCRU();
		try {
			cusmainpage.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void Gotolistquotes(Stage primaryStage) {
		ListQuote cusmainpage = new ListQuote();
		try {
			cusmainpage.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void GotoquoteManagement (Stage primaryStage) {
		QuoteStaff cusmainpage = new QuoteStaff();
		try {
			cusmainpage.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void GotoStaffAppointment (Stage primaryStage) {
		StaffAppointment cusmainpage = new StaffAppointment();
		try {
			cusmainpage.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void GotoListAppointment (Stage primaryStage) {
		ListAppointment cusmainpage = new ListAppointment();
		try {
			cusmainpage.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void Gotoallquote (Stage primaryStage) {
		ListQuote2All cusmainpage = new ListQuote2All();
		try {
			cusmainpage.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void GotoWorkerRegistration (Stage primaryStage) {
		WorkerRegistration cusmainpage = new WorkerRegistration();
		try {
			cusmainpage.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
		
}
package quote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import homepage.CustomerMainPage;
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

public class QuoteCustomer extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label lblid = new Label("Service ID");
        Label lblcid = new Label("Customer ID");
        Label lbld = new Label("Description");
        Label lblc = new Label("Budget");

        TextField txtid = new TextField();
        txtid.setPromptText("Service Id*");

        TextField txtcid = new TextField();
        txtcid.setPromptText("Customer id*");

        TextField txtd = new TextField();
        txtd.setPromptText("Description* ");

        TextField txtc = new TextField();
        txtc.setPromptText("Budget*");

        Button btnquote = new Button("Submit");
        btnquote.setOnAction((event) -> {
            int serviceId = Integer.parseInt(txtid.getText());
            int customerId = Integer.parseInt(txtcid.getText());
            String description = txtd.getText();
            String budget = txtc.getText();

            Quote quote = new Quote(serviceId, customerId, description, budget);
            boolean outcome = requestQuote(quote, serviceId, customerId);
            if (outcome) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Quote");
                alert.setContentText("Your quote has been submitted. Please check appointments after some time for confirmation of your work.");
                alert.show();
                System.out.println("Quote Sent");
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Quote");
                alert.setContentText("Error sending quote. Please try again later.");
                alert.show();
                System.out.println("Error sending quote.");
            }
        });
        
        Button btnback = new Button("Back");
		btnback.setOnAction((event)->{
			GotoCustomermain(primaryStage);
		});

        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(20, 20, 20, 20));

        GridPane.setConstraints(lblid, 0, 0);
        GridPane.setConstraints(txtid, 1, 0);
        GridPane.setConstraints(lblcid, 0, 1);
        GridPane.setConstraints(txtcid, 1, 1);
        GridPane.setConstraints(lbld, 0, 2);
        GridPane.setConstraints(txtd, 1, 2);
        GridPane.setConstraints(lblc, 0, 3);
        GridPane.setConstraints(txtc, 1, 3);
        GridPane.setConstraints(btnquote, 1, 4);
        GridPane.setConstraints(btnback, 2, 4);
        
        pane.setStyle("-fx-background-image: url('file:Login.jpg'); " +
                "-fx-background-size: cover;");

        pane.getChildren().addAll(lblid, lblcid, lbld, lblc, txtid,
                txtcid, txtd, txtc, btnquote,btnback);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quote Request Page");
        primaryStage.setHeight(400);
        primaryStage.setWidth(400);
        primaryStage.setResizable(true);
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

    public boolean requestQuote(Quote quote, int serviceId, int customerId) {
        boolean result = false;
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String HOST = "localhost";
        int PORT = 3306;
        String DATABASE = "ServiceSystem";
        String DBUSER = "root";
        String DBPASS = "Kiran@#123";
        String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
        String sql = "INSERT INTO Quote (ServiceID,CustomerID, Details, Budget) VALUES (?, ?, ?, ?)";
        System.out.println(sql);

        try {
        	 Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
        	 PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setInt(1, quote.getServiceID());
            pstat.setInt(2, quote.getCustomerID());
            pstat.setString(3, quote.getDescription());
            pstat.setString(4, quote.getBudget());

            pstat.executeUpdate();
            result = true;

        } catch (SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
        return result;
    }
}

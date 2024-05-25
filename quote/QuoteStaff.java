package quote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import quote.Quote.Status;

public class QuoteStaff extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
    	
    	Label lblqid = new Label("Quote ID");
    	Label lblcid = new Label("Customer ID");	
    	Label lbldesc = new Label("Description");	
    	Label lblcost = new Label("Budget");	
    	Label lblsid = new Label("Service ID");	
    	Label lblstats= new Label("Status");
        
        TextField txtqid = new TextField();
        TextField txtsid = new TextField();
        TextField txtcid = new TextField();
        TextField txtdesc = new TextField();
        TextField txtcost = new TextField();


        ComboBox<String> statusComboBox = new ComboBox<>();
        List<String> status = new ArrayList<>();
        status.add("Pending");
        status.add("Accepted");
        status.add("Declined");
        statusComboBox.getItems().addAll(status);

        Button btnSearch = new Button("Search");
        btnSearch.setOnAction((event) -> {
            try {
                int qid = Integer.parseInt(txtqid.getText());
                Quote quote = Find(qid);
                if (quote != null) {

                	txtsid.setText(Integer.toString(quote.getServiceID()));
                    txtcid.setText(Integer.toString(quote.getCustomerID()));

                    txtdesc.setText(quote.getDescription());
                    txtcost.setText(quote.getBudget());

                    Status state = quote.getStatus();

                    switch (state) {
                        case Pending:
                            statusComboBox.getSelectionModel().select(0);
                            break;
                        case Accepted:
                            statusComboBox.getSelectionModel().select(1);
                            break;
                        case Declined:
                            statusComboBox.getSelectionModel().select(2);
                            break;
                    }

                    System.out.println("Record found");
                } else {
                	  Alert alert = new Alert(AlertType.WARNING);
                      alert.setTitle("Quote");
                      alert.setContentText("Error searching quote. Please try again later.");
                      alert.show();
                    System.out.println("Record not found");
                }
            } catch (NumberFormatException e) {
            }
        });

        Button btnUpdate = new Button("Update");
        btnUpdate.setOnAction((event) -> {
            try {
                int qid = Integer.parseInt(txtqid.getText());
                int sid = Integer.parseInt(txtsid.getText());
                int cid = Integer.parseInt(txtcid.getText());

                String desc = txtdesc.getText();
                String cost = txtcost.getText();
                String statusOption = statusComboBox.getValue();

                Quote quote = new Quote(qid, sid, cid, desc, cost, statusOption);
                boolean res = refactor(quote);
                if (res) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Quote");
                    alert.setContentText("Your quote has been updated.");
                    alert.show();
                    System.out.println("Record updated");
                } else {
                	  Alert alert = new Alert(AlertType.WARNING);
                      alert.setTitle("Quote");
                      alert.setContentText("Error updating quote. Please try again later.");
                      alert.show();
                    System.out.println("Error: to update record");
                }
            } catch (NumberFormatException e) {
            }
        });
        

		 Button btnback = new Button("Back");
		 btnback.setOnAction((event)->{
			 BackLogin (primaryStage);
		 });
		

        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(20, 20, 20, 20));

        GridPane.setConstraints(lblqid, 0, 1);
        GridPane.setConstraints(txtqid, 1, 1);
        GridPane.setConstraints(lblsid, 0, 2);
        GridPane.setConstraints(txtsid, 1, 2);
        GridPane.setConstraints(lblcid, 0, 3);
        GridPane.setConstraints(txtcid, 1, 3);
        GridPane.setConstraints(lbldesc, 0, 4);
        GridPane.setConstraints(txtdesc, 1, 4);
        GridPane.setConstraints(lblcost, 0, 5);
        GridPane.setConstraints(txtcost, 1, 5);
        GridPane.setConstraints(lblstats, 0, 6);
        GridPane.setConstraints(statusComboBox, 1, 6);
        GridPane.setConstraints(btnSearch, 1, 7);
        GridPane.setConstraints(btnUpdate, 2, 7);
        GridPane.setConstraints(btnback, 3, 7);

        pane.setStyle("-fx-background-image: url('file:Login.jpg'); " +
                "-fx-background-size: cover;");
        
        pane.getChildren().addAll(lblqid, lblsid, lblcid, lbldesc, lblcost, lblstats,
                                  txtqid, txtsid, txtcid, txtdesc, txtcost, statusComboBox,
                                  btnSearch, btnUpdate,btnback);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quote Request Page");
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
        primaryStage.setResizable(true);
        primaryStage.show();

    }
    
	private void BackLogin (Stage primaryStage) {
		ListQuote cusreg = new ListQuote();
		try {
			cusreg.start(primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	

    public boolean refactor(Quote quote) {
        boolean result = false;
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String HOST = "localhost";
        int PORT = 3306;
        String DATABASE = "ServiceSystem";
        String DBUSER = "root";
        String DBPASS = "Kiran@#123";
        String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
        String sql = "UPDATE Quote  SET ServiceID=?,CustomerID=?, Details=?, Budget=?,State=? WHERE QuoteID=?";
        try {
            Class.forName(DRIVER); 
            try (Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
                 PreparedStatement pstat = conn.prepareStatement(sql)) {
                pstat.setInt(1, quote.getServiceID());
                pstat.setInt(2, quote.getCustomerID());
                pstat.setString(3, quote.getDescription());
                pstat.setString(4, quote.getBudget());
                pstat.setString(5, quote.getStatus().toString());
                pstat.setInt(6, quote.getQuoteID());
                pstat.executeUpdate();
            }
            result = true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
        return result;
    }

    public Quote Find (int qid) {
        Quote quote = null;
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String HOST = "localhost";
        int PORT = 3306;
        String DATABASE = "ServiceSystem";
        String DBUSER = "root";
        String DBPASS = "Kiran@#123";
        String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
        String sql = "SELECT * FROM Quote WHERE QuoteID=?";
        try {
            Class.forName(DRIVER);
            try (Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
                 PreparedStatement pstat = conn.prepareStatement(sql)) {
                pstat.setInt(1, qid);
                try (ResultSet rs = pstat.executeQuery()) {
                    if (rs.next()) {
                        int sid = rs.getInt("ServiceID");
                        int cid = rs.getInt("CustomerID");
                        String descript = rs.getString("Details");
                        String cost = rs.getString("Budget");
                        String state = rs.getString("State");
                        quote = new Quote(qid, sid, cid, descript, cost, state);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
        return quote;

    }
}

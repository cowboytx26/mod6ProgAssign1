/*
Short Description:  This program will display a form to the user who can insert, update, select data from the Staff
                    table.  There is also a button to clear the form.
Author:  Brian Wiatrek
Date:  September 21, 2024
*/
import java.sql.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class StaffApp extends Application {

    TextField idTF = new TextField();
    TextField firstNameTF = new TextField();
    TextField lastNameTF = new TextField();
    TextField miTF = new TextField();
    TextField addressTF = new TextField();
    TextField cityTF = new TextField();
    TextField stateTF = new TextField();
    TextField telephoneTF = new TextField();

    public void start(Stage primaryStage) {

        //Create a set of labels for each field in the form
        Label idLabel = new Label("ID:");
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label miLabel = new Label("MI:");
        Label addressLabel = new Label("Address:");
        Label cityLabel = new Label("City:");
        Label stateLabel = new Label("State:");
        Label telephoneLabel = new Label("Telephone:");

        //create a set of buttons for the insert, update, view, and clear functions
        Button insertBtn = new Button("Insert");
        insertBtn.setOnAction(new InsertHandler());
        Button updateBtn = new Button("Update");
        updateBtn.setOnAction(new UpdateHandler());
        Button viewBtn = new Button("View");
        viewBtn.setOnAction(new ViewHandler());
        Button clearBtn = new Button("Clear");
        clearBtn.setOnAction(new ClearHandler());

        //put all the objects on a flow pan
        FlowPane fieldsPane = new FlowPane();
        fieldsPane.setPadding(new Insets(11,12,13,14));
        fieldsPane.getChildren().addAll(idLabel, idTF, firstNameLabel, firstNameTF, lastNameLabel, lastNameTF,
                miLabel, miTF, addressLabel, addressTF, cityLabel, cityTF, stateLabel, stateTF, telephoneLabel,
                telephoneTF, insertBtn, updateBtn, viewBtn, clearBtn);

        Scene scene = new Scene(fieldsPane, 200, 450);

        primaryStage.setTitle("Staff App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class InsertHandler implements EventHandler<ActionEvent> {
        //the insert handler is responsible for inserting data into the Staff table
        @Override
        public void handle(ActionEvent e) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://X/module6progassign", "X", "X");
                String sql = "insert into Staff (id, lastName, firstName, mi, address, city, state, telephone) values ('"
                        + idTF.getText() + "','" + lastNameTF.getText() + "','" + firstNameTF.getText() + "','"
                        + miTF.getText() + "','" + addressTF.getText() + "','" + cityTF.getText() + "','"
                        + stateTF.getText() + "','" + telephoneTF.getText() + "')";
                Statement stmt = connection.createStatement();
                System.out.printf("%s\n", sql);
                int result = stmt.executeUpdate(sql);
                System.out.printf("%d\n", result);
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class UpdateHandler implements EventHandler<ActionEvent> {
        //the update handler is responsible for updating data in the Staff table
        @Override
        public void handle(ActionEvent e) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://X/module6progassign", "X", "X");
                String sql = "update Staff set lastName = '" + lastNameTF.getText() + "', firstName = '" + firstNameTF.getText()
                        + "', mi = '" + miTF.getText() + "', address = '" + addressTF.getText() + "', city = '" + cityTF.getText()
                        + "', state = '" + stateTF.getText() + "', telephone = '" + telephoneTF.getText()
                        + "' where id = '" + idTF.getText() + "'";
                Statement stmt = connection.createStatement();
                System.out.printf("%s\n", sql);
                int result = stmt.executeUpdate(sql);
                System.out.printf("%d\n", result);
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class ViewHandler implements EventHandler<ActionEvent> {
        //the view handler is responsible for selecting data from the Staff table
        @Override
        public void handle(ActionEvent e) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://x/module6progassign", "x", "x");
                String sql = "select id, lastName, firstName, mi, address, city, state, telephone from staff where id = '" + idTF.getText() + "'";
                Statement stmt = connection.createStatement();
                System.out.printf("%s\n", sql);
                ResultSet result = stmt.executeQuery(sql);
                while (result.next()){
                    idTF.setText(result.getString("id"));
                    lastNameTF.setText(result.getString("lastName"));
                    firstNameTF.setText(result.getString("firstName"));
                    miTF.setText(result.getString("mi"));
                    addressTF.setText(result.getString("address"));
                    cityTF.setText(result.getString("city"));
                    stateTF.setText(result.getString("state"));
                    telephoneTF.setText(result.getString("telephone"));
                }
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class ClearHandler implements EventHandler<ActionEvent> {
        //the clear handler clears out the data in the text fields
        @Override
        public void handle(ActionEvent e) {
            idTF.setText("");
            lastNameTF.setText("");
            firstNameTF.setText("");
            miTF.setText("");
            addressTF.setText("");
            cityTF.setText("");
            stateTF.setText("");
            telephoneTF.setText("");
        }
    }

}
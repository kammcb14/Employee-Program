package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class NewEmployeeController  {

    public static TextField nameText;
    public static TextField positionText;
    public static TextField addressText;
    public static TextField phoneText;
    public static TextField cityText;
    public static TextField stateText;
    public static TextField zipCodeText;
    public static TextField emailText;
    public static TextField salaryText;
    public static Person newEmployee;
    public static Stage stage;
    public static Button submitButton;
    public static Button imageButton;

//    public void start(Stage stage) throws Exception{
//        this.stage =stage;
//        Parent root = FXMLLoader.load(getClass().getResource("NewEmployee.fxml"));
//        stage.setTitle("Employee Program");
//        stage.setScene(new Scene(root, 800, 800));
//        stage.show();
//    }
    public static Person display() {

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        Label title = new Label("New Employee");
        title.setAlignment(Pos.CENTER);
        title.setPrefWidth(800);
        title.setFont(new Font("Al Tarikh Regular", 22));

        //name and position
        HBox nameHBox = new HBox();
        nameHBox.setSpacing(10);
        nameHBox.setPadding(new Insets(10, 10, 10, 10));

        Label nameLabel = new Label("Employee Name: ");
        nameText = new TextField();
        nameText.setPromptText("John Smith");
        Label positionLabel = new Label("Position: ");
        positionText = new TextField();
        positionText.setPromptText("Data Entry");

        nameHBox.getChildren().addAll(nameLabel, nameText, positionLabel, positionText);

        //address
        HBox addressHBox = new HBox();
        addressHBox.setSpacing(10);
        addressHBox.setPadding(new Insets(10, 10, 10, 10));

        Label addressLabel = new Label("Address: ");
        addressText = new TextField();
        addressText.setPrefWidth(119);
        Label cityLabel = new Label("City: ");
        cityText = new TextField();
        cityText.setPrefWidth(91);
        Label stateLabel = new Label("State: ");
        stateText = new TextField();
        stateText.setPrefWidth(36);
        Label zipCodeLabel = new Label("Zip Code: ");
        zipCodeText = new TextField();
        zipCodeText.setPrefWidth(79);

        addressHBox.getChildren().addAll(addressLabel, addressText, cityLabel, cityText, stateLabel, stateText, zipCodeLabel, zipCodeText);

        //email and number
        HBox emailHBox = new HBox();
        emailHBox.setSpacing(10);
        emailHBox.setPadding(new Insets(10, 10, 10, 10));

        Label emailLabel = new Label("Email: ");
        emailText = new TextField();
        Label phoneLabel = new Label("Phone Number: ");
        phoneText = new TextField();

        emailHBox.getChildren().addAll(emailLabel, emailText, phoneLabel, phoneText);

        //salary
        HBox salaryHBox = new HBox();
        salaryHBox.setSpacing(10);
        salaryHBox.setPadding(new Insets(10, 10, 10, 10));

        Label salaryLabel = new Label("Salary: ");
        salaryText = new TextField();

        salaryHBox.getChildren().addAll(salaryLabel, salaryText);

        //Submit and Cancel buttons
        submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            newEmployee = new Person();
            newEmployee.setName(nameText.getText());
            newEmployee.setPosition(positionText.getText());
            newEmployee.setSalary(salaryText.getText());

            ContactInfo contact = new ContactInfo(phoneText.getText(),
                    emailText.getText(), addressText.getText(), cityText.getText(), stateText.getText(), zipCodeText.getText());
            newEmployee.setMyContact(contact);
            //System.out.println(newEmployee.toString());
            stage.close();
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            stage.close();
        });

        imageButton = new Button("Add Picture");
        imageButton.setOnAction(e -> {
            FileInputStream input = addImage();
            Image image = new Image(input);
            try {
                newEmployee.setPicture(image);
            }catch(Exception exc){
                System.out.println(exc);
            }
        });
//            try {
//                Image img = new Image(new File("/Users/cleo/Downloads/13495229_1787048281527514_794494658784223592_n.jpg").toURI().toURL().toExternalForm());
//                newEmployee.setPicture(img);
//                System.out.println(tmp.getAbsolutePath());
//            }
//            catch(Exception exc){
//                System.out.println(exc);
//            }

        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.getChildren().addAll(submitButton, cancelButton);

        //add all to vBox
        vbox.getChildren().addAll(title, nameHBox, emailHBox, addressHBox, salaryHBox, imageButton, buttons);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("New Employee");
        stage.setScene(new Scene(vbox,800,800));
        stage.showAndWait();

        return newEmployee;
    }

    private static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }
    public static FileInputStream addImage() {

        FileChooser fc = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File tmp = fc.showOpenDialog(stage);
        try {
            FileInputStream fileInputStream = new FileInputStream(tmp.getAbsolutePath());
            return fileInputStream;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
        //return tmp;
//        File dest = new File(tmp.getAbsolutePath());
//        File copy = new File(dest, tmp.getName());
//        try {
//            //copy.createNewFile();
//            copyFileUsingFileStreams(tmp, copy);
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
    }

//    public static void submitClicked(){
//        newEmployee = new Person();
//        newEmployee.setName(nameText.getText());
//        newEmployee.setPosition(positionText.getText());
//        newEmployee.setSalary(salaryText.getText());
//
//        ContactInfo contact = new ContactInfo(phoneText.getText(),
//                emailText.getText(), addressText.getText(), cityText.getText(), stateText.getText(), zipCodeText.getText());
//        newEmployee.setMyContact(contact);
//        System.out.println(newEmployee.toString());
//        Main.addEmployee(newEmployee);
//        stage.close();
//
//        }


    }


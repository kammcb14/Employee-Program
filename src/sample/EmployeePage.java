package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.ArrayList;

import static com.sun.glass.ui.MenuItem.Separator;

public class EmployeePage {
    public static void display(Person emp){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Employee Page");


        //Outer VBox
        VBox outerVBox = new VBox();

        //HBox with image
        HBox imageHBox = new HBox();
        imageHBox.setAlignment(Pos.CENTER);
        try {
            ImageView picture = new ImageView();
            picture.setImage(emp.getPicture());
            picture.setFitHeight(150);
            picture.setPreserveRatio(true);
//            Rectangle2D viewportRect = new Rectangle2D(0, 0, 200, 150);
//            picture.setViewport(viewportRect);
            imageHBox.getChildren().addAll(picture);
        }
        catch(Exception e) {
            System.out.println(e);
        }


        //Name hbox
        HBox nameHBox = new HBox();
        Label nameLabel = new Label(emp.getName());
        nameLabel.setFont(new Font(18));
        //nameLabel.setAlignment(Pos.CENTER);
        nameHBox.setAlignment(Pos.CENTER);
        nameHBox.getChildren().addAll(nameLabel);


        Label contactLabel = new Label("Contact Information: ");

        //Employee Information Box
        VBox infoVBox = new VBox();
        infoVBox.setAlignment(Pos.CENTER);
        Label phoneEmailLabel = new Label("Phone Number: " + emp.getMyContact().getNumber() + "  Email: " + emp.getMyContact().getEmail());
        phoneEmailLabel.setFont(new Font(14));
        Label addressLabel = new Label("Address: " + emp.getMyContact().getAddress());
        addressLabel.setFont(new Font(14));
        Label salaryAndPositionLabel = new Label("Salary: " + emp.getSalary() + "  Position: " + emp.getPosition());
        salaryAndPositionLabel.setFont(new Font(14));
        infoVBox.getChildren().addAll(phoneEmailLabel,addressLabel,salaryAndPositionLabel);
        infoVBox.setSpacing(10);
        infoVBox.setPadding(new Insets(10,10,10,10));

        //Notes VBox - displays previous notes if they have been made, then allows for the user to input new notes that
        //will be displayed when submit button is clicked.
        VBox previousNotesVBox = new VBox();
        previousNotesVBox.setPadding(new Insets(10,10,10,10));
        previousNotesVBox.setSpacing(10);
        ArrayList<String> notes = emp.getNotes();
        if(notes.size()>0){
            for(String i: notes){
                previousNotesVBox.getChildren().add(new Text(i));
            }
        }
        HBox newNotesHBox = new HBox();
        Label notesLabel = new Label("Notes: ");
        TextArea newNote = new TextArea();
        newNotesHBox.getChildren().addAll(notesLabel,newNote);
        Button submitButton = new Button("Add New Note");
        submitButton.setOnAction(e-> {
            emp.addNote(newNote.getText());
            Text toAdd = new Text(newNote.getText());
            previousNotesVBox.getChildren().add(toAdd);
            newNote.clear();
        });

        //Exit button
        Button exit = new Button("Home Page");
        exit.setOnAction(e-> stage.close());

        outerVBox.getChildren().addAll(imageHBox,nameHBox,infoVBox, previousNotesVBox, newNotesHBox,submitButton, exit);
        outerVBox.setSpacing(10);


        stage.setScene(new Scene(outerVBox,800,800));
        stage.showAndWait();

    }
}

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Main extends Application {

    public ArrayList<Person> employees;
    public ScrollPane layout;
    public Scene scene;
    public Stage stage;
    public VBox vBox;


    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        employees = new ArrayList<Person>();
        //Add an example person
        Person example = new Person("John Smith", new ContactInfo("123-456-7890", "email@email.com", "1234 Smith St", "Pomona", "CA", "91768"),
                "Data Entry", "$100,000");
        FileInputStream input = new FileInputStream("resources/linkedin profile.png");
        Image image = new Image(input);
        example.setPicture(image);
        employees.add(example);

        stage.setTitle("Employee Program");
        scene = new Scene(setupLayout(), 1400,800);
        scene.setFill(Color.BLUE);
        stage.setScene(scene);
        stage.show();

    }

    public VBox setupLayout(){
        ScrollPane scrollpane = new ScrollPane();
        BackgroundFill blueFill = new BackgroundFill(Color.BLUE, new CornerRadii(500), new Insets(10));
        scrollpane.setBackground(new Background(blueFill));
        vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10,10,10,10));
        MenuBar menuBar = new MenuBar();
        menuBar.setOpacity(1);
        Menu fileMenu = new Menu("File");
        MenuItem addEmployeeItem = new MenuItem("Add New Employee");
        addEmployeeItem.setOnAction(e-> {
            addNewEmployeeClicked();
        });
        fileMenu.getItems().add(addEmployeeItem);
        menuBar.getMenus().add(fileMenu);
        //vBox.getChildren().add(menuBar);

        try {

            for (Person emp : employees) {

                vBox.getChildren().add(
                        setupHBox(emp));

            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        scrollpane.setContent(vBox);
        VBox outerVBox = new VBox();
        outerVBox.getChildren().addAll(menuBar,scrollpane);
        return outerVBox;


    }

    public HBox setupHBox(Person emp){
        HBox ans = new HBox();
        ImageView employeePic = new ImageView();
        try{
            employeePic.setImage(emp.getPicture());

        } catch(Exception e){
            System.out.println(e);
        }
        employeePic.setFitHeight(200.0);
        employeePic.setFitWidth(200.0);


        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10,10,10,10));
        BackgroundFill blueFill = new BackgroundFill(new Color(0,0,0,.1), new CornerRadii(25), new Insets(2));
        vBox.setBackground(new Background(blueFill));
        vBox.setPrefWidth(550);

        //name
        Hyperlink nameLink = new Hyperlink(emp.getName());
        nameLink.setOnAction(e-> EmployeePage.display(emp));
        //nameLink.setUnderline(false);
        nameLink.setVisited(true);


        //information displayed
        Label phoneLabel = new Label("Phone Number: " + emp.getMyContact().getNumber());
        Label emailLabel = new Label("Email: " + emp.getMyContact().getEmail());
        Label positionLabel = new Label("Position: " + emp.getPosition());

        vBox.getChildren().addAll(nameLink, positionLabel, emailLabel, phoneLabel);
        ans.getChildren().addAll(employeePic, vBox);

        return ans;
    }

    public void addNewEmployeeClicked(){
        try {
            vBox.getChildren().add(setupHBox(NewEmployeeController.display()));
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

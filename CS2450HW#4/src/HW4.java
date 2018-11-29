import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.awt.*;

public class HW4 extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage myStage)
    {
        Sphere sphere = new Sphere(); // Radius
        Box box = new Box(); // w h d
        Cylinder cylinder = new Cylinder(); // r h

        BorderPane bP = new BorderPane();

        MenuBar menuB = new MenuBar();
        Menu menu = new Menu("File");
        menuB.getMenus().add(menu);
        MenuItem save = new MenuItem("Save");
        MenuItem open = new MenuItem("Open");
        menu.getItems().addAll(save,new SeparatorMenuItem(),open);

        VBox rootNode = new VBox(10);
        rootNode.setAlignment(Pos.CENTER);
        Group shapesGroup = new Group();
        shapesGroup.getChildren().addAll(sphere,box,cylinder);
        SubScene shapesSub = new SubScene(shapesGroup,500,500,true, SceneAntialiasing.DISABLED);
        PerspectiveCamera pCamera = new PerspectiveCamera(true);
        //pCamera.getTransforms().addAll(new Translate(0,0,0));
        shapesSub.setCamera(pCamera);

        Label shapeL = new Label("Select Shape");
        Label xLocation = new Label("x: ");
        Label yLocation = new Label("y: ");
        Label radius = new Label("radius: ");
        Label width = new Label("width: ");
        Label height = new Label("height: ");
        Label length = new Label("length: ");

        VBox circleVB = new VBox(10,xLocation,yLocation,radius);
        VBox boxVB = new VBox(10,xLocation,yLocation,width,height,length);
        VBox cylinderVB = new VBox(10,xLocation,yLocation,radius,height);

        ChoiceBox<String> shapeChoice = getShapeChoiceBox();

        Button b = new Button("Add Shape");
        b.setOnAction(event -> {
            //bP.setBottom();
            String shapeS = shapeChoice.getSelectionModel().getSelectedItem();
            if(shapeS == "SPHERE")
            {

            }else if(shapeS == "BOX")
            {

            }else if(shapeS == "CYLINDER")
            {

            }
        });

        Label label = new Label("Change Background Color: ");
        ChoiceBox<String> colorChoice = getColorChoiceBox();

        Button changeColor = new Button("Change Color");
        changeColor.setOnAction(event -> {
            String colorS = colorChoice.getSelectionModel().getSelectedItem();
            if(colorS == "RED")
            {
                shapesSub.setFill(Color.RED);
            }else if(colorS == "GREEN")
            {
                shapesSub.setFill(Color.GREEN);
            }else if(colorS == "BLUE")
            {
                shapesSub.setFill(Color.BLUE);
            }else if(colorS == "AZURE")
            {
                shapesSub.setFill(Color.AZURE);
            }else if(colorS == "GRAY")
            {
                shapesSub.setFill(Color.GRAY);
            }
        });
        HBox bottomHB = new HBox(10,label,colorChoice,changeColor);
        bottomHB.setAlignment(Pos.CENTER);
        VBox bottomVB = new VBox(10,b,bottomHB);
        bottomVB.setAlignment(Pos.CENTER);
        bottomVB.setPadding(new Insets(10));


        bP.setTop(menuB);
        bP.setCenter(shapesSub);
        //bP.setRight(); // VBOX of TOOLS
        bP.setBottom(bottomVB); // VBOX of 'Add Shape' Button and Background Color

        Scene scene = new Scene(bP);
        myStage.setScene(scene);
        myStage.show();
    }

    ChoiceBox<String> getColorChoiceBox()
    {
        ChoiceBox<String> colorChoice = new ChoiceBox<>();
        colorChoice.getItems().addAll("RED","GREEN","BLUE","AZURE","GRAY");
        return colorChoice;
    }

    ChoiceBox<String> getShapeChoiceBox()
    {
        ChoiceBox<String> shapeChoice = new ChoiceBox<>();
        shapeChoice.getItems().addAll("SPHERE", "BOX", "CYLINDER");
        return shapeChoice;
    }
}

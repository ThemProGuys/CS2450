package edu.cpp.CS2450;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
/**
 * 
 * @author Varoozhan, Sarmen, Jesus, Chanwoo
 * Let's put a more detailed comment on what was done on the project here:
 * 
 *
 */

public class HW4 extends Application
{
	PhongMaterial materialBox;
	PhongMaterial materialCylinder;
	PhongMaterial materialSphere;
	PhongMaterial material;

    public static void main(String[] args){launch(args);}

    public void start(Stage myStage)
    {
        Sphere sphere = new Sphere(3); // Radius
        // temporary given values to print shapes and make the tool box function
        Box box = new Box(5,5,5); // w h d
        Cylinder cylinder = new Cylinder(2,10); // r h

        BorderPane borderPane = new BorderPane();

        MenuBar menuB = new MenuBar();
        Menu menu = new Menu("File");
        menuB.getMenus().add(menu);
        MenuItem save = new MenuItem("Save");
        MenuItem open = new MenuItem("Open");
        menu.getItems().addAll(save,new SeparatorMenuItem(),open);
        
        
		box.getTransforms().add( new Translate(-8, 0, 0));
		cylinder.getTransforms().add( new Translate(8, 0, 0));

        VBox rootNode = new VBox(10);
        rootNode.setAlignment(Pos.CENTER);
        Group shapesGroup = new Group();
        shapesGroup.getChildren().addAll(sphere,box,cylinder);
        SubScene shapesSubScene = new SubScene(shapesGroup,900,600,true, SceneAntialiasing.DISABLED);
        Rotate hRotate = new Rotate(45, Rotate.X_AXIS);
        PerspectiveCamera pCamera = new PerspectiveCamera(true);
        pCamera.getTransforms().addAll(hRotate,new Translate(0,0,-60));
        shapesSubScene.setCamera(pCamera);
        shapesSubScene.setFill(Color.WHITE);

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

        Label backgroundLabel = new Label("Change Background Color: ");
        ChoiceBox<String> backgroundColorChoice = getColorChoiceBox();

        Button changeColor = new Button("Change Color");
        changeColor.setOnAction(event -> {
            String backgroundColorString = backgroundColorChoice.getSelectionModel().getSelectedItem();
            if(backgroundColorString == "RED")
            {
                shapesSubScene.setFill(Color.RED);
            }else if(backgroundColorString == "GREEN")
            {
                shapesSubScene.setFill(Color.GREEN);
            }else if(backgroundColorString == "BLUE")
            {
                shapesSubScene.setFill(Color.BLUE);
            }else if(backgroundColorString == "AZURE")
            {
                shapesSubScene.setFill(Color.AZURE);
            }else if(backgroundColorString == "GRAY")
            {
                shapesSubScene.setFill(Color.GRAY);
            }
        });
        
        HBox bottomHB = new HBox(10,backgroundLabel,backgroundColorChoice,changeColor);
        bottomHB.setAlignment(Pos.CENTER);
        VBox bottomVB = new VBox(10,b,bottomHB);
        bottomVB.setAlignment(Pos.CENTER);
        bottomVB.setPadding(new Insets(10));
        
        
        
        
        Label shapeColorLabel = new Label("Change shape color: ");
        ChoiceBox<String> shapeColorChoice = getColorChoiceBox();
        materialBox =new PhongMaterial();
    	materialCylinder =new PhongMaterial();
    	materialSphere =new PhongMaterial();
    	material =new PhongMaterial();
        
    	material = materialBox;

        shapeColorChoice.getSelectionModel()
        .selectedItemProperty()
        .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
        	String shapeColorString = shapeColorChoice.getSelectionModel().getSelectedItem();
            if(shapeColorString == "RED")
            {
            	material.setDiffuseColor(Color.RED);
            }else if(shapeColorString == "GREEN")
            {
            	material.setDiffuseColor(Color.GREEN);//                	material = material2;            
            }else if(shapeColorString == "BLUE")
            {
            	material.setDiffuseColor(Color.BLUE);//                	material = material2;     
            }else if(shapeColorString == "AZURE")
            {
            	material.setDiffuseColor(Color.AZURE);
            }else if(shapeColorString == "GRAY")
            {
            	material.setDiffuseColor(Color.GRAY);
            }
        });
        
        box.setOnMouseClicked(event->{
        	material = materialBox;
        	box.setMaterial(material);
        }); 
        cylinder.setOnMouseClicked(event->{
        	material = materialCylinder;
        	cylinder.setMaterial(material); 
        }); 
        sphere.setOnMouseClicked(event->{
        	material = materialSphere;
        	sphere.setMaterial(material);
        }); 

        HBox toolHbox1 = new HBox(shapeColorLabel,shapeColorChoice);
        toolHbox1.setPadding(new Insets(20));
        HBox toolHbox2 = new HBox();
        HBox toolHbox3 = new HBox();
        HBox toolHbox4 = new HBox();
        
        VBox rightVB = new VBox(toolHbox1,toolHbox2,toolHbox3,toolHbox4); //this vbox is for the tools part
        
        borderPane.setTop(menuB);
        borderPane.setCenter(shapesSubScene);
        //bP.setRight(); // VBOX of TOOLS
        borderPane.setBottom(bottomVB); // VBOX of 'Add Shape' Button and Background Color
        borderPane.setRight(rightVB);
        
        Scene scene = new Scene(borderPane);
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
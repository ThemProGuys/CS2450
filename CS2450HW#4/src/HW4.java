package edu.cpp.CS2450;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
/**
 * 
 * @author Varoozhan, Sarmen, Jesus, Chanwoo
 * Let's put a more detailed comment on what was done on the project here:
 * Varoozhan & Sarmen: So far we have added the shape change color tool
 *
 */

public class HW4 extends Application
{
	PhongMaterial materialBox;
	PhongMaterial materialCylinder;
	PhongMaterial materialSphere;
	PhongMaterial material;
	Rotate rotateBoxX;
	Rotate rotateBoxY;
	Rotate rotateCylX;
	Rotate rotateCylY;
	Rotate rotateSphX;
	Rotate rotateSphY;
	Rotate rotateX;
	Rotate rotateY;
	Scale boxScale;
	Scale cylScale;
	Scale sphScale;
	Scale scale;
	Translate boxTranslate;
	Translate cylTranslate;
	Translate sphTranslate;
	Translate translate;
	
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
        
/*
 * Varoohzan and Sarmen code starts here:
 */
        
// TOOLS: Change Color Part
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
            	material.setDiffuseColor(Color.GREEN);          
            }else if(shapeColorString == "BLUE")
            {
            	material.setDiffuseColor(Color.BLUE);     
            }else if(shapeColorString == "AZURE")
            {
            	material.setDiffuseColor(Color.AZURE);
            }else if(shapeColorString == "GRAY")
            {
            	material.setDiffuseColor(Color.GRAY);
            }
        });
        
//TOOLS: Scale part
        GridPane gridPaneScale = new GridPane();	
        gridPaneScale.setPadding(new Insets(10));
		Label scaleLabel=new Label("Please Enter Scale Size(default is 1): ");
		Label scaleWidth=new Label("Width: ");
		Label scaleHeight=new Label("Height: ");
		Label scaleDepth=new Label("Depth: ");
		TextField widthTextField = new TextField("1");
		TextField heightTextField = new TextField("1");
		TextField depthTextField = new TextField("1");
		
		gridPaneScale.add(scaleLabel,0,0);
		gridPaneScale.add(scaleWidth,0,1);
		gridPaneScale.add(scaleHeight,0,2);
		gridPaneScale.add(scaleDepth,0,3);
		gridPaneScale.add(widthTextField,1,1);
		gridPaneScale.add(heightTextField,1,2);
		gridPaneScale.add(depthTextField,1,3);
		
		boxScale = new Scale();
		cylScale = new Scale();
		sphScale = new Scale();
		scale = new Scale();
		scale = boxScale;
		
		widthTextField.textProperty().addListener((observable, oldvalue, newvalue) -> {
			double widthValue = Double.parseDouble(widthTextField.getText());
			scale.setX(widthValue);
        });
		heightTextField.textProperty().addListener((observable, oldvalue, newvalue) -> {
			double heightValue = Double.parseDouble(heightTextField.getText());
			scale.setY(heightValue);

        });
		depthTextField.textProperty().addListener((observable, oldvalue, newvalue) -> {
			double depthValue = Double.parseDouble(depthTextField.getText());
			scale.setZ(depthValue);
        });

// TOOLS: Rotate part
        Label shapeRotateLabel = new Label("Rotate Shapes: ");
        Label xLabel = new Label("X-Axis Rotater");
        Label yLabel = new Label("Y-Axis Rotater");
        
        Slider xSlider = new Slider(0, 360, 0);
        xSlider.setPrefWidth(300.0);
        Slider ySlider = new Slider(0, 360, 0);
        ySlider.setPrefWidth(300.0);
        
        xSlider.setShowTickMarks(true);
        xSlider.setShowTickLabels(true);
        ySlider.setShowTickMarks(true);
        ySlider.setShowTickLabels(true);
        
        rotateBoxX = new Rotate(0, Rotate.X_AXIS);
        rotateBoxY = new Rotate(0, Rotate.Y_AXIS);
        rotateCylX = new Rotate(0, Rotate.X_AXIS);
        rotateCylY = new Rotate(0, Rotate.Y_AXIS);
        rotateSphX = new Rotate(0, Rotate.X_AXIS);
        rotateSphY = new Rotate(0, Rotate.Y_AXIS);
        
        xSlider.valueProperty().addListener((observable, oldvalue, newvalue) -> {
            rotateX.setAngle(xSlider.getValue());
        });
        
        ySlider.valueProperty().addListener((observable, oldvalue, newvalue) -> {
            rotateY.setAngle(ySlider.getValue());
        });

    	rotateX =rotateBoxX;
    	rotateY =rotateBoxY;
    	
// TOOLS: Translate part
        GridPane gridPaneTranslate = new GridPane();	
        gridPaneScale.setPadding(new Insets(10));
		Label translateLabel=new Label("Please Enter x,y,z coordinates: ");
		Label translateX=new Label("X: ");
		Label translateY=new Label("Y: ");
		Label translateZ=new Label("Z: ");
		TextField xTextField = new TextField("0");
		TextField yTextField = new TextField("0");
		TextField zTextField = new TextField("0");
		
		gridPaneTranslate.add(translateLabel,0,0);
		gridPaneTranslate.add(translateX,0,1);
		gridPaneTranslate.add(translateY,0,2);
		gridPaneTranslate.add(translateZ,0,3);
		gridPaneTranslate.add(xTextField,1,1);
		gridPaneTranslate.add(yTextField,1,2);
		gridPaneTranslate.add(zTextField,1,3);
		
		boxTranslate = new Translate();
		cylTranslate = new Translate();
		sphTranslate = new Translate();
		translate = new Translate();
		translate = boxTranslate;
		
		xTextField.textProperty().addListener((observable, oldvalue, newvalue) -> {
			double xValue = Double.parseDouble(xTextField.getText());
			translate.setX(xValue);
        });
		yTextField.textProperty().addListener((observable, oldvalue, newvalue) -> {
			double yValue = Double.parseDouble(yTextField.getText());
			translate.setY(yValue);

        });
		zTextField.textProperty().addListener((observable, oldvalue, newvalue) -> {
			double zValue = Double.parseDouble(zTextField.getText());
			translate.setZ(zValue);
        });
        
// common Code for Scale Transform and Rotate
        box.getTransforms().addAll(rotateBoxX, rotateBoxY);
        cylinder.getTransforms().addAll(rotateCylX, rotateCylY);
        sphere.getTransforms().addAll(rotateBoxX, rotateBoxY);
        
        
// Mouse Click Events for each object.
        box.setOnMouseClicked(event->{
        	material = materialBox;
        	box.setMaterial(material);
        	rotateX =rotateBoxX;
        	rotateY =rotateBoxY;
        	scale = boxScale;
        	translate = boxTranslate;
	        box.getTransforms().addAll(scale,translate);

        }); 
        cylinder.setOnMouseClicked(event->{
        	material = materialCylinder;
        	cylinder.setMaterial(material); 
        	rotateX =rotateCylX;
        	rotateY =rotateCylY;
        	scale = cylScale;
        	translate = cylTranslate;
	        cylinder.getTransforms().addAll(scale,translate);

        }); 
        sphere.setOnMouseClicked(event->{
        	material = materialSphere;
        	sphere.setMaterial(material);
        	rotateX =rotateSphX;
        	rotateY =rotateSphY;
        	scale = sphScale;
        	translate = sphTranslate;
	        sphere.getTransforms().addAll(scale,translate);
        }); 
        
        


        HBox toolHboxShapeColors = new HBox(10, shapeColorLabel,shapeColorChoice);
        toolHboxShapeColors.setPadding(new Insets(10));
        HBox toolHboxShapeRotate = new HBox(shapeRotateLabel);
        toolHboxShapeRotate.setAlignment(Pos.CENTER);
        HBox toolHboxShapeRotateSlider1 = new HBox(xLabel);
        toolHboxShapeRotateSlider1.setPadding(new Insets(5));
        HBox toolHboxShapeRotateSlider2 = new HBox(xSlider);
        toolHboxShapeRotateSlider2.setPadding(new Insets(10));
        HBox toolHboxShapeRotateSlider3 = new HBox(yLabel);
        toolHboxShapeRotateSlider3.setPadding(new Insets(5));
        HBox toolHboxShapeRotateSlider4 = new HBox(ySlider);
        toolHboxShapeRotateSlider4.setPadding(new Insets(10));
        VBox rightVB = new VBox(toolHboxShapeColors,toolHboxShapeRotate,toolHboxShapeRotateSlider1,toolHboxShapeRotateSlider2,
        		toolHboxShapeRotateSlider3,toolHboxShapeRotateSlider4,gridPaneScale,gridPaneTranslate); //this vbox is for the tools part
        
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inclassexercises;

/**
 *
 * @author Me
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.SubScene;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.Group;
import javafx.scene.SceneAntialiasing;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Label;

public class InClassLecture20 extends Application{
    Rotate rotateBoxX;
    Rotate rotateBoxY;
    Rotate rotateCylX;
    Rotate rotateCylY;
    Rotate rotateX;
    Rotate rotateY;
    
    public static void main(String[]args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) {
        Box box = new Box(10, 10, 10);
        Cylinder cyl = new Cylinder(10, 10, 10);
        cyl.getTransforms().addAll(new Translate(0, 10, 0));
        box.getTransforms().addAll(new Translate(0, -10, 0));
        
        rotateBoxX = new Rotate(0, Rotate.X_AXIS);
        rotateBoxY = new Rotate(0, Rotate.Y_AXIS);
        rotateCylX = new Rotate(0, Rotate.X_AXIS);
        rotateCylY = new Rotate(0, Rotate.Y_AXIS);
        
        box.getTransforms().addAll(rotateBoxX, rotateBoxY);
        cyl.getTransforms().addAll(rotateCylX, rotateCylY);
        
        rotateX = rotateBoxX;
        rotateY = rotateBoxY;
        
        box.setOnMouseClicked(event -> {
            rotateX = rotateBoxX;
            rotateY = rotateBoxY;
        });
        
        cyl.setOnMouseClicked(event -> {
            rotateX = rotateCylX;
            rotateY = rotateCylY;
        });
        
        Label xLabel = new Label("X-Axis Rotater");
        Label yLabel = new Label("Y-Axis Rotater");
        
        Slider xSlider = new Slider(0, 360, 0);
        Slider ySlider = new Slider(0, 360, 0);
        
        xSlider.setShowTickMarks(true);
        xSlider.setShowTickLabels(true);
        ySlider.setShowTickMarks(true);
        ySlider.setShowTickLabels(true);
        
        xSlider.valueProperty().addListener((observable, oldvalue, newvalue) -> {
            rotateX.setAngle(xSlider.getValue());
        });
        
        ySlider.valueProperty().addListener((observable, oldvalue, newvalue) -> {
            rotateY.setAngle(ySlider.getValue());
        });
        
        Group group = new Group();
        group.getChildren().addAll(box, cyl);
        
        SubScene sub = new SubScene(group, 400, 400, true, SceneAntialiasing.DISABLED);
        sub.setFill(Color.ALICEBLUE);
        
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(sub,xLabel, xSlider, yLabel, ySlider);
        vbox.setAlignment(Pos.CENTER);
        
        PerspectiveCamera pc = new PerspectiveCamera(true);
        pc.getTransforms().addAll(new Translate(0, 0, -100));
        
        sub.setCamera(pc);
        
        Scene scene = new Scene(vbox, 550, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}

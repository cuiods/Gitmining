package edu.nju.git.ui.control.function;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

/**
 * 3-D Button
 * @author cuihao
 * @date 2016-03-31 09:34:52
 */
public class CubeButton {
	
	private Timeline animation;
	
	public Parent createContent() {
		
        Cube c2 = new Cube(1, Color.GREEN);
        c2.setTranslateX(2);
        c2.rx.setAngle(45);
        c2.ry.setAngle(45);
 
        animation = new Timeline();
        animation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                new KeyValue(c2.rx.angleProperty(), 0d)),
                new KeyFrame(Duration.seconds(1),
                new KeyValue(c2.rx.angleProperty(), 360d)));
        animation.setCycleCount(Timeline.INDEFINITE);
 
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.getTransforms().add(new Translate(0, 0, -10));
 
        Group root = new Group();
        root.getChildren().add(c2);
         
        SubScene subScene = new SubScene(root, 640, 480, true, SceneAntialiasing.BALANCED);
        subScene.setCamera(camera);
         
        return new Group(subScene);
    }

}

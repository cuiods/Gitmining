package edu.nju.git.ui.control.function;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

/**
 * 3-D Button
 * 
 * @author cuihao
 * @date 2016-03-31 09:34:52
 */
public class CubeButton {

	private Timeline animation;
	
	/**
	 * create node
	 * @param size
	 * 				1 is a proper size
	 * @param image1  
	 * 				bumpMap image
	 * @param image2
	 * 				diffuseMap image
	 * @param color
	 * 				specular color
	 * @return
	 * 				node to show
	 */
	public Parent createContent(double size, Image image1, Image image2, Color color) {

		Cube c2 = new Cube(size, image1, image2, color);

		return init(c2);
	}
	
	/**
	 * create node
	 * @param size
	 * 				1 is a proper size
	 * @param color
	 * 				specular color
	 * @return
	 * 				node to show
	 */
	public Parent createContent(double size, Color color) {

		Cube c2 = new Cube(size, color);

		return init(c2);
	}

	private Parent init(Cube c2) {
//		c2.setTranslateX(2);
		c2.rx.setAngle(45);
		c2.ry.setAngle(45);

		animation = new Timeline();
		animation.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new KeyValue(c2.rx.angleProperty(), 0d)),
				new KeyFrame(Duration.seconds(2), new KeyValue(c2.rx.angleProperty(), 360d)));
		animation.setCycleCount(Timeline.INDEFINITE);

		PerspectiveCamera camera = new PerspectiveCamera(true);
		camera.getTransforms().add(new Translate(0, 0, -10));

		Group root = new Group();
		root.getChildren().add(c2);

		SubScene subScene = new SubScene(root, 210, 210, true, SceneAntialiasing.BALANCED);
		subScene.setCamera(camera);

		return new Group(subScene);
	}
	
	/**
	 * play 3D animation
	 */
	public void play() {
		animation.play();
	}
	
	/**
	 * stop 3D animation
	 */
	public void stop() {
		animation.stop();
	}

}

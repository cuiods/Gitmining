package edu.nju.git.ui.control.function;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

public class Cube extends Box {
	
	final Rotate rx = new Rotate(0, Rotate.X_AXIS);
    final Rotate ry = new Rotate(0, Rotate.Y_AXIS);
    final Rotate rz = new Rotate(0, Rotate.Z_AXIS);
 
    public Cube(double size, Color color) {
        super(size, size, size);
        setMaterial(new PhongMaterial(color));
        getTransforms().addAll(rz, ry, rx);
    }
    
    public Cube(double size, Image image1, Image image2, Color color) {
        super(size, size, size);
        PhongMaterial material = new PhongMaterial();
        material.setBumpMap(image1);
        material.setDiffuseMap(image2);
        material.setSpecularColor(color);
        setMaterial(material);
        getTransforms().addAll(rz, ry, rx);
    }

}

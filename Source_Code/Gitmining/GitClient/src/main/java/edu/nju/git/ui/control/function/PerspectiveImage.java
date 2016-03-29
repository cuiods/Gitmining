package edu.nju.git.ui.control.function;

 

import javafx.beans.property.DoubleProperty;

import javafx.beans.property.SimpleDoubleProperty;

import javafx.scene.Parent;

import javafx.scene.effect.PerspectiveTransform;

import javafx.scene.effect.Reflection;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

 

/**

 * A Node that displays a image with some 2.5D perspective rotation around the Y

 * axis.

 */

public class PerspectiveImage extends Parent {

 

    private static final double REFLECTION_SIZE = 0.25;

     

    public static final double WIDTH = 600;

    public static final double HEIGHT = WIDTH - (WIDTH * REFLECTION_SIZE)-80;

     

    private static final double RADIUS_H = WIDTH / 2;

    private static final double BACK = WIDTH / 10;

    private PerspectiveTransform transform = new PerspectiveTransform();//透视变换，2d图片出3d效果
    
    private boolean isCenter=false;

    /**

     * Angle Property

     */

    public final DoubleProperty angle = new SimpleDoubleProperty(45) {//定义一个与double值有关的peroperty,property指独立于类型的方法

        @Override

        protected void invalidated() {

            // when angle changes calculate new transform

            double lx = (RADIUS_H - Math.sin(Math.toRadians(angle.get())) * RADIUS_H - 1);

            double rx = (RADIUS_H + Math.sin(Math.toRadians(angle.get())) * RADIUS_H + 1);

            double uly = (-Math.cos(Math.toRadians(angle.get())) * BACK);

            double ury = -uly;

            transform.setUlx(lx);

            transform.setUly(uly);

            transform.setUrx(rx);

            transform.setUry(ury);

            transform.setLrx(rx);

            transform.setLry(HEIGHT + uly);

            transform.setLlx(lx);

            transform.setLly(HEIGHT + ury);

        }

    };

 

    public final double getAngle() {

        return angle.getValue();

    }

 

    public final void setAngle(double value) {

        angle.setValue(value);

    }

 

    public final DoubleProperty angleModel() {

        return angle;

    }
    
    public final boolean getIsCenter(){
    	return isCenter;
    }
    
    public final void setCenter(boolean isCenter){
    	this.isCenter=isCenter;
    }

 

    public PerspectiveImage(Image image) {

        ImageView imageView = new ImageView(image);

        Reflection reflection = new Reflection();//映射效果

        reflection.setFraction(REFLECTION_SIZE);

        imageView.setEffect(reflection);

        setEffect(transform);

        getChildren().addAll(imageView);

    }

}
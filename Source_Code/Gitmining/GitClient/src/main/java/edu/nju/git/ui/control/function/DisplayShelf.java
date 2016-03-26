package edu.nju.git.ui.control.function;
/* ....Show License.... */

import javafx.animation.Interpolator;

import javafx.animation.KeyFrame;

import javafx.animation.KeyValue;

import javafx.animation.Timeline;

import javafx.beans.InvalidationListener;

import javafx.beans.Observable;

import javafx.collections.ObservableList;

import javafx.event.EventHandler;

import javafx.scene.Group;

import javafx.scene.control.ScrollBar;

import javafx.scene.image.Image;

import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyEvent;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Region;

import javafx.scene.shape.Rectangle;

import javafx.util.Duration;

 

/**

 * Simple 7 segment LED style digit. It supports the numbers 0 through 9.

 */

/**

 * A ui control which displays a browse-able display shelf of images

 */

public class DisplayShelf extends Region {

 

    private final Duration DURATION = Duration.millis(500);//Duration，持续时间

    private final Interpolator INTERPOLATOR = Interpolator.EASE_BOTH;//动画的变化率

    private final double SPACING = 50;

    private final double LEFT_OFFSET = -110;

    private final double RIGHT_OFFSET = 110;

    private final double SCALE_SMALL = 0.7;

    private PerspectiveImage[] items;

    private Group centered = new Group();

    private Group left = new Group();

    private Group center = new Group();

    private Group right = new Group();

    private int centerIndex = 0;

    private Timeline timeline;

    private ScrollBar scrollBar = new ScrollBar();

    private boolean localChange = false;

    private Rectangle clip = new Rectangle();
    
//    private boolean isFirst;

 

    public DisplayShelf(Image[] images) {
    	
//    	isFirst = true;

        // set clip

        setClip(clip);

        // set ids for styling via CSS

        setId("displayshelf");  

        scrollBar.setId("display-scrollbar");

        // create items

        items = new PerspectiveImage[images.length];
        
        
        for (int i = 0; i < images.length; i++) {

            final PerspectiveImage item =

                    items[i] = new PerspectiveImage(images[i]);          
           
            final double index = i;
            

            item.setOnMouseClicked((MouseEvent me) -> {
//            	isFirst = false;

                localChange = true;

                scrollBar.setValue(index);

                localChange = false;

                shiftToCenter(item);

            });

        }
        //initialize

        // setup scroll bar

        scrollBar.setMax(items.length - 1);

        scrollBar.setVisibleAmount(1);

        scrollBar.setUnitIncrement(1);

        scrollBar.setBlockIncrement(1);//滚动条增长

        scrollBar.valueProperty().addListener((Observable ov) -> {

            if (!localChange) {

                shiftToCenter(items[(int) Math.round(scrollBar.getValue())]);

            }

        });

        // create content

        centered.getChildren().addAll(left, right, center);

        getChildren().addAll(centered, scrollBar);

        // listen for keyboard events

        setFocusTraversable(true);//设置焦点属性

        setOnKeyPressed((KeyEvent ke) -> {

            if (ke.getCode() == KeyCode.LEFT) {

                shift(1);

                localChange = true;

                scrollBar.setValue(centerIndex);

                localChange = false;

            } else if (ke.getCode() == KeyCode.RIGHT) {

                shift(-1);

                localChange = true;

                scrollBar.setValue(centerIndex);

                localChange = false;

            }

        });

        // update
        update();

    }

 

    @Override

    protected void layoutChildren() {

        // update clip to our size

        clip.setWidth(getWidth());

        clip.setHeight(getHeight()+20);

        // keep centered centered

        centered.setLayoutY((getHeight() - PerspectiveImage.HEIGHT) / 2);

        centered.setLayoutX((getWidth() - PerspectiveImage.WIDTH) / 2);

        // position scroll bar at bottom

        scrollBar.setLayoutX(10);

        scrollBar.setLayoutY(getHeight() - 25);

        scrollBar.resize(getWidth() - 20, 15);

    }

 

    private void update() {
    	
//    	if(isFirst){
//    		scrollBar.setValue(items.length/2);
//    		shiftToCenter(items[items.length/2]);
//    	}

        // move items to new homes in groups

        left.getChildren().clear();

        center.getChildren().clear();

        right.getChildren().clear();

        for (int i = 0; i < centerIndex; i++) {

            left.getChildren().add(items[i]);

        }

        center.getChildren().add(items[centerIndex]);

        for (int i = items.length - 1; i > centerIndex; i--) {

            right.getChildren().add(items[i]);

        }

        // stop old timeline if there is one running

        if (timeline != null) {

            timeline.stop();

        }

        // create timeline to animate to new positions

        timeline = new Timeline();

        // add keyframes for left items

        final ObservableList<KeyFrame> keyFrames = timeline.getKeyFrames();

        for (int i = 0; i < left.getChildren().size(); i++) {

            final PerspectiveImage it = items[i];

            double newX = -left.getChildren().size()

                    * SPACING + SPACING * i + LEFT_OFFSET;

            keyFrames.add(new KeyFrame(DURATION,

                    new KeyValue(it.translateXProperty(), newX, INTERPOLATOR),

                    new KeyValue(it.scaleXProperty(), SCALE_SMALL, INTERPOLATOR),

                    new KeyValue(it.scaleYProperty(), SCALE_SMALL, INTERPOLATOR),

                    new KeyValue(it.angle, 45.0, INTERPOLATOR)));

        }

        // add keyframe for center item

        final PerspectiveImage centerItem = items[centerIndex];

        keyFrames.add(new KeyFrame(DURATION,

                new KeyValue(centerItem.translateXProperty(), 0, INTERPOLATOR),

                new KeyValue(centerItem.scaleXProperty(), 1.0, INTERPOLATOR),

                new KeyValue(centerItem.scaleYProperty(), 1.0, INTERPOLATOR),

                new KeyValue(centerItem.angle, 90.0, INTERPOLATOR)));

        // add keyframes for right items

        for (int i = 0; i < right.getChildren().size(); i++) {

            final PerspectiveImage it = items[items.length - i - 1];

            final double newX = right.getChildren().size()

                    * SPACING - SPACING * i + RIGHT_OFFSET;

            keyFrames.add(new KeyFrame(DURATION,

                    new KeyValue(it.translateXProperty(), newX, INTERPOLATOR),

                    new KeyValue(it.scaleXProperty(), SCALE_SMALL, INTERPOLATOR),

                    new KeyValue(it.scaleYProperty(), SCALE_SMALL, INTERPOLATOR),

                    new KeyValue(it.angle, 135.0, INTERPOLATOR)));

        }

        // play animation

        timeline.play();

    }

 

    private void shiftToCenter(PerspectiveImage item) {

        for (int i = 0; i < left.getChildren().size(); i++) {

            if (left.getChildren().get(i) == item) {

                int shiftAmount = left.getChildren().size() - i;

                shift(shiftAmount);

                return;

            }

        }

        if (center.getChildren().get(0) == item) {

            return;

        }

        for (int i = 0; i < right.getChildren().size(); i++) {

            if (right.getChildren().get(i) == item) {

                int shiftAmount = -(right.getChildren().size() - i);

                shift(shiftAmount);

                return;

            }

        }

    }

 

    public void shift(int shiftAmount) {

        if (centerIndex <= 0 && shiftAmount > 0) {

            return;

        }

        if (centerIndex >= items.length - 1 && shiftAmount < 0) {

            return;

        }

        centerIndex -= shiftAmount;

        update();

    }

}
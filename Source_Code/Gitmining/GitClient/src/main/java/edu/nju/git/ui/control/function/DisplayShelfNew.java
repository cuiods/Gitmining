package edu.nju.git.ui.control.function;


import edu.nju.git.main.Main;
import edu.nju.git.ui.config.StringReader;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
/*
 * to show the home animation
 * @author yyy
 */
public class DisplayShelfNew extends Region{
	private final Duration DURATION = Duration.millis(500);//Duration，持续时间
    private final Interpolator INTERPOLATOR = Interpolator.EASE_BOTH;//动画的变化率
    
    private final double SPACING = 50;
    private final double LEFT_OFFSET = -200;
    private final double RIGHT_OFFSET = 300;
    private final double SCALE_SMALL = 0.7;
    
    private PerspectiveImage[] items;
    private Group centered = new Group();
    private Group left = new Group();
    private Group center = new Group();
    private Group right = new Group();

    private int centerIndex = 0;
    private int length = 0;

    private Timeline timeline;

    private SpecialButton buttons[]; 
    private int buttonIndex=0;

    private boolean localChange = false;

    private Rectangle clip = new Rectangle();
    
    private Background back_in;
    private Background back_out;
    
    public DisplayShelfNew(Image[] images) {
    	setClip(clip);
    	setId("displayshelf");
    	
    	length = images.length;
    	items = new PerspectiveImage[images.length];
    	buttons = new SpecialButton[images.length];
    	
    	//setting the buttons' preference
    	Image in = new Image(Main.class.getResource(StringReader.readPath("picture")+"imageIn.png").toString());
    	Image exit = new Image(Main.class.getResource(StringReader.readPath("picture")+"imageOut.png").toString());
    	BackgroundImage imageIn = new BackgroundImage(in,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
    	BackgroundImage imageOut = new BackgroundImage(exit,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
    	back_in = new Background(imageIn);
    	back_out = new Background(imageOut);
    	
    	for(int i=0;i<images.length;i++){
    		final PerspectiveImage item = items[i] = new PerspectiveImage(images[i]);
    		final double index = i;
    		buttons[i] = new SpecialButton();
    		buttons[i].setBackground(back_out);
    		buttons[i].getStyleClass().add("specialButton");
    		buttons[i].setIndex(i);
    		
    		
    		item.setOnMouseClicked((MouseEvent me) ->{
    			//to do
    			localChange = true;
    			buttonIndex = (int)index;
    			localChange = false;
    			
    			if(item.getIsCenter()){
    				for(int j=0;j<buttonIndex;j++){
    					items[j].setCenter(false);
    				}
    				for(int j=buttonIndex+1;j<images.length;j++){
    					items[j].setCenter(false);
    				}
    				
    				item.jump(buttonIndex);
    				//to jump ,has to finish aftertime
    			}else{
    				shiftToCenter(item);
//    				for(int j=0;j<images.length;j++){
//    					if(buttons[j].getIndex()==buttonIndex){
//    						buttons[j].setBackground(back_in);
//    					}else{
//    						buttons[j].setBackground(back_out);
//    					}
//    				}
    			}
    			    			
    		});
    	}
    	//initialize the buttons
    	for(int i=0;i<images.length;i++){
    		SpecialButton butt = buttons[i];
    		buttons[i].setOnMouseEntered((MouseEvent me) ->{
    			if (!localChange) {
    				buttonIndex = butt.getIndex();
                    shiftToCenter(items[(int) Math.round(butt.getIndex())]);
                    
                }
    		});
    		
   
    	}
    	
    	centered.getChildren().addAll(left,right,center);
    	getChildren().add(centered);
    	for(int i=0;i<images.length;i++){
    		getChildren().add(buttons[i]);
    	}
    	
    	//listen for keyboard events
    	setFocusTraversable(true);
    	
    	setOnKeyPressed((KeyEvent ke)->{
    		if (ke.getCode() == KeyCode.LEFT) {
                shift(1);
                localChange = true;
                buttonIndex = centerIndex;
                localChange = false;
            } else if (ke.getCode() == KeyCode.RIGHT) {
                shift(-1);
                localChange = true;
                buttonIndex = centerIndex;
                localChange = false;

            }

    	});
    	
    	update();
    	
    	
    	
    }
    
    protected void layoutChildren(){
    	clip.setWidth(getWidth());
    	clip.setHeight(getHeight()+20);
    	
    	// keep centered centered
        centered.setLayoutY((getHeight() - PerspectiveImage.HEIGHT) / 2);
        centered.setLayoutX((getWidth() - PerspectiveImage.WIDTH) / 2);
        
        //position the buttons
        for(int i=0;i<items.length;i++){
        	buttons[i].setLayoutX(i*50+(getWidth()-500)/2);
        	buttons[i].setLayoutY(getHeight()-25);
        	buttons[i].resize(30, 3);
        }
    	
    }
    
    private void update(){
    	left.getChildren().clear();
    	center.getChildren().clear();
    	right.getChildren().clear();
    	
    	for (int i=0;i<centerIndex;i++){
    		left.getChildren().add(items[i]);
    	}
    	
    	center.getChildren().add(items[centerIndex]);
    	
    	for(int i=items.length -1;i>centerIndex;i--){
    		right.getChildren().add(items[i]);
    	}
    	
    	if(timeline != null){
    		timeline.stop();
    	}
    	
    	timeline = new Timeline();
    	
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
    
    private void shiftToCenter(PerspectiveImage item){
    	item.setCenter(true);
    	for(int i=0;i<items.length;i++){
    		if(buttons[i].getIndex()==buttonIndex){
    			buttons[i].setBackground(back_in);
    		}else{
    			buttons[i].setBackground(back_out);
    			items[i].setCenter(false);
    		}
    	}
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
    
    public void shift(int shiftAmount){
    	if (centerIndex <= 0 && shiftAmount > 0) {

            return;

        }

        if (centerIndex >= items.length - 1 && shiftAmount < 0) {

            return;

        }

        centerIndex -= shiftAmount;

        update();
    }
    
    
    class SpecialButton extends Button{
    	private int index;
    	
    	public int getIndex(){
    		return index;
    	}
    	
    	public void setIndex(int index){
    		this.index = index;
    	}
    }
}

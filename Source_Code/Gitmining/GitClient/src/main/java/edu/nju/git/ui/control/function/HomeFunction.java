package edu.nju.git.ui.control.function;


 

import java.net.URL;
import java.util.ResourceBundle;

import edu.nju.git.VO.UserVO;
import edu.nju.git.bl.factory.impl.BlFactory;
import edu.nju.git.bl.service.UserBlService;
import edu.nju.git.main.Main;
import edu.nju.git.type.MostType;
import edu.nju.git.ui.config.StringReader;
import edu.nju.git.ui.control.FunctionPanel;
import edu.nju.git.ui.control.UIManager;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

 

/**

 * the home function 
 * @author yyy

 */

public class HomeFunction extends FunctionPanel {
	
	@FXML private AnchorPane anchorPane;
	@FXML private ImageView value;
	@FXML private ImageView active;
	@FXML private ImageView gists;
	
	private UserBlService userBl;
    private static final double WIDTH = 820, HEIGHT = 450;
    

    private Timeline animation;

 

    public Parent createContent() {

         // load images

        Image[] images = new Image[6];
        for(int i=0;i<images.length;i++){
        	images[i] = new Image( Main.class.getResource(StringReader.readPath("picture")+"home/git"+(i)+".png").toString());
        }
       
        // create display shelf

        DisplayShelfNew displayShelf = new DisplayShelfNew(images);

        displayShelf.setPrefSize(WIDTH, HEIGHT);

        this.setCssFactory(UIManager.instance().getCssFactory());
//        System.out.println(getCssFactory().getDisplayShelf());
        String displayShelfCss = getCssFactory().getDisplayShelf();

        displayShelf.getStylesheets().add(displayShelfCss);       

        return displayShelf;

    }
    
    private ImageView getUsers(MostType type,ImageView view){
    	userBl = BlFactory.instance().getUserBlService();
    	String userName = userBl.getMostRank(type);
    	UserVO vo = userBl.getUserInfo(userName);
    	Image heading = new Image(vo.getAvatar_url());
    	view.setImage(heading);
    	view.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_userDetail", new Object[]{userName});
				
			}
    		
    	});
    	
    	return view;
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Parent display = createContent();
		anchorPane.getChildren().addAll(display);
		value = getUsers(MostType.USER_VALUE,value);
		active = getUsers(MostType.USER_ACTIVITY,active);
		gists = getUsers(MostType.USER_GIST,gists);
		
		
	}



	@Override
	public void initPanel(Object[] bundle) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setChildren(Parent region) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void removeChild() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getLocationName() {
		return "Home";
	}

}
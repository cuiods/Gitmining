package edu.nju.git.ui.control;

import java.net.URL;

import edu.nju.git.main.Main;
import edu.nju.git.ui.config.ConfigReader;
import edu.nju.git.ui.config.ScreenShot;
import edu.nju.git.ui.config.StringReader;
import edu.nju.git.ui.css.CSSFactory;
import edu.nju.git.ui.css.DefaultCSSFactory;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * <h1>Singleton manager of presentation</h1>
 * <ul>
 * 	<li>Get root panel.</li>
 * 	<li>Change Cascading Style Sheets.</li>
 * </ul>
 * @author cuihao
 * @date 2016-03-04 22:58:33
 */
public final class UIManager {
	/**
	 * sychronization singleton.
	 */
	private static volatile UIManager manager;
	/**
	 * current root panel.
	 */
	private ScreenShot root;
	/**
	 * key-value pair of all the services.
	 */
//	private Map<String, BlService> services = new HashMap<>();
	/**
	 * current stage
	 */
	private Stage primaryStage;
	/**
	 * css factory
	 */
	private CSSFactory cssFactory;
	
	private UIManager(){};
	
	public static UIManager instance() {
		if (manager==null) {
			synchronized (UIManager.class) {
				if (manager == null) {
					manager = new UIManager();
				}
			}
		}
		return manager;
	}
	
	/**
	 * initialize root panel.
	 * @return
	 */
	public Parent initialize(Stage stage){
		cssFactory = new DefaultCSSFactory();
		root = ConfigReader.readParentPanel("index");
		root.getPanel().initPanel(new Object[]{stage});
		root.getRoot().getStylesheets().add(cssFactory.getIndexCSS());
		primaryStage = stage;
		primaryStage.setTitle(StringReader.readString("title"));
		URL url = Main.class.getResource(StringReader.readPath("picture")+"icon.png");
		primaryStage.getIcons().add(new Image(url.toString()));
		return root.getRoot();
	}
	
	/**
	 * Change to a new Function Panel.
	 * @param funtion
	 * 			next funtion panel to change
	 * @param bundle
	 * 			data to the next panel
	 */
	public void changeFunction(String name, Object[] bundle) {
		ScreenShot function = ConfigReader.readParentPanel(name);
		root.getPanel().setChildren(function.getRoot());
		function.getPanel().initPanel(bundle);
	}

	/**
	 * get current css factory
	 * @return {@link CSSFactory}
	 */
	public CSSFactory getCssFactory() {
		return cssFactory;
	}

	public void setCssFactory(CSSFactory cssFactory) {
		this.cssFactory = cssFactory;
	}
	
}

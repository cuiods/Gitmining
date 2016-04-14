package edu.nju.git.ui.control;

import java.net.URL;

import edu.nju.git.main.Main;
import edu.nju.git.rmi.RMIClientLauncher;
import edu.nju.git.ui.config.ConfigReader;
import edu.nju.git.ui.config.ScreenShot;
import edu.nju.git.ui.config.StringReader;
import edu.nju.git.ui.css.CSSFactory;
import edu.nju.git.ui.css.DefaultCSSFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	 * Synchronization singleton.
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
		RMIClientLauncher.initRMI();
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
	/*
	 * mini the stage
	 */
	public void hide(){
		primaryStage.setIconified(!primaryStage.isIconified());
	}
	
	/**
	 * Change to a new Function Panel.
	 * @param funtion
	 * 			next function panel to change
	 * @param bundle
	 * 			data to the next panel
	 */
	public Parent changeFunction(String name, Object[] bundle) {
		ScreenShot function = ConfigReader.readParentPanel(name);
		function.getPanel().initPanel(bundle);
		root.getPanel().setChildren(function.getRoot());
		IndexPanel task = (IndexPanel) root.getPanel();
		task.appendFunction(function);
		return function.getRoot();
	}
	
	public void changeFunction(Parent node) {
		root.getPanel().setChildren(node);
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
	
	public void changeTheme(CSSFactory cssFactory){
		//primaryStage.hide();
		setCssFactory(cssFactory);
		root = ConfigReader.readParentPanel("index");
		root.getPanel().initPanel(new Object[]{primaryStage});
		root.getRoot().getStylesheets().add(cssFactory.getIndexCSS());
		primaryStage.setTitle(StringReader.readString("title"));
		URL url = Main.class.getResource(StringReader.readPath("picture")+"icon.png");
		primaryStage.getIcons().add(new Image(url.toString()));
		Scene scene = new Scene(root.getRoot());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}

package edu.nju.ui.control;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import edu.nju.git.bl.service.BlService;
import edu.nju.git.main.Main;
import edu.nju.git.ui.config.ConfigReader;
import edu.nju.git.ui.config.ScreenShot;
import edu.nju.git.ui.config.StringReader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * <h1>Singleton manager of presentation</h1>
 * <ul>
 * 	<li>Get {@link BlService} by key. (name)</li>
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
	private Map<String, BlService> services = new HashMap<>();
	/**
	 * current stage
	 */
	private Stage primaryStage;
	
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
		root = ConfigReader.readParentPanel("index");
		root.getPanel().initPanel(null);
		URL urlcss = Main.class.getResource(StringReader.readPath("css")+"index.css");
		root.getRoot().getStylesheets().add(urlcss.toString());
		primaryStage = stage;
		primaryStage.setTitle(StringReader.readString("title"));
		URL url = Main.class.getResource(StringReader.readPath("picture")+"icon.png");
		primaryStage.getIcons().add(new Image(url.toString()));
		return root.getRoot();
	}
	
	/**
	 * Change the scene's css style.
	 * @param name
	 * 		name of the style.
	 */
	public void changeCSS(String name) {
		if (primaryStage != null) {
			URL url = Main.class.getResource(StringReader.readPath("css")+name+".css");
			primaryStage.getScene().getStylesheets().add(url.toString());
		} else {
			System.out.println("Error:primaryStage is null");
		}
	}
	public void addService(String key, BlService service){
		services.put(key, service);
	}
	public BlService getService(String key) {
		return services.get(key);
	}
	
}

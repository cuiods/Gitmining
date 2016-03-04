package edu.nju.ui.control;

import java.util.HashMap;
import java.util.Map;

import edu.nju.git.bl.service.BlService;
import edu.nju.git.ui.config.ConfigReader;
import edu.nju.git.ui.config.ScreenShot;
import javafx.scene.Parent;

/**
 * <h1>Singleton manager of presentation</h1>
 * <ul>
 * 	<li>Get {@link BlService} by key. (name)</li>
 * 	<li>Get root panel.</li>
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
	public Parent initialize(){
		root = ConfigReader.readParentPanel("index");
		return root.getRoot();
	}
	public void addService(String key, BlService service){
		services.put(key, service);
	}
	public BlService getService(String key) {
		return services.get(key);
	}
	
}

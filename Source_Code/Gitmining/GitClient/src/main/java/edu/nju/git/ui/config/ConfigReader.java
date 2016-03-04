package edu.nju.git.ui.config;

import java.io.IOException;
import java.net.URL;

import edu.nju.git.main.Main;
import edu.nju.ui.control.GitPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TitledPane;

/**
 * <h1>CAN ONLY WORK WHEN JAVAFX IS STARTED!</h1>
 * <p>This class is used to read config from a .fxml file by its {@code static} method.</p>
 * @see #readParentPanel(String)
 * @author cuihao
 *
 */
public class ConfigReader {
	/**
	 * <h1>CAN ONLY WORK WHEN JAVAFX IS STARTED!</h1>
	 * <p>Read a panel config by its file name.</p>
	 * <p>The config file must be put in <b>src/main/resources/config/</b></p>
	 * <p>If an exception has been catched, the return value may be {@code null}.</p>
	 * @param name
	 * 			do not need ".fxml", it will be added later by this class.
	 * @return {@link ScreenShot}
	 */
	public static ScreenShot readParentPanel(String name){
		FXMLLoader loader = new FXMLLoader();
		URL url = Main.class.getResource(getRootPath()+name+".fxml");
		loader.setLocation(url);
		ScreenShot screenShot = null;
		try {
			Parent parent = loader.load();
			GitPanel panel = loader.getController();
			screenShot = new ScreenShot(parent, panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenShot;
	}
	/**
	 * get path to "config" directory.
	 */
	private static String getRootPath(){
		return StringReader.readPath("config");
	}
}

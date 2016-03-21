package edu.nju.git.ui.config;

import edu.nju.git.ui.control.GitPanel;
import javafx.scene.Parent;

/**
 * <h1>A record of a panel:{@link Parent}.</h1>
 * <p>This is a screenshort used to describe a panel.</p>
 * <p>1) Used to recover last panel.<br>
 * 2) Used as a data to init a panel.</p>
 * @author cuihao
 * @see GitPanel
 * @date 2016-03-04 20:13:50
 */
public class ScreenShot {
	/**
	 * Parent component auto created by root element of the .fxml file.
	 */
	private Parent root;
	/**
	 * {@code controller} of the {@link #root} panel.
	 */
	private GitPanel panel;
	
	public ScreenShot(Parent root, GitPanel panel) {
		this.root = root;
		this.panel = panel;
	}
	/**
	 * get {@link #root}
	 * @return 
	 */
	public Parent getRoot() {
		return root;
	}
	public void setRoot(Parent root) {
		this.root = root;
	}
	/**
	 * get {@link #panel}
	 * @return
	 */
	public GitPanel getPanel() {
		return panel;
	}
	public void setPanel(GitPanel panel) {
		this.panel = panel;
	}
	
}

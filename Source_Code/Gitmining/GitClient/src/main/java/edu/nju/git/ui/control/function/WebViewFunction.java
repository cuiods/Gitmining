package edu.nju.git.ui.control.function;

import java.net.URL;
import java.util.ResourceBundle;

import edu.nju.git.ui.control.FunctionPanel;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * web engine to show url of the repository or usr
 * @author cuihao
 * @date 2016-03-26 16:04:05
 */
public class WebViewFunction extends FunctionPanel{

	private String url = "";
	@FXML private AnchorPane pane;
	private WebView webView;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void initPanel(Object[] bundle) {
		url = (String) bundle[0];
//		ProgressIndicator progressIndicator = new ProgressIndicator();
//		LoadWebTask task = new LoadWebTask(url);
//		progressIndicator.progressProperty().bind(task.progressProperty());
//		progressIndicator.visibleProperty().bind(task.runningProperty());
//		progressIndicator.setPrefSize(150, 150);
//		pane.getChildren().add(progressIndicator);
//		new Thread(task).start();
		webView = new WebView();
		webView.setPrefSize(870, 640);	
		webView.requestFocus();
		pane.getChildren().add(webView);
		final WebEngine webEngine = webView.getEngine();
		webEngine.load(url);
	}

	@Override
	public void setChildren(Parent region) {
	}

	@Override
	public void removeChild() {
	}

	@Override
	public String getLocationName() {
		return "web view";
	}
	
	class LoadWebTask extends Task<Void> {

		private String url;
		
		public LoadWebTask(String url) {
			this.url = url;
		}
		
		@Override
		protected Void call() throws Exception {
			webView = new WebView();
			webView.setPrefSize(870, 640);	
			webView.requestFocus();
			pane.getChildren().add(webView);
			final WebEngine webEngine = webView.getEngine();
			webEngine.load(url);
			return null;
		}
		
	}

}

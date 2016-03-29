package edu.nju.git.ui.control.function;

import java.net.URL;
import java.util.ResourceBundle;

import edu.nju.git.ui.control.FunctionPanel;
import edu.nju.git.ui.control.GitPanel;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * web engine to show url of the repository or usr
 * @author cuihao
 * @date 2016-03-26 16:04:05
 */
public class WebViewFunction extends FunctionPanel{

	@FXML private VBox vbox;
	private String url = "";
	@FXML private WebView webView;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void initPanel(Object[] bundle) {
		url = (String) bundle[0];
		final WebEngine webEngine = webView.getEngine();
		webEngine.load(url);
	}

	@Override
	public void setChildren(Parent region) {
	}

	@Override
	public void removeChild() {
	}

}

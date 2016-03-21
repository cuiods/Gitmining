package edu.nju.git.ui.css;

import java.net.URL;

import edu.nju.git.main.Main;
import edu.nju.git.ui.config.StringReader;

public class DefaultCSSFactory extends CSSFactory{
	@Override
	public String getIndexCSS() {
		URL urlcss = Main.class.getResource(StringReader.readPath("css")+"index.css");
		return urlcss.toString();
	}

	@Override
	public String getTaskCSS() {
		URL urlcss = Main.class.getResource(StringReader.readPath("css")+"task.css");
		return urlcss.toString();
	}

	@Override
	public String getFunctionHome() {
		URL urlcss = Main.class.getResource(StringReader.readPath("css")+"function.css");
		return urlcss.toString();
	}

}
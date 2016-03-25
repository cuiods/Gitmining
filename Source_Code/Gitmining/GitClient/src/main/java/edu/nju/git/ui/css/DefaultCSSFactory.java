package edu.nju.git.ui.css;

import java.net.URL;

import edu.nju.git.main.Main;
import edu.nju.git.ui.config.StringReader;

/**
 * Default cascading style sheets.
 * @author cuihao
 * @date 2016-03-23 09:01:35
 */
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

	@Override
	public String getFunctionRepoList() {
		URL urlcss = Main.class.getResource(StringReader.readPath("css")+"function_repolist.css");
		return urlcss.toString();
	}

	@Override
	public String getFunctionRepoTableLabel() {
		URL urlcss = Main.class.getResource(StringReader.readPath("css")+"RepotableLabel.css");
		return urlcss.toString();
	}

	@Override
	public String getFunctionUserTableLabel() {
		URL urlcss = Main.class.getResource(StringReader.readPath("css")+"RepotableLabel.css");
		return urlcss.toString();
	}

	@Override
	public String getDisplayShelf() {
		URL urlcss = Main.class.getResource(StringReader.readPath("css")+"DisplayShelf.css");
		return urlcss.toString();
	}

	

}

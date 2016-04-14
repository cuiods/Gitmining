package edu.nju.git.ui.css;

import java.net.URL;

import edu.nju.git.main.Main;
import edu.nju.git.ui.config.StringReader;

public class OldCSSFactory extends CSSFactory{

	@Override
	public String getIndexCSS() {
		URL urlcss = Main.class.getResource(StringReader.readPath("css")+"back_up/index.css");
		return urlcss.toString();
	}

	@Override
	public String getTaskCSS() {
		URL urlcss = Main.class.getResource(StringReader.readPath("css")+"back_up/task.css");
		return urlcss.toString();
	}

	@Override
	public String getFunctionHome() {
		URL urlcss = Main.class.getResource(StringReader.readPath("css")+"back_up/function.css");
		return urlcss.toString();
	}

	@Override
	public String getDisplayShelf() {
		URL urlcss = Main.class.getResource(StringReader.readPath("css")+"back_up/DisplayShelf.css");
		return urlcss.toString();
	}

	@Override
	public String getFunctionRepoList() {
		URL urlcss = Main.class.getResource(StringReader.readPath("css")+"back_up/function_repolist.css");
		return urlcss.toString();
	}

	@Override
	public String getFunctionRepoTableLabel() {
		URL urlcss = Main.class.getResource(StringReader.readPath("css")+"back_up/RepotableLabel.css");
		return urlcss.toString();
	}

	@Override
	public String getFunctionUserTableLabel() {
		URL urlcss = Main.class.getResource(StringReader.readPath("css")+"back_up/RepotableLabel.css");
		return urlcss.toString();
	}

	@Override
	public String getFunctionUserDetail() {
		URL urlcss = Main.class.getResource(StringReader.readPath("css")+"back_up/function_detail.css");
		return urlcss.toString();
	}

}

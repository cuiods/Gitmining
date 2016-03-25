package edu.nju.git.ui.control;

import edu.nju.git.ui.css.CSSFactory;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Region;

/**
 * <h1>Controller of a parent element read from {@link FXMLLoader}</h1>
 * <p><b>Instruction:</b><br>
 * Every time you add a panel, you need to write a {@code class} extends this<br>
 * {@code abstract class} and implements these {@code methods} below.</p>
 * @author cuihao
 * @see #initPanel(Object[])
 * @see #removeChild()
 * @date 2016-03-04 20:27:51
 */
public abstract class GitPanel implements Initializable{
	private CSSFactory cssFactory;
	/**
	 * In this method, you need to play animation of the panel and <br>
	 * use data from last panel.
	 * @param bundle
	 * 			data from last panel:Object[]<br>
	 * 			<b>Need to check its class type.</b>
	 */
	public abstract void initPanel(Object[] bundle);
	/**
	 * Every panel has only <b>one</b> main child panel.<br>
	 * set it.
	 * @param region
	 */
	public abstract void setChildren(Parent region);
	/**
	 * remove the panel's <b>main</b> child node.
	 */
	public abstract void removeChild();
	public CSSFactory getCssFactory() {
		return cssFactory;
	}

	/**
	 * set CSS module
	 * @param cssFactory
     */
	public void setCssFactory(CSSFactory cssFactory) {
		this.cssFactory = cssFactory;
	}
}

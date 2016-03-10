package edu.nju.git.ui.components;

import java.awt.Font;

import javax.swing.JLabel;

import org.dom4j.Element;

import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.text.MySetText;

/**
 *@author tj
 *@date 2015年12月4日
 */
public class MyLabel extends JLabel {

	private static final long serialVersionUID = -6759882662343607401L;
	private int x;
	private int y;
	private int h;
	private int w;
	public MyLabel(String text){
		this.setText(text);
		setFont(new Font("微软雅黑", Font.PLAIN, 20));
	}
	public MyLabel(Element text, UIController controller){
		w = Integer.parseInt(text.attributeValue("w"));
		h = Integer.parseInt(text.attributeValue("h"));
		x = Integer.parseInt(text.attributeValue("x"));
		y = Integer.parseInt(text.attributeValue("y"));
		setBounds(x, y, w, h);
		setFont(new Font("微软雅黑", Font.PLAIN, 20));
		MySetText settext = new MySetText(text, controller);
		setText(settext.getText());
	}
}

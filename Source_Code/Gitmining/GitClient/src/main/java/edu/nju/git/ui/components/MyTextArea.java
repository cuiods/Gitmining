package edu.nju.git.ui.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

import org.dom4j.Element;

import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.text.MySetText;
/*
 * used to show the information
 * @author yyy
 * @date 2016-03-10 19:45
 */
public class MyTextArea extends JTextArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int h;
	private int w;
	public MyTextArea(Element text,UIController controller){
		w = Integer.parseInt(text.attributeValue("w"));
		h = Integer.parseInt(text.attributeValue("h"));
		x = Integer.parseInt(text.attributeValue("x"));
		y = Integer.parseInt(text.attributeValue("y"));
		setBounds(x, y, w, h);
		setFont(new Font("微软雅黑", Font.PLAIN, 16));
		MySetText setText = new MySetText(text,controller);
		setText(setText.getText());
		this.setForeground(Color.white);
		this.setLineWrap(true);
		this.setOpaque(false);
		this.setEditable(false);
	}
}

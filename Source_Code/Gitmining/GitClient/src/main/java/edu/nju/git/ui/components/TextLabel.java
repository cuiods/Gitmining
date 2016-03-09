package edu.nju.git.ui.components;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.dom4j.Element;

import edu.nju.git.ui.controller.UIController;
/*
 * used to add a textfield to show the current page
 * @author yyy
 * @date 2016-03-09 20:44
 */
public class TextLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Element element;
	private UIController controller;
	private int w;
	private int h;
	private int x;
	private int y;
	
	public TextLabel(Element element,UIController controller){
		this.element = element;
		this.controller = controller;
		setLayout(null);
		w = Integer.parseInt(element.attributeValue("w"));
		h = Integer.parseInt(element.attributeValue("h"));
		x = Integer.parseInt(element.attributeValue("x"));
		y = Integer.parseInt(element.attributeValue("y"));
		setBounds(x, y, w, h);
		getPageNum();
	}
	
	public void getPageNum(){
		JTextField text = new JTextField();
		String pageNum = "";
		if(element.attributeValue("name").contains("USER")){
			pageNum = ""+controller.getUserController().getCurrentPage();
		}else{
			pageNum =""+controller.getReposController().getCurrentPage();
		}
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setFont(new Font("微软雅黑",Font.PLAIN,16));
		text.setBounds(0, 0, w, h);
		text.setEditable(false);
		add(text);
		
	
	}
	
}

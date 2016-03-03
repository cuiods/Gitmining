package edu.nju.git.ui.components;

import java.awt.Font;

import javax.swing.JComboBox;

import org.dom4j.Element;

import edu.nju.git.ui.controller.UIController;

/**
 * My ComboBox
 * @author cuihao
 * @date 2015-11-26 19:44:07
 * @param <T> items in the ComBobox
 */
public class MyComboBox<T> extends JComboBox<T> {

	private static final long serialVersionUID = 3980256366114323391L;
	Element element;

	public MyComboBox(Element element, UIController controller) {
		super();
		this.element = element;
		setOpaque(false);
		if(element!=null){
			int w = Integer.parseInt(element.attributeValue("w"));
			int h = Integer.parseInt(element.attributeValue("h"));
			int x = Integer.parseInt(element.attributeValue("x"));
			int y = Integer.parseInt(element.attributeValue("y"));
			
			setBounds(x, y, w, h);
		}
		setUI(new MyComboBoxUI());
		setRenderer(new MyComboBoxRender<T>());
		setFont(new Font("微软雅黑", Font.PLAIN, 16));
		setEditable(false);
		addMyItems();
	}

	public MyComboBox() {
		this(null, null);
	}

	@SuppressWarnings("unchecked")
	public void addMyItems() {
		if(element!=null){
			String temp = element.attributeValue("items");
			String[] items = temp.split(";");
			for (int i = 0; i < items.length; i++) {
				addItem((T) items[i]);
			}
		}
	}
	
//	public static void main(String[] args){
//		JFrame frame = new JFrame("test");
//		frame.setTitle("ScrollBar Demo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(180, 70);
//        frame.setAlwaysOnTop(true);
//        MyCombox<String> s = new MyCombox<String>();
//        s.addItem("总经理");
//        s.addItem("财务人员");
//        frame.getContentPane().add(s);
//        frame.setVisible(true);
//	}

}

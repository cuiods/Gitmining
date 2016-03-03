package edu.nju.git.ui.text;

import java.lang.reflect.Constructor;

import org.dom4j.Element;

import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.frame.MainPanel;

/**
 * 
 * @author cuihao
 *
 */
public class MySetText {
	String textClass = "";
	UIController controller;

	public MySetText(Element element, UIController controller) {
		if(element!=null)
		textClass = element.attributeValue("textType");
		this.controller = controller;
	}

	public String getText() {
		if (textClass == null || textClass.isEmpty() || textClass.equals("null")) {
			return "";
		}
		Text text = null;
		try {
			Class<?> textC = Class.forName(MainPanel.packageName + "setText." + textClass);
			Constructor<?> ctr = textC.getConstructor();
			text = (Text) ctr.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return text.get(controller);
	}
	/**
	 * 审批单据专用函数
	 * @param id
	 * @param type
	 * @return String
	 */
	public String getListText(String id, String type) {
		char c = type.charAt(0);
		String s = type.toLowerCase().substring(1);
		textClass = c + s + "Text";
		Text text = null;
		try {
			Class<?> textC = Class.forName(MainPanel.packageName + "setText." + textClass);
			Constructor<?> ctr = textC.getConstructor(String.class);
			text = (Text) ctr.newInstance(id);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return text.get(controller);
	}
}

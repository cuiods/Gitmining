package edu.nju.git.ui.components;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.dom4j.Element;

import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.frame.MainPanel;

/**
 * my Scroll pane
 * @author cuihao
 * @date 2015-11-25 19:23:54
 * @param <T> components in the scroll pane
 */
public class MyScrollPane<T> extends JScrollPane{

	private static final long serialVersionUID = -2221225508091244689L;
	private JList<T> list = new JList<T>();
	private DefaultListModel<T> model = new DefaultListModel<T>();
	protected Element element;
	protected UIController controller;

	/**
	 * Main constructor used in {@link MainPanel}
	 * @param element
	 * @param controller
	 */
	public MyScrollPane(Element element, UIController controller) {
		getVerticalScrollBar().setUI(new MyScrollPaneUI());
		this.element = element;
		this.controller = controller;
		setBounds(Integer.parseInt(element.attributeValue("x")), Integer.parseInt(element.attributeValue("y")),
				Integer.parseInt(element.attributeValue("w")), Integer.parseInt(element.attributeValue("h")));
	}
	
	/**
	 * default model: list
	 */
	public MyScrollPane(){
		list.setModel(model);
        getViewport().setView(list);
        getVerticalScrollBar().setUI(new MyScrollPaneUI());
	}
	
	/**
	 * The scroll pane will use data in the JList
	 * @param list
	 */
	public MyScrollPane(JList<T> list){
		this.list = list;
        getViewport().setView(list);
        getVerticalScrollBar().setUI(new MyScrollPaneUI());
	}
		
	public JList<T> getList() {
		return list;
	}

	public void setList(JList<T> list) {
		this.list = list;
	}
	
	public void addItem(T x){
		model.addElement(x);
	}
	
	public void removeItem(T x){
		model.removeElement(x);
	}
	
	public void removeItem(int i){
		model.remove(i);
	}
	
}

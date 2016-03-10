package edu.nju.git.ui.components.table;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.ScrollPaneConstants;

import org.dom4j.Element;

import edu.nju.git.type.SortType;
import edu.nju.git.ui.components.MyScrollPane;
import edu.nju.git.ui.components.MyScrollPaneUI;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.listener.LabelListener;

/**
 * When create a table, you need to override two methods to initialize data For<br>
 * example, when create a personnelTable,you need to create<br>
 * {@link PersonnelTable} and override <b>getData</b> method and <b>deleteData</b> method.<br>
 * <br>
 * When override addData method, you can use method <b>addData(MyTableLabel)</b><br>
 * to add a label to the table
 * 
 * @author cuihao
 * @see MyTableLabel
 * @see #addData(MyTableLabel)
 * @date 2015-11-29 23:36:18
 */
public abstract class MyTable extends MyScrollPane<MyTableLabel> implements MouseListener {

	private static final long serialVersionUID = -6295339660809296638L;

	/**
	 * list of {@link MyTableLabel} to be shown
	 */
	private ArrayList<MyTableLabel> data = new ArrayList<MyTableLabel>();

	private JList<MyTableLabel> list = new JList<MyTableLabel>();

	public MyTable(Element element, UIController controller) {
		super(element, controller);
		addMouseListener(this);
		getData();
		setView();
		getVerticalScrollBar().setUI(new MyScrollPaneUI());
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	}

	/**
	 * add a row to the table
	 * 
	 * @param label:
	 *            MyTableLabel
	 */
	protected void addData(MyTableLabel label) {
		data.add(label);
	}

	/**
	 * reset location of table labels
	 */
	protected void setView() {
		int width = 0;
		int height = 0;
		for (int i = 0; i < data.size(); i++) {
			MyTableLabel label = data.get(i);
//			MyTableLabel.pathBit =0;
			width = label.getWidth();
			height = label.getHeight();
			label.setBounds(0, i * height, width, height);
			if (i % 2 != 0){
//				MyTableLabel.pathBit = 1;
				label.setPicPath("src/main/resources/pictures/grey.png");
				MouseListener listener[] = label.getMouseListeners();
				LabelListener labelLis =(LabelListener) listener[0];
				labelLis.setPicPath("src/main/resources/pictures/grey.png");
				label.repaint();
			}
			data.set(i, label);
			list.add(label);
		}
		list.setPreferredSize(new Dimension(width, height * data.size()));
		list.setOpaque(false);
		getViewport().setView(list);
	}

	/**
	 * initialize ArrayList<MyTableLabel> data
	 * 
	 * @see# addData
	 */
	protected abstract void getData();
//	/*
//	 * sort the data in the table
//	 */
//	public abstract void sortTable(SortType type,boolean reverse);
	/**
	 * delete data using business logic methods.
	 * @return ResultMessage
	 */
	//protected abstract ResultMessage deleteData(MyTableLabel label);
	
	/**
	 * remove label in the table
	 * @param component: {@link MyTableLabel}
	 */
	public void my_remove(MyTableLabel component) {
		list.removeAll();
		data.remove(component);
		setView();
		controller.getFrame().repaint();
	}

	/**
	 * remove label in the table by index
	 * @param index
	 */
	public void my_remove(int index) {
		list.removeAll();
		data.remove(index);
		setView();
		controller.getFrame().repaint();
	}
	
	/**
	 * set data list of the table
	 * @param labels
	 */
	public void setDataList(ArrayList<MyTableLabel> labels) {
		this.data = labels;
		list.removeAll();
		setView();
		controller.getFrame().repaint();
	}

	/**
	 * add a label
	 * @param label
	 */
	public void myAddLabel(MyTableLabel label) {
		if (label != null) {
			data.add(label);
			list.removeAll();
			setView();
			controller.getFrame().repaint();
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		controller.getFrame().repaint();
	}

	public void mouseEntered(MouseEvent e) {
		controller.getFrame().repaint();
	}

	public void mouseExited(MouseEvent e) {
		controller.getFrame().repaint();
	}

	public void mousePressed(MouseEvent e) {
		controller.getFrame().repaint();
	}

	public void mouseReleased(MouseEvent e) {
		controller.getFrame().repaint();
	}

	/**
	 * get data list
	 * @return ArrayList<MyTableLabel>
	 */
	public ArrayList<MyTableLabel> getDataList() {
		return data;
	}

}

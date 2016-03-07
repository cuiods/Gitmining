package edu.nju.git.ui.components.table;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.dom4j.Element;

import edu.nju.git.ui.components.MainButton;
import edu.nju.git.ui.components.MyCheckBox;
import edu.nju.git.ui.components.MyScrollPane;
import edu.nju.git.ui.components.MyTextField;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.listener.LabelListener;

/**
 * General table label a table is composed of a {@link MyTable} extends
 * {@link MyScrollPane} and many table labels, this is a general table label
 * including {@link MyCheckBox} , {@link MyTextField}, and {@link MainButton}
 * 
 * @author cuihao
 * @date 2015-11-29 23:23:49
 */
public class MyTableLabel extends JLabel  {
	private UIController controller;
	private static final long serialVersionUID = 420418378988671847L;

	/**
	 * width of the label
	 */
	private int width;

	/**
	 * height of the label
	 */
	private int height;

	/**
	 * the label includes many components including {@link MyCheckBox}
	 * {@link MyTextField}, and {@link MainButton}
	 */
	private java.awt.Component[] components;

	/**
	 * path of picture background (white default)
	 */
	private String picPath = "src/main/resources/pictures/white.png";

	/**
	 * delete button of the label
	 */
	MainButton deleteButton = null;

	/**
	 * edit button of the label
	 */
	MainButton editButton = null;

	/**
	 * the color of the label
	 */
	public static boolean isWhite;

	/**
	 * a reference used in button listener
	 * 
	 * @warning table might be uninitialized
	 */
	private MyTable table;
	/*
	 * 
	 */
	protected Element element;

	/**
	 * @param element
	 *            of the <b>table</b>
	 * @param height
	 *            of this label
	 * @param components
	 *            this label, must be components
	 * @param table
	 *            which the label belongs to. This parameter is not a good
	 *            design
	 */
	public MyTableLabel(Element element, UIController controller, int height, java.awt.Component[] components,
			MyTable table) {
		setLayout(null);
		width = Integer.parseInt(element.attributeValue("w"));
		this.height = height;
		this.components = components;
		this.table = table;
		this.controller = controller;
		this.element = element;
		setPreferredSize(new Dimension(width, height));
		initializeComponents();
		if(element.attributeValue("class").equals("table.ListTable"))
		this.addMouseListener(new LabelListener(this,controller));
	}

	/**
	 * add check box and button additionally
	 */
	private void initializeComponents() {
		int column = (int) (width / (components.length + 1.5));
		/**
		 * add check box
		 */
//		MyCheckBox check = new MyCheckBox("");
		int checkWidth = column / 2;
//		check.setBounds((checkWidth-25)/2, (height-17)/2, 25, 17);
//		add(check);
		/**
		 * add data components
		 */
		int before = checkWidth;
		for (int i = 0; i < components.length; i++) {
			java.awt.Component component = components[i];
			component.setFont(new Font("微软雅黑",Font.PLAIN,15));
			component.setBounds(before, (height-component.getHeight())/2, component.getWidth() , component.getHeight());
			before+=component.getWidth()+10;
			add(component);
		}
		/**
		 * add delete button and edit button
		 */
//		deleteButton = new MainButton("tabledelete");
//		deleteButton.setBounds(checkWidth + components.length * column+(checkWidth-height*3/8)/2, height*5/16+height/30, height*3/8,height*3/8);
//		deleteButton.setVisible(true);
//		add(deleteButton);
//		editButton = new MainButton("change");
//		editButton.setBounds(checkWidth + components.length * column + (checkWidth-height / 4)*3/2, height / 4, height / 2 ,
//				height / 2);
//		editButton.setVisible(true);
//		add(editButton);
//		editButton.addMouseListener(new TableEditListener(editButton));
//		deleteButton.addMouseListener(new tableDeleteListener(table, this, deleteButton));
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon(picPath).getImage(), 0, 0, this.getWidth(), this.getHeight() + 20, this);
	}

	/**
	 * get component in the label
	 * 
	 * @param index
	 * @return java.awt.component
	 */
	public java.awt.Component getComponents(int index) {
		return components[index];
	}

	/**
	 * get column of the component
	 * 
	 * @return length of components
	 */
	public int getColumnNum() {
		return components.length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void changeGrey() {
		picPath = "src/main/resources/pictures/grey.png";
	}

	public void changeWhite() {
		picPath = "src/main/resources/pictures/white.png";
	}
	
	public String getPicPath(){
		return this.picPath;
	}
	
	public void setPicPath(String picPath){
		this.picPath = picPath;
	}
	
	public Element getElement(){
		return this.element;
	}
	
	/**
	 * delete button listener
	 * 
	 * @author cuihao
	 * @date 2015-11-29 23:35:13
	 */
	class tableDeleteListener implements MouseListener {
		MyTable table;
		MyTableLabel label;
		MainButton button;

		public tableDeleteListener(MyTable table, MyTableLabel label, MainButton button) {
			this.table = table;
			this.label = label;
			this.button = button;
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
			button.setIn(true);
			button.repaint();
		}

		public void mouseExited(MouseEvent e) {
			button.setIn(false);
			button.repaint();
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
//			ResultMessage result = table.deleteData(label);
//			if (result != null) {
//				if (result.isSuccess()) {
//					table.my_remove(label);
//					new MyDialog("删除成功！", true,controller);
//				} else {
//					new MyDialog(result.getErrorMessage(), true,controller);
//				}
//			}
			table.repaint();
		}

	}

	/**
	 * Table edit listener If the editButton is clicked, then the components of
	 * this label is <b>Editable</b>
	 * 
	 * @author cuihao
	 * @date 2015-11-30 09:00:01
	 */
	class TableEditListener implements MouseListener {

		MainButton button;

		public TableEditListener(MainButton button) {
			this.button = button;
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
			button.setIn(true);
			button.repaint();
		}

		public void mouseExited(MouseEvent e) {
			button.setIn(false);
			button.repaint();
		}

		public void mousePressed(MouseEvent e) {
		}

		/**
		 * set editable
		 */
		public void mouseReleased(MouseEvent e) {
			boolean focus = false;
			for (int i = 0; i < getColumnNum(); i++) {
				java.awt.Component c = components[i];
				if (c instanceof MyTextField) {
					MyTextField text = (MyTextField) c;
					text.setEditable(true);
					if (!focus) {
						text.requestFocus();
						focus = true;
					}
				}
			}
		}
	}

	

}

package edu.nju.git.ui.components;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

/**
 * my check box
 * @author cuihao
 * @date 2015-11-29 23:55:33
 */
public class MyCheckBox extends JCheckBox{
	
	private static final long serialVersionUID = -8600958515428523132L;
	
	private static final String PIC_PLAIN = "src/main/resources/pictures/units/checkBox.png";
	private static final String PIC_SELECTED = "src/main/resources/pictures/units/checkBox1.png";
	
	public MyCheckBox() {
		this("",new ImageIcon(PIC_PLAIN), false);
	}
	
	public MyCheckBox(String s) {
		this(s, new ImageIcon(PIC_PLAIN), false);
	}
	
	public MyCheckBox(String s, ImageIcon icon, boolean isSelected) {
		super(s, icon, isSelected);
		addItemListener(new MyCheckBoxListener());
		setOpaque(false);
	}
	
	class MyCheckBoxListener implements ItemListener {

		public void itemStateChanged(ItemEvent e) {
			JCheckBox jcb = (JCheckBox) e.getItem();
			{
				if (jcb.isSelected()) {
					jcb.setIcon(new ImageIcon(PIC_SELECTED));
				} else
					jcb.setIcon(new ImageIcon(PIC_PLAIN));
			}
		}
	}
}


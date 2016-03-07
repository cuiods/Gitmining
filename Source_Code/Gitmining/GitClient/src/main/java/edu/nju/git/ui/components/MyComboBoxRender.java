package edu.nju.git.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * 
 * @author cuihao
 *
 * @param <T>
 */
public class MyComboBoxRender<T> implements ListCellRenderer<T>{
	
	private DefaultListCellRenderer render = new DefaultListCellRenderer();
	
	public MyComboBoxRender() {
		super();
	}

	public Component getListCellRendererComponent(JList<? extends T> list, T value, int index, boolean isSelected,
			boolean cellHasFocus) {
		JLabel renderer = (JLabel) render.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (isSelected) {
			renderer.setBackground(Color.LIGHT_GRAY);
			renderer.setForeground(Color.white);
		} else {
			renderer.setBackground(Color.white);
		}
		list.setSelectionBackground(Color.LIGHT_GRAY);
		list.setBorder(null);
		renderer.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		renderer.setHorizontalAlignment(JLabel.CENTER);
		return renderer;
	}

}

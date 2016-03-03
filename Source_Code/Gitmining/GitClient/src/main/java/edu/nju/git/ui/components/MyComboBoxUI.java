package edu.nju.git.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

/**
 * my ComBobox UI
 * @author cuihao
 * @date 2015-11-26 19:41:51
 */
public class MyComboBoxUI extends BasicComboBoxUI{
	/**
	 * scroll button
	 */
	private JButton button;
	/**
	 * something I don't know.
	 */
	private boolean boundsLight = false;
	/**
	 * font size
	 */
	private int fontSize = 16;
	/**
	 * font
	 */
	private String font = "微软雅黑";
	
	/**
	 * ComboBox button picture
	 */
	private final String PIC_NORMAL = "src/main/resources/pictures/units/combobutton.png";
	private final String PIC_INTO = "src/main/resources/pictures/units/combobutton1.png";
	public MyComboBoxUI() {
		super();
	}
	
	/**
	 * get button
	 */
	@Override
	protected JButton createArrowButton(){
		button = new JButton();
		button.setIcon(new ImageIcon(PIC_NORMAL));
		button.setRolloverEnabled(true);
		button.setRolloverIcon(new ImageIcon(PIC_INTO));
		//button.setRolloverSelectedIcon(new ImageIcon(PIC_CLICKED));
		button.setBorder(null);
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		return button;
	}
	
	/**
	 * paint button
	 */
	public void paint(Graphics g, JComponent c) {
		hasFocus = comboBox.hasFocus();
		Graphics2D g2 = (Graphics2D) g;
		if (!comboBox.isEditable()) {
			Rectangle r = rectangleForCurrentValue();
			paintCurrentValueBackground(g2, r, hasFocus);
			paintCurrentValue(g2, r, hasFocus);
		}
//		if (comboBox.isPopupVisible()) {
//			button.setIcon(new ImageIcon(PIC_INTO));
//		} else {
//			button.setIcon(new ImageIcon(PIC_NORMAL));
//		}
	}
	
	/**
	 * paint text
	 */
	public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
//		Font oldFont = comboBox.getFont();
//		comboBox.setFont(new Font(font, Font.PLAIN, fontSize));

		super.paintCurrentValue(g, bounds, hasFocus);
//		comboBox.setFont(oldFont);
	}

	public Dimension getPreferredSize(JComponent c) {
		return super.getPreferredSize(c);
	}

	public boolean isBoundsLight() {
		return boundsLight;
	}

	public void setBoundsLight(boolean boundsLight) {
		this.boundsLight = boundsLight;
	}

	/**
	 * create scroll pane
	 */
	protected ComboPopup createPopup() {
		ComboPopup popup = new BasicComboPopup(comboBox) {
			private static final long serialVersionUID = 7869205825443338959L;

			protected JScrollPane createScroller() {
				@SuppressWarnings("unchecked")
				MyScrollPane<String> sp = new MyScrollPane<String>(list);
				sp.setFont(new Font(font, Font.PLAIN, fontSize));
				sp.setHorizontalScrollBar(null);
				return sp;
			}

			/**
			 * paint border
			 */
			public void paintBorder(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.DARK_GRAY);
				g2.drawRoundRect(0, -button.getHeight(), getWidth() - 1, getHeight() + button.getHeight() - 1, 0, 0);
			}
		};
		return popup;
	}
}

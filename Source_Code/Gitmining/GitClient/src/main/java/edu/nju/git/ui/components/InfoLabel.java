package edu.nju.git.ui.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
/*
 * used for show information in tables
 * @author yyy
 * @date 2016-03-09 23:19
 */
public class InfoLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InfoLabel(String info){
		super(info);
		this.setHorizontalAlignment(JLabel.CENTER);
		setFont(new Font("微软雅黑",Font.PLAIN,16));
		this.setForeground(Color.white);
	}
}

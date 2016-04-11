package org.GitServer.ui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

public class Window extends JFrame {

	private static final long serialVersionUID = -1299364480784890173L;
	private JPanel contentPane;
	private JLabel loading;
	private JLabel lblGitmining;


	public Window() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		lblGitmining = new JLabel("loading……");
		lblGitmining.setFont(new Font("楷体", Font.PLAIN, 40));
		lblGitmining.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblGitmining, BorderLayout.SOUTH);
		
		loading = new JLabel(new ImageIcon("pic"+File.separator+"loading.gif"));
		contentPane.add(loading, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void setDone(){
		loading.setIcon(new ImageIcon("pic"+File.separator+"done.jpg"));
		lblGitmining.setText("data loading,done.");
	}
}

package edu.nju.git.ui.frame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.nju.git.ui.components.MainButton;
import edu.nju.git.ui.config.ConfigReader;
import edu.nju.git.ui.config.FrameConfig;



/**
 * A ConfirmFrame is a Dialog requiring user to choose.
 * Use method <b>isConfirm</b> to get what user chooses.<br>
 * For example:<br>
 * 	 &nbsp&nbsp&nbsp&nbsp ConfirmFrame frame = new ConfirmFrame(ConfirmFrame.DELETE);<br>
 *	 &nbsp&nbsp&nbsp&nbsp System.out.println(frame.isConfirm());
 * @author cuihao
 * @date 2015-11-30 22:14:23
 * @see# isConfirm
 */
public class ConfirmFrame extends MainFrame {
	
	private static final long serialVersionUID = -7548510958161064852L;
	
	/**
	 * This is <b>a picture path</b> indicating what need to be confirmed
	 * Usually using ConfirmFrame
	 */
	private String message;
	/**
	 * width of frame
	 */
	private int width;
	/**
	 * height of frame
	 */
	private int height;
	
	private JPanel panel;
	private boolean isClicked = false;
	private boolean isConfirm = false;
	private MainButton confirm;
	private MainButton cancel;
	
	public static final String DELETE = "pictures/units/deleteConfirm.png";
	public static final String SAVE = "pictures/units/saveConfirm.png";
	
	public ConfirmFrame() {
		this("");
	}
	
	public ConfirmFrame(String message) {
		super();
		this.message = message;
		initializeFrame();
		initializePanel();
		initializeLabel();
		initializeButton();
	}
	
	/**
	 * This is a method returns whether user confirmed.
	 * @return boolean
	 */
	public boolean isConfirm(){
		while(!isClicked){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		this.dispose();
		return isConfirm;
	}
	
	private void initializeFrame() {
		ConfigReader reader = new ConfigReader();
		FrameConfig config = reader.readFrame("ConfirmFrame");
		width = config.getWidth();
		height = config.getHeight();
		setResizable(false);
		setSize(width,height);
		setAutoRequestFocus(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - width) / 2,(screenSize.height-height)/2);
		setUndecorated(true);
		setVisible(true);
	}
	
	private void initializePanel() {
		panel = new JPanel(){
			private static final long serialVersionUID = 6241281467240637572L;
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(new ImageIcon("pictures/confirmFrame.png").getImage(),0,0, getWidth(), getHeight(), this);
			}
		};
		panel.setBounds(0, 0, getWidth(), getHeight());
		getContentPane().add(panel);
		panel.setLayout(null);
	}
	
	private void initializeButton() {
		confirm = new MainButton("confirm");
		confirm.setBounds(55, 165, 112, 34);
		confirm.setVisible(true);
		confirm.addMouseListener(new ConfirmDialogListener());
		cancel = new MainButton("cancel");
		cancel.setBounds(195, 165, 112, 34);
		cancel.setVisible(true);
		cancel.addMouseListener(new CancelDialogListener());
		panel.add(confirm);
		panel.add(cancel);
	}
	
	private void initializeLabel() {
		JLabel label = new JLabel(){

			private static final long serialVersionUID = 565241279742922849L;

			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(new ImageIcon(message).getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		label.setBounds(0, 0, getWidth(), getHeight()-60);
		panel.add(label);
	}
	
	/**
	 * listener of confirm button
	 * @author cuihao
	 *
	 */
	class ConfirmDialogListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {}

		public void mouseEntered(MouseEvent e) {
			confirm.setIn(true);
			confirm.repaint();
		}

		public void mouseExited(MouseEvent e) {
			confirm.setIn(false);
			confirm.repaint();
		}

		public void mousePressed(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {
			isClicked = true;
			isConfirm = true;
		}
		
	}
	
	/**
	 * listener of cancel button
	 * @author cuihao
	 *
	 */
	class CancelDialogListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {
			cancel.setIn(true);
			cancel.repaint();
		}

		public void mouseExited(MouseEvent e) {
			cancel.setIn(false);
			cancel.repaint();
		}

		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {
			isClicked = true;
			isConfirm = false;
		}
	}
	
	public static void main(String[] args) {
		ConfirmFrame frame = new ConfirmFrame(ConfirmFrame.DELETE);
		//System.out.println(frame.isConfirm());
	}
}

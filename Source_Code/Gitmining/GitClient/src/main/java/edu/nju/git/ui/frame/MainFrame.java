package edu.nju.git.ui.frame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import edu.nju.git.ui.config.ConfigReader;
import edu.nju.git.ui.config.FrameConfig;
import edu.nju.git.ui.config.PanelConfig;
import edu.nju.git.ui.controller.UIController;

/**
 * Main frame of client
 * @author tj cuihao
 * @date 2015-11-18 14:34:59
 */
public class MainFrame extends JFrame{

	private static final long serialVersionUID = 3444411286661024461L;
	
	/**
	 * configure of the frame
	 */
	private FrameConfig config;
	private String panelName;
	/**
	 * panel in the frame
	 */
	private MainPanel panel;
	/**
	 * control fading in and fading out
	 */
	private static Timer timer;
	private static float opaque = 1.0f;
	
	private int winX,winY;
	private int oldX,oldY;
	private int x,y;
	private int tempX,tempY;
	public MainFrame() {
		initialize();
	}
	public MainFrame(String panelName) {
		this.panelName = panelName;
		initialize();
	}
	/**
	 * Change the panel in the frame
	 * @param panel to change
	 * @param UIController
	 */
	public void changeTo(String panel,UIController controller) {
		if(panel.equals(panelName)){
			return;
		}
		//read configure of the new panel
		ConfigReader reader = new ConfigReader();
		PanelConfig panelConfig = reader.readPanel(panel);
		//create new panel
		MainPanel mainPanel = new MainPanel(panelConfig,controller);
		setPanel(mainPanel);
		//remove old panel
		getContentPane().setVisible(false);
		//set new panel
		setContentPane(mainPanel);
		mainPanel.setVisible(true);
		mainPanel.requestFocus();
		repaint();
	}
	/**
	 * Fade out and then fade in.
	 * @param speed(0,1f) 0.02 recommended
	 * @param delay
	 */
	public void fadeTo(int delay,float speed,String panel,UIController controller) {
		assert speed > 0 && speed < 1;
		opaque = 1.0f;
		timer = new Timer(delay, new Fade(speed, this, panel, controller));
		timer.start();
	}
	public MainPanel getPanel() {
		return panel;
	}
	public void setPanel(MainPanel panel) {
		this.panel = panel;
	}
	
	/**
	 * initialize frame and listener
	 */
	private void initialize() { 
		//read configure
		ConfigReader reader = new ConfigReader();
		config = reader.readFrame("MainFrame");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(config.getWidth(),config.getHeight());
		//in the middle
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - config.getWidth()) / 2,(screenSize.height-config.getHeight())/2);
		setUndecorated(true);
		//drag listener
		MyListener listener = new MyListener();
		addMouseMotionListener(listener);
		addMouseListener(listener);
	}

	/**
	 * 
	 * frame drag listener
	 * @author tj
	 *
	 */
	class MyListener implements MouseListener,MouseMotionListener{

		public void mouseDragged(MouseEvent e) {
			Point point = e.getPoint();
			Rectangle rec = getBounds();
					
			winX = (int)rec.getX();
			winY = (int)rec.getY();		
			x = (int)point.getX();
			y = (int)point.getY();		
			tempX = x - oldX;
			tempY = y - oldY;		
			
			setLocation(winX + tempX, winY + tempY);	
		}
		public void mouseMoved(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {
			Point point = e.getPoint();		
			tempX = (int)point.getX();
			tempY = (int)point.getY();
			oldX = (int)point.getX();
			oldY = (int)point.getY();		
		}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}		
	}
	
	/**
	 * Fade in and fade out listner for timer
	 * @author cuihao
	 * @see Timer
	 */
	static class Fade implements ActionListener{
		private float speed = 0.0f;
		private boolean isFadeIn = false;
		private static MainFrame frame;
		private String panel;
		private UIController controller;
		public Fade(float speed,MainFrame frame,String panel,UIController controller) {
			opaque-=2*speed;
			this.speed = speed;
			Fade.frame = frame;
			this.panel = panel;
			this.controller = controller;
		}
		@SuppressWarnings("restriction")
		public void actionPerformed(ActionEvent e) {
			if(!isFadeIn){
				opaque-=speed;
			}else{
				opaque+=speed;
			}
			if(opaque<=speed){
				isFadeIn = true;
				new Runnable() {					
					public void run() {
						frame.changeTo(panel, controller);
					}
				}.run();
			}
			if(opaque>=(1.0f-speed)){
				SwingUtilities.invokeLater(new Runnable() {  
	                public void run() {  
	                    com.sun.awt.AWTUtilities.setWindowOpacity(Fade.frame, opaque);  
	                }  
	            });
				timer.stop();
			}
			SwingUtilities.invokeLater(new Runnable() {  
                public void run() {  
                    com.sun.awt.AWTUtilities.setWindowOpacity(Fade.frame, opaque);  
                }  
            });
		}
	}
	
	
}

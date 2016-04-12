package org.GitServer.ui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

public class Troy {

	private TrayIcon trayIcon;
	private SystemTray systemTray;
	private PopupMenu popupMenu;
	private String iconpath = "pic"+File.separator+"tray.gif";
	
	private final int WIDTH = 20;
	private final int HEIGHT = 20;
	private final String text = "GitMining";
	
	public void addMenu(String name,Consumer<Object> consumer){
		MenuItem menuItem = new MenuItem(name);
		popupMenu.add(menuItem);
		menuItem.addActionListener((e)->{consumer.accept(null);});
	}
	
	public void addAction(Consumer<Object> consumer){
		trayIcon.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if(MouseEvent.BUTTON1==e.getButton()){
					consumer.accept(null);
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
			
		});
	}
	
	public void creatTray(){
		if (SystemTray.isSupported()) { 
			
            popupMenu = new PopupMenu();  
            Image image ;
			try {
				image = ImageIO.read(new File(iconpath));
				 trayIcon = new TrayIcon(image.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT), text, popupMenu);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
           
			 systemTray = SystemTray.getSystemTray();  
	         try {
				systemTray.add(trayIcon);
			} catch (AWTException e) {
				e.printStackTrace();
			}  
			
		}
        
	}  
}

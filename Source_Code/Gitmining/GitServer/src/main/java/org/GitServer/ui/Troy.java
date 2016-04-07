package org.GitServer.ui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

public class Troy {

	private TrayIcon trayIcon;
	private SystemTray systemTray;
	private PopupMenu popupMenu;
	private String iconpath = "pic"+File.separator+"loading.gif";
	
	private final int WIDTH = 20;
	private final int HEIGHT = 20;
	private final String text = "GitMining";
	
	public void addMenu(String name,Consumer<Object> consumer){
		MenuItem menuItem = new MenuItem(name);
		popupMenu.add(menuItem);
		menuItem.addActionListener((e)->{consumer.accept(null);});
	}
	
	public void addMenu(){
		MenuItem restart = new MenuItem("restart");  
        MenuItem exit = new MenuItem("exit");  
        popupMenu.add(restart);  
        popupMenu.add(exit);  
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

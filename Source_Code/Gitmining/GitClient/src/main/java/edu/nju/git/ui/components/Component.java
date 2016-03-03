package edu.nju.git.ui.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

/** 
 *basic property of a single <b>picture</b>
 *last modified:2015-11-17 22:30:50
 *@author cuihao
 */
public class Component {
	private int x;
	private int y;
	private int width;
	private int height;
	private String path="src/main/resources/pictures/";
	/**
	 * <b>Picture</b> name
	 */
	private String name;
	private String className;
	public Component(int x,int y,int width,int height,String name,String className){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.name=name;
		this.className = className;
		path=path+name+".png";
	}
	
	/**
	 * Draw its own picture
	 * @param g
	 */
	public void createComponent(Graphics g){
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);
		Image image;
		try {
			image = ImageIO.read(new FileInputStream(path));
			g2D.drawImage(image, x, y, width,height,null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
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
	public void changePath(){
		path="pictures/"+name+"1"+".png";
	}
	public void setPath(){
		path="pictures/"+name+".png";
	}
	public void setPath(String path){
		this.path=path;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

}

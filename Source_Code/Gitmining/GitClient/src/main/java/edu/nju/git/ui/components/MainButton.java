package edu.nju.git.ui.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.dom4j.Element;

import edu.nju.git.ui.controller.UIController;

/**
 *@author tj
 *@date 2015年11月18日
 */
public class MainButton extends JLabel{

	private static final long serialVersionUID = 4647675998577572101L;
	protected int x;
	protected int y;
	protected int h;
	protected int w;
	private String name;
	private boolean isIn;
	private boolean isPressed;
	private boolean isExit;
	private boolean isClicked;//used for sort
	private Element element;
	private String picPath;
	public MainButton(Element button,UIController controller){
		w = Integer.parseInt(button.attributeValue("w"));
		h = Integer.parseInt(button.attributeValue("h"));
		x = Integer.parseInt(button.attributeValue("x"));
		y = Integer.parseInt(button.attributeValue("y"));
		name =button.attributeValue("name");
		element = button;
		picPath = "src/main/resources/pictures/buttons/"+name+".png";
		setBounds(x,y,w,h);
	}
	
	public MainButton(String name){
		this.name = name;
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);
		String path = picPath;
		String str = name;
		if(isIn){
			if(name.contains("guide")&&name.contains("3")) str = name.substring(0, name.length()-1);
			picPath = "src/main/resources/pictures/buttons/"+str+"1.png";
			/*
			 * used for sort button
			 */
			if(name.contains("sort")&&path.contains("1"))
				picPath = "src/main/resources/pictures/buttons/"+name+"2.png";
			else if(name.contains("sort")&&path.contains("2"))
				picPath = "src/main/resources/pictures/buttons/"+name+".png";
			//end
		}else if(isPressed){
			if(name.contains("guide")&&name.contains("3")) str = name.substring(0, name.length()-1);
			picPath = "src/main/resources/pictures/buttons/"+str+"2.png";
			/*
			 * used for sort button
			 */
			if(name.contains("sort")) {picPath = path;}
			//end
		}else if(isExit&&!isClicked){
			if(name.contains("sort")&&path.contains("1"))
				picPath = "src/main/resources/pictures/buttons/"+name+".png";
			else if(name.contains("sort")&&path.contains("2"))
				picPath = "src/main/resources/pictures/buttons/"+name+"1.png";
			else if(name.contains("sort"))
				picPath = "src/main/resources/pictures/buttons/"+name+"2.png";
		}else{
		
			picPath = "src/main/resources/pictures/buttons/"+str+".png";
			/*
			 * used for sort button
			 */
			if(name.contains("sort")){
				picPath = path;
			}
		}
		g2D.drawImage(new ImageIcon(picPath).getImage(), 0,0,this.getWidth(),this.getHeight(), this);
	}
	public void setIn(boolean isIn){
		this.isIn = isIn;
	}
	public void setPressed(boolean isPressed){
		this.isPressed = isPressed;
	}
	public void setExit(boolean isExit){
		this.isExit = isExit;
	}
	//used for sort
	public void setClicked(boolean isClicked){
		this.isClicked = isClicked;
	}
	//end
	public Element getElement() {
		return element;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPicPath(){
		return this.picPath;
	}
	
//	public static void main(String[] args){
//		
//	}

}

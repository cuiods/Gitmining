package edu.nju.git.ui.config;

import org.dom4j.Element;

/**
 * Configure of a <b>picture</b><br>
 * including x,y,width,height
 * @see Component
 * @author cuihao
 * last modified: 2015-11-17 22:40:59
 */
public class ComponentConfig {
	private String name;
	private String className;
	private int x;
	private int y;
	private int w;
	private int h;
	public ComponentConfig(Element component){
		this.name = component.attributeValue("name");
		this.x = Integer.parseInt(component.attributeValue("x"));
		this.y = Integer.parseInt(component.attributeValue("y"));
		this.w = Integer.parseInt(component.attributeValue("w"));
		this.h = Integer.parseInt(component.attributeValue("h"));
		this.className = component.attributeValue("class");
	}
	public String getName() {
		return name;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getW() {
		return w;
	}
	public int getH() {
		return h;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
}

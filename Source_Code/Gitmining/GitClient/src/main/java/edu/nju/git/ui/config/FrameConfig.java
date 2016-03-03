package edu.nju.git.ui.config;

import org.dom4j.Element;

/**
 * configure info of a window
 * @author cuihao
 * last modified:2015-11-17 23:05:32
 */
public class FrameConfig {
	/**
	 * width of a window
	 */
	private int width;
	/**
	 * height of a window
	 */
	private int height;
	private String name;
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public FrameConfig(Element frame) {
		this.width = Integer.parseInt(frame.attributeValue("width"));
		this.height = Integer.parseInt(frame.attributeValue("height"));
		this.name = frame.attributeValue("name");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

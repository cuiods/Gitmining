package edu.nju.git.ui.config;

import org.dom4j.Element;

public class UnitConfig extends ComponentConfig{
	private Element element;
	public UnitConfig(Element component) {
		super(component);
		this.setElement(component);
	}
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
}

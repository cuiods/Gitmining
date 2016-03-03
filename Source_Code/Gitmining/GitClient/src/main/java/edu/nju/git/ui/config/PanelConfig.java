package edu.nju.git.ui.config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import edu.nju.git.ui.components.Component;


/**
 * Configure info of a panel, including all the component configures
 * @author cuihao
 * 2015-11-18 14:33:21
 */
public class PanelConfig {

	/**
	 * width of a panel
	 */
	private int width;
	/**
	 * height of a panel
	 */
	private int height;
	/**
	 * location x
	 */
	private int x;
	/**
	 * location y
	 */
	private int y;
	/**
	 * picture configure info of this panel. A panel will show its picture
	 * according to {@link Component}
	 * 
	 * @see Component
	 */
	private ArrayList<ComponentConfig> components;
	/**
	 * component configure info of this panel
	 * components are buttons, labels, tables, etc
	 * these components are created by the element in {@link UnitConfig}
	 * @see UnitConfig
	 */
	private ArrayList<UnitConfig> units;
	
	private ArrayList<PanelConfig> panels;
	private Element element;
	public PanelConfig(Element panel) {
		this.element = panel;
		width = Integer.parseInt(panel.attributeValue("w"));
		height = Integer.parseInt(panel.attributeValue("h"));
		setComponents(getComponentConfigures(panel));
		setUnits(getUnitConfigures(panel));
		setPanels(getPanelConfigures(panel));
	}

	/**
	 * get picture configures
	 * @param Element
	 * @return list of {@link ComponentConfig}
	 */
	private ArrayList<ComponentConfig> getComponentConfigures(Element panel) {
		List<Element> elements = panel.elements("component");
		ArrayList<ComponentConfig> configs = new ArrayList<ComponentConfig>(elements.size());
		for (Element component : elements) {
			ComponentConfig config = new ComponentConfig(component);
			configs.add(config);
		}
		return configs;
	}

	/**
	 * get component configures
	 * @param panel
	 * @return list of {@link UnitConfig}
	 */
	private ArrayList<UnitConfig> getUnitConfigures(Element panel) {
		List<Element> elements = panel.elements("unit");
		ArrayList<UnitConfig> configs = new ArrayList<UnitConfig>(elements.size());
		for (Element component : elements) {
			UnitConfig config = new UnitConfig(component);
			configs.add(config);
		}
		return configs;
	}
	
	/**
	 * get panel configure
	 * @param panel
	 * @return list of {@link PanelConfig}
	 */
	private ArrayList<PanelConfig> getPanelConfigures(Element panel) {
		List<Element> elements = panel.elements("myPanel");
		ArrayList<PanelConfig> configs = new ArrayList<PanelConfig>(elements.size());
		for (Element component : elements) {
			PanelConfig config = new PanelConfig(component);
			configs.add(config);
		}
		return configs;
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

	public ArrayList<ComponentConfig> getComponents() {
		return components;
	}

	public void setComponents(ArrayList<ComponentConfig> components) {
		this.components = components;
	}

	public ArrayList<UnitConfig> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<UnitConfig> units) {
		this.units = units;
	}

	public ArrayList<PanelConfig> getPanels() {
		return panels;
	}

	public void setPanels(ArrayList<PanelConfig> panels) {
		this.panels = panels;
	}

	public Element getElement() {
		return element;
	}
	
//	public static void main(String[] args) {
//		ConfigReader reader = new ConfigReader();
//		PanelConfig config = reader.readPanel("MainPanel");
//		System.out.println(config.getComponents().get(0).getX());
//	}
}

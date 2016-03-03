package edu.nju.git.ui.config;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * read configure info from xml file
 * @author cuihao
 * last modified: 
 */
public class ConfigReader {
	private String path;
	private Element root;
	public ConfigReader() {
		this("src/main/resources/config/UIConfig.xml");
	}
	
	/**
	 * read file and initialize root element
	 * @param myPath: xml filePath
	 */
	public ConfigReader(String myPath){
		path = myPath;
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(path);
			root = doc.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * read {@link FrameConfig} by name
	 * @param frameName
	 * @return FrameConfig
	 */
	public FrameConfig readFrame(String frameName) {
		for(Iterator<Element> i = root.elementIterator(); i.hasNext();) {
			Element frame = (Element) i.next();
			if(frame.attributeValue("name").equals(frameName)){
				FrameConfig config = new FrameConfig(frame);
				return config;
			}
		}
		return null;
	}
	/**
	 * read {@link PanelConfig} by name
	 * @param panelName
	 * @return PanelConfig
	 */
	public PanelConfig readPanel(String panelName) {
		for(Iterator<Element> i = root.elementIterator(); i.hasNext();) {
			Element frame = (Element) i.next();
			for(Iterator<Element> j = frame.elementIterator(); j.hasNext();) {
				Element panel = (Element) j.next();
				if(panel.attributeValue("name").equals(panelName)){
					PanelConfig config = new PanelConfig(panel);
					return config;
				}
			}
		}
		return null;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Element getRoot() {
		return root;
	}
	public void setRoot(Element root) {
		this.root = root;
	}
	
}

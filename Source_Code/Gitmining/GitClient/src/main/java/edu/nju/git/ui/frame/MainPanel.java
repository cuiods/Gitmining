package edu.nju.git.ui.frame;

import java.awt.Graphics;
import java.awt.ItemSelectable;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.dom4j.Element;

import edu.nju.git.ui.components.Component;
import edu.nju.git.ui.components.MainButton;
import edu.nju.git.ui.config.ComponentConfig;
import edu.nju.git.ui.config.PanelConfig;
import edu.nju.git.ui.config.UnitConfig;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.listener.ButtonListener;

/**
 * General panel<br>
 * The panel will show pictures and add components according to "UIConfig.xml"
 * @author cuihao 
 * @date 2015-11-19 00:04:34
 */
public class MainPanel extends JPanel {
	
	private static final long serialVersionUID = 5802205870400021451L;
	/**
	 * configure info of this panel
	 * 
	 * @see PanelConfig
	 */
	private PanelConfig config;
	/**
	 * pictures of this panel
	 */
	private ArrayList<Component> components;
	/**
	 * {@link java.awt.Component} of this panel
	 */
	private ArrayList<java.awt.Component> units;
	/**
	 * @see UIController
	 */
	private UIController controller;

	public static final String packageName = "edu.nju.lms.presentation.";
	private int x;
	private int y;
	private int h;
	private int w;
	private String name;

	public MainPanel(PanelConfig config, UIController controller) {
		this.controller = controller;
		this.config = config;
		units = new ArrayList<java.awt.Component>();
		setLayout(null);
		Element button = config.getElement();
		w = Integer.parseInt(button.attributeValue("w"));
		h = Integer.parseInt(button.attributeValue("h"));
		x = Integer.parseInt(button.attributeValue("x"));
		y = Integer.parseInt(button.attributeValue("y"));
		name = button.attributeValue("name");
		setBounds(x, y, w, h);
		createComponents();
		createUnits();
		addKeyListener(new MyKeyListener());
	}

	/**
	 * initialize picture {@link Component} by {@link ComponentConfig}
	 */
	private void createComponents() {
		ArrayList<ComponentConfig> componentConfigs = config.getComponents();
		components = new ArrayList<Component>(componentConfigs.size());
		for (ComponentConfig c : componentConfigs) {
			Component component = new Component(c.getX(), c.getY(), c.getW(), c.getH(), c.getName(), c.getClassName());
			components.add(component);
		}
	}

	/**
	 * initialize {@link java.awt.Component} using {@link UnitConfig} if
	 * type==1, initialize Listener extends {@link MouseListener} or {@link ItemListener}
	 */
	private void createUnits() {
		for (UnitConfig unit : config.getUnits()) {
			try {
				/**
				 * create and initialize components(units)
				 */
				Class<?> myUnit = Class.forName(packageName + "components." + unit.getClassName());
				java.awt.Component com;
				Constructor<?> ctr = myUnit.getConstructor(Element.class, UIController.class);
				com = (java.awt.Component) ctr.newInstance(unit.getElement(), controller);
				add(com);
				com.addKeyListener(new MyKeyListener());
				units.add(com);
				/**
				 * add listener
				 */
				if (unit.getElement().attributeValue("type").equals("1")) {
					Class<?> listenner = Class
							.forName(packageName + "mouseListener." + unit.getElement().attributeValue("listenerName"));
					MouseListener mouseListener;
					Constructor<?> ct = listenner.getConstructor(ArrayList.class, UIController.class,
							java.awt.Component.class);
					mouseListener = (MouseListener) ct.newInstance(units, controller, com);
					com.addMouseListener(mouseListener);
				} else if (unit.getElement().attributeValue("type").equals("itemListener")) {
					Class<?> listenner = Class
							.forName(packageName + "mouseListener." + unit.getElement().attributeValue("listenerName"));
					ItemListener itemListener;
					Constructor<?> ct = listenner.getConstructor(ArrayList.class, UIController.class,
							java.awt.Component.class);
					itemListener = (ItemListener) ct.newInstance(units, controller, com);
					((ItemSelectable) com).addItemListener(itemListener);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * draw pictures
	 */
	@Override
	protected void paintComponent(Graphics g) {
		for (Component com : components) {
			com.createComponent(g);
		}
	}

	public PanelConfig getConfig() {
		return config;
	}

	public void setConfig(PanelConfig config) {
		this.config = config;
	}

	public ArrayList<java.awt.Component> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<java.awt.Component> units) {
		this.units = units;
	}

	public String getName() {
		return name;
	}
	
	/**
	 * <b>ShortCut</b>:<br>
	 * <b>VK_UP</b>: last guide_button<br>
	 * <b>VK_DOWN</b>: last guide_button<br>
	 * <b>VK_CTRL+VK_S</b>: save
	 * <b>VK_ENTER</b>: confirm
	 * @function Keyboard shortcut listener.
	 * @author cuihao
	 * @date 2015-12-16 14:42:58
	 */
	class MyKeyListener implements KeyListener {
		
		/**
		 * first guide button.
		 */
		private int first = -1; 
		/**
		 * last guide button.
		 */
		private int end = -1;
		/**
		 * index of save button.
		 */
		private int save = -1;
		/**
		 * index of confirm button.
		 */
		private int confirm = -1;
		/**
		 * all the components of the panel.
		 */
		ArrayList<java.awt.Component> units;
		
		/**
		 * logic of keyboard listener
		 */
		public void keyPressed(KeyEvent e) {
			units = getUnits();
			calIndex();
			int index = -1;
			switch(e.getKeyCode()){
			case KeyEvent.VK_UP: index = 0; break;
			case KeyEvent.VK_DOWN:index = 1; break;
			case KeyEvent.VK_ESCAPE:index = 2; break;
			case KeyEvent.VK_ENTER:index = 3; break;
			case KeyEvent.VK_S: index = e.isControlDown()?4:-1; break;
			}
			if(!(index<0)) doOperation(index);
		}
		public void keyReleased(KeyEvent e) {
		}
		public void keyTyped(KeyEvent e) {}
		
		/**
		 * find first and last guide button
		 */
		private void calIndex(){
			boolean find = false;
			boolean isEnd = false;
			end = units.size();
			for(int i = 0; i < units.size(); i++) {
				java.awt.Component c = units.get(i);
				if(c instanceof MainButton) {
					String name  = "";
					if((name = ((MainButton) c).getName()).contains("guide_")){
						if(!find) {
							first = i;
							find = true;
						}
					}else if(find&&!isEnd){
						end = i-1;
						isEnd = true;
					}
					if(name.contains("confirm")||name.contains("SignIn")||name.contains("create")){
						confirm = i;
					}
					if(name.contains("save")){
						save = i;
					}
				}
			}
		}
		
		/**
		 * find guide button to next panel
		 * @return index of next button
		 */
		private int getNext(){
			if(first<0||end<0) return -1;
			int result = first;
			for(int i = first; i <= end; i++) {
				MainButton button = (MainButton) units.get(i);
				if(button.getName().contains("3")){
					result = i;
				}
			}
			if(result<end){
				return result+1;
			}else{
				return first+1;
			}
		}
		
		/**
		 * Find guide button to last panel
		 * @return index of last button.
		 */
		private int getBefore(){
			if(first<0||end<0) return -1;
			int result = first;
			for(int i = first; i <= end; i++) {
				MainButton button = (MainButton) units.get(i);
				if(button.getName().contains("3")){
					result = i;
				}
			}
			if(result>first+1){
				return result-1;
			}else{
				return end;
			}
		}		
		
		/**
		 * Get mouselistener and do short cut operation.
		 * @param index
		 */
		private void doOperation(int index){
			int indexArray[] = {getBefore(), getNext() ,first,confirm,save};
			int op = indexArray[index];
			if(op < 0) return;
			MainButton button = (MainButton) units.get(op);
			ButtonListener listener = (ButtonListener) button.getMouseListeners()[0];
			listener.mouseReleased(null);
		}
	}
}

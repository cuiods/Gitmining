package edu.nju.git.ui.components;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.dom4j.Element;

import edu.nju.git.VO.UserVO;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.controller.UserController;

public class UserPanel extends JPanel{
	
	private static final long serialVersionUID = -1800716181939686012L;
	private Element element;
	private UIController controller;
	private String userName;
	private UserController service;
	
	public UserPanel(Element element, UIController controller){
		this.element = element;
		this.controller = controller;
		setLayout(null);
		userName = controller.getID();
		int w = Integer.parseInt(element.attributeValue("w"));
		int h = Integer.parseInt(element.attributeValue("h"));
		int x = Integer.parseInt(element.attributeValue("x"));
		int y = Integer.parseInt(element.attributeValue("y"));
		setBounds(x, y, w, h);
		setOpaque(false);
		service = controller.getUserController();
		initialize();
	}
	
	private void initialize() {
		UserVO userVO = service.getDetailedUser(controller.getID());
		MyLabel login = new MyLabel(userVO.getLogin()); 
	    login.setBounds(490,36,200,30);add(login);
		MyLabel name = new MyLabel(userVO.getName());
		name.setBounds(490,80,200,30);add(name);
		MyLabel company = new MyLabel(userVO.getCompany());
		company.setBounds(490,124,220,30);add(company);
		
	}

}

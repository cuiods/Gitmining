package edu.nju.git.ui.controller;

import edu.nju.git.ui.config.ConfigReader;
import edu.nju.git.ui.config.PanelConfig;
import edu.nju.git.ui.frame.MainFrame;
import edu.nju.git.ui.frame.MainPanel;

public class UIController {
	/**
	 * frame of client
	 */
	private MainFrame frame;
	private ReposController repo;
	private UserController user;
	
	private String id;
	private boolean before;
	private String beforeKeyword="";
	
	public UIController() {
		initialize();
		repo = new ReposController();
		user = new UserController();
		frame = new MainFrame();
		ConfigReader r = new ConfigReader();
		PanelConfig panelConfig = r.readPanel("MainPanel");
		MainPanel mainPanel = new MainPanel(panelConfig,this);
		frame.getContentPane().add(mainPanel);
		frame.setVisible(true);
		frame.setPanel(mainPanel);
	}

	/**
	 * change to another panel
	 * @param name of panel to change
	 */
	public void changeTo(String panel) {
		frame.changeTo(panel, this);
	}
	
	/**
	 * fade out and change to a panel and fade in
	 * @param delay
	 * @param speed
	 * @param panel
	 * @see #changeTo(String)
	 */
	public void fadeTo(int delay, float speed, String panel){
		frame.fadeTo(delay, speed, panel, this);
	}

	/**
	 * initialize controller
	 */
	private void initialize() {
		
	}
	
	public MainFrame getFrame() {
		return frame;
	}
	
	public ReposController getReposController(){
		return this.repo;
	}
	
	public UserController getUserController(){
		return this.user;
	}
	
	public void setID(String id){
		this.id = id;
	}
	
	public String getID(){
		return id;
	}
	/*
	 * to judge if need to go back the before view
	 */
	public boolean isBefore(){
		return this.before;
	}
	
	public void setBefore(boolean isBefore){
		this.before = isBefore;
	}
	
	public void setBeforeKeywords(String keywords){
		this.beforeKeyword = keywords;
	}
	
	public String getBeforeKeywords(){
		return this.beforeKeyword;
	}
	
}

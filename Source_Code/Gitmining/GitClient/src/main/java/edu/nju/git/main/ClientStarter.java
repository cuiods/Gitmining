package edu.nju.git.main;

import edu.nju.git.ui.controller.UIController;

/**
 * Client starter
 * @author cuihao
 * @date 2015-12-08 20:59:44
 */
public class ClientStarter {
	
	/**
	 * Start presentation
	 * Before start the client, server must be activated
	 * @param args
	 */
	public static void main(String[] args) {
		new UIController();
	}
}

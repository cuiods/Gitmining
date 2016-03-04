package edu.nju.ui.control;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

import javafx.scene.layout.Region;

/**
 * <h1>Present a task user started.</h1>
 * <p>
 * User can start a task in index panel:{@link IndexPanel}, IF and <b>ONLY</b> IF a task<br>
 * is created, services can be used. A task panel can set function panel: {@link FunctionPanel}<br>
 * A task can hold many function panels at a time, but only one will present to<br>
 * users.
 * </p>
 * <p>
 * 	Function panels can be memorized and can go back.
 * </p>
 * @author cuihao
 * @date 2016-03-04 23:16:26
 *
 */
public class TaskPanel extends GitPanel{
	
	private Stack<FunctionPanel> functions = new Stack<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initPanel(Object[] bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setChildren(Region region) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeChild() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * get back to lask function.
	 */
	public void back(){
		
	}

}

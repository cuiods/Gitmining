package edu.nju.ui.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.layout.Region;

/**
 * <p>Root panel of the application.</p>
 * <p>This is likely a home page of this application.User can add a task {@link TaskPanel}.<br>
 * In every task, user can use different functions.{@link FunctionPanel}. Tasks can be merged<br>
 * so that user can compare the data.</p>
 * @author cuihao
 * @date 2016-03-04 23:06:32
 *
 */
public class IndexPanel extends GitPanel{
	private List<TaskPanel> tasks = new ArrayList<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void initPanel(Object[] bundle) {
		
	}

	@Override
	public void setChildren(Region region) {
		
	}

	@Override
	public void removeChild() {
		
	}
	
	/**
	 * merge two task by their index in {@link #tasks}
	 * @param origin
	 * 			index of the origin task.
	 * @param toMerge
	 * 			index of the task to be merged.
	 */
	public void mergeTask(int origin, int toMerge){
		
	}

	public List<TaskPanel> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskPanel> tasks) {
		this.tasks = tasks;
	}

}

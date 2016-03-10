package edu.nju.git.ui.text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.nju.git.VO.RepoVO;
import edu.nju.git.ui.controller.ReposController;
import edu.nju.git.ui.controller.UIController;
/*
 * used to get basic repo info
 * @author yyy
 * @date 2016-03-10 18:47
 */
public class RepoInfoText extends Text {

	@Override
	public String get(UIController controller) {
		String owner ="";
		String repoName ="";
		try {
			BufferedReader br = new BufferedReader(new FileReader("repo.git"));
			try {
				owner = br.readLine();
				repoName = br.readLine();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ReposController repoControl = controller.getReposController();
		RepoVO repo = repoControl.getDetailedRepo(owner, repoName);
		String info = "";
	
		return null;
	}

}

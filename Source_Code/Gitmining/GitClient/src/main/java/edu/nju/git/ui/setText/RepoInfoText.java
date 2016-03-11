package edu.nju.git.ui.setText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.nju.git.VO.RepoVO;
import edu.nju.git.ui.controller.ReposController;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.text.Text;
/*
 * used to get basic repo info
 * @author yyy
 * @date 2016-03-10 18:47
 */
public class RepoInfoText extends Text {

	@Override
	public String get(UIController controller) {
		
		String[] or = controller.getID().split(" ");
		
		
		ReposController repoControl = controller.getReposController();
		
		RepoVO repo = repoControl.getDetailedRepo(or[0], or[1]);
		
		String info = "";
		
		info = "Name:   "+repo.getName()+"\n"
		+"Language:   "+repo.getLanguage()+"\n"
		+"Owner:   "+repo.getOwnerName()+"\n"
		+"Owner_type:   "+repo.getType()+"\n"
		+"Url:  "+repo.getUrl()+"\n"
		+"Create_at:  "+repo.getCreate_at()+"\n"
		+"Update_at:  "+repo.getUpdate_at()+"\n"
		+"Num_stars:  "+repo.getNum_stars()+"\n"
		+"Num_forks:  "+repo.getNum_forks()+"\n"
		+"Num_subscribers:  "+repo.getNum_subscribers()+"\n"
		+"Description:  "+repo.getDescription();
		
		return info;
	}

}

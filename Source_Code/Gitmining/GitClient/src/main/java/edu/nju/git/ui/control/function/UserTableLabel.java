package edu.nju.git.ui.control.function;

import java.net.URL;
import java.util.ResourceBundle;

import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.ui.control.GitPanel;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

/**
 * One single item in the user table
 * @author cuihao
 * @date 2016-03-24 16:42:11
 */
public class UserTableLabel extends GitPanel{
	private UserBriefVO userBriefVO;
	@FXML private Hyperlink name;
	@FXML private Label company;
	@FXML private Label create;
	@FXML private Label update;
	@FXML private Label repos;
	@FXML private Label following;
	@FXML private Label followed;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	/**
	 * Set data and initialize.
	 * @param userBriefVO
	 */
	public void setUserBriefVO(UserBriefVO userBriefVO) {
		this.userBriefVO = userBriefVO;
		initialize();
	}
	
	private void initialize(){
		if (userBriefVO != null) {
			name.setText(userBriefVO.getLogin());
			company.setText(userBriefVO.getCompany());
			create.setText(userBriefVO.getCreate_at());
			update.setText(userBriefVO.getUpdate_at());
			repos.setText(userBriefVO.getPublic_repos()+"");
			following.setText(userBriefVO.getFollowing()+"");
			followed.setText(userBriefVO.getFollowers()+"");
		}
	}

	@Override
	public void initPanel(Object[] bundle) {
	}

	@Override
	public void setChildren(Parent region) {
	}

	@Override
	public void removeChild() {
	}

}

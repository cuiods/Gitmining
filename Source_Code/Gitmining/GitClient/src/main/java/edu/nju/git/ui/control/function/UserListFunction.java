package edu.nju.git.ui.control.function;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.VO.UserVO;
import edu.nju.git.bl.factory.impl.BlFactory;
import edu.nju.git.bl.service.UserBlService;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.type.SortType;
import edu.nju.git.ui.config.ConfigReader;
import edu.nju.git.ui.config.ScreenShot;
import edu.nju.git.ui.control.FunctionPanel;
import edu.nju.git.ui.control.UIManager;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class UserListFunction extends FunctionPanel{
	@FXML private VBox listBox;
	@FXML private ScrollPane scroll;
	@FXML private TextField page;
	@FXML private TextField search;
	@FXML private Button btn_repo;
	@FXML private Button btn_following;
	@FXML private Button btn_followed;
	
	private boolean isStar;
	private boolean isFork;
	private boolean isContributor;	
	
	private List<UserBriefVO> datalist;
	private UserBlService userblServive;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setCssFactory(UIManager.instance().getCssFactory());
		userblServive = BlFactory.instance().getUserBlService();
		initialGeneral();
		listBox.toFront();
		
	}
	
	@FXML
	protected void next() {
		List<UserBriefVO> data = null;
		try {
			data = userblServive.nextPage();
		} catch (PageOutOfBoundException e) {
			return;
		}
		datalist = data;
		page.setText((Integer.parseInt(page.getText())+1)+"");
		updateList(data);
	}
	
	@FXML
	protected void previous(){
		if (Integer.parseInt(page.getText())==1) {
			return;
		}
		List<UserBriefVO> data = null;
		try {
			data = userblServive.previousPage();
		} catch (PageOutOfBoundException e) {
			return;
		}
		datalist = data;
		page.setText((Integer.parseInt(page.getText())-1)+"");
		updateList(data);
	}
	
	@FXML
	protected void search(){
		String text = search.getText();
		List<UserBriefVO> list = userblServive.getSearchResult(text);
		list = userblServive.getShownUserList();
		if (list!=null) {
			datalist = list;
			updateList(datalist);
			page.setText("1");
		}
	}
	
	@FXML
	protected void sortGenerous(){
		btn_repo.setText("Repositorys");
		btn_followed.setText("Followed");
		btn_following.setText("Following");
		
		
//		List<UserBriefVO> list = userblServive.sort(SortType.USER_NAME,false);
//		list = userblServive.getShownUserList();
		List<UserBriefVO> list = userblServive.getSearchResult("");
		if (list!=null) {
			datalist = list;
			updateList(datalist);
			page.setText("1");
		}
		
//		initialGeneral();
	}
	
	@FXML
	protected void sortRepos() {
		btn_following.setText("Following");
		btn_followed.setText("Followed");
		btn_repo.setText("Repositorys"+(isStar?"↑":"↓"));
		List<UserBriefVO> list = userblServive.sort(SortType.REPO_NUM,isStar=!isStar);
		list = userblServive.getShownUserList();
		if (list!=null) {
			datalist = list;
			updateList(datalist);
			page.setText("1");
		}
	}
	
	@FXML
	protected void sortFollowing() {
		btn_following.setText("Following"+(isFork?"↑":"↓"));
		btn_followed.setText("Followed");
		btn_repo.setText("Repositorys");
		List<UserBriefVO> list = userblServive.sort(SortType.FOLLOWER_NUM,isFork=!isFork);
		list = userblServive.getShownUserList();
		if (list!=null) {
			datalist = list;
			updateList(datalist);
			page.setText("1");
		}
	}
	
	@FXML
	protected void sortFollowed(){
		btn_following.setText("Following");
		btn_followed.setText("Followed"+(isContributor?"↑":"↓"));
		btn_repo.setText("Repositorys");
		List<UserBriefVO> list = userblServive.sort(SortType.FOLLOWER_NUM,isContributor=!isContributor);
		list = userblServive.getShownUserList();
		if (list!=null) {
			datalist = list;
			updateList(datalist);
			page.setText("1");
		}
	}
	
	@FXML
	protected void compare(){
		ObservableList<Node> nodes = listBox.getChildren();
		ArrayList<UserVO> choosen = new ArrayList<>();
loop:	for(Node node: nodes) {
			if (node instanceof AnchorPane) {
				ObservableList<Node> ns = ((AnchorPane) node).getChildren();
				for(Node n: ns) {
					if (n instanceof CheckBox) {
						if(!((CheckBox)n).isSelected()){
							choosen.remove(choosen.size()-1);
							continue loop;
						}
					}
					if(n instanceof Hyperlink) {
						String name = ((Hyperlink)n).getText();
						UserVO vo = userblServive.getUserInfo(name);
						choosen.add(vo);
					}
				}
			}
		}
		Parent root=UIManager.instance().changeFunction("function_userCompare", new Object[]{choosen});
		root.getStylesheets().add(getCssFactory().getFunctionUserDetail());
	}
	
	/**
	 * update list in the table
	 * @param list
	 */
	private void updateList(List<UserBriefVO> list){
		if (list==null) {
			return;
		}
		listBox.getChildren().clear();
		for(Iterator<UserBriefVO> iterator = list.iterator(); iterator.hasNext();){
			UserBriefVO UserBriefVO = iterator.next();
			ScreenShot pane = ConfigReader.readParentPanel("UserTableLabel");
			pane.getRoot().getStylesheets().add(getCssFactory().getFunctionRepoTableLabel());
			UserTableLabel label = (UserTableLabel) pane.getPanel();
			label.setUserBriefVO(UserBriefVO);
			listBox.getChildren().add(pane.getRoot());
		}
	}
	
	/**
	 * initilaize at the beginning
	 */
	private void initialGeneral(){
		datalist = userblServive.getSearchResult("");
		if (userblServive.getTotalPage()>=1) {
			try {
				datalist = userblServive.jumpToPage(1);
			} catch (PageOutOfBoundException e) {
				e.printStackTrace();
			}
		}
		if (datalist==null) {
			return;
		}
		for(Iterator<UserBriefVO> iterator = datalist.iterator(); iterator.hasNext();){
			UserBriefVO UserBriefVO = iterator.next();
			ScreenShot pane = ConfigReader.readParentPanel("UserTableLabel");
			pane.getRoot().getStylesheets().add(getCssFactory().getFunctionRepoTableLabel());
			UserTableLabel label = (UserTableLabel) pane.getPanel();
			label.setUserBriefVO(UserBriefVO);
			listBox.getChildren().add(pane.getRoot());
		}
		page.setText("1");
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

	@Override
	public String getLocationName() {
		return "User";
	}

}

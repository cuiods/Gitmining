package edu.nju.git.ui.control.function;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.RepoVO;
import edu.nju.git.bl.factory.impl.BlFactory;
import edu.nju.git.bl.service.RepoBlService;
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

/**
 * Controller of repolist panel
 * @author cuihao
 * @date 2016-03-23 20:29:52
 */
public class RepoListFunction extends FunctionPanel{

	@FXML private VBox listBox;
	@FXML private ScrollPane scroll;
	@FXML private TextField page;
	@FXML private TextField search;
	@FXML private Button btn_star;
	@FXML private Button btn_fork;
	@FXML private Button btn_Subscri;
	@FXML private Button btn_compare;
	
	private boolean isStar;
	private boolean isFork;
	private boolean isSubscriber;	
	
	private List<RepoBriefVO> datalist;
	private RepoBlService service;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setCssFactory(UIManager.instance().getCssFactory());
		service = BlFactory.instance().getRepoBlService();
		initialGeneral();
		listBox.toFront();
		
	}
	
	@FXML
	protected void next() {
		List<RepoBriefVO> data = null;
		try {
			data = service.nextPage();
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
		List<RepoBriefVO> data = null;
		try {
			data = service.previousPage();
		} catch (PageOutOfBoundException e) {
			return;
		}
		datalist = data;
		page.setText((Integer.parseInt(page.getText())-1)+"");
		updateList(data);
	}
	
	@FXML
	protected void search(){
		btn_star.setText("Stars");
		btn_Subscri.setText("Subscribers");
		btn_fork.setText("Forks");
		String text = search.getText();
		List<RepoBriefVO> list = service.getSearchResult(text);
		list = service.getShownRepoList();
		if (list!=null) {
			datalist = list;
			updateList(datalist);
			page.setText("1");
		}
	}
	
	@FXML
	protected void sortGenerous(){
		btn_star.setText("Stars");
		btn_Subscri.setText("Subscribers");
		btn_fork.setText("Forks");
//		List<RepoBriefVO> list = service.sort(SortType.REPO_NAME,false);
//		list = service.getShownRepoList();
		List<RepoBriefVO> list = service.getSearchResult("");
		if (list!=null) {
			datalist = list;
			updateList(datalist);
			page.setText("1");
		}
//		initialGeneral();
	}
	
	@FXML
	protected void sortStar() {
		btn_fork.setText("Forks");
		btn_Subscri.setText("Subscribers");
		btn_star.setText("Stars"+(isStar?"↑":"↓"));
		List<RepoBriefVO> list = service.sort(SortType.STAR_NUM,isStar=!isStar);
		list = service.getShownRepoList();
		if (list!=null) {
			datalist = list;
			updateList(datalist);
			page.setText("1");
		}
	}
	
	@FXML
	protected void sortFork() {
		btn_fork.setText("Forks"+(isFork?"↑":"↓"));
		btn_Subscri.setText("Subscribers");
		btn_star.setText("Stars");
		List<RepoBriefVO> list = service.sort(SortType.FORK_NUM,isFork=!isFork);
		list = service.getShownRepoList();
		if (list!=null) {
			datalist = list;
			updateList(datalist);
			page.setText("1");
		}
	}
	
	@FXML
	protected void sortSubscriber(){
		btn_fork.setText("Forks");
		btn_Subscri.setText("Subscribers"+(isSubscriber?"↑":"↓"));
		btn_star.setText("Stars");
		List<RepoBriefVO> list = service.sort(SortType.SUBSCR_NUM,isSubscriber=!isSubscriber);
		list = service.getShownRepoList();
		if (list!=null) {
			datalist = list;
			updateList(datalist);
			page.setText("1");
		}
	}
	
	@FXML
	protected void compare(){
		ObservableList<Node> nodes = listBox.getChildren();
		ArrayList<RepoVO> choosen = new ArrayList<>();
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
						String[] names = ((Hyperlink)n).getText().split("/");
						RepoVO vo = service.getRepoBasicInfo(names[0], names[1]);
						choosen.add(vo);
					}
				}
			}
		}
		Parent root = UIManager.instance().changeFunction("function_repoCompare", new Object[]{choosen});
		root.getStylesheets().add(getCssFactory().getFunctionUserDetail());
	}
	
	/**
	 * update list in the table
	 * @param list
	 */
	private void updateList(List<RepoBriefVO> list){
		if (list==null) {
			return;
		}
		listBox.getChildren().clear();
		for(Iterator<RepoBriefVO> iterator = list.iterator(); iterator.hasNext();){
			RepoBriefVO repoBriefVO = iterator.next();
			ScreenShot pane = ConfigReader.readParentPanel("RepotableLabel");
			pane.getRoot().getStylesheets().add(getCssFactory().getFunctionRepoTableLabel());
			RepoTableLabel label = (RepoTableLabel) pane.getPanel();
			label.setRepoBriefVO(repoBriefVO);
			listBox.getChildren().add(pane.getRoot());
		}
	}
	
	/**
	 * initilaize at the beginning
	 */
	private void initialGeneral(){
		datalist = service.getSearchResult("");
		if (service.getTotalPage()>=1) {
			try {
				datalist = service.jumpToPage(1);
			} catch (PageOutOfBoundException e) {
				e.printStackTrace();
			}
		}
		if (datalist==null) {
			return;
		}
		for(Iterator<RepoBriefVO> iterator = datalist.iterator(); iterator.hasNext();){
			RepoBriefVO repoBriefVO = iterator.next();
			ScreenShot pane = ConfigReader.readParentPanel("RepotableLabel");
			pane.getRoot().getStylesheets().add(getCssFactory().getFunctionRepoTableLabel());
			RepoTableLabel label = (RepoTableLabel) pane.getPanel();
			label.setRepoBriefVO(repoBriefVO);
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
		return "Repository";
	}

}

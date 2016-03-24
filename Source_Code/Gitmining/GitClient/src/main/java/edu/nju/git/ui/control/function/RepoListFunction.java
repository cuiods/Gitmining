package edu.nju.git.ui.control.function;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.bl.factory.impl.BlFactory;
import edu.nju.git.bl.service.RepoBlService;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.type.SortType;
import edu.nju.git.ui.config.ConfigReader;
import edu.nju.git.ui.config.ScreenShot;
import edu.nju.git.ui.control.GitPanel;
import edu.nju.git.ui.control.UIManager;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Controller of repolist panel
 * @author cuihao
 * @date 2016-03-23 20:29:52
 */
public class RepoListFunction extends GitPanel{

	@FXML private VBox listBox;
	@FXML private ScrollPane scroll;
	@FXML private TextField page;
	@FXML private TextField search;
	@FXML private Button btn_star;
	@FXML private Button btn_fork;
	@FXML private Button btn_contri;
	
	private boolean isStar;
	private boolean isFork;
	private boolean isContributor;	
	
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
		btn_contri.setText("Contributors");
		btn_fork.setText("Forks");
//		List<RepoBriefVO> list = service.sort(SortType.REPO_NAME);
//		list = service.getShownRepoList();
//		if (list!=null) {
//			datalist = list;
//			updateList(datalist);
//			page.setText("1");
//		}
		initialGeneral();
	}
	
	@FXML
	protected void sortStar() {
		btn_fork.setText("Forks");
		btn_contri.setText("Contributors");
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
		btn_contri.setText("Contributors");
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
	protected void sortContributor(){
		btn_fork.setText("Forks");
		btn_contri.setText("Contributors"+(isContributor?"↑":"↓"));
		btn_star.setText("Stars");
		List<RepoBriefVO> list = service.sort(SortType.SUBSCR_NUM,isContributor=!isContributor);
		list = service.getShownRepoList();
		if (list!=null) {
			datalist = list;
			updateList(datalist);
			page.setText("1");
		}
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

}

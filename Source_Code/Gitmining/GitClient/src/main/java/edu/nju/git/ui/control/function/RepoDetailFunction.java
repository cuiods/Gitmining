package edu.nju.git.ui.control.function;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import edu.nju.git.VO.RepoVO;
import edu.nju.git.VO.chartvo.MyChartVO;
import edu.nju.git.bl.factory.impl.BlFactory;
import edu.nju.git.bl.service.RepoBlService;
import edu.nju.git.ui.chart.ChartType;
import edu.nju.git.ui.chart.RepoLanguagePieChart;
import edu.nju.git.ui.chart.RepoSpiderChart;
import edu.nju.git.ui.control.FunctionPanel;
import edu.nju.git.ui.control.UIManager;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * RepoDetail controller
 */
public class RepoDetailFunction extends FunctionPanel{
    /**
     * FXML components
     */
    @FXML private Label generalName;
    @FXML private TextArea description;
    @FXML private Label create;
    @FXML private Label update;
    @FXML private Hyperlink url;
    @FXML private Hyperlink owner;
    @FXML private Label language;
    @FXML private Label size;
    @FXML private Label star;
    @FXML private Label fork;
    @FXML private Label contri;
    @FXML private Label subcri;
    @FXML private Label collab;
    @FXML private VBox vbox;
    @FXML private AnchorPane radarPane;
    @FXML private Button activity;
    @FXML private AnchorPane lAnchorPane;

    /**
     * repository business logic service
     */
    private RepoBlService service;
    /**
     * vo of the repository
     */
    private RepoVO repoVO;
    @Override
    public void initPanel(Object[] bundle) {
    	setCssFactory(UIManager.instance().getCssFactory());
    	repoVO = service.getRepoBasicInfo((String)bundle[0],(String)bundle[1]);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                initData(bundle);
                initLink();
                initRelative();
                return null;
            }
        };
        Platform.runLater(task);
    }

    @Override
    public void setChildren(Parent region) {

    }

    @Override
    public void removeChild() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service = BlFactory.instance().getRepoBlService();
    }
    
    @FXML
    public void view(){
    	ArrayList<MyChartVO> vos = new ArrayList<>();
    	vos.add(repoVO.getActivityChart());
    	UIManager.instance().changeFunction("function_Statistic",new Object[]{ChartType.RepoAcitivity,vos});
    }
    
    private void initLink() {
		url.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_webView",new Object[]{url.toString().split("'")[1]});
			}
		});
		owner.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Parent root=UIManager.instance().changeFunction("function_userDetail", new Object[]{owner.getText()});
				root.getStylesheets().add(getCssFactory().getFunctionUserDetail());
			}
		});
	}
    
    private void initLanguage() {
    	RepoLanguagePieChart pieChart = new RepoLanguagePieChart();
    	MyChartVO vo = new MyChartVO();
    	List<Integer> temp = repoVO.getLanguagesLine();
    	int[] values = new int[temp.size()];
    	for(int i = 0; i < values.length; i++) {
    		values[i] = temp.get(i).intValue();
    	}
    	List<String> tempStr = repoVO.getLanguagesField();
    	String[] strings = new String[tempStr.size()];
    	for (int i = 0; i < strings.length; i++) {
    		strings[i] = tempStr.get(i);
    	}
    	vo.setChartVO(strings, values);
    	lAnchorPane.getChildren().add(pieChart.createContent(vo));
    }
    
    private void initData(Object[] bundle){
        if (repoVO == null) return;
        generalName.setText(repoVO.getName());
        description.setText(repoVO.getDescription());
        create.setText(repoVO.getCreate_at());
        update.setText(repoVO.getUpdate_at());
        url.setText(repoVO.getUrl());
        owner.setText(repoVO.getOwnerName());
        language.setText(repoVO.getLanguage());
        size.setText(repoVO.getSize()+"");
        star.setText(repoVO.getNum_stars()+"");
        fork.setText(repoVO.getNum_forks()+"");
        contri.setText(repoVO.getNum_contributors()+"");
        subcri.setText(repoVO.getNum_subscribers()+"");
        collab.setText(repoVO.getNum_collaborators()+"");
        initLanguage();
        setRadar();
    }

	@Override
	public String getLocationName() {
		return repoVO.getName();
	}
	
	private void setRadar(){
		ArrayList<RepoVO> repos = new ArrayList<>();
		repos.add(repoVO);
		RepoSpiderChart chart = new RepoSpiderChart(repos, 350, 300);
		radarPane.getChildren().add(chart.createComponent());
	}
	
	private void initRelative() {
		List<String> uservos = service.getRepoCollaborator(repoVO.getOwnerName(), repoVO.getName());
		if (uservos.size() > 15) {
			uservos = uservos.subList(0, 15);
		}
		for(String user: uservos) {
			Hyperlink hyperlink = new Hyperlink(user);
			hyperlink.setPrefSize(200, 50);
			hyperlink.setFont(new Font(20));
			hyperlink.setAlignment(Pos.CENTER);
			hyperlink.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					Parent root=UIManager.instance().changeFunction("function_userDetail", new Object[]{user});
					root.getStylesheets().add(getCssFactory().getFunctionUserDetail());
				}
			});
			vbox.getChildren().add(hyperlink);
		}
	}

}

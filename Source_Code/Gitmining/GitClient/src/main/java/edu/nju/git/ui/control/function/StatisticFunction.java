package edu.nju.git.ui.control.function;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import edu.nju.git.VO.chartvo.MyChartVO;
import edu.nju.git.bl.impl.RepoChartBlImpl;
import edu.nju.git.bl.impl.UserChartBlImpl;
import edu.nju.git.bl.service.RepoChartBlService;
import edu.nju.git.bl.service.UserChartBlService;
import edu.nju.git.ui.chart.ChartType;
import edu.nju.git.ui.chart.CompanyUserBarChart;
import edu.nju.git.ui.chart.MyChart;
import edu.nju.git.ui.chart.RepoCollaBarChart;
import edu.nju.git.ui.chart.RepoContriBarChart;
import edu.nju.git.ui.chart.RepoCreateTimeBarChart;
import edu.nju.git.ui.chart.RepoForkBarChart;
import edu.nju.git.ui.chart.RepoLanguageBarChart;
import edu.nju.git.ui.chart.RepoSizeBarChart;
import edu.nju.git.ui.chart.RepoStarsBarChart;
import edu.nju.git.ui.chart.RepoSubscriberBarChart;
import edu.nju.git.ui.chart.UserCollaReposBarChart;
import edu.nju.git.ui.chart.UserContriReposBarChart;
import edu.nju.git.ui.chart.UserCreateLineChart;
import edu.nju.git.ui.chart.UserEmailBarChart;
import edu.nju.git.ui.chart.UserFollowerBarChart;
import edu.nju.git.ui.chart.UserGistBarChart;
import edu.nju.git.ui.chart.UserOwnReposBarChart;
import edu.nju.git.ui.chart.UserSubscriBarChart;
import edu.nju.git.ui.chart.UserTypePieChart;
import edu.nju.git.ui.control.FunctionPanel;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * chart controller
 * @author cuihao
 * @date 2016-03-31 16:37:21
 */
public class StatisticFunction extends FunctionPanel{

	@FXML private AnchorPane chartPane;
	
	private MyChart chart;
	private MyChartVO vo;
	private RepoChartBlService repoChart;
	private UserChartBlService userChart;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		repoChart = RepoChartBlImpl.instance();
		userChart = UserChartBlImpl.instance();
	}

	@Override
	public void initPanel(Object[] bundle) {
		ChartType type = (ChartType) bundle[0];
		//TODO can use reflection
		try {
			switch(type) {
			case RepoLanguage:
				chart = new RepoLanguageBarChart();
				vo = repoChart.statLanguage();
				break;
			case RepoCreateTime:
				chart = new RepoCreateTimeBarChart();
				vo = repoChart.statCreateTime();
				break;
			case RepoContributor:
				chart = new RepoContriBarChart();
				vo = repoChart.statContributors();
				break;
			case RepoCollaborator:
				chart = new RepoCollaBarChart();
				vo = repoChart.statCollaborators();
				break;
			case RepoSize:
				chart = new RepoSizeBarChart();
				vo = repoChart.statSize();
				break;
			case RepoSubscribor:
				chart = new RepoSubscriberBarChart();
				vo = repoChart.statSubscriber();
				break;
			case RepoForks:
				chart = new RepoForkBarChart();
				vo = repoChart.statForks();
				break;
			case RepoStars:
				chart = new RepoStarsBarChart();
				vo = repoChart.statStars();
				break;
			case UserType:
				chart = new UserTypePieChart();
				vo = userChart.statUserType();
				break;	
			case CompanyUser:
				chart = new CompanyUserBarChart();
				vo = userChart.statCompanyUser();
				break;
			case UserEmail:
				chart = new UserEmailBarChart();
				vo = userChart.statUserEmail();
				break;
			case UserOwnRepos:
				chart = new UserOwnReposBarChart();
				vo = userChart.statUserOwnRepo();
				break;
			case UserGist:
				chart = new UserGistBarChart();
				vo = userChart.statUserGist();
				break;
			case UserFollowers:
				chart = new UserFollowerBarChart();
				vo = userChart.statUserFolllowers();
				break;
			case UserCreate:
				chart = new UserCreateLineChart();
				vo = userChart.statUserCreateTime();
				break;
			case UserSubsRepos:
				chart = new UserSubscriBarChart();
				vo = userChart.statUserSubscrRepo();
				break;
			case UserCollaRepos:
				chart = new UserCollaReposBarChart();
				vo = userChart.statUserCollabRepo();
				break;
			case UserContriRepos:
				chart = new UserContriReposBarChart();
				vo = userChart.statUserContriRepo();
				break;	
			default:break;
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		chartPane.getChildren().add(chart.createContent(vo));
	}

	@Override
	public String getLocationName() {
		return "Language";
	}

}

package edu.nju.git.ui.control.function;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import edu.nju.git.VO.chartvo.MyChartVO;
import edu.nju.git.bl.impl.RepoChartBlImpl;
import edu.nju.git.bl.service.RepoChartBlService;
import edu.nju.git.ui.chart.ChartType;
import edu.nju.git.ui.chart.LanguageBarChart;
import edu.nju.git.ui.chart.MyChart;
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		repoChart = RepoChartBlImpl.instance();
	}

	@Override
	public void initPanel(Object[] bundle) {
		ChartType type = (ChartType) bundle[0];
		try {
			switch(type) {
			case RepoLanguage:
				chart = new LanguageBarChart();
				vo = repoChart.statLanguage();
				break;
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

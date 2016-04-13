package edu.nju.git.ui.chart;

import edu.nju.git.ui.chart.common.MyBarChart;
import edu.nju.git.ui.chart.common.MyPieChart;

public class RepoLanguageBarChart extends MyPieChart {

	@Override
	public String chartName() {
		return "Language Statistic";
	}

	@Override
	public double[] updown() {
		return new double[]{0,1000,200};
	}

	@Override
	protected double getHeight() {
		
		return 580;
	}

	@Override
	protected double getWidth() {
		
		return 800;
	}

}

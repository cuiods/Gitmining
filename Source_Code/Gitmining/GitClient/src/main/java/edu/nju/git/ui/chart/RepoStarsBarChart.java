package edu.nju.git.ui.chart;

import edu.nju.git.ui.chart.common.MyBarChart;

public class RepoStarsBarChart extends MyBarChart{

	@Override
	public String chartName() {
		return "Star Statistic";
	}

	@Override
	public double[] updown() {
		return new double[]{0,2500,500};
	}

}

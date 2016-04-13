package edu.nju.git.ui.chart;

import edu.nju.git.ui.chart.common.MyBarChart;

public class RepoForkBarChart extends MyBarChart{
	@Override
	public String chartName() {
		return "Forks Statistic";
	}

	@Override
	public double[] updown() {
		return new double[]{0,1800,300};
	}

}

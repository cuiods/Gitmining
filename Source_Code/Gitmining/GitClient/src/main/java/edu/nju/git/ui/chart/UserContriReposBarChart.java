package edu.nju.git.ui.chart;

import edu.nju.git.ui.chart.common.MyBarChart;

public class UserContriReposBarChart extends MyBarChart {

	@Override
	public String chartName() {
		return "User Num Ranging in Contribute Repositories";
	}

	@Override
	public double[] updown() {
		return new double[]{0,1200,120};
	}

}

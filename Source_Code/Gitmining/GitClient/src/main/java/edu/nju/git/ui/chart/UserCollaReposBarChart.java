package edu.nju.git.ui.chart;

import edu.nju.git.ui.chart.common.MyBarChart;

public class UserCollaReposBarChart extends MyBarChart {

	@Override
	public String chartName() {
		
		return "User Num Ranging in Collabrate Repositories";
	}

	@Override
	public double[] updown() {
		return new double[]{0,1500,150};
	}

}

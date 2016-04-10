package edu.nju.git.ui.chart;

import edu.nju.git.ui.chart.common.MyBarChart;

public class UserEmailBarChart extends MyBarChart {

	@Override
	public String chartName() {
		
		return "User Num Ranging in Different Emails";
	}

	@Override
	public double[] updown() {
		
		return new double[]{0,15000,1500};
	}

}

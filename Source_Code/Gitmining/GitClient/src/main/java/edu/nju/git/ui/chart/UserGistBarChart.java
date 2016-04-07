package edu.nju.git.ui.chart;

import edu.nju.git.ui.chart.common.MyBarChart;

public class UserGistBarChart extends MyBarChart {

	@Override
	public String chartName() {
		// TODO Auto-generated method stub
		return "User Num Ranging in Gist Num";
	}

	@Override
	public double[] updown() {
		// TODO Auto-generated method stub
		return new double[]{0,50000,5000};
	}

}

package edu.nju.git.ui.chart;

import edu.nju.git.ui.chart.common.MyBarChart;

public class RepoCreateTimeBarChart extends MyBarChart{

	@Override
	public String chartName() {
		return "create time";
	}

	@Override
	public double[] updown() {
		return new double[]{0,1500,300};
	}

}

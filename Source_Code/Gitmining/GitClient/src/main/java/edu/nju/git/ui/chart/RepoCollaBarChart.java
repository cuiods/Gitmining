package edu.nju.git.ui.chart;

import edu.nju.git.ui.chart.common.MyBarChart;

public class RepoCollaBarChart extends MyBarChart{

	@Override
	public String chartName() {
		return "Collaborator Statistic";
	}

	@Override
	public double[] updown() {
		return new double[]{0,2800,400};
	}

}

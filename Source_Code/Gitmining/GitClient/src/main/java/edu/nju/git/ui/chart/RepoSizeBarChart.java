package edu.nju.git.ui.chart;

public class RepoSizeBarChart extends MyBarChart{

	@Override
	public String chartName() {
		return "Size Statistic";
	}

	@Override
	public double[] updown() {
		return new double[]{0,1200,300};
	}

}

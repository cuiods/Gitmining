package edu.nju.git.ui.chart;

public class RepoForkBarChart extends MyBarChart{
	@Override
	public String chartName() {
		return "Forks Statistic";
	}

	@Override
	public double[] updown() {
		return new double[]{0,1500,300};
	}

}

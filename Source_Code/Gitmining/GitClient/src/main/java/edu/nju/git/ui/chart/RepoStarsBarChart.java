package edu.nju.git.ui.chart;

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

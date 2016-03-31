package edu.nju.git.ui.chart;

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

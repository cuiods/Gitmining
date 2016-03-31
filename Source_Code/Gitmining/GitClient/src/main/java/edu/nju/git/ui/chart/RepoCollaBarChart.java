package edu.nju.git.ui.chart;

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

package edu.nju.git.ui.chart;

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

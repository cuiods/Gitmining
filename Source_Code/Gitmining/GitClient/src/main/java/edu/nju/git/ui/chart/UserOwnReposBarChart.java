package edu.nju.git.ui.chart;

public class UserOwnReposBarChart extends MyBarChart {

	@Override
	public String chartName() {
		// TODO Auto-generated method stub
		return "User Num Ranging in Own Repository Num";
	}

	@Override
	public double[] updown() {
		// TODO Auto-generated method stub
		return new double[]{0,40000,4000};
	}

}

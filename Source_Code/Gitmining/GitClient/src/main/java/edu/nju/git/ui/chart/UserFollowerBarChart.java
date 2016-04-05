package edu.nju.git.ui.chart;

public class UserFollowerBarChart extends MyBarChart {

	@Override
	public String chartName() {
		// TODO Auto-generated method stub
		return "User Num Ranging in Followers' Num";
	}

	@Override
	public double[] updown() {
		// TODO Auto-generated method stub
		return new double[]{0,40000,4000};
	}

}

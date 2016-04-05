package edu.nju.git.ui.chart;

public class UserSubscriBarChart extends MyBarChart {

	@Override
	public String chartName() {
		// TODO Auto-generated method stub
		return "User Num Ranging in Subscribing Repositories";
	}

	@Override
	public double[] updown() {
		// TODO Auto-generated method stub
		return new double[]{0,1600,160};
	}

}

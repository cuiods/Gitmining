package edu.nju.git.ui.chart;

public class CompanyUserBarChart extends MyBarChart {

	@Override
	public String chartName() {
		return "User Num of Different Company";
	}

	@Override
	public double[] updown() {
		
		return new double[]{0,350,35};
	}

	

}

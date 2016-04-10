package edu.nju.git.ui.chart;

import edu.nju.git.ui.chart.common.MyAreaChart;

public class RepoAreaChart extends MyAreaChart{

	public RepoAreaChart() {
	}
	
	public RepoAreaChart(int width, int height) {
		super(width,height);
	}
	
	@Override
	public String getXName() {
		return "Time";
	}

	@Override
	public String getYName() {
		return "Activity";
	}

	@Override
	public String chartName() {
		return "Repository Activity Chart";
	}


}

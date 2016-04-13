package edu.nju.git.ui.chart;

import edu.nju.git.ui.chart.common.MyPieChart;

public class RepoLanguagePieChart extends MyPieChart{

	@Override
	protected double getHeight() {
		return 260;
	}

	@Override
	protected double getWidth() {
		return 263;
	}

	@Override
	public String chartName() {
		return "Language";
	}

}

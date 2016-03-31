package edu.nju.git.ui.chart;

import edu.nju.git.VO.chartvo.MyChartVO;
import javafx.scene.Parent;

/**
 * chart class used to generate a chart node.
 * @author cuihao
 * @date 2016-03-31 16:38:53
 */
public abstract class MyChart {
	/**
	 * create a chart node.
	 * @param chartVO
	 * 				chartVO used to generate a chart
	 * @return
	 * 				chart node
	 */
	public abstract Parent createContent(MyChartVO chartVO);
	/**
	 * get chart name
	 * @return
	 * 			name of the chart.
	 */
	public abstract String chartName();
}

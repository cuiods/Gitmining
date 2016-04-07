package edu.nju.git.ui.chart.common;

import java.util.ArrayList;

import edu.nju.git.VO.chartvo.MyChartVO;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public abstract class MyAreaChart extends MyChart{

	private AreaChart<String, Number> chart;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private int width = 800;
    private int height = 580;
    
    public MyAreaChart() {
	}
    
    public MyAreaChart(int width, int height) {
    	this.width = width;
    	this.height = height;
    }
	
	@Override
	public Node createContent(MyChartVO chartVO) {
		xAxis = new CategoryAxis(FXCollections.<String>observableArrayList(chartVO.getFields()));
		xAxis.setLabel(getXName());
		chart = new AreaChart<>(xAxis, yAxis);
		chart.setTitle(chartName());
		AreaChart.Series<String, Number> series =new AreaChart.Series<>();
        for (int i = 0; i < chartVO.getFields().length; i++) {
        	 series.getData().add(new AreaChart.Data<String, Number>(chartVO.getFields()[i], chartVO.getValues()[i]));
        }
        chart.getData().add(series);
        chart.setPrefSize(width, height);
        chart.setLegendVisible(false);
        return chart;
	}
	
	@Override
	public Node createContent(ArrayList<MyChartVO> vos) {
		assert vos.size() >= 1;
		xAxis = new CategoryAxis(FXCollections.<String>observableArrayList(vos.get(0).getFields()));
		xAxis.setLabel(getXName());
		yAxis = new NumberAxis();
		chart = new AreaChart<>(xAxis, yAxis);
		chart.setTitle(chartName());
		for(MyChartVO chartVO: vos) {
			AreaChart.Series<String, Number> series =new AreaChart.Series<>();
	        for (int i = 0; i < chartVO.getFields().length; i++) {
	        	 series.getData().add(new XYChart.Data<String, Number>(chartVO.getFields()[i], chartVO.getValues()[i]));
	        }
	        chart.getData().add(series);
		}
        chart.setPrefSize(width, height);
        chart.setLegendVisible(false);
        return chart;
	}
	
	/**
	 * @return
	 * 		description of X value of the chart
	 */
	public abstract String getXName();
	/**
	 * @return
	 * 		description of Y value of the chart
	 */
	public abstract String getYName();
	
	@Override
	public double[] updown() {
		return null;
	}

}

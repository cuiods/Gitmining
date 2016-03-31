package edu.nju.git.ui.chart;

import java.util.Arrays;

import edu.nju.git.VO.chartvo.MyChartVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class LanguageBarChart {
	
	private BarChart chart;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Parent createContent(MyChartVO chartVO) {
        xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(chartVO.getFields()));
        yAxis = new NumberAxis("Units Sold", 0.0d, 1000.0d, 200.0d);
        chart = new BarChart(xAxis, yAxis);
        
        chart.getData().add(new XYChart.Data(1, 1));
        return chart;
    }

}

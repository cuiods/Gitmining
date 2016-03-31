package edu.nju.git.ui.chart;

import edu.nju.git.VO.chartvo.MyChartVO;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class LanguageBarChart extends MyChart{
	
	private BarChart<String, Number> chart;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
 
    @Override
	public Parent createContent(MyChartVO chartVO) {
        xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(chartVO.getFields()));
        yAxis = new NumberAxis("Value", 0.0d, 1000.0d, 200.0d);
    	chart = new BarChart<>(xAxis, yAxis);
		chart.setTitle("Language Statistic");
        XYChart.Series<String, Number> series =new XYChart.Series<>();
        for (int i = 0; i < chartVO.getFields().length; i++) {
        	 series.getData().add(new XYChart.Data<String, Number>(chartVO.getFields()[i], chartVO.getValues()[i]));
        }
        chart.getData().add(series);
        return chart;
    }

	@Override
	public String chartName() {
		return "Repository Language Bar Chart";
	}

}

package edu.nju.git.ui.chart;

import edu.nju.git.VO.chartvo.MyChartVO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

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
        	 series.setName(chartVO.getFields()[i]);
        	 series.getData().add(new XYChart.Data<String, Number>(chartVO.getFields()[i], chartVO.getValues()[i]));
        }
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(100), 
            new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent actionEvent) {
                for (XYChart.Series<String, Number> series : chart.getData()) {
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        data.setYValue(0);
                    }
                }
            }
        }), new KeyFrame(Duration.millis(1000), 
        		new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						for (XYChart.Series<String, Number> series : chart.getData()) {
		                    for (int i = 0; i < chartVO.getFields().length; i++) {
		                        series.getData().get(i).setYValue(chartVO.getValues()[i]);
		                    }
		                }
					}      	
        }));
        tl.play();
        chart.getData().add(series);
        chart.setPrefSize(800, 580);
        chart.setLegendVisible(false);
        return chart;
    }

	@Override
	public String chartName() {
		return "Repository Language Bar Chart";
	}

}

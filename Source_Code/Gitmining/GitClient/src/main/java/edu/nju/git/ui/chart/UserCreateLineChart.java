package edu.nju.git.ui.chart;

import java.util.ArrayList;

import edu.nju.git.VO.chartvo.MyChartVO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

public class UserCreateLineChart extends MyChart {
	private LineChart<String, Number> chart;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private int index;

	@Override
	public String chartName() {
		return "User Num Ranging in CreateTime";
	}

	@Override
	public Parent createContent(MyChartVO chartVO) {
		xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(chartVO.getFields()));
        double[] bound = updown();
        yAxis = new NumberAxis("Value", bound[0],bound[1], bound[2]);
    	chart = new LineChart<>(xAxis, yAxis);
		chart.setTitle(chartName());
		XYChart.Series<String, Number> series =new XYChart.Series<>();
		
//		
//		ArrayList<KeyFrame> animationData= new ArrayList<KeyFrame>();
//		for (int i=0;i<chartVO.getFields().length;i++){
//			index = i;
//			KeyFrame key = new KeyFrame(Duration.millis(i*500),
//					new EventHandler<ActionEvent>(){
//
//				@Override
//				public void handle(ActionEvent event) {
//					for (XYChart.Series<String, Number> series : chart.getData()) {
//	                	series.getData().add(new XYChart.Data<String, Number>(chartVO.getFields()[index], chartVO.getValues()[index]));
//	                }
//					
//				}
//			});
//			animationData.add(key);
//		}
//        Timeline tl = new Timeline(animationData);
		Timeline tl = new Timeline(new KeyFrame(Duration.millis(10),
				new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				for (XYChart.Series<String, Number> series : chart.getData()) {
                	series.getData().add(new XYChart.Data<String, Number>(chartVO.getFields()[0], chartVO.getValues()[0]));
                }
				
			}
		}),new KeyFrame(Duration.millis(500),
				new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				for (XYChart.Series<String, Number> series : chart.getData()) {
                	series.getData().add(new XYChart.Data<String, Number>(chartVO.getFields()[1], chartVO.getValues()[1]));
                }
				
			}
		}),new KeyFrame(Duration.millis(1000),
				new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				for (XYChart.Series<String, Number> series : chart.getData()) {
                	series.getData().add(new XYChart.Data<String, Number>(chartVO.getFields()[2], chartVO.getValues()[2]));
                }
				
			}
		}),new KeyFrame(Duration.millis(1500),
				new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				for (XYChart.Series<String, Number> series : chart.getData()) {
                	series.getData().add(new XYChart.Data<String, Number>(chartVO.getFields()[3], chartVO.getValues()[3]));
                }
				
			}
		}),new KeyFrame(Duration.millis(2000),
				new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				for (XYChart.Series<String, Number> series : chart.getData()) {
                	series.getData().add(new XYChart.Data<String, Number>(chartVO.getFields()[4], chartVO.getValues()[4]));
                }
				
			}
		}),new KeyFrame(Duration.millis(2500),
				new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				for (XYChart.Series<String, Number> series : chart.getData()) {
                	series.getData().add(new XYChart.Data<String, Number>(chartVO.getFields()[5], chartVO.getValues()[5]));
                }
				
			}
		}),new KeyFrame(Duration.millis(3000),
				new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				for (XYChart.Series<String, Number> series : chart.getData()) {
                	series.getData().add(new XYChart.Data<String, Number>(chartVO.getFields()[6], chartVO.getValues()[6]));
                }
				
			}
		}),new KeyFrame(Duration.millis(3500),
				new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				for (XYChart.Series<String, Number> series : chart.getData()) {
                	series.getData().add(new XYChart.Data<String, Number>(chartVO.getFields()[7], chartVO.getValues()[7]));
                }
				
			}
		})
				);
        tl.play();
        chart.getData().add(series);
        chart.setPrefSize(800, 580);
        chart.setLegendVisible(false);
        return chart;
	}

	@Override
	public double[] updown() {
		return new double[]{0,14000,1400};
	}

}

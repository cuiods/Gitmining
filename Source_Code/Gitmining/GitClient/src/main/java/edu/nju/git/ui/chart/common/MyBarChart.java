package edu.nju.git.ui.chart.common;

import edu.nju.git.VO.chartvo.MyChartVO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public abstract class MyBarChart extends MyChart{
	
	private BarChart<String, Number> chart;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
 
    @Override
	public Node createContent(MyChartVO chartVO) {
        xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(chartVO.getFields()));
        double[] bound = updown();
        yAxis = new NumberAxis("Value", bound[0],bound[1], bound[2]);
    	chart = new BarChart<>(xAxis, yAxis);
		chart.setTitle(chartName());
		XYChart.Series<String, Number> series =new XYChart.Series<>();
        for (int i = 0; i < chartVO.getFields().length; i++) {
        	 series.setName(chartVO.getFields()[i]);
        	 XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(chartVO.getFields()[i], 0);
        	 data.setNode(new HoveredNode(chartVO.getValues()[i]));
        	 series.getData().add(data);
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
    
    class HoveredNode extends StackPane {
    	public HoveredNode(int value) {
    		final Label label = createDataThresholdLabel(value);

			setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					getChildren().add(label);
					setCursor(Cursor.NONE);
					toFront();
				}
			});
			setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					getChildren().clear();
					setCursor(Cursor.CROSSHAIR);
				}
			});
		}
    }
    
    private Label createDataThresholdLabel(int value) {
		final Label label = new Label(value + "");
		label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
		label.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");

		label.setTextFill(Color.FIREBRICK);

		label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
		return label;
	}


}

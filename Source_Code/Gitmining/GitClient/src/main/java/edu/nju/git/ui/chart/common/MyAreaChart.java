package edu.nju.git.ui.chart.common;

import java.util.ArrayList;

import edu.nju.git.VO.chartvo.MyChartVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

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
		ArrayList<XYChart.Series<String, Number>> serieslist = new ArrayList<>();
		for(MyChartVO chartVO: vos) {
			AreaChart.Series<String, Number> series =new AreaChart.Series<>();
	        for (int i = 0; i < chartVO.getFields().length; i++) {
	        	XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(chartVO.getFields()[i], chartVO.getValues()[i]);
	        	data.setNode(new HoveredThresholdNode(i==0?0:chartVO.getValues()[i-1], chartVO.getValues()[i]));
	        	series.getData().add(data);
	        }
	        serieslist.add(series);
		}
		chart.getData().addAll(serieslist);
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
	
	/** a node which displays a value on hover, but is otherwise empty */
	class HoveredThresholdNode extends StackPane {
		public HoveredThresholdNode(int priorValue, int value) {
			setPrefSize(10, 10);

			final Label label = createDataThresholdLabel(priorValue, value);

			setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					getChildren().setAll(label);
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

		private Label createDataThresholdLabel(int priorValue, int value) {
			final Label label = new Label(value + "");
			label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
			label.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

			if (priorValue == 0) {
				label.setTextFill(Color.DARKGRAY);
			} else if (value > priorValue) {
				label.setTextFill(Color.FORESTGREEN);
			} else {
				label.setTextFill(Color.FIREBRICK);
			}

			label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
			return label;
		}
	}

}

package edu.nju.git.ui.chart;

import edu.nju.git.VO.chartvo.MyChartVO;
import edu.nju.git.ui.chart.common.MyChart;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
		xAxis.setCategories(FXCollections.<String> observableArrayList(chartVO.getFields()));
		double[] bound = updown();
		yAxis = new NumberAxis("Value", bound[0], bound[1], bound[2]);
		chart = new LineChart<>(xAxis, yAxis);
		chart.setTitle(chartName());
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		chart.getData().add(series);

		//
		// ArrayList<KeyFrame> animationData= new ArrayList<KeyFrame>();
		// for (int i=0;i<chartVO.getFields().length;i++){
		// index = i;
		// KeyFrame key = new KeyFrame(Duration.millis(i*500),
		// new EventHandler<ActionEvent>(){
		//
		// @Override
		// public void handle(ActionEvent event) {
		// for (XYChart.Series<String, Number> series : chart.getData()) {
		// series.getData().add(new XYChart.Data<String,
		// Number>(chartVO.getFields()[index], chartVO.getValues()[index]));
		// }
		//
		// }
		// });
		// animationData.add(key);
		// }
		// Timeline tl = new Timeline(animationData);
		Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//for (XYChart.Series<String, Number> series : chart.getData()) {
				XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(chartVO.getFields()[0], chartVO.getValues()[0]);
				data.setNode(new HoveredThresholdNode(0,chartVO.getValues()[0]));
				series.getData().add(data);
				//}

			}
		}), new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//for (XYChart.Series<String, Number> series : chart.getData()) {
				XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(chartVO.getFields()[1], chartVO.getValues()[1]);
				data.setNode(new HoveredThresholdNode(chartVO.getValues()[0],chartVO.getValues()[1]));
				series.getData().add(data);
				//}

			}
		}), new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//for (XYChart.Series<String, Number> series : chart.getData()) {
				XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(chartVO.getFields()[2], chartVO.getValues()[2]);
				data.setNode(new HoveredThresholdNode(chartVO.getValues()[1],chartVO.getValues()[2]));
				series.getData().add(data);
				//}

			}
		}), new KeyFrame(Duration.millis(1500), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//for (XYChart.Series<String, Number> series : chart.getData()) {
				XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(chartVO.getFields()[3], chartVO.getValues()[3]);
				data.setNode(new HoveredThresholdNode(chartVO.getValues()[2],chartVO.getValues()[3]));
				series.getData().add(data);
				//}

			}
		}), new KeyFrame(Duration.millis(2000), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//for (XYChart.Series<String, Number> series : chart.getData()) {
				XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(chartVO.getFields()[4], chartVO.getValues()[4]);
				data.setNode(new HoveredThresholdNode(chartVO.getValues()[3],chartVO.getValues()[4]));
				series.getData().add(data);
				//}

			}
		}), new KeyFrame(Duration.millis(2500), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//for (XYChart.Series<String, Number> series : chart.getData()) {
				XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(chartVO.getFields()[5], chartVO.getValues()[5]);
				data.setNode(new HoveredThresholdNode(chartVO.getValues()[4],chartVO.getValues()[5]));
				series.getData().add(data);
				//}

			}
		}), new KeyFrame(Duration.millis(3000), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//for (XYChart.Series<String, Number> series : chart.getData()) {
				XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(chartVO.getFields()[6], chartVO.getValues()[6]);
				data.setNode(new HoveredThresholdNode(chartVO.getValues()[5],chartVO.getValues()[6]));
				series.getData().add(data);
				//}

			}
		}), new KeyFrame(Duration.millis(3500), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//for (XYChart.Series<String, Number> series : chart.getData()) {
				XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(chartVO.getFields()[7], chartVO.getValues()[7]);
				data.setNode(new HoveredThresholdNode(chartVO.getValues()[6],chartVO.getValues()[7]));
				series.getData().add(data);
				//}

			}
		}));
		tl.play();
		ObservableList<XYChart.Data<String, Number>> dataset = series.getData();
		for(int i = 0; i < dataset.size(); i++) {
			final XYChart.Data<String, Number> data = dataset.get(i);
			data.setNode(new HoveredThresholdNode(i==0?0:dataset.get(i-1).getYValue().intValue(),dataset.get(i).getYValue().intValue()));
		}
		
		chart.setPrefSize(800, 580);
		chart.setLegendVisible(false);
		return chart;
	}

	@Override
	public double[] updown() {
		return new double[] { 0, 14000, 1400 };
	}

	/** a node which displays a value on hover, but is otherwise empty */
	class HoveredThresholdNode extends StackPane {
		public HoveredThresholdNode(int priorValue, int value) {
			setPrefSize(15, 15);

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
			label.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

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

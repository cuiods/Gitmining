package edu.nju.git.ui.chart.common;

import java.text.DecimalFormat;

import edu.nju.git.VO.chartvo.MyChartVO;
import javafx.animation.TranslateTransitionBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

@SuppressWarnings("deprecation")
public abstract class MyPieChart extends MyChart{

	@Override
	public Node createContent(MyChartVO chartVO) {
		PieChart.Data dataList[] = new PieChart.Data[chartVO.getFields().length];
		int sum = 0;
		for(int i=0;i<chartVO.getFields().length;i++){
			sum += chartVO.getValues()[i];
		}
		DecimalFormat    df   = new DecimalFormat("######0.00");   
		for(int i=0;i<chartVO.getFields().length;i++){
			dataList[i] = new 
					PieChart.Data(chartVO.getFields()[i]+": "/*+df.format(chartVO.getValues()[i]*1.0/sum*100)+"%"*/, chartVO.getValues()[i]);
		}
		ObservableList<PieChart.Data> pieChartData = FXCollections
				.observableArrayList(dataList);
		PieChart chart = new PieChart(pieChartData);
		
		for (PieChart.Data d : pieChartData) {
			d.getNode().setOnMouseEntered(new MouseHoverAnimation(d, chart));
			d.getNode().setOnMouseExited(new MouseExitAnimation());
		}
		chart.setClockwise(false);
		chart.setLabelLineLength(5);
		//chart.setTitle(chartName());
		chart.setPrefSize(getWidth(), getHeight());
		chart.setLegendSide(Side.BOTTOM);
		chart.setStyle("-fx-font-size:9;");
		return chart;
	}

	@Override
	public abstract String chartName();

	@Override
	public double[] updown() {
		return null;
	}
	
	protected abstract double getHeight();
	protected abstract double getWidth();
	
	static class MouseHoverAnimation implements EventHandler<MouseEvent> {
		static final Duration ANIMATION_DURATION = new Duration(500);
		static final double ANIMATION_DISTANCE = 0.04;
		private double cos;
		private double sin;
		private PieChart chart;

		public MouseHoverAnimation(PieChart.Data d, PieChart chart) {
			this.chart = chart;
			double start = 0;
			double angle = calcAngle(d);
			for (PieChart.Data tmp : chart.getData()) {
				if (tmp == d) {
					break;
				}
				start += calcAngle(tmp);
			}

			cos = Math.cos(Math.toRadians(start + angle / 2));
			sin = Math.sin(Math.toRadians(start + angle / 2));
		}

		@Override
		public void handle(MouseEvent arg0) {
			Node n = (Node) arg0.getSource();
			double minX = Double.MAX_VALUE;
			double maxX = Double.MAX_VALUE * -1;

			for (PieChart.Data d : chart.getData()) {
				minX = Math
					.min(minX, d.getNode().getBoundsInParent().getMinX());
				maxX = Math
					.max(maxX, d.getNode().getBoundsInParent().getMaxX());
		}

		double radius = maxX - minX;
		TranslateTransitionBuilder.create()
		.toX((radius * ANIMATION_DISTANCE) * cos)
		.toY((radius * ANIMATION_DISTANCE) * (-sin))
		.duration(ANIMATION_DURATION).node(n).build().play();
		}

		private static double calcAngle(PieChart.Data d) {
			double total = 0;
			for (PieChart.Data tmp : d.getChart().getData()) {
				total += tmp.getPieValue();
			}
			return 360 * (d.getPieValue() / total);
		}
	}

	static class MouseExitAnimation implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			TranslateTransitionBuilder.create().toX(0).toY(0)
			.duration(new Duration(500)).node((Node) event.getSource())
			.build().play();
		}
	}

}

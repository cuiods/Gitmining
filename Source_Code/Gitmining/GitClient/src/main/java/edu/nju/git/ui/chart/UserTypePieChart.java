package edu.nju.git.ui.chart;

import java.text.DecimalFormat;

import edu.nju.git.VO.chartvo.MyChartVO;
import edu.nju.git.ui.chart.common.MyChart;
import javafx.animation.TranslateTransitionBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class UserTypePieChart extends MyChart{

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
					PieChart.Data(chartVO.getFields()[i]+": "+df.format(chartVO.getValues()[i]*1.0/sum*100)+"%", chartVO.getValues()[i]);
		}
		ObservableList<PieChart.Data> pieChartData = FXCollections
				.observableArrayList(dataList);
		PieChart chart = new PieChart(pieChartData);

		for (PieChart.Data d : pieChartData) {
			d.getNode().setOnMouseEntered(new MouseHoverAnimation(d, chart));
			d.getNode().setOnMouseExited(new MouseExitAnimation());
		}

		chart.setClockwise(false);
		chart.setTitle(chartName());
		chart.setPrefSize(800, 580);
		return chart;
	}

	@Override
	public String chartName() {
		return "User Type Between User and Orgnization";
	}

	@Override
	public double[] updown() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	static class MouseHoverAnimation implements EventHandler<MouseEvent> {
		static final Duration ANIMATION_DURATION = new Duration(500);
		static final double ANIMATION_DISTANCE = 0.06;
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
		System.out.println("cos:"+ cos);
		System.out.println("sin"+ sin);
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

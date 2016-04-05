package edu.nju.git.ui.chart;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleEdge;

import javafx.embed.swing.SwingNode;
import javafx.scene.Node;

/**
 * Radar or spider chart.<br>
 * {@link #createComponent()}: This method returns a javafx node.
 * @author cuihao
 * @date 2016-04-05 18:58:25
 */
public abstract class MySpiderChart {
	private int width;
	private int height;
	public MySpiderChart(int width, int height) {
		this.width = width;
		this.height = height;
	}
	/**
	 * chart name
	 * @return
	 */
	public abstract String getChartName();
	/**
	 * chart data
	 * @return
	 */
	public abstract CategoryDataset getSpiderData();

	/**
	 * number of chart rings.
	 * @return
	 */
	public abstract int getTicks();
	/**
	 * whether draw rings in the chart.
	 * @return
	 */
	public boolean drawRings(){
		return true;
	}
	/**
	 * Create javafx node
	 * @return
	 *  				{@link Node}
	 */
	public Node createComponent() {
		SwingNode swingNode = new SwingNode();
		createSwingNode(swingNode);
		return swingNode;
	}
	/**
	 * add swing node.
	 * @param node
	 */
	private void createSwingNode(SwingNode node) {
		SwingUtilities.invokeLater(() -> {
			node.setContent(getSwingComponent());
		});
	}
	/**
	 * add swing component
	 * @return
	 */
	private JComponent getSwingComponent() {
		JFreeChart chart = createChart();
		ChartPanel panel = new ChartPanel(chart);
		panel.setPreferredSize(new Dimension(width, height));
		return panel;
	}

	private JFreeChart createChart() {
		MySpiderWebPlot spider = new MySpiderWebPlot(getSpiderData());
		spider.setBackgroundAlpha(0);
		spider.setMaxValue(1);
		JFreeChart chart = new JFreeChart(getChartName(), TextTitle.DEFAULT_FONT, spider, false);
		chart.setBackgroundPaint(ChartColor.WHITE);
		chart.setBorderVisible(false);
		LegendTitle legendtitle = new LegendTitle(spider);
		legendtitle.setPosition(RectangleEdge.BOTTOM);
		chart.addSubtitle(legendtitle);
		return chart;
	}
	
}

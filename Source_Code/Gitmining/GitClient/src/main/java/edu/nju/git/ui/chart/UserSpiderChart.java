package edu.nju.git.ui.chart;

import java.util.ArrayList;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import edu.nju.git.VO.UserVO;

public class UserSpiderChart extends MySpiderChart{
	private ArrayList<UserVO> users ;
	public UserSpiderChart(ArrayList<UserVO> vo, int width, int height) {
		super(width, height);
		users = vo;
	}
	@Override
	public String getChartName() {
		return "User Radar Chart";
	}
	@Override
	public CategoryDataset getSpiderData() {
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		for (UserVO user: users) {
			String group = user.getName();
			data.addValue(user.getRadar_activity(), group, "Activity");
			data.addValue(user.getRadar_follower(), group, "Follower");
			data.addValue(user.getRadar_gist(), group, "Gists");
			data.addValue(user.getRadar_ownrepos(), group, "Repositories");
			data.addValue(user.getRadar_value(), group, "Value");
		}
		return data;
	}
	@Override
	public int getTicks() {
		return 5;
	}
}

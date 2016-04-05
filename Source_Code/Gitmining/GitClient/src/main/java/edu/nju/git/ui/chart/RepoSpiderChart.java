package edu.nju.git.ui.chart;

import java.util.ArrayList;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import edu.nju.git.VO.RepoVO;

public class RepoSpiderChart extends MySpiderChart{

	private ArrayList<RepoVO> repos ;
	public RepoSpiderChart(ArrayList<RepoVO> vo, int width, int height) {
		super(width, height);
		repos = vo;
	}
	
	@Override
	public String getChartName() {
		return "Repository Radar Chart";
	}

	@Override
	public CategoryDataset getSpiderData() {
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		for(RepoVO repo: repos) {
			String group = repo.getName();
			data.addValue(repo.getRadar_activity(), group, "Activity");
			data.addValue(repo.getRadar_complexity(), group, "Complexity");
			data.addValue(repo.getRadar_forks(), group, "Forks");
			data.addValue(repo.getRadar_popular(), group, "Popularity");
			System.out.println(repo.getRadar_size());
			data.addValue(repo.getRadar_size(), group, "Size");
		}
		return data;
	}

	@Override
	public int getTicks() {
		return 5;
	}

}

package edu.nju.git.ui.control.function;

import java.lang.reflect.Field;
import java.util.ArrayList;

import edu.nju.git.VO.RepoVO;
import edu.nju.git.VO.chartvo.MyChartVO;
import edu.nju.git.ui.chart.RepoAreaChart;
import edu.nju.git.ui.chart.RepoSpiderChart;
import edu.nju.git.ui.chart.common.MyAreaChart;
import edu.nju.git.ui.control.FunctionPanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * Repository compare function.
 * @author cuihao
 * @date 2016-04-06 12:32:10
 */
public class RepoCompareFunction extends FunctionPanel{

	@FXML private AnchorPane radar;
	@FXML private AnchorPane activity;
	@FXML private TableView<RepoVO> repoTable;
	
	private ArrayList<RepoVO> repos;

	@SuppressWarnings("unchecked")
	@Override
	public void initPanel(Object[] bundle) {
		repos = (ArrayList<RepoVO>) bundle[0];
		RepoSpiderChart chart = new RepoSpiderChart(repos, 380, 250);
		radar.getChildren().add(chart.createComponent());
		ObservableList<RepoVO> tablevos = FXCollections.observableArrayList(repos);
		repoTable.setItems(tablevos);
		addTableItems();
		ArrayList<MyChartVO> vos = new ArrayList<>();
		for(RepoVO repo: repos) {
			vos.add(repo.getActivityChart());
		}
		MyAreaChart areaChart = new RepoAreaChart(390,270);
		activity.getChildren().add(areaChart.createContent(vos));
	}

	@Override
	public String getLocationName() {		
		return "Repository Compare";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addTableItems(){
		Field[] fields = RepoVO.class.getDeclaredFields();
		try {
            for(int i = 0; i < fields.length; i++) {
            	String name = fields[i].getName();
            	String type = fields[i].getGenericType().toString();
            	if(type.equals("class java.lang.String")) {
					TableColumn column = new TableColumn<>(name);
					column.setMaxWidth(150);
            		column.setCellValueFactory(new PropertyValueFactory<RepoVO,String>(name));
            		repoTable.getColumns().add(column);
            	}
            	if(type.equals("int")) {
            		String[] temp=name.split("_");
					TableColumn column = new TableColumn<>(temp.length>1? temp[1]:temp[0]);
					column.setMaxWidth(150);
            		column.setCellValueFactory(new PropertyValueFactory<RepoVO,Integer>(name));
            		repoTable.getColumns().add(column);
            	}
            	if(type.equals("double")) {
            		TableColumn column = new TableColumn<>(name);
            		column.setMaxWidth(150);
            		column.setCellValueFactory(new PropertyValueFactory<RepoVO,Double>(name));
            		repoTable.getColumns().add(column);
            	}
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
	}

}

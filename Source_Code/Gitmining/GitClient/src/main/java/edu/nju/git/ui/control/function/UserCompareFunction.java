package edu.nju.git.ui.control.function;

import java.lang.reflect.Field;
import java.util.ArrayList;

import edu.nju.git.VO.RepoVO;
import edu.nju.git.VO.UserVO;
import edu.nju.git.ui.chart.UserSpiderChart;
import edu.nju.git.ui.control.FunctionPanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class UserCompareFunction extends FunctionPanel{
	@FXML private AnchorPane radar;
	@FXML private TableView<UserVO> userTable;
	
	private ArrayList<UserVO> users;

	@Override
	public String getLocationName() {
		return "User Compare";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initPanel(Object[] bundle) {
		users = (ArrayList<UserVO>) bundle[0];
		UserSpiderChart chart = new UserSpiderChart(users, 380, 250);
		radar.getChildren().add(chart.createComponent());
		ObservableList<UserVO> uservos = FXCollections.observableArrayList(users);
		userTable.setItems(uservos);
		addTableItems();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addTableItems(){
		Field[] fields = UserVO.class.getDeclaredFields();
		try {
            for(int i = 0; i < fields.length; i++) {
            	String name = fields[i].getName();
            	String type = fields[i].getGenericType().toString();
            	if(type.equals("class java.lang.String")) {
					TableColumn column = new TableColumn<>(name);
					column.setMaxWidth(150);
            		column.setCellValueFactory(new PropertyValueFactory<RepoVO,String>(name));
            		userTable.getColumns().add(column);
            	}
            	if(type.equals("int")) {
            		String[] temp=name.split("_");
					TableColumn column = new TableColumn<>(temp.length>1? temp[1]:temp[0]);
					column.setMaxWidth(150);
            		column.setCellValueFactory(new PropertyValueFactory<RepoVO,Integer>(name));
            		userTable.getColumns().add(column);
            	}
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
	}

}

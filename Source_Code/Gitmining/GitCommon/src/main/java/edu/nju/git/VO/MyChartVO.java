package edu.nju.git.VO;

import java.io.Serializable;
import java.util.List;
/**
 * the VO offer all information to draw a chart except radar chart.
 * @author daixinyan
 * @date 2016-03-21
 */
public class MyChartVO implements Serializable{
	
	private static final long serialVersionUID = 4739675295257908042L;
	/**the biggest value in chart*/
	 private int maxValue;
	/**the smallest value in chart*/
	private int minValue;
	
	
	/**the sum for calculating percentage*/
	 private int sumOfValue;
	 
	 private List<String> fileds;
	 private List<Integer> values;
	public int getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	public int getMinValue() {
		return minValue;
	}
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
	public int getSumOfValue() {
		return sumOfValue;
	}
	public void setSumOfValue(int sumOfValue) {
		this.sumOfValue = sumOfValue;
	}
	public List<String> getFileds() {
		return fileds;
	}
	public void setFileds(List<String> fileds) {
		this.fileds = fileds;
	}
	public List<Integer> getValues() {
		return values;
	}
	public void setValues(List<Integer> values) {
		this.values = values;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	 
	 
}

package edu.nju.git.VO.chartvo;

import java.io.Serializable;
/**
 * the VO offer all information to draw a chart except radar chart.
 * @author daixinyan
 * @date 2016-03-21
 */
public class MyChartVO implements Serializable{
	
	protected static final long serialVersionUID = 4739675295257908042L;
	 
	 protected String[] fields;
	 protected int[] values;

	public String[] getFields() {
		return fields;
	}

	public int[] getValues() {
		return values;
	}
	
	public void setChartVO(String[] fields, int[] values) {
		this.fields = fields;
		this.values = values;
	}
}

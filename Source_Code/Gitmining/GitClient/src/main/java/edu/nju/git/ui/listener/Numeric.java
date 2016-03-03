package edu.nju.git.ui.listener;
/**
 *@author tj
 *@date 2015年12月6日
 */
public class Numeric {
	public static boolean isNumeric(String str) {
		if(str.isEmpty()){
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if ((!Character.isDigit(str.charAt(i)))&&(str.charAt(i)!='.')) {
				return false;
			}
		}
		return true;
	}
}

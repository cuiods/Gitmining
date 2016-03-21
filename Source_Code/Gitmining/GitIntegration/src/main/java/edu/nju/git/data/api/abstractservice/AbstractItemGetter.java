package edu.nju.git.data.api.abstractservice;

/**
 * the parent class of pofactories ,who read a po by single api.
 * ( we only get one value by one such api)
 * @author daixinyan
 * @date 2016-03-08
 * @param <T>
 */
public abstract class AbstractItemGetter implements FieldsGetterService{

	/**
	 * @see ItemService
	 */
	
	
	public AbstractItemGetter(){}
	
	public int getInteger(String string)
	{
		try{
			return Integer.parseInt(this.getItem(string));
		}catch(NumberFormatException exception){
			exception.printStackTrace();
			return 0;
		}
		
	}
	
	public String getString(String item)
	{
		return this.getItem(item).replace("\"", "");
	}
	
	public boolean getBoolean(String item)
	{
		return Boolean.parseBoolean(this.getItem(item));
	}
	
	public abstract String getItem(String item);
	
}

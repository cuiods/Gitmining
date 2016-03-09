package edu.nju.git.data.factory.impl.POfactory;

public abstract class AbstractPOfactory{

	protected abstract int getInteger(String string);
	
	protected abstract String getString(String item);
	
	protected abstract boolean getBoolean(String item);
}

package edu.nju.git.data.api.listdocument;

import com.fasterxml.jackson.databind.JsonNode;

import edu.nju.git.data.factory.impl.POfactory.AbstractFieldsGetter;

public class Document extends AbstractFieldsGetter{

	protected JsonNode fatherJsonNode;
	public Document() {	}
	public void setJsonNode(JsonNode node){
		fatherJsonNode = node;
	}
	
	@Override
	protected int getInteger(String string) {
		try{
			return (this.getItem(string).asInt(0));
		}catch(Exception exception){
			exception.printStackTrace();
			return 0;
		}
	}

	public  JsonNode getItem(String item){
		try{
			JsonNode temp = fatherJsonNode.findValue(item);
			return temp;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	protected String getString(String item) {
		JsonNode node =  this.getItem(item);
		String re= node==null? "":node.textValue();
		return re==null?"":re;
	}

	@Override
	protected boolean getBoolean(String item) {
		JsonNode node =  this.getItem(item);
		return node==null? false:node.asBoolean();
	}
}

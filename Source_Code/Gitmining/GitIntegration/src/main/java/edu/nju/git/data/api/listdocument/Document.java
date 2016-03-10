package edu.nju.git.data.api.listdocument;

import com.fasterxml.jackson.databind.JsonNode;

import edu.nju.git.data.api.abstractservice.FieldsGetterService;

public class Document implements FieldsGetterService{

	protected JsonNode fatherJsonNode;
	public Document() {	}
	public void setJsonNode(JsonNode node){
		fatherJsonNode = node;
	}
	
	@Override
	public int getInteger(String string) {
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
	public String getString(String item) {
		JsonNode node =  this.getItem(item);
		String re= node==null? "":node.textValue();
		return re==null?"":re;
	}

	@Override
	public boolean getBoolean(String item) {
		JsonNode node =  this.getItem(item);
		return node==null? false:node.asBoolean();
	}
}

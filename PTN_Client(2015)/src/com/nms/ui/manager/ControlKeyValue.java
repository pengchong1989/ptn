package com.nms.ui.manager;

public class ControlKeyValue {
	
	private String id;
	private String name;
	private Object object;
	
	public ControlKeyValue(String id , String value){
		this.id=id; 
		this.name=value;
	}
	
	public ControlKeyValue(String id , String value,Object object){
		this.id=id;
		this.name=value;
		this.object=object;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public Object getObject() {
		return object;
	}

	@Override
	public String toString() {
	      return name;
	}
}

package com.nms.db.enums;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

/**
 * qos方向枚举类
 * @author kk
 *
 */
public enum EQosDirection {
	FORWARD(1),BACKWARD(2);
	
	private int value;
	
	private EQosDirection(int value){
		this.value = value;
	}
	public int getValue(){
		return this.value;
	}
	public static EQosDirection from(int value){
		for(EQosDirection cos : EQosDirection.values()){
			if( value == cos.getValue()){
				return cos;
			}
		}
		return null;
	}
	
	@Override
	public String toString(){
		if(value == 1){
			return ResourceUtil.srcStr(StringKeysObj.STRING_FORWARD);
		}else if(value == 2){
			return ResourceUtil.srcStr(StringKeysObj.STRING_BACKWARD);
		}
		return "";
	}
}

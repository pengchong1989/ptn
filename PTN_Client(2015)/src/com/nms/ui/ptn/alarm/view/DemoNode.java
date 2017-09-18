/*
 * This source code is part of TWaver 4.1
 *
 * Serva Software PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * Copyright 2002 - 2011 Serva Software. All rights reserved.
 */

package com.nms.ui.ptn.alarm.view;

import twaver.Element;
import twaver.Node;
import twaver.TWaverUtil;

public class DemoNode extends Node {
	
    private Class demoClass = null;
    private DemoPane demoPane = null;
    
    public DemoNode(Element parent, Class demoClass) {
        this.setParent(parent);
        this.demoClass = demoClass;
        String className = TWaverUtil.getClassNameWithoutPackage(demoClass);
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<className.length(); i++){
        	char c = className.charAt(i);
        	if(isUpperCase(c)){
        		if(i == className.length()-1 || !isUpperCase(className.charAt(i+1))){
            		if(sb.length() != 0){
            			sb.append(" ");
            		}
        		}
        	} 
        	sb.append(c);
        }
        this.setName(sb.toString());
//        this.setIcon("/demo/resource/images/leaf.gif");
    }

    public Class getDemoClass() {
        return demoClass;
    }

    public DemoPane getDemoPane() {
        return demoPane;
    }

    public void setDemoPane(DemoPane demoPane) {
        this.demoPane = demoPane;
    }

    private  boolean isUpperCase(char c){
		return c >= 'A' && c <= 'Z';
	}
}
package com.nms.ui.manager.control;

import java.util.Locale;

import org.japura.gui.model.DateDocument;
import org.japura.util.date.DateSeparator;

import com.nms.ui.manager.ResourceUtil;

public class PtnDateDocument extends DateDocument{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public PtnDateDocument(){
		// 默认的语言和格式。 语言取登陆界面选择的语言。日期分割符为DateSeparator.HYPHEN即"-"
		super(ResourceUtil.language.equals("zh_CN") ? Locale.CHINESE : Locale.ENGLISH, DateSeparator.HYPHEN);
//		super(Locale.CHINESE, DateSeparator.HYPHEN);
	}
}

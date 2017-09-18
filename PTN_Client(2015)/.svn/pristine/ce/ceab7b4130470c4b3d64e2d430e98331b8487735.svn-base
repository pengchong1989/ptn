package com.nms.ui.manager.control;

import org.japura.gui.calendar.CalendarField;

/**
 * 日历控件
 * 
 * @author kk
 * 
 */
public class PtnCalendarField extends CalendarField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 新实例
	 */
	public PtnCalendarField(PtnDateDocument ptnDateDocument) {
		super(ptnDateDocument);
		this.init();
	}

	/**
	 * 初始化控件
	 */
	private void init() {
		this.getTextField().setEditable(false);
		this.setCalendarWindowTitle("");
	}

	/**
	 * 设置文本框默认文本
	 * 
	 * @param text
	 *            日期 格式应为yyyy-MM-dd
	 */
	public void setText(String text) {
		this.getTextField().setText(text);
	}

	/**
	 * 获取文本框的内容
	 * 
	 * @return 文本框内容。 为yyyy-MM-dd格式的日期
	 */
	public String getText() {
		return this.getTextField().getText();
	}
}

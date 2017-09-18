package com.nms.ui.frame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTable;

import twaver.ElementAttribute;
import twaver.TDataBox;
import twaver.table.TElementTable;

import com.nms.ui.manager.ExceptionManage;

public class ViewDataTable<T extends ViewDataObj> extends TElementTable {

	private static final long serialVersionUID = -3857205891473344164L;
	List<ElementAttribute> attributes = new ArrayList<ElementAttribute>();
	private String layoutType;
	private TDataBox box = new TDataBox();

	public ViewDataTable(String tableName) {
		this.setDataBox(box);
		ReadTableXml readTableXml = new ReadTableXml();
		layoutType = readTableXml.readTableXml(attributes, tableName);
		this.setElementClass(ViewDataObj.class);
		this.registerElementClassAttributes(ViewDataObj.class, attributes);
		this.setColumnResizable(true);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowResizable(false);
		this.getColumnByName("index").setRenderer(new RowIDRenderer());
		getTableHeader().setReorderingAllowed(false);
		getTableHeader().setResizingAllowed(false);
	}

	/**
	 * 添加一条记录
	 * 
	 * @param obj
	 */
	public void add(final T obj) {
		try {
			if (obj != null) {
				obj.putClientProperty();
				getBox().addElement(obj);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 返回一条选中记录
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getSelect() {
		if (getBox().getLastSelectedElement() == null) {
			return null;
		} else {
			return (T) getBox().getLastSelectedElement();
		}
	}

	/**
	 * 返回多条选中记录
	 * 
	 * @return
	 */
	@SuppressWarnings( { "unchecked", "rawtypes" })
	public List<T> getAllSelect() {
		List<T> objectList = new ArrayList<T>();
		try {
			Iterator iter = getBox().selection();
			while (iter.hasNext()) {
				objectList.add((T) iter.next());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return objectList;
	}

	/**
	 * 初始化table数据
	 * 
	 * @param objList
	 */
	public void initData(List<T> objList) {
		this.clear();
		if (objList != null) {
			for (T obj : objList) {
				this.add(obj);
			}
		}

	}
	
	public void addData(List<T> objList) {
		if (objList != null) {
			for (T obj : objList) {
				this.add(obj);
			}
		}

	}

	/**
	 * 清除table数据
	 */
	public void clear() {
		this.clearSelection();
		this.getTableModel().clearPublishedData();
		this.getTableModel().clearRawData();
	}

	/**
	 * 返回所有数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllElement() {
		List<T> objectList = new ArrayList<T>();
		for (Object obj : box.getAllElements()) {
			objectList.add((T) obj);
		}
		return objectList;
	}

	public List<ElementAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<ElementAttribute> attributes) {
		this.attributes = attributes;
	}

	public String getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(String layoutType) {
		this.layoutType = layoutType;
	}

	public TDataBox getBox() {
		return box;
	}

}

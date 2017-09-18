package com.nms.ui.ptn.systemconfig.dialog.qos;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.bean.ptn.qos.QosQueue;

public class ComparableSort implements Comparable<Object> {

	List<Object> list = new ArrayList<Object>();

	public ComparableSort() {
	}

	public Object compare(Object obj) {
		compareTo(obj);
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int compareTo(Object obj) {
		if (obj instanceof List) {
			setList((List) obj);
		}
		for (int i = 0; i < list.size(); i++) {
			int k = i;
			Object temp = new Object();
			for (int j = k; j < list.size(); j++) {
				if (list.get(j) instanceof QosQueue) {
					QosQueue qosJ = (QosQueue) list.get(j);
					QosQueue qosK = (QosQueue) list.get(k);
					if (qosK.getId() > qosJ.getId()) {
						k = j;
					}
				}
				if (list.get(j) instanceof QosInfo) {
					QosInfo qosJ = (QosInfo) list.get(j);
					QosInfo qosK = (QosInfo) list.get(k);
					if (qosK.getId() > qosJ.getId()) {
						k = j;
					}
				}
				if (list.get(j) instanceof OamInfo) {
					OamInfo oamJ = (OamInfo) list.get(j);
					OamInfo oamK = (OamInfo) list.get(k);
					if (oamK.getId() > oamJ.getId()) {
						k = j;
					}
				}
				if (list.get(j) instanceof Integer) {
					if (Integer.valueOf(list.get(k).toString()) > Integer
							.valueOf(list.get(j).toString())) {
						k = j;
					}
				}
			}
			temp = list.get(k);
			list.set(k, list.get(i));
			list.set(i, temp);
		}

		return 0;
	}

	public static void main(String[] args) {
		ComparableSort com = new ComparableSort();
		com.getList().add(13);
		com.getList().add(2);
		com.getList().add(13);
		com.getList().add(2);
		com.compareTo(com.getList());

		for (Object info : com.getList()) {
			System.out.println(info);
		}
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

}

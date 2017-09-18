package com.nms.db.enums;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public enum EJobStatus {
	TSF(1), LINKDOWN(2), ADMINDOWN(3), NOTPRESENT(4), DATALINKDOWN(5),UP(0);

	private int value;

	private EJobStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static EJobStatus forms(int value) {
		for (EJobStatus cesType : EJobStatus.values()) {
			if (value == cesType.value)
				return cesType;
		}
		return null;
	}

	@Override
	public String toString() {
		if (value == 1)
			return ResourceUtil.srcStr(StringKeysObj.JOB_TSF);
		else if (value == 2)
			return ResourceUtil.srcStr(StringKeysObj.JOB_LINKDOWN);
		else if (value == 3)
			return ResourceUtil.srcStr(StringKeysObj.JOB_ADMINDOWN);
		else if (value == 4)
			return ResourceUtil.srcStr(StringKeysObj.JOB_NOTPRESENT);
		else if (value == 5)
			return ResourceUtil.srcStr(StringKeysObj.JOB_DATALINKDOWN);
		else if(value == 0)
			return ResourceUtil.srcStr(StringKeysObj.JOB_UP);
		else
			return "";
	}
}

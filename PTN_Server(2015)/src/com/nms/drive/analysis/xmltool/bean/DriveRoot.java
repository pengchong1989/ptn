package com.nms.drive.analysis.xmltool.bean;

import java.util.ArrayList;
import java.util.List;

public class DriveRoot {

	private List<DriveAttribute> driveAttributeList = new ArrayList<DriveAttribute>();

	public List<DriveAttribute> getDriveAttributeList() {
		return driveAttributeList;
	}

	public void setDriveAttributeList(List<DriveAttribute> driveAttributeList) {
		this.driveAttributeList = driveAttributeList;
	}

}

package com.nms.drivechenxiao.analysis.oam;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.oam.OamEventObject;

public class AnalysisOamEvent extends CxtOpLump{
	public List<OamEventObject> analysisEfm(byte[] command, CXNEObject CXNEObject){
		List<OamEventObject> list =new ArrayList<OamEventObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		list = super.analysisTabble("OamEfm", t);
		
		return list;
	}
}

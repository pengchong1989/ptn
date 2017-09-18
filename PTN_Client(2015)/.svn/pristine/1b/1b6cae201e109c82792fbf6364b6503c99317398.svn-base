/*
 * AddACDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.ne.ac.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;

import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.Acbuffer;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTitle;

/**
 * 
 * @author __USER__
 */
public class AddACDialog {
	private List<Acbuffer> bufferList = new ArrayList<Acbuffer>();
	private AcPortInfo acInfo = null;
	private int siteId;
	private int acId; // 创建ac或者更新ac时返回的acId
	private AcPanel acPanel;
	private AddAcDialogStep1 step1;
	private AddAcDialogStep2 step2;
	private AddAcDialogStep3 step3;
	private AddAcCXDialogStep1 step1_cx; // 晨晓创建AC第一步
	private AddAcCXDialogStep2 step2_cx; // 晨晓创建AC第二步
	private AddAcDialogStep4 step4;

	public AddACDialog(JPanel jpanel ,int siteId) {
		 step1 = new AddAcDialogStep1();
		 step2 = new AddAcDialogStep2();
		 step3 = new AddAcDialogStep3(siteId);
		 step4 = new AddAcDialogStep4();
		 setSize(step1);
		 setSize(step2);
		 setSize(step3);
		 setLocation(step1);
		 setLocation(step2);
		 setLocation(step3);
		 setTitle(step1, ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_AC));
		 setTitle(step2, ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_AC));
		 setTitle(step3, ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_AC));

		// 晨晓创建AC第一步
		step1_cx = new AddAcCXDialogStep1();
		step2_cx = new AddAcCXDialogStep2();
		setSize(step1_cx);
		setSize(step2_cx);
		setSize(step3);
		setLocation(step1_cx);
		setLocation(step2_cx);
		setLocation(step3);
		setTitle(step1_cx, ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_AC));
		setTitle(step2_cx, ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_AC));
		setTitle(step3, ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_AC));
		this.acPanel = (AcPanel) jpanel;
	}

	public AddACDialog(JPanel jpanel, AcPortInfo acPortInfo, List<Acbuffer> bufList) {
		step1 = new AddAcDialogStep1();
		step1.getBatch().setEnabled(false);
		step2 = new AddAcDialogStep2();
		step3 = new AddAcDialogStep3(0);
		setSize(step1);
		setSize(step2);
		setSize(step3);
		setLocation(step1);
		setLocation(step2);
		setLocation(step3);
		setTitle(step1, ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_AC));
		setTitle(step2, ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_AC));
		setTitle(step3, ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_AC));

		// 晨晓创建AC第一步
		step1_cx = new AddAcCXDialogStep1();
		step2_cx = new AddAcCXDialogStep2();
		setSize(step1_cx);
		setSize(step2_cx);
		setSize(step3);
		setLocation(step1_cx);
		setLocation(step2_cx);
		setLocation(step3);
		setTitle(step1_cx, ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_AC));
		setTitle(step2_cx, ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_AC));
		setTitle(step3, ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_AC));

		this.acPanel = (AcPanel) jpanel;
		this.acInfo = acPortInfo;
		this.bufferList = bufList;
	}

	private void setTitle(JDialog dialog, String title) {
		dialog.setTitle(title);
	}

	private void setSize(JDialog dialog) {
		dialog.setSize(640, 400);
		dialog.setMinimumSize(new Dimension(640, 400));
	}

	private void setLocation(JDialog dialog) {
		dialog.setLocation(UiUtil.getWindowWidth(dialog.getWidth()), UiUtil.getWindowHeight(dialog.getHeight()));
	}

	public AddAcDialogStep1 getStep1() {
		return step1;
	}

	public AddAcDialogStep2 getStep2() {
		return step2;
	}

	public AddAcDialogStep3 getStep3() {
		return step3;
	}

	public List<Acbuffer> getBufferList() {
		return bufferList;
	}

	public void setBufferList(List<Acbuffer> bufferList) {
		this.bufferList = bufferList;
	}

	public AcPortInfo getAcInfo() {
		return acInfo;
	}

	public void setAcInfo(AcPortInfo acInfo) {
		this.acInfo = acInfo;
	}

	public AddAcCXDialogStep1 getStep1_cx() {
		return step1_cx;
	}

	public AddAcCXDialogStep2 getStep2_cx() {
		return step2_cx;
	}

	public void setStep2_cx(AddAcCXDialogStep2 step2Cx) {
		step2_cx = step2Cx;
	}

	public AddAcDialogStep4 getStep4() {
		return step4;
	}
}
package com.nms.ui.ptn.ne.ac.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import com.nms.db.bean.ptn.port.Acbuffer;
import com.nms.db.bean.system.code.Code;
import com.nms.db.bean.system.code.CodeGroup;
import com.nms.db.enums.QosCosLevelEnum;
import com.nms.model.system.code.CodeService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.util.ComboBoxDataUtil;
import com.nms.ui.ptn.ne.ac.view.AddStreamDialog;

public class BufferController {

	AddStreamDialog dialog;

	private Map<Integer, String> codeIdAndCodeNameMap = new HashMap<Integer, String>();

	private boolean flag;

	public BufferController(AddStreamDialog dialog) {
		this.dialog = dialog;
		codeIdAndCodeNameMap = this.getCodeIdAndCodeNameMap();
		AddListeners();
	}

	private void AddListeners() {
		dialog.getConfirmButton().addActionListener(new MyActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				flag = verifyVlanId();
				if (flag) {
					confirmActionPerformed();
				} else {
					DialogBoxUtil.errorDialog(dialog, ResourceUtil.srcStr(StringKeysTip.TIP_VLANID));
				}
			}

			@Override
			public boolean checking() {
				return true;
			}
		});
		dialog.getCancelButton().addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelActionPerformed();
			}
		});
	}

	private boolean verifyVlanId() {
		int vlanId = 0;
		boolean isEnabled = false;
		try {
			vlanId = Integer.parseInt(dialog.getVlanTextField().getText());
			if (vlanId < 2 || vlanId > 4095) {
				return isEnabled;
			} else {
				isEnabled = true;
			}
		} catch (Exception e) {
			return isEnabled;
		}
		return isEnabled;
	}

	private Map<Integer, String> getCodeIdAndCodeNameMap() {
		Map<Integer, String> codeIdAndValueMap = null;
		CodeService_MB codeService = null;
		List<Code> codeList = null;
		try {
			codeIdAndValueMap = new HashMap<Integer, String>();
			codeService = (CodeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Code);
			codeList = codeService.selectAll();
			for (Code code : codeList) {
				codeIdAndValueMap.put(code.getId(), code.getCodeName());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(codeService);
			codeList = null;
		}
		return codeIdAndValueMap;
	}

	private void cancelActionPerformed() {
		dialog.dispose();
	}

	private void confirmActionPerformed() {
		Acbuffer newbuf = null;
		try {
			newbuf = collectData(); // 收集数据
			// 验证mac和IP
			if (!verifyData(newbuf)) {
				return;
			}
			if (dialog.getacbuffer() == null) { // 新建流
				dialog.getAddAcDialog().getBufferList().add(newbuf);
			} else { // 修改流
				dialog.getacbuffer().setBufferEnable(newbuf.getBufferEnable());
				dialog.getacbuffer().setVlanId(newbuf.getVlanId());
				dialog.getacbuffer().setSourceIp(newbuf.getSourceIp());
				dialog.getacbuffer().setSourceMac(newbuf.getSourceMac());
				dialog.getacbuffer().setTargetIp(newbuf.getTargetIp());
				dialog.getacbuffer().setTargetMac(newbuf.getTargetMac());
				dialog.getacbuffer().setEightIp(newbuf.getEightIp());
				dialog.getacbuffer().setIpDscp(newbuf.getIpDscp());
				dialog.getacbuffer().setModel(newbuf.getModel());
				dialog.getacbuffer().setCir(newbuf.getCir());
				dialog.getacbuffer().setPir(newbuf.getPir());
				dialog.getacbuffer().setCm(newbuf.getCm());
				dialog.getacbuffer().setCbs(newbuf.getCbs());
				dialog.getacbuffer().setPbs(newbuf.getPbs());
				dialog.getacbuffer().setStrategy(newbuf.getStrategy());
				dialog.getacbuffer().setPhb(newbuf.getPhb());
				dialog.getacbuffer().setSourceTcpPortId(newbuf.getSourceTcpPortId());
				dialog.getacbuffer().setEndTcpPortId(newbuf.getEndTcpPortId());
				dialog.getacbuffer().setIPTOS(newbuf.getIPTOS());
				dialog.getacbuffer().setPortType(newbuf.getPortType());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			newbuf = null;
		}
		detailTableDataBox();
		dialog.dispose();
	}

	public void detailTableDataBox() {
		DefaultTableModel defaultTableModel = null;
		try {
			defaultTableModel = (DefaultTableModel) dialog.getAddAcDialog().getStep3().getDetailTable().getModel();
			defaultTableModel.getDataVector().clear();
			defaultTableModel.fireTableDataChanged();
			for (int i = 0; i < dialog.getAddAcDialog().getBufferList().size(); i++) {
				Object[] obj = new Object[] {
						dialog.getAddAcDialog().getBufferList().get(i),
						i + 1,
						this.codeIdAndCodeNameMap.get(dialog.getAddAcDialog().getBufferList().get(i).getPhb()),
						dialog.getAddAcDialog().getBufferList().get(i).getCir(),
						dialog.getAddAcDialog().getBufferList().get(i).getCbs(),
						dialog.getAddAcDialog().getBufferList().get(i).getCm() == 0 ? new Boolean(false) : new Boolean(
								true), dialog.getAddAcDialog().getBufferList().get(i).getPir(),
						dialog.getAddAcDialog().getBufferList().get(i).getPbs() };
				defaultTableModel.addRow(obj);
			}
			dialog.getAddAcDialog().getStep3().getDetailTable().setModel(defaultTableModel);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			defaultTableModel = null;
		}

	}

	private Acbuffer collectData() {
		ControlKeyValue enable = (ControlKeyValue) dialog.getEnableJComboBox().getSelectedItem();
		ControlKeyValue model = (ControlKeyValue) dialog.getPortModeJComboBox().getSelectedItem();
		ControlKeyValue cm = (ControlKeyValue) dialog.getCmJComboBox().getSelectedItem();
		ControlKeyValue strategy = (ControlKeyValue) dialog.getStrategyJComboBox().getSelectedItem();
		ControlKeyValue phb = (ControlKeyValue) dialog.getAssignJComboBox().getSelectedItem();
		ControlKeyValue portType = (ControlKeyValue) dialog.getPortTypeCmb().getSelectedItem();

		Acbuffer acbuffer = new Acbuffer();
		acbuffer.setBufferEnable(Integer.valueOf(((Code) enable.getObject()).getCodeValue()));
		acbuffer.setVlanId(Integer.valueOf(dialog.getVlanTextField().getText()));
		acbuffer.setSourceMac(dialog.getSourceMacTextField().getText());
		acbuffer.setTargetMac(dialog.getSinkMacTextField().getText());
		acbuffer.setEightIp(Integer.valueOf(dialog.getBaIpTextField().getText()));
		acbuffer.setSourceIp(dialog.getSourceIpTextField().getText());
		acbuffer.setTargetIp(dialog.getSinkIpTextField().getText());
		acbuffer.setIpDscp(Integer.valueOf(dialog.getIpDscpTextField().getText()));
		acbuffer.setModel(Integer.valueOf(((Code) model.getObject()).getCodeValue()));
		acbuffer.setModelLog(Integer.valueOf(((Code) model.getObject()).getCodeValue()));
		acbuffer.setCir(Integer.valueOf(dialog.getCirTextField().getText()));
		acbuffer.setPir(Integer.valueOf(dialog.getPirTextField().getText()));
		acbuffer.setCm(Integer.valueOf(((Code) cm.getObject()).getCodeValue()));
		acbuffer.setCbs(Integer.valueOf(dialog.getCbsTextField().getText()));
		acbuffer.setPbs(Integer.valueOf(dialog.getPbsTextField().getText()));
		acbuffer.setStrategy(Integer.valueOf(((Code) strategy.getObject()).getCodeValue()));
		acbuffer.setStrategyLog(Integer.valueOf(((Code) strategy.getObject()).getCodeValue()));
		acbuffer.setPhb(Integer.valueOf(phb.getId()));
		acbuffer.setSourceTcpPortId(Integer.parseInt(dialog.getSourceTcpPortText().getText()));
		acbuffer.setEndTcpPortId(Integer.parseInt(dialog.getEndTcpPortText().getText()));
		acbuffer.setIPTOS(Integer.parseInt(dialog.getIPTOSText().getText()));
		acbuffer.setPortType(Integer.valueOf(((Code) portType.getObject()).getCodeValue()));
		return acbuffer;
	}

	public void openAddStreamDialog() {
		initData(dialog);
		dialog.setLocation(UiUtil.getWindowWidth(dialog.getWidth()) / 2,UiUtil.getWindowHeight(dialog.getHeight()) / 2);
		if(ResourceUtil.language.equals("zh_CN")){
			dialog.setSize(550, 350);
		}else{
			dialog.setSize(750, 350);
		}
		dialog.setVisible(true);
	}

	private void initData(AddStreamDialog dialog) {
		ComboBoxDataUtil comboBoxDataUtil=new ComboBoxDataUtil();
		try {
			comboBoxDataUtil.comboBoxData(dialog.getEnableJComboBox(), "ENABLEDSTATUE");
			comboBoxDataUtil.comboBoxData(dialog.getPortModeJComboBox(), "MODEL");
			comboBoxDataUtil.comboBoxData(dialog.getStrategyJComboBox(), "STRATEGYMODE");
			comboBoxDataUtil.comboBoxData(dialog.getAssignJComboBox(), "CONRIRMPHB");
			comboBoxDataUtil.comboBoxData(dialog.getCmJComboBox(), "BUFFERCM");
			comboBoxDataUtil.comboBoxData(dialog.getPortTypeCmb(), "TREATYTYPE");

			if (dialog.getacbuffer() != null) {
				this.comboBoxSelect(dialog.getEnableJComboBox(), "ENABLEDSTATUE", dialog.getacbuffer().getBufferEnable());
				dialog.getVlanTextField().setText(dialog.getacbuffer().getVlanId() + "");
				dialog.getSourceMacTextField().setText(dialog.getacbuffer().getSourceMac());
				dialog.getSinkMacTextField().setText(dialog.getacbuffer().getTargetMac());
				dialog.getBaIpTextField().setText(dialog.getacbuffer().getEightIp() + "");
				dialog.getSourceIpTextField().setText(dialog.getacbuffer().getSourceIp());
				dialog.getSinkIpTextField().setText(dialog.getacbuffer().getTargetIp());
				dialog.getIpDscpTextField().setText(dialog.getacbuffer().getIpDscp() + "");
				this.comboBoxSelect(dialog.getPortModeJComboBox(), "MODEL", dialog.getacbuffer().getModel());
				dialog.getCirTextField().setText(dialog.getacbuffer().getCir() + "");
				dialog.getPirTextField().setText(dialog.getacbuffer().getPir() + "");
				this.comboBoxSelect(dialog.getCmJComboBox(), "BUFFERCM", dialog.getacbuffer().getCm());
				dialog.getCbsTextField().setText(dialog.getacbuffer().getCbs() + "");
				dialog.getPbsTextField().setText(dialog.getacbuffer().getPbs() + "");
				this.comboBoxSelect(dialog.getStrategyJComboBox(), "STRATEGYMODE", dialog.getacbuffer().getStrategy());

				String phbStr = this.codeIdAndCodeNameMap.get(dialog.getacbuffer().getPhb());
				dialog.getAssignJComboBox().setSelectedIndex(QosCosLevelEnum.from(phbStr));
				dialog.getSourceTcpPortText().setText(dialog.getacbuffer().getSourceTcpPortId()+"");
				dialog.getEndTcpPortText().setText(dialog.getacbuffer().getEndTcpPortId()+"");
				dialog.getIPTOSText().setText(dialog.getacbuffer().getIPTOS()+"");
				this.comboBoxSelect(dialog.getPortTypeCmb(), "TREATYTYPE", dialog.getacbuffer().getPortType());
				/*
				 * this.comboBoxSelect(dialog.getAssignJComboBox(),
				 * "CONRIRMPHB", dialog.getacbuffer().getPhb());
				 */
			} else {
				this.comboBoxSelect(dialog.getEnableJComboBox(), "ENABLEDSTATUE", 1);
				dialog.getVlanTextField().setText(2 + "");
				dialog.getSourceMacTextField().setText("00-00-00-00-00-01");
				dialog.getSinkMacTextField().setText("00-00-00-00-00-02");
				dialog.getBaIpTextField().setText(1 + "");
				dialog.getSourceIpTextField().setText("10.18.1.1");
				dialog.getSinkIpTextField().setText("10.18.1.2");
				dialog.getIpDscpTextField().setText(0 + "");
				this.comboBoxSelect(dialog.getPortModeJComboBox(), "MODEL", 0);
				dialog.getCirTextField().setText(0 + "");
				dialog.getPirTextField().setText(100 + "");
				this.comboBoxSelect(dialog.getCmJComboBox(), "BUFFERCM", 0);
				dialog.getCbsTextField().setText(0 + "");
				dialog.getPbsTextField().setText(0 + "");
				this.comboBoxSelect(dialog.getStrategyJComboBox(), "STRATEGYMODE", 0);
				this.comboBoxSelect(dialog.getAssignJComboBox(), "CONRIRMPHB", 0);
				dialog.getSourceTcpPortText().setText("0");
				dialog.getEndTcpPortText().setText("0");
				dialog.getIPTOSText().setText("0");
				this.comboBoxSelect(dialog.getPortTypeCmb(), "TREATYTYPE", 0);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}

	}

	private void comboBoxSelect(JComboBox jComboBox, String identity, int key) throws Exception {

		CodeGroup codeGroup = null;
		List<Code> codeList = null;
		DefaultComboBoxModel defaultComboBoxModel = null;
		try {
			codeGroup = UiUtil.getCodeGroupByIdentity(identity);
			codeList = codeGroup.getCodeList();
			for (Code obj : codeList) {
				if (Integer.parseInt(obj.getCodeValue()) == key) {
					defaultComboBoxModel = (DefaultComboBoxModel) jComboBox.getModel();
					defaultComboBoxModel.setSelectedItem(new ControlKeyValue(obj.getId() + "", obj.getCodeName(), obj));
					jComboBox.setModel(defaultComboBoxModel);
					break;
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			codeList = null;
			defaultComboBoxModel = null;
		}
	}

	private boolean verifyData(Acbuffer newbuf) {
		boolean flag = true;

		if (newbuf.getSourceMac() != null && newbuf.getSourceMac().equals("")) {
			DialogBoxUtil.errorDialog(dialog, ResourceUtil.srcStr(StringKeysTip.TIP_MACEMPTY));
			flag = false;
			return flag;
		}
		if (newbuf.getTargetMac() != null && newbuf.getTargetMac().equals("")) {
			DialogBoxUtil.errorDialog(dialog, ResourceUtil.srcStr(StringKeysTip.TIP_MACEMPTY));
			flag = false;
			return flag;
		}

		if (newbuf.getSourceIp() != null && newbuf.getSourceIp().equals("")) {
			DialogBoxUtil.errorDialog(dialog, ResourceUtil.srcStr(StringKeysTip.TIP_IPEMPTY));
			flag = false;
			return flag;
		}

		if (newbuf.getTargetIp() != null && newbuf.getTargetIp().equals("")) {
			DialogBoxUtil.errorDialog(dialog, ResourceUtil.srcStr(StringKeysTip.TIP_IPEMPTY));
			flag = false;
			return flag;
		}
		// 验证mac
		if (!verifyMac(newbuf.getSourceMac())) {
			DialogBoxUtil.errorDialog(dialog, ResourceUtil.srcStr(StringKeysTip.TIP_MACERROR));
			flag = false;
			return flag;
		}

		if (!verifyMac(newbuf.getTargetMac())) {
			DialogBoxUtil.errorDialog(dialog, ResourceUtil.srcStr(StringKeysTip.TIP_MACERROR));
			flag = false;
			return flag;
		}

		// 验证IP
		if (!verifyIP(newbuf.getSourceIp())) {
			DialogBoxUtil.errorDialog(dialog, ResourceUtil.srcStr(StringKeysTip.TIP_IPERROR));
			flag = false;
			return flag;
		}
		if (!verifyIP(newbuf.getTargetIp())) {
			DialogBoxUtil.errorDialog(dialog, ResourceUtil.srcStr(StringKeysTip.TIP_IPERROR));
			flag = false;
			return flag;
		}
		if(newbuf.getCir() > newbuf.getPir()){
			DialogBoxUtil.errorDialog(dialog, ResourceUtil.srcStr(StringKeysTip.CIR_PIR));
			flag = false;
			return flag;
		}
		return flag;

	}

	private boolean verifyIP(String ip) {
		String[] ips = ip.split("\\.");
		boolean flag = true;
		if (ips.length == 4) {
			for (String str : ips) {
				char[] chars = str.toCharArray();
				for (char ch : chars) {
					if (ch < '0' || ch > '9') {
						flag = false;
						break;
					}
				}
				if (!flag) {
					break;
				}
				int intIp = Integer.parseInt(str);
				if (intIp < 0 || intIp > 255) {
					flag = false;
					break;
				}
			}
		} else {
			flag = false;
		}

		return flag;
	}

	private boolean verifyMac(String mac) {
		String[] macStrs = mac.split("-");
		boolean flag = true;
		if (macStrs.length == 6) {
			for (String macStr : macStrs) {
				char[] chars = macStr.toCharArray();
				if (chars.length == 2) {
					String tempStr = null;
					for (char ch : chars) {
						char[] temp = new char[] { ch };
						tempStr = new String(temp);
						if (!tempStr.matches("\\p{XDigit}")) {
							flag = false;
							break;
						}
					}

				} else {
					flag = false;
					break;
				}
			}
		} else {
			flag = false;
		}

		return flag;
	}

}

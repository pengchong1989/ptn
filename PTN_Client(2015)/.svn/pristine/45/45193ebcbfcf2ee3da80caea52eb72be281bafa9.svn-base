package com.nms.ui.ptn.statistics.lable.dialog;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.LabelInfo;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.report.SSLabel;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.LabelInfoService_MB;
import com.nms.model.ptn.path.tunnel.LspInfoService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;

/**
 * 显示已经使用的标签信息
 * @author guoqc
 */
public class ShowUsedLabelDialog extends PtnDialog {
	private static final long serialVersionUID = -5099293329201732528L;
	private JTextArea textArea = null;
	private JScrollPane scrollPane = null;
	public ShowUsedLabelDialog(SSLabel label) {
		try {
			this.initComponent();
			this.showUsedLabel(label);
			UiUtil.showWindow(this, 600, 400);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.setTitle(ResourceUtil.srcStr(StringKeysBtn.BTN_QUERY_USED_LABEL));
		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
		this.textArea.setLineWrap(true);
		scrollPane = new JScrollPane();
		this.scrollPane.setViewportView(this.textArea);
		this.add(this.scrollPane);
	}
	
	private void showUsedLabel(SSLabel label) {
		PortService_MB portService = null;
		LspInfoService_MB lspService = null;
		try {
			String type = label.getLabelType();
			int siteId = label.getSiteId();
			if("ALL".equals(type)){
				List<Integer> usedLabelList = this.getAllUsedLabelList(label);
				this.textArea.append(label.getSiteName()+": ");
				for (Integer integer : usedLabelList) {
					this.textArea.append(integer+" ");
				}
			}else if("PW".equals(type)){
				this.showPwLabelList(label);
			}else{
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				lspService = (LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO);
				int portId = label.getPortId();
				PortInst portInst = new PortInst();
				portInst.setSiteId(siteId);
				portInst.setPortType("NNI");
				List<PortInst> portList = portService.select(portInst);
				for (PortInst p : portList) {
					if(portId == p.getPortId()){
						portInst = p;
						break;
					}
				}
				List<Lsp> lspList = lspService.selectByPort(portId);
				if(lspList != null && !lspList.isEmpty()){
					this.textArea.append(portInst.getPortName()+": ");
					for (Lsp lsp : lspList) {
						if(lsp.getAPortId() > 0 && lsp.getAPortId() == portId){
							this.textArea.append(lsp.getBackLabelValue()+" ");
						}else if(lsp.getZPortId() > 0 && lsp.getZPortId() == portId){
							this.textArea.append(lsp.getFrontLabelValue()+" ");
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(lspService);
		}
	}
	
	private List<Integer> getAllUsedLabelList(SSLabel label){
		LabelInfoService_MB labelService = null;
		List<Integer> usedLabelList = new ArrayList<Integer>();
		try {
			LabelInfo labelinfo = new LabelInfo();
			labelinfo.setSiteid(label.getSiteId());
			labelinfo.setType("WH");
			labelService = (LabelInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LABELINFO);
			List<LabelInfo> labelList = labelService.selectUsedLabel(labelinfo);
			//查出该网元下所有已用的标签
			for (LabelInfo usedLabel : labelList) {
				usedLabelList.add(usedLabel.getLabelValue());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(labelService);
		}
		return usedLabelList;
	}

	private void showPwLabelList(SSLabel label) {
		try {
			//查出该网元下所有已用的标签
			List<Integer> usedLabelList = this.getAllUsedLabelList(label);
			//查出该网元下所有lsp已用的标签
			List<Integer> lspUsedLabelList = this.getLspUsedLabelList(label.getSiteId());
			//将已用的lsp标签过滤掉，剩下的就是pw已用的标签
			this.textArea.append(label.getSiteName() + ": ");
			for (Integer integer : usedLabelList) {
				if(!lspUsedLabelList.contains(integer)){
					this.textArea.append(integer+" ");
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private List<Integer> getLspUsedLabelList(int siteId) {
		LspInfoService_MB lspService = null;
		List<Integer> lspUsedLabelList = new ArrayList<Integer>();
		try {
			lspService = (LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO);
			List<Lsp> lspList = lspService.selectBySiteId(siteId);
			if(lspList != null && !lspList.isEmpty()){
				for (Lsp lsp : lspList) {
					if(lsp.getASiteId() > 0 && lsp.getASiteId() == siteId){
						lspUsedLabelList.add(lsp.getBackLabelValue());
					}else if(lsp.getZSiteId() > 0 && lsp.getZSiteId() == siteId){
						lspUsedLabelList.add(lsp.getFrontLabelValue());
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(lspService);
		}
		return lspUsedLabelList;
	}
}

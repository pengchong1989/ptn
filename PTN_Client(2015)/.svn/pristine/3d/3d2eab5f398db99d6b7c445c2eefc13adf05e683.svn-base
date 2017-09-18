package com.nms.ui.ptn.systemconfig.Template.controller;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.qos.QosMappingAttr;
import com.nms.db.bean.ptn.qos.QosMappingMode;
import com.nms.db.bean.ptn.qos.VlanpriToColor;
import com.nms.model.ptn.qos.QosMappingTemplateService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.systemconfig.Template.view.VlanpriToColorPanel;
import com.nms.ui.ptn.systemconfig.Template.view.dialog.EditeVlanpriToColorDialog;

/**
 * VLANPRI到颜色映射控制类
 * 
 * @author dzy
 * 
 */
public class VlanpriToColorController extends AbstractController {

	private VlanpriToColorPanel vlanpriToColorPanel = null; // msp主界面面板

	/**
	 * 创建一个新的实例
	 * 
	 * @param msp主界面面板
	 */
	public VlanpriToColorController(VlanpriToColorPanel vlanpriToColorPanel) {
		this.vlanpriToColorPanel = vlanpriToColorPanel;
	}

	/**
	 * 刷新界面数据
	 */
	@Override
	public void refresh() throws Exception {
		QosMappingTemplateService_MB qosMappingTemplateService = null;
		List<QosMappingMode> qosMappingList=null;
		QosMappingAttr qosMappingAttrSel;
		List<VlanpriToColor> vlanpriToColorList;
		try {
			qosMappingAttrSel = new QosMappingAttr();
			qosMappingAttrSel.setMappingType(4);
			qosMappingTemplateService = (QosMappingTemplateService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QOSMAPPINGTEMPLATESERVICE);
			qosMappingList=qosMappingTemplateService.refresh(qosMappingAttrSel);
			vlanpriToColorList = getvlanpriToColorList(qosMappingList);
			this.vlanpriToColorPanel.clear();
			this.vlanpriToColorPanel.initData(vlanpriToColorList);
			this.vlanpriToColorPanel.updateUI();

		} catch (Exception e) {
 			throw e;
		} finally {
			UiUtil.closeService_MB(qosMappingTemplateService);
		}
	}

	/**
	 * 转换列表数据
	 * @param qosMappingList
	 * 					模板数据
	 * @return
	 */
	private List<VlanpriToColor> getvlanpriToColorList(
			List<QosMappingMode> qosMappingList) {
		VlanpriToColor vlanpriToColor ;
		List<VlanpriToColor> vlanpriToColorList = new ArrayList<VlanpriToColor>();
		if(null!=qosMappingList&&qosMappingList.size()>0){
			for(QosMappingMode qosMappingMode :qosMappingList){
				vlanpriToColor = new VlanpriToColor();
				for(QosMappingAttr qosMappingAttr:qosMappingMode.getQosMappingAttrList()){
					if(0==qosMappingAttr.getValue()){
						vlanpriToColor.setVlanpri0(qosMappingAttr.getColor());
					}else if(1==qosMappingAttr.getValue()){
						vlanpriToColor.setVlanpri1(qosMappingAttr.getColor());
					}else if(2==qosMappingAttr.getValue()){
						vlanpriToColor.setVlanpri2(qosMappingAttr.getColor());
					}else if(3==qosMappingAttr.getValue()){
						vlanpriToColor.setVlanpri3(qosMappingAttr.getColor());
					}else if(4==qosMappingAttr.getValue()){
						vlanpriToColor.setVlanpri4(qosMappingAttr.getColor());
					}else if(5==qosMappingAttr.getValue()){
						vlanpriToColor.setVlanpri5(qosMappingAttr.getColor());
					}else if(6==qosMappingAttr.getValue()){
						vlanpriToColor.setVlanpri6(qosMappingAttr.getColor());
					}else if(7==qosMappingAttr.getValue()){
						vlanpriToColor.setVlanpri7(qosMappingAttr.getColor());
					}
				}
				vlanpriToColor.setName(qosMappingMode.getName());
				vlanpriToColor.setQosMappingMode(qosMappingMode);
				vlanpriToColorList.add(vlanpriToColor);
			}
		}
		return vlanpriToColorList;
	}

	/**
	 * 打开新建窗口
	 */
	@Override
	public void openCreateDialog() {
		try {
			new EditeVlanpriToColorDialog(null,this.vlanpriToColorPanel,"");
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	/**
	 * 打开修改窗口
	 */
	@Override
	public void openUpdateDialog() {
		try {
			new EditeVlanpriToColorDialog(this.vlanpriToColorPanel.getSelect().getQosMappingMode(),this.vlanpriToColorPanel,TypeAndActionUtil.ACTION_UPDATE);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	/**
	 * 删除数据
	 */
	@Override
	public void delete() {
		int result = 0;
		List<VlanpriToColor> vlanpriToColorList;
		QosMappingTemplateService_MB qosMappingTemplateService = null;
		try {
			vlanpriToColorList = this.vlanpriToColorPanel.getAllSelect();
			qosMappingTemplateService = (QosMappingTemplateService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QOSMAPPINGTEMPLATESERVICE);
			for(VlanpriToColor vlanpriToColor:vlanpriToColorList){
				result = qosMappingTemplateService.delete(vlanpriToColor.getQosMappingMode().getQosMappingAttrList().get(0).getGroupid());
			}
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(qosMappingTemplateService);
		}
	}
}

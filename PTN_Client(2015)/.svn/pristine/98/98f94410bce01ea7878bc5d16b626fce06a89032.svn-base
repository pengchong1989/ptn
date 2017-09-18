package com.nms.ui.ptn.basicinfo.controller;

import java.util.List;

import com.nms.db.bean.path.SetNameRule;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.path.NameRuleService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.basicinfo.SetNameRulePanel;
import com.nms.ui.ptn.basicinfo.dialog.segment.AddSetNameRuleDialog;

/**
 * 处理操作日志
 * 
 * @author sy
 * 
 */
public class SetNameRuleController extends AbstractController {

	private SetNameRulePanel panel;

	public SetNameRuleController(SetNameRulePanel SetNameRulePanel) {
		this.panel = SetNameRulePanel;
	}

	private SetNameRule namerule = null;

	// 新建
	@Override
	public void openCreateDialog() {
		SetNameRule setnameRule = new SetNameRule();
//		if (this.panel.getSelect() != null) {
//			this.panel.getSelect().setName("");
//		}
		new AddSetNameRuleDialog(setnameRule, panel);

	}

	// 修改
	@Override
	public void openUpdateDialog() throws Exception {
		SetNameRule setnameRule = this.panel.getSelect();
		new AddSetNameRuleDialog(setnameRule, panel);

	}

	// 刷新
	public void refresh() throws Exception {

		NameRuleService_MB nameRuleService = null;
		List<SetNameRule> NameRuleList = null;
		try {
			nameRuleService = (NameRuleService_MB) ConstantUtil.serviceFactory.newService_MB(Services.NAMERULESERVICE);
			if (namerule == null) {
				// 若过滤条件为空，则显示所有信息
				namerule = new SetNameRule();
			}
			NameRuleList = nameRuleService.select(namerule);
			this.panel.clear();
			this.panel.initData(NameRuleList);
			this.panel.updateUI();
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(nameRuleService);
		}
	}

	// 删除
	@Override
	public void delete() throws Exception {
		List<SetNameRule> nameRules = this.panel.getAllSelect();
		NameRuleService_MB nameRuleService = null;
		
		try {
			nameRuleService = (NameRuleService_MB) ConstantUtil.serviceFactory.newService_MB(Services.NAMERULESERVICE);
			for(SetNameRule nameRule : nameRules)
			{
				nameRuleService.delete(nameRule.getId());
			}
			// 添加日志记录
			this.insertOpeLog(EOperationLogType.DELETENAMERULE.getValue(), ResultString.CONFIG_SUCCESS, null, null);			
			DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
			this.panel.getController().refresh();
			
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(nameRuleService);
		}
	}
	private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){
		AddOperateLog.insertOperLog(panel.getDeleteButton(), operationType, result, oldMac, newMac, 0,ResourceUtil.srcStr(StringKeysTitle.TIT_SETNAMERULE),"");		
	}
}
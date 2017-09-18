package com.nms.ui.ptn.performance.controller;

import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.alarm.service.CSVUtil;
import com.nms.ui.ptn.performance.view.CurrPerformCountFilterDialog;
import com.nms.ui.ptn.performance.view.CurrPerformCountPanel;

/**
 * 实时性能统计
 * guoqc
 */
public class CurrPerformCountController extends AbstractController {
	private CurrPerformCountPanel view;
	private CurrPerformCountFilterDialog dialog;
	
	public CurrPerformCountController(CurrPerformCountPanel view) {
		this.view = view;
	}

	@Override
	public void openFilterDialog() {
		this.dialog = new CurrPerformCountFilterDialog(this.view);
	}

	// 导出
	@Override
	public void export() throws Exception {
		String[] s = {};
		UiUtil uiUtil = new UiUtil();
		int result = 0;
		try {
			CSVUtil csvUtil = new CSVUtil();
			String path = csvUtil.showChooserWindow("save", "选择文件", s);
			if(path != null && !"".equals(path)){
				String csvFilePath = path + ".csv";
				if(uiUtil.isExistAlikeFileName(csvFilePath)){
					result = DialogBoxUtil.confirmDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_FILEPATHHASEXIT));
					if(result == 1){
						return ;
					}
				}
				csvUtil.WriteCsvCurrPerformCount(csvFilePath, this.view.getTable().getAllElement());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	public CurrPerformCountPanel getView() {
		return view;
	}

	@Override
	public void refresh() throws Exception {
		
	}

	public CurrPerformCountFilterDialog getDialog() {
		return dialog;
	}
}

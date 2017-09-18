package com.nms.ui.ptn.performance.controller;

import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.alarm.service.CSVUtil;
import com.nms.ui.ptn.performance.view.PathPerformCountFilterDialog;
import com.nms.ui.ptn.performance.view.PathPerformCountPanel;

/**
 * 端到端性能统计
 * guoqc
 */
public class PathPerformCountController extends AbstractController {
	private PathPerformCountPanel view;
	private PathPerformCountFilterDialog dialog;
	
	public PathPerformCountController(PathPerformCountPanel view) {
		this.view = view;
	}

	@Override
	public void openFilterDialog() {
		this.dialog = new PathPerformCountFilterDialog(this.view);
	}

	// 导出
	@Override
	public void export() throws Exception {
		String[] s = {};
		UiUtil uiUtil = new UiUtil();
		int comfirmResult = 0;
		String csvFilePath = "";
		try {
			CSVUtil csvUtil = new CSVUtil();
			String path = csvUtil.showChooserWindow("save", "选择文件", s);
			if(path != null && !"".equals(path)){
				csvFilePath = path + ".csv";
				if(uiUtil.isExistAlikeFileName(csvFilePath)){
					comfirmResult = DialogBoxUtil.confirmDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_FILEPATHHASEXIT));
					if(comfirmResult == 1){
						return ;
					}
				}
				csvUtil.WriteCsvPathPerformCount(csvFilePath, this.view.getTable().getAllElement());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			uiUtil = null;
			csvFilePath = null;
		}
	}

	public PathPerformCountPanel getView() {
		return view;
	}

	@Override
	public void refresh() throws Exception {
		
	}

	public PathPerformCountFilterDialog getDialog() {
		return dialog;
	}
}

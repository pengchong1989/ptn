package com.nms.ui.ptn.statistics.layerRate;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.ELayerRate;
import com.nms.db.bean.system.ELayerRateInfo;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.alarm.service.CSVUtil;

public class LayerRateCountController extends AbstractController {
	private LayerRateCountPanel view;
	
	public LayerRateCountController(LayerRateCountPanel layerRateCountPanel) {
		this.view = layerRateCountPanel;
	}

	@Override
	public void refresh() throws Exception {
		this.view.clear();
		List<ELayerRateInfo> rateList = new ArrayList<ELayerRateInfo>();
		for (ELayerRate eLayerRate : ELayerRate.values()) {
			ELayerRateInfo layer = new ELayerRateInfo();
			layer.setObjName(eLayerRate.toString());
			layer.setLayerNumber(eLayerRate.getValue());
			rateList.add(layer);
		}
		this.view.initData(rateList);
		this.view.updateUI();
	}

	public void export(){
		String[] s = {};
		try {
			CSVUtil csvUtil = new CSVUtil();
			String path = csvUtil.showChooserWindow("save", "选择文件", s);
			csvUtil.WriteCsvLayerRateCount(path, this.view.getTable().getAllElement());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
}

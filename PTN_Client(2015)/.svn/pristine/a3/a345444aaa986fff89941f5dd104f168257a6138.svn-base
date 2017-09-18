package com.nms.ui.ptn.clock.view;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.clock.FrequencySource;
import com.nms.ui.frame.AbstractController;

public class FrequencyStatusController  extends AbstractController{

	private FrequencyStatusPanel view;
	private List<FrequencySource> infos;
	public FrequencyStatusController(FrequencyStatusPanel frequencyStatusPanel, List<FrequencySource> frequencySources) {
		infos = frequencySources;
		view = frequencyStatusPanel;
		refresh();
	}

	@Override
	public void refresh() {
		List<FrequencySource> frequencySourceList = null;
		if(infos != null){
			frequencySourceList = new ArrayList<FrequencySource>();
			for(FrequencySource frequencySource : infos){
				if(frequencySource.getClockName() == 255 && frequencySource.getSourceStatus() == 255){
					frequencySourceList.add(frequencySource);
				}
			}
		}
		if(frequencySourceList != null){
			view.initData(frequencySourceList);
			view.updateUI();
		}
	}

}

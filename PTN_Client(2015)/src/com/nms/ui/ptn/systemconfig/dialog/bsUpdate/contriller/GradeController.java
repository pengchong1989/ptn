package com.nms.ui.ptn.systemconfig.dialog.bsUpdate.contriller;

import com.nms.ui.frame.AbstractController;
import com.nms.ui.ptn.systemconfig.dialog.bsUpdate.view.GradeTablePanel;

public class GradeController extends AbstractController {
	private GradeTablePanel view;
  

	public GradeController(GradeTablePanel gradeTablePanel) {
		this.view=gradeTablePanel;
	}

	public GradeTablePanel getView() {
		return view;
	}

	

	public void setView(GradeTablePanel view) {
		this.view = view;
	}

	@Override
	public void refresh() throws Exception {
	}
}

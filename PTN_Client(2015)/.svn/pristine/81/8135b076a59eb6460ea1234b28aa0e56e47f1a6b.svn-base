package com.nms.ui.topology.routebusiness.view;

import com.nms.db.bean.path.Segment;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysTitle;

/**
 * 查询光功率
 * @author guoqc
 */
public class QuerySfpPowDialog extends PtnDialog {
	private static final long serialVersionUID = 3248974637851455262L;
	private Segment segment = null;
	private QuerySfpPowPanel querySfpPowPanel; //基本信息列表
	
	public QuerySfpPowDialog(Segment seg) {
		try {
			this.segment = seg;
			this.initComponent();
			UiUtil.showWindow(this, 800, 300);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_QUERY_SFPPOW));
		querySfpPowPanel = new QuerySfpPowPanel(this.segment);
		this.add(querySfpPowPanel);
	}
}

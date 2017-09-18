package com.nms.ui.ptn.basicinfo.dialog.segment;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.path.protect.WrappingProtectService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.SearchUiUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.NeTreePanel;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.basicinfo.SegmentPanel;
import com.nms.ui.ptn.business.ces.CesBusinessPanel;
import com.nms.ui.ptn.business.elan.ElanBusinessPanel;
import com.nms.ui.ptn.business.eline.ElineBusinessPanel;
import com.nms.ui.ptn.business.etree.EtreeBusinessPanel;
import com.nms.ui.ptn.business.loopProtect.LoopProtectPanel;
import com.nms.ui.ptn.business.pw.PwBusinessPanel;
import com.nms.ui.ptn.business.tunnel.TunnelBusinessPanel;

public class SearchSegmentDialog extends PtnDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5994266314285769677L;
	private SearchSiteTablePanel searchSiteTablePanel;
	private JPanel jPanel = null;

	public SearchSegmentDialog(JPanel jPanel) {
		try {
			this.setTitle(ResourceUtil.srcStr(StringKeysBtn.BTN_SEARCH));
			this.jPanel = jPanel;
			this.initComponent();
			this.setLayout();
			this.addListener();
			UiUtil.showWindow(this, 700, 500);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}

	}

	/**
	 * 添加监听
	 */
	private void addListener() {
		this.btnSearch.addActionListener(new MyActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btnSearch.setResult(1);
					search();
				} catch (Exception e1) {
					ExceptionManage.dispose(e1, this.getClass());
				}

			}

			@Override
			public boolean checking() {
				return true;
			}
		});
		this.btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchSegmentDialog.this.dispose();
			}
		});
	}

	/**
	 * 设置布局
	 */
	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 630, 50 };
		componentLayout.columnWeights = new double[] { 1 };
		componentLayout.rowHeights = new int[] { 400, 40 };
		componentLayout.rowWeights = new double[] { 0.0, 0.0 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		// 添加域tree
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.splitPane, c);
		this.add(this.splitPane);

		// 添加按钮
		c.gridy = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		componentLayout.setConstraints(this.btnSearch, c);
		this.add(this.btnSearch);

		c.gridx = 1;
		componentLayout.setConstraints(this.btnCanel, c);
		this.add(this.btnCanel);
	}

	/**
	 * 初始化控件
	 * 
	 * @throws Exception
	 */
	private void initComponent() throws Exception {
		this.splitPane = new JSplitPane();
		this.searchSiteTablePanel = new SearchSiteTablePanel();
		this.btnSearch = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SEARCH), true);
		this.btnCanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		// this.treePanel = new JPanel(new BorderLayout());
		// this.treePanel.add(new JScrollPane(this.tree), BorderLayout.CENTER);
		// this.treePanel.setPreferredSize(new Dimension(150, 1000));
		this.neTreePanel = new NeTreePanel(true, 2, this.searchSiteTablePanel.getTable(),false);
		this.splitPane.add(this.neTreePanel, JSplitPane.LEFT);
		this.splitPane.add(this.searchSiteTablePanel, JSplitPane.RIGHT);
		// this.tree.setTTreeSelectionMode(TTree.CHECK_DESCENDANT_ANCESTOR_SELECTION);
	}


	/**
	 * 
	 * 搜索
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void search() throws Exception {
		List<SiteInst> siteInstList = null;
		DispatchUtil segmentDispatch = null;
		String result = null;
		SegmentPanel segmentPanel = null;
		TunnelBusinessPanel tunnelBusinessPanel = null;
		PwBusinessPanel pwBusinessPanel = null;
		ElineBusinessPanel elineBusinessPanel = null;
		EtreeBusinessPanel etreeBusinessPanel = null;
		ElanBusinessPanel elanBusinessPanel = null;
		CesBusinessPanel cesBusinessPanel = null;
		LoopProtectPanel loopProtectPanel = null;
		EtreeInfoService_MB etreeService = null;
		ElanInfoService_MB elanInfoService = null;
		TunnelService_MB tunnelService = null;
		PwInfoService_MB pwInfoService = null;
		ElineInfoService_MB elineService = null;
		CesInfoService_MB cesInfoService = null;
		WrappingProtectService_MB wrappingProtectService = null;
		try {

			siteInstList = this.searchSiteTablePanel.getTable().getAllElement();

			if (this.jPanel instanceof SegmentPanel) {
				if (siteInstList.size() < 2) {
					DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_SITE_TWO));
					return;
				}
				List<Segment> ss=new ArrayList<Segment>();
				List<Segment> seg=new ArrayList<Segment>();				
				segmentDispatch = new DispatchUtil(RmiKeys.RMI_SEGMENT);
				result = segmentDispatch.searchSegment(siteInstList,seg);
				if(seg!=null){
					for(int i=0;i<seg.size();i++){
						Segment old=new Segment();
						seg.get(i).getoldaPortInst().setPortName(getPortName(seg.get(i).getoldaPortInst().getPortId()));
						seg.get(i).getoldzPortInst().setPortName(getPortName(seg.get(i).getoldzPortInst().getPortId()));
						old.setoldaPortInst(seg.get(i).getoldaPortInst());
						old.setoldzPortInst(seg.get(i).getoldzPortInst());
						seg.get(i).setoldaPortInst(seg.get(i).getaPortInst());
						seg.get(i).setoldzPortInst(seg.get(i).getzPortInst());
						seg.get(i).setaPortInst(null);
						seg.get(i).setzPortInst(null);
						ss.add(old);
					}
				}
				/**
				 * 添加操作日记记录
				 */				
				DialogBoxUtil.succeedDialog(this, result);
				for(int i=0;i<seg.size();i++){
					this.insertOpeLog(EOperationLogType.SEGEMENTSELECT.getValue(), result, ss.get(i), seg.get(i));	
				}
				segmentPanel = (SegmentPanel) this.jPanel;
				segmentPanel.getController().refresh();
			} else if (this.jPanel instanceof TunnelBusinessPanel) {
				// System.out.println("search tunnel.......");
//				tunnelService = (TunnelService) ConstantUtil.serviceFactory.newService(Services.Tunnel);
//				tunnelService.doSearch(siteInstList);
//				UiUtil.closeService(tunnelService);
				SearchUiUtil searchUiutil = new SearchUiUtil();
				searchUiutil.searchTunnel(siteInstList);
				/**
				 * 添加操作日记记录
				 */
				AddOperateLog.insertOperLog(btnSearch, EOperationLogType.TUNNELSELECT.getValue(), result);
				DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
				tunnelBusinessPanel = (TunnelBusinessPanel) this.jPanel;
				tunnelBusinessPanel.getController().refresh();

			} else if (this.jPanel instanceof PwBusinessPanel) {
				// System.out.println("search pw.......");
//				pwInfoService = (PwInfoService) ConstantUtil.serviceFactory.newService(Services.PwInfo);
//				pwInfoService.exeSearchPW(siteInstList);
//				UiUtil.closeService(pwInfoService);
				SearchUiUtil searchUiutil = new SearchUiUtil();
				searchUiutil.searchPw(siteInstList);
				/**
				 * 添加操作日记记录
				 */
				AddOperateLog.insertOperLog(btnSearch, EOperationLogType.PWSELECT.getValue(), null);
				DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
				pwBusinessPanel = (PwBusinessPanel) this.jPanel;
				pwBusinessPanel.getController().refresh();

			} else if (this.jPanel instanceof ElineBusinessPanel) {
				// System.out.println("search eline.......");
//				elineService = (ElineService) ConstantUtil.serviceFactory.newService(Services.Eline);
//				elineService.searchActive(siteInstList);
				SearchUiUtil searchUiutil = new SearchUiUtil();
				searchUiutil.searchEline(siteInstList);
				/**
				 * 添加操作日记记录
				 */
				AddOperateLog.insertOperLog(btnSearch, EOperationLogType.ELINESELECT.getValue(), null);
				DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
				elineBusinessPanel = (ElineBusinessPanel) this.jPanel;
				elineBusinessPanel.getController().refresh();

			} else if (this.jPanel instanceof EtreeBusinessPanel) { // etree搜索
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				etreeService.search(siteInstList);
				/**
				 * 添加操作日记记录
				 */
				AddOperateLog.insertOperLog(btnSearch, EOperationLogType.ETREESELECT.getValue(), null);
				DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
				etreeBusinessPanel = (EtreeBusinessPanel) this.jPanel;
				etreeBusinessPanel.getController().refresh();

			} else if (this.jPanel instanceof ElanBusinessPanel) {// elan搜索
				elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
				elanInfoService.search(siteInstList);
				/**
				 * 添加操作日记记录
				 */
				AddOperateLog.insertOperLog(btnSearch, EOperationLogType.ELANSELECT.getValue(), null);
				DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
				elanBusinessPanel = (ElanBusinessPanel) this.jPanel;
				elanBusinessPanel.getController().refresh();

			} else if (this.jPanel instanceof CesBusinessPanel) {
				// System.out.println("search ces.......");
				SearchUiUtil searchUiutil = new SearchUiUtil();
				searchUiutil.searchCES(siteInstList);
				/**
				 * 添加操作日记记录
				 */
				AddOperateLog.insertOperLog(btnSearch, EOperationLogType.CESSELECT.getValue(), null);
				DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
				cesBusinessPanel = (CesBusinessPanel) this.jPanel;
				cesBusinessPanel.getController().refresh();

			} else if (this.jPanel instanceof LoopProtectPanel) { // 环搜索
				if (siteInstList.size() < 3) {
					DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_NOTLOOP));
					return;
				}
				wrappingProtectService = (WrappingProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WRAPPINGPROTECT);
				wrappingProtectService.searchLoopProtect(siteInstList);
				/**
				 * 添加操作日记记录
				 */
				AddOperateLog.insertOperLog(btnSearch, EOperationLogType.LOOPPROTECTSELECT.getValue(), null);
				DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
				loopProtectPanel = (LoopProtectPanel) this.jPanel;
				loopProtectPanel.getController().refresh();

			}

			this.dispose();

		} catch (Exception e) {
			throw e;
		} finally {
			siteInstList = null;
			segmentDispatch = null;
			result = null;
			UiUtil.closeService_MB(tunnelService);
			UiUtil.closeService_MB(wrappingProtectService);
			UiUtil.closeService_MB(cesInfoService);
			UiUtil.closeService_MB(elanInfoService);
			UiUtil.closeService_MB(etreeService);
			UiUtil.closeService_MB(elineService);
			UiUtil.closeService_MB(pwInfoService);
		}
	}

	private String getPortName(int portId) {
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			return portService.selectPortybyid(portId).getPortName();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		finally
		{
			UiUtil.closeService_MB(portService);
		}
		return "";
	}
	
	private void insertOpeLog(int operationType, String result, Segment oldSeg, Segment newSeg){
		this.getOamSiteName(newSeg);
		newSeg.setAqosqueue(null);
		newSeg.setZqosqueue(null);
		AddOperateLog.insertOperLog(btnSearch, operationType, result, oldSeg, newSeg, newSeg.getASITEID(), ResourceUtil.srcStr(StringKeysTab.TAB_SEGMENT), "segmentSearch");
		AddOperateLog.insertOperLog(btnSearch, operationType, result, oldSeg, newSeg, newSeg.getZSITEID(), ResourceUtil.srcStr(StringKeysTab.TAB_SEGMENT), "segmentSearch");					
	}
	
	private void getOamSiteName(Segment seg){
		List<OamInfo> oamList = seg.getOamList();
		if(oamList != null && oamList.size() > 0){
			for (OamInfo oamInfo : oamList) {
				if(oamInfo.getOamMep().getSiteId() == seg.getASITEID()){
					oamInfo.getOamMep().setSiteName(seg.getShowSiteAname());
				}else if(oamInfo.getOamMep().getSiteId() == seg.getZSITEID()){
					oamInfo.getOamMep().setSiteName(seg.getShowSiteZname());
				}
			}
		}
	}
	
	private JSplitPane splitPane;
	private PtnButton btnSearch;
	private JButton btnCanel;
	private NeTreePanel neTreePanel;
}

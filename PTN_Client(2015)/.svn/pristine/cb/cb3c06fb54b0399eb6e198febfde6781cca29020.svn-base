package com.nms.ui.ptn.ne.time.view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.nms.db.bean.ptn.clock.PtpPortinfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.ptn.ne.time.controller.TimeSyncController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;


public class TimeSyncPanel  extends ContentView<PtpPortinfo>{
	//private JPanel jpanel;	
	private JPanel buttonPanel;
	public TimeSyncView timesynview;
	private PtnButton  btnSavePort;
	private PtnButton  btnSynPort;
	private PtnButton addptp;
	private PtnButton updateptp;
	private PtnButton delptp;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2948549699545806047L;
	public TimeSyncPanel() throws Exception {
		super("ptpPort",RootFactory.CORE_MANAGE);
		init();	
		
		controller=new TimeSyncController(this);
	   
	}
	
	
	public void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_PTPPORT_INFO)));
		try {
			initComponent();
			setLayout();
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void initComponent() {
		timesynview  = new TimeSyncView();
		buttonPanel = new JPanel();
		btnSavePort=new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),true);
		btnSynPort=new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SYNCHRO));
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		buttonPanel.setLayout(flowLayout);
		buttonPanel.add(btnSynPort);
		buttonPanel.add(btnSavePort);
		//jpanel = new JPanel();
		
		}

	public void setLayout() {
		setTypePanleLayout();
//		GridBagLayout panelLayout = new GridBagLayout();
//		jpanel.setLayout(panelLayout);
//		GridBagConstraints c = null;
//		c = new GridBagConstraints();
//		c.gridx = 0;
//		c.gridy = 0;
//		c.gridheight = 1;
//		c.gridwidth = 1;
//		c.weightx = 1.0;
//		c.weighty = 1.0;
//		c.fill = GridBagConstraints.BOTH;
//		panelLayout.setConstraints(jpanel, c);
//		this.add(jpanel);
	}
	
	
	private void setTypePanleLayout() {		
		GridBagLayout topPanelLayout = new GridBagLayout();
		topPanelLayout.rowHeights = new int[] { 210, 300 ,60};
		topPanelLayout.rowWeights = new double[] { 0.0, 1.0 ,0.0};
		this.setLayout(topPanelLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets = new Insets(0, 0, 0, 0);
		topPanelLayout.setConstraints(timesynview, c);
		this.add(timesynview);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0, 8, 0, 0);
		topPanelLayout.setConstraints(buttonPanel, c);
		this.add(buttonPanel);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets = new Insets(0, 0, 0, 0);
		topPanelLayout.setConstraints(this.getContentPanel(), c);
		this.add(this.getContentPanel());
	}
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSynchroButton());
		needRemoveButtons.add(getSearchButton());
		needRemoveButtons.add(getRefreshButton());	
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getUpdateButton());
		needRemoveButtons.add(getDeleteButton());
		return needRemoveButtons;
	}

	@Override
	public List<JButton> setAddButtons() {
		List<JButton> needButtons = new ArrayList<JButton>();
		this.addptp = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CREATE),false,RootFactory.CORE_MANAGE);
		this.updateptp = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE),false,RootFactory.CORE_MANAGE);
		this.delptp = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE),false,RootFactory.CORE_MANAGE);
		this.addptp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
//				if(upgradeManagePanel.getTable().getAllElement().size() ==0){
//					DialogBoxUtil.succeedDialog(null,ResourceUtil.srcStr(StringKeysLbl.UPDOWN_SOFTWARE));
//					return;
//				}
				try {
					controller.openCreateDialog();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		updateptp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.openUpdateDialog();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		delptp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.delete();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		needButtons.add(addptp);
		needButtons.add(updateptp);
		needButtons.add(delptp);
		return needButtons;
	}
	
	
	
	public void setController() {
		
	}

	public PtnButton getBtnSavePort() {
	return btnSavePort;
    }

    public void setBtnSavePort(PtnButton btnSavePort) {
	this.btnSavePort = btnSavePort;
     }
    
    public PtnButton getBtnSynPort() {
    	return btnSynPort;
        }

    public void setBtnSynPort(PtnButton btnSynPort) {
    	this.btnSynPort = btnSynPort;
         }
}

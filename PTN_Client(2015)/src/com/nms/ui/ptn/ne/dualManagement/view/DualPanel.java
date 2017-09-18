package com.nms.ui.ptn.ne.dualManagement.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.nms.db.bean.ptn.path.eth.DualInfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.ptn.ne.dualManagement.controller.DualContorller;
import com.nms.ui.ptn.ne.eline.view.AcElinePanel;
import com.nms.ui.ptn.ne.eline.view.PwElinePanel;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * <p>文件名称:DualPanel.java</p>
 * <p>文件描述:单站侧Dual界面</p>
 * <p>版权所有:版权所有(c)2013-2015</p>
 * <p>公司:北京建博信通软件技术有限公司</p>
 * <p>内容摘要:</p>
 * <p>其他说明:</p>
 * <p>完成时间:2015-3-5</p>
 * <p>修改记录:</p>
 * <pre>
 *   修改日期:
 *   版本号:
 *   修改人:
 *   修改内容:
 * </pre>
 * @version 1.0
 * @author zhangkun
 *
 */
public class DualPanel extends ContentView<DualInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1551216625117436909L;
	/*********************代码段：常量设置*************************************/
	private DualPanel dualPanelView;
	private JSplitPane splitPanel;
	private JTabbedPane tabbedPane;
	private JPanel infoPanel;
	private AcElinePanel acElinePanel;
	private PwElinePanel pwElinePanel;
	
	
	public DualPanel()
	{
		super("dualInfo", RootFactory.CORE_MANAGE);
		try 
		{
			this.init();
			this.dualPanelView = this;
			super.controller.refresh();
			
		} catch (Exception e) 
		{
			ExceptionManage.dispose(e, getClass());
		}
	}

	private void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_DUALINFO)));
		initComponent();
		setLayout();
		this.setActionListention();
	}

     private void initComponent() {
		tabbedPane = new JTabbedPane();
		splitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPanel.setOneTouchExpandable(true);
		int high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue()/2;
		splitPanel.setDividerLocation(high - 65);
		splitPanel.setTopComponent(this.getContentPanel());
		splitPanel.setBottomComponent(tabbedPane);
		infoPanel =  new JPanel();
		acElinePanel = new AcElinePanel();
		acElinePanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_AC_LIST)));
		pwElinePanel = new PwElinePanel();
		pwElinePanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_PW_LIST)));
		tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_BASIC_INFO), this.infoPanel);
	}

/****************设置布局*************************/
	private void setLayout() {
		setInfoPabelLayout();
		GridBagLayout panelLayout = new GridBagLayout();
		this.setLayout(panelLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		panelLayout.setConstraints(this.splitPanel, c);
		this.add(splitPanel);
		
	}

	private void setInfoPabelLayout() 
	{
	GridBagConstraints gridBagConstraints = new GridBagConstraints();
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWeights = new double[] {0.5,0.5};
	infoPanel.setLayout(gridBagLayout);
	addComponent(infoPanel, acElinePanel, 0, 0, 0.5, 1.0, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
	addComponent(infoPanel, pwElinePanel, 1, 0, 0.5, 1.0, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
    }
	
  private void setActionListention()
  {
		getTable().addElementClickedActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(getSelect() == null)
				{
					acElinePanel.clear();
					pwElinePanel.clear();
					return ;
				}else
				{
					getController().initDetailInfo();
				}
			}
		});
  }

	@Override
	public void setController() {
		super.controller = new DualContorller(this);
	}
	
	public List<JButton> setNeedRemoveButtons()
	{
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSearchButton());
		return needRemoveButtons;
	}

	public AcElinePanel getAcElinePanel() {
		return acElinePanel;
	}

	public void setAcElinePanel(AcElinePanel acElinePanel) {
		this.acElinePanel = acElinePanel;
	}

	public PwElinePanel getPwElinePanel() {
		return pwElinePanel;
	}

	public void setPwElinePanel(PwElinePanel pwElinePanel) {
		this.pwElinePanel = pwElinePanel;
	}
	
}

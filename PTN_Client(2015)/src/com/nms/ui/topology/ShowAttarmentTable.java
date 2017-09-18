package com.nms.ui.topology;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import twaver.ComponentCallbackHandler;
import twaver.TWaverConst;
import twaver.TWaverUtil;
import twaver.network.ui.ComponentAttachment;
import twaver.network.ui.ElementUI;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTab;

/**
 * @author zhangkun
 */
public class ShowAttarmentTable extends ComponentAttachment {

	private static final long serialVersionUID = -1175756273255519624L;
	private JTable table = null;/* 表格 */
	private JLabel label = null;
	private int width = 0;
	private int monamelength = 0;
	private int Kpinamelength = 0;
	private int KpiValuelength = 0;
	private int Kpimaxlength = 0;
	private int Kpiminlength = 0;
	private TextLength textLength = null;
	private int[] widths;
	private Segment sgmust = null;
	private JPanel panel = TWaverUtil.createVerticalPanel(1);

	public ShowAttarmentTable(String name, ElementUI ui) {
		super(name, ui);
		label = new JLabel(ResourceUtil.srcStr(StringKeysTab.TAB_SEGMENT_INFO), JLabel.CENTER);
		this.panel.add(label);
		sgmust = (Segment) getElementUI().getElement().getClientProperty("listKeyModel");
		textLength = new TextLength();
		initList();
		Dimension dd = panel.getPreferredSize();
		this.setSize(dd);
		this.setClosable(true);
		this.setStyle(TWaverConst.ATTACHMENT_STYLE_BUBBLE);
		this.setPosition(TWaverConst.POSITION_INNER_BOTTOMRIGHT);
		this.setXOffset(15); 
		this.setYOffset(-8);
		TWaverUtil.iteratorComponent(panel, new ComponentCallbackHandler() {
			public void processComponent(Component component) {
				if (component instanceof JComponent) {
					((JComponent) component).setOpaque(false);
				}
			}
		});

		label.setBackground(Color.blue);
		label.setForeground(Color.WHITE);
		label.setOpaque(true);

		panel.setOpaque(true);
		panel.setBackground(new Color(255, 255, 255, 160));

		this.setComponent(panel);
	}

	private void initList() {		
		String[][] tableValues = null;
		String[] columnNames = { ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_DUAN_NAME), 
				                 ResourceUtil.srcStr(StringKeysTab.TAB_SEGMENTASITE_NAME),
				                 ResourceUtil.srcStr(StringKeysTab.TAB_SEGMENTZSITE_NAME), 
				                 ResourceUtil.srcStr(StringKeysTab.TAB_SEGMENTAPort_NAME), 
				                 ResourceUtil.srcStr(StringKeysTab.TAB_SEGMENTZPort_NAME)};
		int moLength = 0;
		int kpiLength = 0;
		int kpiValueLength = 0;
		int kpiUpLength = 0;
		int kpiDownLength = 0;
		DefaultTableModel tableModel = null; 
		try {
			/* 定义表格列名数值 */
			moLength = textLength.getStringInfo(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_DUAN_NAME));
			kpiLength = textLength.getStringInfo(ResourceUtil.srcStr(StringKeysTab.TAB_SEGMENTASITE_NAME));
			kpiValueLength = textLength.getStringInfo(ResourceUtil.srcStr(StringKeysTab.TAB_SEGMENTZSITE_NAME));
			kpiUpLength = textLength.getStringInfo(ResourceUtil.srcStr(StringKeysTab.TAB_SEGMENTAPort_NAME));
			kpiDownLength = textLength.getStringInfo(ResourceUtil.srcStr(StringKeysTab.TAB_SEGMENTZPort_NAME));

			if (null != sgmust) {
				tableValues = getShuZu();
				/* 是表格成不可编辑状态 */
				tableModel = new DefaultTableModel(tableValues, columnNames) {
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};

				table = new JTable(tableModel);
				moLength=isLength(monamelength,moLength);
				kpiLength=isLength(Kpinamelength,kpiLength);
				kpiValueLength=isLength(KpiValuelength,kpiValueLength);
				kpiUpLength=isLength(Kpimaxlength,kpiUpLength);
				kpiDownLength=isLength(Kpiminlength,kpiDownLength);
				
				widths = new int[] { moLength * 11, kpiLength * 10, kpiValueLength* 10, kpiUpLength * 10, kpiDownLength * 10,};

				table.setColumnModel(this.getColumnModel(widths));

				table.setSize(300, width * 35);

				this.panel.add(table.getTableHeader());
				this.panel.add(table, BorderLayout.CENTER);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			 tableValues = null;
			 columnNames = null;
			 tableModel = null; 
		}
	}

	/**
	 * 用于判断表格的长度
	 * @param textLeng
	 * @param modelLentgh
	 * @return
	 */
	private int isLength(int textLeng, int modelLentgh) {
		int reust = 0;
		try {
			reust = (textLeng > modelLentgh) ? textLeng : modelLentgh;
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return reust;
	}

	/**
	 * 
	 * <P>
	 * 设置每列的列宽
	 * </P>
	 * 
	 * @param widths2
	 * 
	 * @return
	 */
	private TableColumnModel getColumnModel(int[] widths) {
		TableColumnModel columns = table.getColumnModel();

		for (int i = 0; i < widths.length; i++) {
			TableColumn column = columns.getColumn(i);
			column.setPreferredWidth(widths[i]);
		}
		return columns;
	}

	/**
	 * 将集合的数据转换为一个二维数组
	 * 
	 * @param listKeyModel
	 * @param size
	 * @return
	 */
	private String[][] getShuZu() {
		
		String[][] object = null;
		PortService_MB portService = null;
		SiteService_MB siteService = null;
		SiteInst aSiteInst = null;
		SiteInst zSiteInst = null;
		PortInst aPortInst = null;
		PortInst zPortInst = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			aSiteInst = siteService.select(sgmust.getASITEID());
			zSiteInst = siteService.select(sgmust.getZSITEID());
			aPortInst = portService.selectPortybyid(sgmust.getAPORTID());
			zPortInst = portService.selectPortybyid(sgmust.getZPORTID());
			
			object = new String[1][5];
			object[0][0] = sgmust.getNAME();
			object[0][1] = aSiteInst.getCellId();
			object[0][2] = zSiteInst.getCellId();
			object[0][3] = aPortInst.getPortName();
			object[0][4] = zPortInst.getPortName();
			
			monamelength = textLength.getStringInfo(sgmust.getNAME());
			Kpinamelength = textLength.getStringInfo(aSiteInst.getCellDescribe());
			KpiValuelength = textLength.getStringInfo(zSiteInst.getCellDescribe());
			Kpimaxlength = textLength.getStringInfo(aPortInst.getPortName());
			Kpiminlength = textLength.getStringInfo(zPortInst.getPortName());
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally
		{
             UiUtil.closeService_MB(portService);
             UiUtil.closeService_MB(siteService);
             aSiteInst = null;
     		 zSiteInst = null;
     		 aPortInst = null;
     		 zPortInst = null;
		}
		return object;
	}
}

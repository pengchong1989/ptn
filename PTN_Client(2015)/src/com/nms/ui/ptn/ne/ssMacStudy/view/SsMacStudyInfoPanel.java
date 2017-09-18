package com.nms.ui.ptn.ne.ssMacStudy.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import com.nms.ui.frame.ViewDataTable;
import com.nms.db.bean.ptn.SsMacStudy;


public class SsMacStudyInfoPanel  extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4442083213455101239L;
	private JScrollPane scrollPane_pw;
	private ViewDataTable<SsMacStudy> table_ssmacstudy;
	private final String TABLENAME_MACADDRESS = "macAddress";

	public SsMacStudyInfoPanel() {		
			initComponents();
			setLayout();	
	}

	public void clear() {
		table_ssmacstudy.clear();
	}

	public void initData(SsMacStudy ssMacStudy) throws Exception {
		if (null != ssMacStudy) {
			List<SsMacStudy> ssMacStudyList = null;
			try {
				ssMacStudyList = new ArrayList<SsMacStudy>();
				String []macs= ssMacStudy.getMacAddress().split("\\|");
				for(int i = 0; i< macs.length; i++)
				{
					SsMacStudy ss = new SsMacStudy();
					ss.setPortId(ssMacStudy.getPortId());
					ss.setVlan(ssMacStudy.getVlan());
					ss.setMacAddress(macs[i]);
					ssMacStudyList.add(ss);
				}
             table_ssmacstudy.initData(ssMacStudyList);
			} catch (Exception e) {
				throw e;
			} finally {

			}
		}
	}
	

	private void initComponents() {
		table_ssmacstudy = new ViewDataTable<SsMacStudy>(TABLENAME_MACADDRESS);
		table_ssmacstudy.getTableHeader().setResizingAllowed(true);
		table_ssmacstudy.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table_ssmacstudy.setTableHeaderPopupMenuFactory(null);
		table_ssmacstudy.setTableBodyPopupMenuFactory(null);
		scrollPane_pw = new JScrollPane();
		scrollPane_pw.setViewportView(table_ssmacstudy);
		scrollPane_pw.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_pw.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	private void setLayout() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		GridBagLayout qosGridBagLayout = new GridBagLayout();
		this.setLayout(qosGridBagLayout);
		addComponent(this, scrollPane_pw, 0, 0, 0.5, 1.0, 0, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
	}

	private void addComponent(JPanel panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
	}


	
}

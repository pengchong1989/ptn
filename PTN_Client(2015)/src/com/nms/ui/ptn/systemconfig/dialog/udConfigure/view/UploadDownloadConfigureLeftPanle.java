package com.nms.ui.ptn.systemconfig.dialog.udConfigure.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import twaver.DataBoxSelectionEvent;
import twaver.DataBoxSelectionListener;
import twaver.Node;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.system.Field;
import com.nms.model.util.SiteFileTree;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.ptn.systemconfig.UploadDownloadConfigurePanel;

/**
 * 上载/下载配置文件左边面板
 * 
 * @author pc
 * 
 */
public class UploadDownloadConfigureLeftPanle extends JPanel {

	private static final long serialVersionUID = -1267873479538816905L;
	private JLabel lblQuery;
	private JTextField txtQuery;
	private JScrollPane scrollPane;
	public  Field constField = null;
	private UploadDownloadConfigurePanel view;
	private SiteFileTree siteTreePanel; // 网元树

	public UploadDownloadConfigureLeftPanle(UploadDownloadConfigurePanel uploadDownloadConfigurePanel) {
		view = uploadDownloadConfigurePanel;
		this.initComponents();
		this.setLayout();
		this.addListener();
	}

	private void initComponents() {
		this.siteTreePanel = new SiteFileTree(null, null, null);
		lblQuery = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_QUICK_FIND));
		txtQuery = new JTextField();
		scrollPane = new JScrollPane(this.siteTreePanel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportView(this.siteTreePanel);
	}

	/**
	 * box点击事件
	 */
	private void addListener() {
		this.siteTreePanel.getBox().getSelectionModel().addDataBoxSelectionListener(new DataBoxSelectionListener() {

			@SuppressWarnings({ "rawtypes" })
			@Override
			public void selectionChanged(DataBoxSelectionEvent arg0) {

				try {
					Iterator it = siteTreePanel.getBox().getSelectionModel().selection();
					List<Integer> siteInsts = new ArrayList<Integer>();
					while (it.hasNext()) {
						Node node = (Node) it.next();
						if (node.getUserObject() instanceof SiteInst) {
							siteInsts.add(((SiteInst) node.getUserObject()).getSite_Inst_Id());
						}
					}
					view.getUploadDownloadConfigureRightPanle().setIntegers(siteInsts);
					view.getUploadDownloadConfigureRightPanle().getController().refresh();
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}
			}
		});
	}

	private void setLayout() {
		GridBagLayout c = new GridBagLayout();
		c.columnWidths = new int[] { 50, 180, 20 };
		c.columnWeights = new double[] { 0, 0, 0, 0 };
		c.rowHeights = new int[] { 10, 540 };
		c.rowWeights = new double[] { 0, 0, 0, 0, 0 };
		this.setLayout(c);

		GridBagConstraints g = new GridBagConstraints();
		g.fill = GridBagConstraints.BOTH;
		g.insets = new Insets(5, 5, 5, 5);

		// 第一行
		g.gridx = 0;
		g.gridy = 0;
		c.setConstraints(this.lblQuery, g);
		this.add(this.lblQuery);

		g.gridx = 1;
		c.setConstraints(this.txtQuery, g);
		this.add(this.txtQuery);

		// 第二行
		g.gridx = 0;
		g.gridy = 1;
		g.gridwidth = 2;
		c.setConstraints(this.scrollPane, g);
		this.add(this.scrollPane);

	}

}

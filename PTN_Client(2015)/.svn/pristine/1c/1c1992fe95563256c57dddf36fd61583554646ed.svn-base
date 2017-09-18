package com.nms.ui.ptn.systemconfig.dialog.bsUpdate.contriller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.ptn.systemconfig.dialog.bsUpdate.view.AfterUpgradeDialog;
import com.nms.ui.ptn.systemconfig.dialog.bsUpdate.view.BatchUpgradeVersionsDialog;
import com.nms.ui.ptn.upgradeManage.UpgradeManageUtil;
public class BatchUpgradeVersionsDialogController implements ActionListener {

	public BatchUpgradeVersionsDialog view;
	private ZipFile zipFile;
	private List<ZipEntry> zipEntries = new ArrayList<ZipEntry>();
	private List<SiteInst> siteInstList;
	private BatchUpgradeVersionsDialog batchUpgradeVersionsDialog;
	public BatchUpgradeVersionsDialogController(BatchUpgradeVersionsDialog batchUpgradeVersionsDialog) {
		this.batchUpgradeVersionsDialog = batchUpgradeVersionsDialog;
		this.view = batchUpgradeVersionsDialog;	 
		try {
			siteInstList =  this.batchUpgradeVersionsDialog.getBatchSoftwareUpgradeRightPanel().getAllSelect();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
		}		
		addListener();
	}



	private void addListener() {
		view.getConfirm().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String filePath =view.getChooseFileTextField().getText();
					if(null ==filePath ||filePath.equals("")){
						DialogBoxUtil.errorDialog(null,  ResourceUtil.srcStr(StringKeysLbl.LBL_SELECT_UPGRADEFILE));
						return ;
					}
					zipFile = UpgradeManageUtil.loadZip(zipEntries, filePath);
					//由于异步的原因，网元升级结果只能在开始升级之前置""
					for(SiteInst siteInst:siteInstList){
						siteInst.setResult("");
						siteInst.setSchedule(0);
					}
					AfterUpgradeDialog afterUpgradePanel = new AfterUpgradeDialog(zipEntries,zipFile,siteInstList,view.getType(),new JFrame());
					UiUtil.showWindow(afterUpgradePanel, 800, 500);
					view.dispose();
					List<SiteInst> insts = batchUpgradeVersionsDialog.getBatchSoftwareUpgradeRightPanel().getTable().getAllElement();
					batchUpgradeVersionsDialog.getBatchSoftwareUpgradeRightPanel().clear();
					batchUpgradeVersionsDialog.getBatchSoftwareUpgradeRightPanel().initData(insts);
					batchUpgradeVersionsDialog.getBatchSoftwareUpgradeRightPanel().updateUI();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});
		
		view.getCancel().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					view.dispose();
					if(zipFile != null){
						zipFile.close();
					}
				} catch (Exception e2) {
					ExceptionManage.dispose(e2,this.getClass());
				}
			}
		});

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals(ResourceUtil.srcStr(StringKeysBtn.BTN_OPEN))) {
			File file = null;
			view.getFileChooser().setApproveButtonText(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
			view.getFileChooser();
			view.getFileChooser().setFileFilter(new FileNameExtensionFilter("ZIP", "zip"));
			int result = view.getFileChooser().showOpenDialog(view);
			if (result == JFileChooser.APPROVE_OPTION) {
				file = view.getFileChooser().getSelectedFile();
				view.getChooseFileTextField().setText(file.getAbsolutePath());
				try {
				} catch (Exception e1) {
					ExceptionManage.dispose(e1, this.getClass());
				}
				
			}
		}
	}
}
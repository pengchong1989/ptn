package com.nms.ui.ptn.business.dialog.elanpath;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;

import twaver.Element;
import twaver.Link;
import twaver.Node;
import twaver.TView;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.ptn.ne.ac.view.AcListDialog;

/**
 * 添加elan的右键菜单类
 * 
 * @author kk
 * 
 */
public class AddElanMenu {

	private AddElanDialog addElanDialog = null;
	private TView tView = null;
	private  List<AcPortInfo> acPortListInfo = new ArrayList<AcPortInfo>();
	public AddElanMenu(AddElanDialog addElanDialog, TView tView) {
		this.addElanDialog = addElanDialog;
		this.tView = tView;
	}

	/**
	 * 创建右键菜单
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	protected JMenuItem createMenu(final String key, final Element element,final ElanInfo info,final List<ElanInfo> infos) throws Exception {
		if (null == key && "".equals(key)) {
			throw new Exception("key is null");
		}
		JMenuItem menuItem = null;
		try {

			menuItem = new JMenuItem(ResourceUtil.srcStr(key));
			menuItem.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					Link link = null;
					
					try {
						// 设置
						if (key.equals(StringKeysMenu.MENU_CONFIG)) {

							if (element instanceof Node) {
								Node node = (Node) element;
								node.setBusinessObject(ResourceUtil.srcStr(StringKeysObj.OBJ_SELECTED));
								node.addAttachment("topoTitle");
								// 弹出选择AC端口界面
								selectPort(element,infos);
								autoSelectPw(node);
								addElanDialog.getSelectNodeList().add(node);
							}

						} else if (key.equals(StringKeysMenu.MENU_CANEL_CONFIG)) { // 取消设置
							if (element instanceof Node) {
								element.removeAttachment("topoTitle");
								element.setBusinessObject("");
								deleteAc(element,info);
								deletePw(element);
								addElanDialog.getSelectNodeList().remove(element);
							} else if (element instanceof Link) {
								link = (Link) element;
								deletePath(link);
							}

						} else if (key.equals(StringKeysMenu.MENU_SELECT_PORT)) { // 选择端口

							selectPort(element,infos);

						} else if (key.equals(StringKeysMenu.MENU_SELECT_PATH)) { // 设置路径
							link = (Link) element;
							setPath(link);
						}
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}

				}
			});

		} catch (Exception e) {
			throw e;
		}
		return menuItem;
	}

	/**
	 * 设置端口
	 * @throws Exception
	 */
	private void selectPort(Element element, List<ElanInfo> infos) throws Exception {
		AcListDialog acListDialog = null;
		List<AcPortInfo> listAcportInfo;
		SiteInst siteInst = null; 
		List<AcPortInfo> acPortList = null; 
		List<AcPortInfo> acPortListOther=null;
				
		try {
			if(infos != null && !infos.isEmpty()){
				acPortListOther = new ArrayList<AcPortInfo>();
				acListDialog = new AcListDialog(true, element,infos);
				siteInst = (SiteInst) element.getUserObject();
				listAcportInfo=new ArrayList<AcPortInfo>();
				acPortList = this.addElanDialog.getBranchAcTable().getAllElement();
				boolean fals =isContains(acPortList);
				if(fals){
					removeRepeatedType();
						for(AcPortInfo acinfo:acPortListInfo){
							if(acinfo.getSiteId()==siteInst.getSite_Inst_Id()){
								listAcportInfo.add(acinfo);
							}else{
								acPortListOther.add(acinfo);
							}
						}
				}
				acPortListInfo.clear();
				acPortListInfo.addAll(acPortListOther);
				acListDialog.getAcTable().addData(listAcportInfo);
				UiUtil.showWindow(acListDialog, 600, 350);
			}else{
				acListDialog = new AcListDialog(true, element,0);
			}
			addElanDialog.setport(acListDialog, element);
		} catch (Exception e) {
			throw e;
		} finally {
			acListDialog = null;
			listAcportInfo=null;
			siteInst = null; 
			acPortList = null; 
			acPortListOther=null;
		}

	}

	/**
	 * 设置路径
	 * 
	 * @throws Exception
	 */
	private void setPath(Link link) throws Exception {
		PwInfo pwInfo = null;
		List<PwInfo> pwInfoList = null;
		try {
			pwInfo = (PwInfo) link.getUserObject();
			this.checkingPath(pwInfo);

			link.putLinkColor(Color.BLUE);
			pwInfoList = this.addElanDialog.getPwInfoTable().getAllElement();
			pwInfoList.add(pwInfo);
			this.addElanDialog.getPwInfoTable().clear();
			this.addElanDialog.getPwInfoTable().initData(pwInfoList);
		} catch (Exception e) {
			throw e;
		} finally {
			pwInfo = null;
			pwInfoList = null;
		}

	}

	/**
	 * 删除路径
	 * 
	 * @throws Exception
	 */
	private void deletePath(Link link) throws Exception {
		PwInfo pwInfo = null;
		List<PwInfo> pwInfoList = null;
		List<PwInfo> pwInfoList_add = null;
		try {
			pwInfoList_add = new ArrayList<PwInfo>();

			link.putLinkColor(Color.GREEN);
			pwInfo = (PwInfo) link.getUserObject();
			pwInfoList = this.addElanDialog.getPwInfoTable().getAllElement();
			for (PwInfo pwInfo_table : pwInfoList) {
				if (pwInfo_table.getPwId() != pwInfo.getPwId()) {
					pwInfoList_add.add(pwInfo_table);
				}
			}
			this.addElanDialog.getPwInfoTable().clear();
			this.addElanDialog.getPwInfoTable().initData(pwInfoList_add);
		} catch (Exception e) {
			throw e;
		} finally {
			pwInfo = null;
			pwInfoList = null;
			pwInfoList_add = null;
		}

	}

	/**
	 * 验证所有link中是否有与pwinfo相同路径的 如果有调用移除方法
	 * 
	 * @param pwinfo
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void checkingPath(PwInfo pwinfo) throws Exception {
		List<Element> allElements = null;
		PwInfo pwInfoLink = null;
		Link link = null;
		try {
			allElements = tView.getDataBox().getAllElements();

			for (Element element : allElements) {
				if (element instanceof Link) {
					link = (Link) element;
					pwInfoLink = (PwInfo) link.getUserObject();
					if ((pwInfoLink.getASiteId() == pwinfo.getASiteId() || pwInfoLink.getASiteId() == pwinfo.getZSiteId()) && (pwInfoLink.getZSiteId() == pwinfo.getASiteId() || pwInfoLink.getZSiteId() == pwinfo.getZSiteId())) {
						this.deletePath(link);
					}
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			allElements = null;
			pwInfoLink = null;
			link = null;
		}
	}

	/**
	 * 删除AC
	 * 
	 * @param element
	 * @throws Exception
	 */
	private void deleteAc(Element element,ElanInfo info) throws Exception {

		SiteInst siteInst = null; 
		List<AcPortInfo> acPortList = null; 
		List<AcPortInfo> acPortList_add = null;
		try {
			acPortList_add = new ArrayList<AcPortInfo>();
			siteInst = (SiteInst) element.getUserObject();
			acPortList = this.addElanDialog.getBranchAcTable().getAllElement();
			
			if (null != acPortList && acPortList.size() > 0) {
				for (AcPortInfo acPortInfo : acPortList) {
					//如果相等就是这个网元所属AC
					if (acPortInfo.getSiteId() != siteInst.getSite_Inst_Id()) {
						acPortList_add.add(acPortInfo);
					}else{
						if(info!=null){
							acPortListInfo.add(acPortInfo);
						}
					}
				}
				this.addElanDialog.getBranchAcTable().clear();
				this.addElanDialog.getBranchAcTable().initData(acPortList_add);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			siteInst = null;
			acPortList = null;
			acPortList_add = null;
		}
	}
	/**
	 * 删除pw
	 * 
	 * @param element
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void deletePw(Element element) throws Exception {
		List<Element> allElements = null;
		SiteInst siteInst = null;
		Link link = null;
		PwInfo pwInfoLink = null;
		try {
			allElements = tView.getDataBox().getAllElements();
			siteInst = (SiteInst) element.getUserObject();
			for (Element element_all : allElements) {
				if (element_all instanceof Link) {
					link = (Link) element_all;
					pwInfoLink = (PwInfo) link.getUserObject();
					if (pwInfoLink.getASiteId() == siteInst.getSite_Inst_Id() || pwInfoLink.getZSiteId() == siteInst.getSite_Inst_Id()) {
						this.deletePath(link);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			allElements = null;
			siteInst = null;
			link = null;
			pwInfoLink = null;
		}
	}

	/**
	 * 自动发现pw 点击设置按钮后调用此方法
	 * 
	 * @param type
	 * @throws Exception
	 */
	private void autoSelectPw(Node node) throws Exception {
		try {

			if (this.addElanDialog.getSelectNodeList().size() > 0) {
				for (Node node_select : this.addElanDialog.getSelectNodeList()) {
					this.findLink(node_select, node);
				}
			}

		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 找nodeA和nodez之间的link
	 * 
	 * @param node_a
	 * @param node_z
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void findLink(Node node_a, Node node_z) throws Exception {

		List<Link> linkList = null;
		List<Element> elementList = null;
		Link link = null;
		boolean flag = true;
		try {
			linkList = new ArrayList<Link>();
			elementList = tView.getDataBox().getAllElements();

			// 遍历拓扑上所有元素，如果是link。并且from和to 与参数node_a和node_z一致，就放到集合中。
			for (Element element : elementList) {

				if (element instanceof Link) {
					link = (Link) element;
					if ((link.getFrom() == node_a || link.getFrom() == node_z) && (link.getTo() == node_a || link.getTo() == node_z)) {
						linkList.add(link);
					}
				}
			}

			// 如果node_a和node_z中link大与0条。 并且没有蓝色（已经选择）的,就默认选中一条路径
			if (linkList.size() > 0) {
				for (Link link_find : linkList) {
					if (link_find.getLinkColor() == Color.BLUE) {
						flag = false;
						break;
					}
				}

				if (flag) {
					this.setPath(linkList.get(0));
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			linkList = null;
			elementList = null;
			link = null;
		}
	}
	//去除相同的项  acPortListInfo
	private void removeRepeatedType() {
		List<AcPortInfo> NorepeatedCapability = null;
		NorepeatedCapability=new ArrayList<AcPortInfo>();
		NorepeatedCapability.addAll(acPortListInfo);
		for (int i = 0; i < NorepeatedCapability.size() - 1; i++) {
			for (int j = NorepeatedCapability.size() - 1; j > i; j--) {
				if (NorepeatedCapability.get(j).getSiteId()==NorepeatedCapability.get(i).getSiteId()) {
					NorepeatedCapability.remove(j);
				}
			}
		}
		acPortListInfo.clear();
		acPortListInfo.addAll(NorepeatedCapability);
	}
	
	private boolean isContains(List<AcPortInfo> acPortList){
		try {
			int length=acPortListInfo.size();
			if (null != acPortList && acPortList.size() > 0) {
				for (AcPortInfo acPortInfo : acPortList) {
						for(int i=0;i<length;i++){
							if(acPortListInfo.get(i).getSiteId()==acPortInfo.getSiteId()){
								acPortListInfo.remove(i);
								return false;
							}
						}
						
					}
				}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return true;
	}
}

package com.nms.ui.ptn.systemconfig.dialog.fieldConfig.action;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import twaver.Dummy;
import twaver.Element;
import twaver.Group;
import twaver.Node;
import twaver.SubNetwork;
import twaver.TDataBox;
import twaver.tree.ElementNode;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.system.Field;
import com.nms.db.bean.system.NetWork;
import com.nms.db.bean.system.user.UserField;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.system.FieldService_MB;
import com.nms.model.system.NetService_MB;
import com.nms.model.system.SubnetService_MB;
import com.nms.model.system.user.UserFieldService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.util.TopologyUtil;
import com.nms.ui.ptn.systemconfig.NeConfigView;

public class FieldConfigAction {

	// 点击树节点触发事件
	public void addTreeNodeClickAction(NeConfigView view) {
		if (view.getLeftPane().getTree().getSelectionPath().getLastPathComponent() instanceof ElementNode) {
			ElementNode elementNode = (ElementNode) view.getLeftPane().getTree().getSelectionPath().getLastPathComponent();
			if (elementNode.getElement().getClass().getSimpleName().equals("Dummy")) {
				Dummy network = (Dummy) elementNode.getElement();
				Field field = (Field) network.getUserObject();
				view.getLeftPane();
				view.getLeftPane().setConstField(field);
				try {
					view.getRightPanel().setField(field);
					view.getRightPanel().getController().refresh();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}else if(elementNode.getElement().getClass().getSimpleName().equals("SubNetwork")){
				SubNetwork subNetwork = (SubNetwork) elementNode.getElement();
				NetWork netWork = (NetWork) subNetwork.getUserObject();
				ConstantUtil.fieldId = netWork.getNetWorkId();
			}
		}
	}

	// 初始化树,显示树节点
	public void initTree(NeConfigView view) {
		view.getLeftPane().getBox().clear();
		UserFieldService_MB userFieldService = null;
		FieldService_MB fieldService = null;
		List<Field> fieldList = null;
		List<UserField> fields = null;
		List<Integer> ids = null;
		List<NetWork> netWorks = null;
		NetService_MB netService = null;
		try {
			fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			userFieldService = (UserFieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.USERFIELD);
			netService = (NetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.NETWORKSERVICE);
			fields = userFieldService.query(ConstantUtil.user.getUser_Id());//
			netWorks = netService.select();
			if (fields.size() == 0 || fields == null) {
				fieldList = fieldService.select();
			} else {
				ids = new ArrayList<Integer>();
				for (int i = 0; i < fields.size(); i++) {
					ids.add(fields.get(i).getField_id());
				}
				fieldList = fieldService.selectfieldidByid(ids);
			}
			
				for(NetWork netWork :netWorks){
					SubNetwork subNetwork = new SubNetwork();
					subNetwork.setName(netWork.getNetWorkName());
					subNetwork.setLocation(netWork.getNetX(), netWork.getNetY());
					subNetwork.setUserObject(netWork);
					subNetwork.setColorBackground(new Color(153, 204, 255));
					view.getLeftPane().getBox().addElement(subNetwork);
					for(Field field :fieldList){
						if(field.getNetWorkId() == netWork.getNetWorkId()){
							refreshTreeBox(view.getLeftPane().getBox(), field,subNetwork);
						}
					}
				}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(userFieldService);
			UiUtil.closeService_MB(fieldService);
			UiUtil.closeService_MB(netService);
		}
	}

	// 更新树节点
	private void refreshTreeBox(TDataBox box, Field field,SubNetwork groupSubNetwork) {
		
		Dummy subNetwork = null;
		List<SiteInst> siteInstList = null;
		SubnetService_MB subnetService = null;
		List<Field> fieldList_subnet = null; // 子网集合
		Element element_parent = null; // 网元的父元素
		Group group = null;
		try {
			// 创建域对象
			subNetwork = new Dummy();
			subNetwork.setName(field.getFieldName());
			subNetwork.setLocation(field.getFieldX(), field.getFieldY());
			subNetwork.setUserObject(field);
			subNetwork.setParent(groupSubNetwork);
			box.addElement(subNetwork);

			// 先把域中的网元都显示出来
			siteInstList = field.getSiteInstList();
			if (null != siteInstList && siteInstList.size() > 0) {
				for (int i = 0; i < siteInstList.size(); i++) {
					box.addElement(this.convertNodeSite(siteInstList.get(i), subNetwork));
				}
			}

			subnetService = (SubnetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SUBNETSERVICE);
			fieldList_subnet = subnetService.querySiteByCondition(field);
			if (null != fieldList_subnet && fieldList_subnet.size() > 0) {
				// 遍历子网。添加到拓扑中
				for (Field field_subnet : fieldList_subnet) {
					group = this.createGroup(box,field_subnet, subNetwork);
					if (null != field_subnet.getSiteInstList() && field_subnet.getSiteInstList().size() > 0) {
						for (SiteInst siteInst : field_subnet.getSiteInstList()) {
							element_parent = group;
							// 添加网元
							box.addElement(this.convertNodeSite(siteInst, element_parent));
						}
					}
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(subnetService);
		}
	}
	/**
	 * 在拓扑上创建子网
	 * 
	 * @param field
	 *            子网db对象
	 * @param parent
	 *            域对象
	 * @return group 子网拓扑对象
	 */
	private Group createGroup(TDataBox box,Field field, Element parent) throws Exception {
		Group group = new Group();
		try {
			group.setName(field.getFieldName());
			group.setLocation(field.getFieldX(), field.getFieldY());
			group.setParent(parent);
			group.setUserObject(field);
			box.addElement(group);
		} catch (Exception e) {
			throw e;
		}
		return group;
	}

	/**
	 * 根据网元对象创建拓扑node
	 * 
	 * @param siteInst
	 * @param group
	 * @return
	 * @throws Exception
	 */
	private Node convertNodeSite(SiteInst siteInst, Element element) throws Exception {
		Node node = null;
		TopologyUtil topologyUtil=new TopologyUtil();
		try {
			node = new Node(siteInst.getSite_Inst_Id());
			node.setName(siteInst.getCellId() + "");
//			if(element instanceof SubNetwork){
				node.setLocation(siteInst.getSiteX(), siteInst.getSiteY());
//			}else{
//				Group group=(Group) element;
//				node.setLocation(group.getLocation());
//			}
			node.setParent(element);
			if (siteInst.getIsGateway() == 1) {
				node.putLabelIcon(getClass().getResource("/com/nms/ui/images/topo/gateway.png").toString());
			}
			topologyUtil.setNodeImage(node, siteInst);
			node.setUserObject(siteInst);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
		return node;
	}


	// 初始列表
	public void initTabledata(Field constField, NeConfigView view) {
		if (constField == null) {
			return;
		}

		SiteService_MB siteService = null;
		List<SiteInst> siteInstList = null;
		try {
//			view.getRightPanel().getSiteInstTable().clear();
			view.getRightPanel().getTable().clear();
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);

			SiteInst siteInst = new SiteInst();
			siteInst.setFieldID(constField.getId());
			siteInstList = siteService.select(siteInst);

			for (SiteInst site : siteInstList) {
//				view.getRightPanel().getSiteInstTable().add(site);
				view.getRightPanel().getTable().add(site);
			}
			view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}

	}

	// 下发配置
	public void confirmActionPerformed(NeConfigView view) {
		try {
			DispatchUtil configDispath = new DispatchUtil(RmiKeys.RMI_ADMINISTRATECONFIG);
			view.getLeftPane();
			if (view.getLeftPane().getConstField() != null)
				configDispath.excuteInsert(view.getLeftPane().constField.getId());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	//
	public void clearActionPerformed(NeConfigView view) {

	}

}

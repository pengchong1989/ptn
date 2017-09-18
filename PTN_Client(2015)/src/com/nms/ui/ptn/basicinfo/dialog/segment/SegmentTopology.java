/*
 * EquipmentTopology.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.basicinfo.dialog.segment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import twaver.Element;
import twaver.Link;
import twaver.Node;
import twaver.TDataBox;
import twaver.TUIManager;
import twaver.TWaverConst;
import twaver.network.TNetwork;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.TopoAttachment;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.util.TopologyUtil;

/**
 * 
 * @author __USER__
 */
public class SegmentTopology extends javax.swing.JPanel {
	
	private static final long serialVersionUID = -2990814889188609036L;
	//private static SegmentTopology equipmentTopology = null;
	private  TDataBox box = new TDataBox();
	private  TNetwork network = new TNetwork(box);
	private  Node node_a = null;
	private  Node node_z = null;

	 {
		TUIManager.registerAttachment("topoTitle", TopoAttachment.class);
	}

	public TDataBox getBox() {
		return this.box;
	}

	public  Node getNode_a() {
		return node_a;
	}

	public void setNode_a(Node node_a) {
		this.node_a = node_a;
	}

	public  Node getNode_z() {
		return node_z;
	}

	public void setNode_z(Node node_z) {
		this.node_z = node_z;
	}

//	public static SegmentTopology getTopology() {
//		if (equipmentTopology == null) {
//			equipmentTopology = new SegmentTopology();
//		}
//		return equipmentTopology;
//	}

	public TNetwork getNetWork() {
		return network;
	}

	/** Creates new form EquipmentTopology */
	public SegmentTopology() {
		super(new BorderLayout());
		try {
			//equipmentTopology = this;
			this.add(network);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
		}
	}

	/**
	 * 弹出窗口
	 */
	public void showSegmentDialog(SiteInst siteInst, String type, Element element,AddSegment dialog) {
		try {

			this.removeLink();

			final SegmentDiaLog segmentdialog = new SegmentDiaLog(true, siteInst, type, element,dialog);

			/**
			 * 给NODE赋值
			 */
			if (type.equals("A")) {
				setNode_a((Node) element);
			} else {
				setNode_z((Node) element);
			}
			segmentdialog.setLocation(UiUtil.getWindowWidth(segmentdialog.getWidth()), UiUtil.getWindowHeight(segmentdialog.getHeight()));
			segmentdialog.setVisible(true);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 绑定数据
	 * 
	 * @throws Exception
	 */
	public void boxDate() throws Exception {
		SiteService_MB siteService = null;
		List<SiteInst> siteInstList = null;
		TopologyUtil topologyUtil=new TopologyUtil();
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInstList = siteService.selectRootSite(ConstantUtil.user);

			if (siteInstList != null) {
				for (int i = 0; i < siteInstList.size(); i++) {
					Node node = new Node();
					node.setName(siteInstList.get(i).getCellId());
					node.setLocation(siteInstList.get(i).getSiteX(), siteInstList.get(i).getSiteY());
					topologyUtil.setNodeImage(node, siteInstList.get(i));
					node.setUserObject(siteInstList.get(i));
					box.addElement(node);
				}
			}
//			network.doLayout(TWaverConst.LAYOUT_EAST);
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}

	/**
	 * 移除Link
	 */
	@SuppressWarnings("rawtypes")
	public void removeLink() {
		try {
			Iterator it = box.iterator();
			while (it.hasNext()) {
				Element ele = (Element) it.next();
				if (ele instanceof Link) {
					getBox().removeElement(SegmentDiaLog.link);
					break;
				}

			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 创建link的基础属性
	 */
	private Link createLink(Node nodea, Node nodez) throws Exception {
		try {
			Link link = new Link();
			link.setFrom(nodea);
			link.setTo(nodez);
			link.setLinkType(TWaverConst.LINE_TYPE_DEFAULT);
			link.putLinkColor(Color.GREEN);
			link.putLinkFlowingWidth(3);
			link.putLinkWidth(3);
			return link;
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 根据ID绑定数据
	 * 
	 * @param ID
	 * @throws Exception
	 */
	public void boxData(int ID) throws Exception {

		Segment segment = null;
		SegmentService_MB segmentservice = null;
		List<Segment> SegmentList = null;
		Node node_a = null;
		Node node_z = null;
		int x = 0;
		int y = 0;
		SiteInst siteInst;
		SiteService_MB siteService = null;
		try {
			siteInst = new SiteInst();
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			box.clear();
			if (ID > 0) {
				segmentservice = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
				segment = new Segment();
				segment.setId(ID);
				SegmentList = segmentservice.select(segment);

				if (SegmentList != null && SegmentList.size() <= 1) {
					for (int i = 0; i < SegmentList.size(); i++) {
						siteInst = siteService.select(SegmentList.get(i).getASITEID());
						node_a = this.createNode(SegmentList.get(i).getASITEID(), siteInst.getSiteX(), siteInst.getSiteY());
						node_a.setBusinessObject("A");
						node_a.addAttachment("topoTitle");
						
						siteInst = siteService.select(SegmentList.get(i).getZSITEID());
						node_z = this.createNode(SegmentList.get(i).getZSITEID(), siteInst.getSiteX(), siteInst.getSiteY());
						if (SegmentList.size() == 1) {
							node_z.setBusinessObject("Z");
							node_z.addAttachment("topoTitle");
						}
						this.box.addElement(node_a);
						this.box.addElement(node_z);
						this.box.addElement(this.createLink(node_a, node_z));
					}
//					network.doLayout(TWaverConst.LAYOUT_EAST);
				} else {
					throw new Exception("根据主键查询返回结果错误");
				}
			}
			/*x = 20;
			y += 80;*/
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(segmentservice);
			UiUtil.closeService_MB(siteService);
		}

	}

	/**
	 * 创建node
	 */
	private Node createNode(int ID, int x, int y) throws Exception {
		SiteInst siteInst = null;
		SiteService_MB siteService = null;
		List<SiteInst> siteInstList = null;
		Node node = null;
		TopologyUtil topologyUtil=new TopologyUtil();
		try {
			siteInst = new SiteInst();
			siteInst.setSite_Inst_Id(ID);
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInstList = siteService.select(siteInst);

			if (siteInstList != null && siteInstList.size() == 1) {
				siteInst = siteInstList.get(0);

				node = new Node();
				node.setName(siteInst.getCellId());
				node.setLocation(x, y);
				topologyUtil.setNodeImage(node, siteInst);
				node.setUserObject(siteInst);
			}
			return node;
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	@SuppressWarnings("unused")
	private void initComponents() {

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));
	}// </editor-fold>
	// GEN-END:initComponents

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	// End of variables declaration//GEN-END:variables

}
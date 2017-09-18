package com.nms.drivechenxiao.test;

import java.util.Date;
import java.util.List;

import org.omg.CORBA.IntHolder;

import com.nms.drivechenxiao.service.CXDriveService;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.drivechenxiao.service.bean.porteth.qos.AFObject;
import com.nms.drivechenxiao.service.bean.porteth.qos.BEObject;
import com.nms.drivechenxiao.service.bean.porteth.qos.CSObject;
import com.nms.drivechenxiao.service.bean.porteth.qos.EFObject;
import com.nms.drivechenxiao.service.bean.porteth.uni.UNIObject;
import com.nms.drivechenxiao.service.bean.pweth.PwEthObject;
import com.nms.drivechenxiao.service.bean.qos.QosObject;
import com.nms.drivechenxiao.service.bean.qos.eelsp.EELSPQosObject;
import com.nms.drivechenxiao.service.bean.tunnel.LSPObject;
import com.nms.drivechenxiao.service.bean.tunnel.TunnelObject;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.ui.manager.ExceptionManage;

public class TEST222 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 
		IntHolder ih = new IntHolder(10002);

		EthPortObject ethPortObject = new EthPortObject();
		CSObject cs6 = new CSObject();
		cs6.setCir("0");
		CSObject cs7 = new CSObject();
		cs7.setCir("0");
		AFObject af1 = new AFObject();
		af1.setCir("0");
		AFObject af2 = new AFObject();
		af2.setCir("0");
		AFObject af3 = new AFObject();
		af3.setCir("0");
		AFObject af4 = new AFObject();
		af4.setCir("0");
		BEObject be = new BEObject();
		be.setYellowwredstart("64");
		EFObject ef = new EFObject();
		ef.setCir("40000");
		ethPortObject.setName("fe.1.1");
		ethPortObject.setRole("uni");
		ethPortObject.setCs6(cs6);
		ethPortObject.setCs7(cs7);
		ethPortObject.setAf1(af1);
		ethPortObject.setAf2(af2);
		ethPortObject.setAf3(af3);
		ethPortObject.setAf4(af4);
		ethPortObject.setBe(be);
		ethPortObject.setEf(ef);

		EthPortObject ethPortObject1 = new EthPortObject();
		CSObject cs61 = new CSObject();
		cs6.setCir("0");
		CSObject cs71 = new CSObject();
		cs7.setCir("0");
		AFObject af11 = new AFObject();
		af1.setCir("0");
		AFObject af21 = new AFObject();
		af2.setCir("0");
		AFObject af31 = new AFObject();
		af3.setCir("0");
		AFObject af41 = new AFObject();
		af4.setCir("0");
		BEObject be1 = new BEObject();
		be.setYellowwredstart("64");
		EFObject ef1 = new EFObject();
		ef.setCir("40000");
		ethPortObject1.setName("ge.1.1");
		ethPortObject1.setRole("nni");
		ethPortObject1.setCs6(cs61);
		ethPortObject1.setCs7(cs71);
		ethPortObject1.setAf1(af11);
		ethPortObject1.setAf2(af21);
		ethPortObject1.setAf3(af31);
		ethPortObject1.setAf4(af41);
		ethPortObject1.setBe(be1);
		ethPortObject1.setEf(ef1);

		EthPortObject ethPortObject2 = new EthPortObject();
		CSObject cs62 = new CSObject();
		cs6.setCir("0");
		CSObject cs72 = new CSObject();
		cs7.setCir("0");
		AFObject af12 = new AFObject();
		af1.setCir("0");
		AFObject af22 = new AFObject();
		af2.setCir("0");
		AFObject af32 = new AFObject();
		af3.setCir("0");
		AFObject af42 = new AFObject();
		af4.setCir("0");
		BEObject be2 = new BEObject();
		be.setYellowwredstart("64");
		EFObject ef2 = new EFObject();
		ef.setCir("40000");
		ethPortObject2.setName("ge.1.1");
		ethPortObject2.setRole("nni");
		ethPortObject2.setCs6(cs62);
		ethPortObject2.setCs7(cs72);
		ethPortObject2.setAf1(af12);
		ethPortObject2.setAf2(af22);
		ethPortObject2.setAf3(af32);
		ethPortObject2.setAf4(af42);
		ethPortObject2.setBe(be2);
		ethPortObject2.setEf(ef2);

		EthPortObject ethPortObject3 = new EthPortObject();
		CSObject cs63 = new CSObject();
		cs6.setCir("0");
		CSObject cs73 = new CSObject();
		cs7.setCir("0");
		AFObject af13 = new AFObject();
		af1.setCir("0");
		AFObject af23 = new AFObject();
		af2.setCir("0");
		AFObject af33 = new AFObject();
		af3.setCir("0");
		AFObject af43 = new AFObject();
		af4.setCir("0");
		BEObject be3 = new BEObject();
		be.setYellowwredstart("64");
		EFObject ef3 = new EFObject();
		ef.setCir("40000");
		ethPortObject3.setName("fe.1.1");
		ethPortObject3.setRole("uni");
		ethPortObject3.setCs6(cs63);
		ethPortObject3.setCs7(cs73);
		ethPortObject3.setAf1(af13);
		ethPortObject3.setAf2(af23);
		ethPortObject3.setAf3(af33);
		ethPortObject3.setAf4(af43);
		ethPortObject3.setBe(be3);
		ethPortObject3.setEf(ef3);

		UNIObject uniObject = new UNIObject();
		uniObject.setIclrmode("vlanpri2cng");
		uniObject.setOclrmode("trustcng");
		uniObject.setCos2vlanpri("0");
		
		EELSPQosObject qosObject1 = new EELSPQosObject("EELSP");
		qosObject1.setName("eelsptunnel3");
		qosObject1.getAf1().setIcir("0");
		qosObject1.getAf1().setOcir("0");
		qosObject1.getAf1().setIeir("0");
		qosObject1.getAf1().setOeir("0");
		qosObject1.getAf2().setIcir("0");
		qosObject1.getAf2().setOcir("0");
		qosObject1.getAf2().setIeir("0");
		qosObject1.getAf2().setOeir("0");
		qosObject1.getCs6().setIcir("0");
		qosObject1.getCs6().setOcir("0");
		qosObject1.getCs7().setIcir("0");
		qosObject1.getCs7().setOcbs("0");
		qosObject1.getEf().setIcir("640");
		qosObject1.getEf().setOcir("640");
		qosObject1.getBe().setIeir("0");
		qosObject1.getBe().setOeir("0");
		
		EELSPQosObject qosObject2 = new EELSPQosObject("EELSP");
		qosObject2.setName("eelsp2");
		qosObject2.getAf1().setIcir("0");
		qosObject2.getAf1().setOcir("0");
		qosObject2.getAf1().setIeir("0");
		qosObject2.getAf1().setOeir("0");
		qosObject2.getAf2().setIcir("0");
		qosObject2.getAf2().setOcir("0");
		qosObject2.getAf2().setIeir("0");
		qosObject2.getAf2().setOeir("0");
		qosObject2.getCs6().setIcir("0");
		qosObject2.getCs6().setOcir("0");
		qosObject2.getCs7().setIcir("0");
		qosObject2.getCs7().setOcbs("0");
		qosObject2.getEf().setIcir("40000");
		qosObject2.getEf().setOcir("40000");
		qosObject2.getBe().setIeir("0");
		qosObject2.getBe().setOeir("0");
		
		EELSPQosObject qosObject3 = new EELSPQosObject("EELSP");
		qosObject2.setName("eelsp3");
		qosObject2.getAf1().setIcir("0");
		qosObject2.getAf1().setOcir("0");
		qosObject2.getAf1().setIeir("0");
		qosObject2.getAf1().setOeir("0");
		qosObject2.getAf2().setIcir("0");
		qosObject2.getAf2().setOcir("0");
		qosObject2.getAf2().setIeir("0");
		qosObject2.getAf2().setOeir("0");
		qosObject2.getCs6().setIcir("0");
		qosObject2.getCs6().setOcir("0");
		qosObject2.getCs7().setIcir("0");
		qosObject2.getCs7().setOcbs("0");
		qosObject2.getEf().setIcir("40000");
		qosObject2.getEf().setOcir("40000");
		qosObject2.getBe().setIeir("0");
		qosObject2.getBe().setOeir("0");
		
		EELSPQosObject qosObject4 = new EELSPQosObject("EELSP");
		qosObject2.setName("eelsp4");
		qosObject2.getAf1().setIcir("0");
		qosObject2.getAf1().setOcir("0");
		qosObject2.getAf1().setIeir("0");
		qosObject2.getAf1().setOeir("0");
		qosObject2.getAf2().setIcir("0");
		qosObject2.getAf2().setOcir("0");
		qosObject2.getAf2().setIeir("0");
		qosObject2.getAf2().setOeir("0");
		qosObject2.getCs6().setIcir("0");
		qosObject2.getCs6().setOcir("0");
		qosObject2.getCs7().setIcir("0");
		qosObject2.getCs7().setOcbs("0");
		qosObject2.getEf().setIcir("40000");
		qosObject2.getEf().setOcir("40000");
		qosObject2.getBe().setIeir("0");
		qosObject2.getBe().setOeir("0");
		
		TunnelObject tunnelObject = new TunnelObject();
		tunnelObject.setName("30");
		tunnelObject.setPeerid("30");
		tunnelObject.setRole("ingress");
		tunnelObject.setQos("eelsp11");
		LSPObject[] lspObjects1 = new LSPObject[1];
		lspObjects1[0] = new LSPObject();
		lspObjects1[0].setName("1");
		lspObjects1[0].setPeer("20.0.0.203");
		lspObjects1[0].setCarrierif("ge.1.1");
		lspObjects1[0].setInlabel("4223");
		lspObjects1[0].setOutlabel("4322");
		tunnelObject.setLSPObjects(lspObjects1);
		
		TunnelObject tunnelObject2 = new TunnelObject();
		tunnelObject2.setName("1");
		tunnelObject2.setPeerid("1");
		tunnelObject2.setRole("ingress");
		tunnelObject2.setQos("eelsp11");
		LSPObject[] lspObjects2 = new LSPObject[1];
		lspObjects2[0] = new LSPObject();
		lspObjects2[0].setName("1");
		lspObjects2[0].setPeer("20.0.0.202");
		lspObjects2[0].setCarrierif("ge.1.1");
		lspObjects2[0].setInlabel("322");
		lspObjects2[0].setOutlabel("223");
		
		PwEthObject pwEthObject1 = new PwEthObject();
		pwEthObject1.setName("1");
		pwEthObject1.setPeer("20.0.0.203");
		pwEthObject1.setInlabel("1322");
		pwEthObject1.setOutlabel("1223");
		pwEthObject1.setCarrierif("tunnel/1");
		pwEthObject1.setQos("eelsp3");
		
		PwEthObject pwEthObject2 = new PwEthObject();
		pwEthObject2.setName("1");
		pwEthObject2.setPeer("20.0.0.202");
		pwEthObject2.setInlabel("1223");
		pwEthObject2.setOutlabel("1322");
		pwEthObject2.setCarrierif("tunnel/1");
		pwEthObject2.setQos("eelsp4");
		

		TEST222 TEST = new TEST222();
		CXDriveService cXDriveService = new CXDriveService();
		CXNEObject cxNeObject = new CXNEObject();
		CXNEObject cxNeObject2 = new CXNEObject();
		try {
			cxNeObject.setNeIp("20.0.0.202");
			cxNeObject.setNePort(3333);
			cxNeObject.setAdmin("admin");
			cxNeObject.setPassword("admin");
			cXDriveService.init(cxNeObject);
			OperationObject operationObject = new OperationObject();
			TEST.login(cxNeObject, operationObject, cXDriveService);
//			TEST.updatePort(cxNeObject, ethPortObject, operationObject, cXDriveService, ih);
//			TEST.updatePort(cxNeObject, ethPortObject1, operationObject, cXDriveService, ih);
//			TEST.updatePortUNI(cxNeObject, uniObject, ethPortObject, operationObject, cXDriveService, ih);
			TEST.createLSP(cxNeObject, qosObject1, tunnelObject, operationObject, cXDriveService, ih);
//			TEST.createPW(cxNeObject, qosObject3, pwEthObject1, operationObject, cXDriveService, ih);
			TEST.logOut(cxNeObject, operationObject, cXDriveService);

			cxNeObject2.setNeIp("20.0.0.203");
			cxNeObject2.setNePort(3333);
			cxNeObject2.setAdmin("admin");
			cxNeObject2.setPassword("admin");
			cXDriveService.init(cxNeObject2);
			OperationObject operationObject2 = new OperationObject();
			TEST.login(cxNeObject2, operationObject2, cXDriveService);
//			TEST.updatePort(cxNeObject2, ethPortObject2, operationObject2, cXDriveService, ih);
//			TEST.updatePort(cxNeObject2, ethPortObject3, operationObject2, cXDriveService, ih);
//			TEST.updatePortUNI(cxNeObject2, uniObject, ethPortObject3, operationObject2, cXDriveService, ih);
//			TEST.createLSP(cxNeObject2, qosObject2, tunnelObject2, operationObject2, cXDriveService, ih);
//			TEST.createPW(cxNeObject2, qosObject4, pwEthObject2, operationObject2, cXDriveService, ih);
			TEST.logOut(cxNeObject2, operationObject2, cXDriveService);

			int count = 0;
			while (true) {
				System.out.println("-------------------------------------------");
				List<CXActionObject> actionObjectList = operationObject.getCxActionObjectList();
				for (int i = 0; i < actionObjectList.size(); i++) {
					System.out.println(cxNeObject.getNeIp() + "结果:" + actionObjectList.get(i).getActionId() + " : " + actionObjectList.get(i).getStatus());
					if ("登入成功".equals(actionObjectList.get(i).getStatus())) {
						count++;
					}
				}
				System.out.println("-------------------------------------------");
				List<CXActionObject> actionObjectList2 = operationObject2.getCxActionObjectList();
				for (int i = 0; i < actionObjectList2.size(); i++) {
					System.out.println(cxNeObject2.getNeIp() + "结果:" + actionObjectList2.get(i).getActionId() + " : " + actionObjectList2.get(i).getStatus());
					if ("登入成功".equals(actionObjectList2.get(i).getStatus())) {
						count++;
					}
				}

				Thread.sleep(2000);
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,TEST222.class);
		} finally {
			cXDriveService.destroy(cxNeObject);
			cXDriveService.destroy(cxNeObject2);
		}
	}

	private void login(CXNEObject cxNeObject, OperationObject operationObject, CXDriveService cXDriveService) throws Exception {
		try {
			System.out.println("开始:" + new Date());

			int actionId = 10000;

			CXActionObject cXActionObject = new CXActionObject();
			cXActionObject.setActionId(actionId);
			cXActionObject.setCxNeObject(cxNeObject);
			operationObject.getCxActionObjectList().add(cXActionObject);

			cXDriveService.login(operationObject, cXActionObject);

			System.out.println("完成:" + new Date());

		} catch (Exception e) {
			throw e;
		}
	}

	private void logOut(CXNEObject cxNeObject, OperationObject operationObject, CXDriveService cXDriveService) throws Exception {
		try {
			System.out.println("开始:" + new Date());

			int actionId = 10001;

			CXActionObject cXActionObject = new CXActionObject();
			cXActionObject.setActionId(actionId);
			cXActionObject.setCxNeObject(cxNeObject);
			operationObject.getCxActionObjectList().add(cXActionObject);

			cXDriveService.logout(operationObject, cXActionObject);

			System.out.println("完成:" + new Date());

		} catch (Exception e) {
			throw e;
		}
	}

	private void updatePort(CXNEObject cxNeObject, EthPortObject ethPortObject, OperationObject operationObject, CXDriveService cXDriveService, IntHolder intHolder) throws Exception {
		try {
			System.out.println("开始:" + new Date());

			int actionId = intHolder.value;

			CXActionObject cXActionObject = new CXActionObject();
			cXActionObject.setActionId(actionId);
			cXActionObject.setCxNeObject(cxNeObject);
			cXActionObject.setEthPortObject(ethPortObject);
			operationObject.getCxActionObjectList().add(cXActionObject);

			cXDriveService.updatePortETH(operationObject, cXActionObject);

			System.out.println("完成:" + new Date());
			intHolder.value++;

		} catch (Exception e) {
			throw e;
		}
	}

	private void updatePortUNI(CXNEObject cxNeObject, UNIObject uniObject, EthPortObject ethPortObject, OperationObject operationObject, CXDriveService cXDriveService, IntHolder intHolder) throws Exception {
		try {
			System.out.println("开始:" + new Date());

			int actionId = intHolder.value;

			CXActionObject cXActionObject = new CXActionObject();
			cXActionObject.setActionId(actionId);
			cXActionObject.setCxNeObject(cxNeObject);
			cXActionObject.setEthPortObject(ethPortObject);
			cXActionObject.setUNIObject(uniObject);
			operationObject.getCxActionObjectList().add(cXActionObject);

			cXDriveService.updatePortETH(operationObject, cXActionObject);

			System.out.println("完成:" + new Date());
			intHolder.value++;
		} catch (Exception e) {
			throw e;
		}
	}

	private void createLSP(CXNEObject cxNeObject, QosObject qosObject, TunnelObject tunnelObject, OperationObject operationObject, CXDriveService cXDriveService, IntHolder intHolder) throws Exception {
		try {
			System.out.println("开始:" + new Date());

			int actionId = intHolder.value;

			CXActionObject cXActionObject = new CXActionObject();
			cXActionObject.setActionId(actionId);
			cXActionObject.setCxNeObject(cxNeObject);
			cXActionObject.setQosObject(qosObject);
			cXActionObject.setTunnelObject(tunnelObject);
			operationObject.getCxActionObjectList().add(cXActionObject);

			cXDriveService.createTunnelObject(operationObject, cXActionObject);

			System.out.println("完成:" + new Date());
			intHolder.value++;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void createPW(CXNEObject cxNeObject, QosObject qosObject, PwEthObject pwEthObject, OperationObject operationObject, CXDriveService cXDriveService, IntHolder intHolder) throws Exception{
		try {
			System.out.println("开始:" + new Date());

			int actionId = intHolder.value;

			CXActionObject cXActionObject = new CXActionObject();
			cXActionObject.setActionId(actionId);
			cXActionObject.setCxNeObject(cxNeObject);
			cXActionObject.setQosObject(qosObject);
			cXActionObject.setPwEthObject(pwEthObject);
			operationObject.getCxActionObjectList().add(cXActionObject);

			cXDriveService.createPwEthObject(operationObject, cXActionObject);

			System.out.println("完成:" + new Date());
			intHolder.value++;
		} catch (Exception e) {
			throw e;
		}
	}

}

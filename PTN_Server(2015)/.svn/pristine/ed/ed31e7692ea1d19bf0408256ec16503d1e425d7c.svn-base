package com.nms.corba.ninterface.framework;

import org.apache.log4j.Logger;
import org.omg.CORBA.Policy;
import org.omg.PortableServer.IdAssignmentPolicyValue;
import org.omg.PortableServer.IdUniquenessPolicyValue;
import org.omg.PortableServer.ImplicitActivationPolicyValue;
import org.omg.PortableServer.LifespanPolicyValue;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAManager;
import org.omg.PortableServer.RequestProcessingPolicyValue;
import org.omg.PortableServer.Servant;
import org.omg.PortableServer.ServantRetentionPolicyValue;
import org.omg.PortableServer.ThreadPolicyValue;

public class CorbaUserPOA {

	private static Logger LOG = Logger.getLogger(CorbaUserPOA.class.getName());

	POA userPOA = null;

	POAManager userPOAManager = null;

	boolean init(String strUserPoaName) {
		LOG.info("Corba POA init begin with name" + strUserPoaName);
		try {
			POA rootPoa = CorbaServer.getInstanse().getRootPOA();

			Policy[] policys = new Policy[7];

			policys[0] = rootPoa
					.create_lifespan_policy(LifespanPolicyValue.TRANSIENT);

			policys[1] = rootPoa
					.create_id_assignment_policy(IdAssignmentPolicyValue.SYSTEM_ID);

			policys[2] = rootPoa
					.create_id_uniqueness_policy(IdUniquenessPolicyValue.UNIQUE_ID);

			policys[3] = rootPoa
					.create_implicit_activation_policy(ImplicitActivationPolicyValue.NO_IMPLICIT_ACTIVATION);

			policys[4] = rootPoa
					.create_servant_retention_policy(ServantRetentionPolicyValue.RETAIN);

			policys[5] = rootPoa
					.create_request_processing_policy(RequestProcessingPolicyValue.USE_ACTIVE_OBJECT_MAP_ONLY);

			policys[6] = rootPoa
					.create_thread_policy(ThreadPolicyValue.ORB_CTRL_MODEL);

			userPOA = rootPoa.create_POA(strUserPoaName, null, policys);
			userPOAManager = userPOA.the_POAManager();
		} catch (Exception e) {
			LOG.error("exception happen when init");
			return false;
		}

		LOG.info("Corba POA init end");

		return true;
	}

	byte[] activateObject(Servant objServant) {
		try {
			return userPOA.activate_object(objServant);
		} catch (Exception e) {
			LOG.error("activateObject failed");
		}
		return null;
	}

	void deactivateObject(byte[] oid) {
		try {
			userPOA.deactivate_object(oid);
		} catch (Exception e) {
			LOG.error("deactivateObject failed");
		}
	}

	public POA getUserPOA() {
		return userPOA;
	}

	boolean activatePOAMgr() {
		try {
			userPOAManager.activate();
		} catch (Exception e) {
			LOG.error("activatePOAMgr exception");
			return false;
		}
		return true;
	}

	boolean deactivatePOAMgr() {
		try {
			userPOAManager.deactivate(false, false);
		} catch (Exception e) {
			LOG.error("deactivatePOAMgr exception");
			return false;
		}
		return true;
	}
}

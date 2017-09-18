package com.nms.corba.ninterface.impl.service;

import globaldefs.ProcessingFailureException;
import subnetworkConnection.CCIterator_IPOA;
import subnetworkConnection.CrossConnectList_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;

public class CCIterator_Impl extends CCIterator_IPOA {
	
	private ICorbaSession userSession = null;

	public CCIterator_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}

	@Override
	public void destroy() throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLength() throws ProcessingFailureException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean next_n(int arg0, CrossConnectList_THolder arg1)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub
		return false;
	}

}

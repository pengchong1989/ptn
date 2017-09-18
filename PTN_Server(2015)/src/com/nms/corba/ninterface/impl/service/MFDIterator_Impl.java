package com.nms.corba.ninterface.impl.service;

import com.nms.corba.ninterface.framework.ICorbaSession;

import flowDomain.MFDIterator_IPOA;
import flowDomain.MFDList_THolder;
import globaldefs.ProcessingFailureException;

public class MFDIterator_Impl extends MFDIterator_IPOA {

	private ICorbaSession userSession = null;

	public MFDIterator_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}
	
	@Override
	public boolean next_n(int how_many, MFDList_THolder mfdList)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getLength() throws ProcessingFailureException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void destroy() throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

}

package com.nms.corba.ninterface.impl.protection;

import globaldefs.ProcessingFailureException;
import protection.ProtectionGroupIterator_IPOA;
import protection.ProtectionGroupList_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;

public class ProtectionGroupIterator_Impl extends ProtectionGroupIterator_IPOA {
	private ICorbaSession userSession = null;

	public ProtectionGroupIterator_Impl(ICorbaSession userSession) {
		// TODO Auto-generated constructor stub
		userSession = userSession;
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
	public boolean next_n(int arg0, ProtectionGroupList_THolder arg1)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub
		return false;
	}

}

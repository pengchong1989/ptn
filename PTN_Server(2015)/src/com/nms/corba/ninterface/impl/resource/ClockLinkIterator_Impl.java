package com.nms.corba.ninterface.impl.resource;

import globaldefs.ProcessingFailureException;
import clockSource.ClockLinkIterator_IPOA;
import clockSource.ClockLinkList_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;

public class ClockLinkIterator_Impl extends ClockLinkIterator_IPOA {

	private ICorbaSession userSession = null;

	public ClockLinkIterator_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}
	
	@Override
	public boolean next_n(int how_many, ClockLinkList_THolder clList)
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

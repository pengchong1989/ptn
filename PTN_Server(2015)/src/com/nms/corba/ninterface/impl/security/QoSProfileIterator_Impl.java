package com.nms.corba.ninterface.impl.security;

import globaldefs.ProcessingFailureException;
import transmissionDescriptor.QoSProfileIterator_IPOA;
import transmissionDescriptor.QoSProfileList_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;

public class QoSProfileIterator_Impl extends QoSProfileIterator_IPOA {
	
	private ICorbaSession userSession = null;

	public QoSProfileIterator_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}

	@Override
	public boolean next_n(int how_many, QoSProfileList_THolder qosProfileList)
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

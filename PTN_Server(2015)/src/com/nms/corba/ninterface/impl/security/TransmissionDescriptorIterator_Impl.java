package com.nms.corba.ninterface.impl.security;

import globaldefs.ProcessingFailureException;
import transmissionDescriptor.TransmissionDescriptorIterator_IPOA;
import transmissionDescriptor.TransmissionDescriptorList_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;

public class TransmissionDescriptorIterator_Impl extends
		TransmissionDescriptorIterator_IPOA {
	
	private ICorbaSession userSession = null;

	public TransmissionDescriptorIterator_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}
	
	@Override
	public boolean next_n(int how_many,
			TransmissionDescriptorList_THolder transmissionDescList)
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

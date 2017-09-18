package com.nms.corba.ninterface.framework;

import mtnmVersion.Version_IPOA;

import org.apache.log4j.Logger;


public class VersionImpl extends Version_IPOA {
	private static Logger LOG = Logger.getLogger(VersionImpl.class.getName());
	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
	    LOG.info("[getVersion]begin.");
	    String strVersion = "IDL Version:" + CorbaConfig.getInstanse().getIdlVersion();
	    LOG.info("[getVersion]version is " + strVersion);
	    LOG.info("[getVersion]end.");
	    return strVersion;
	}

}

package com.nms.snmp.ninteface.mib;
//--AgentGen BEGIN=_BEGIN
//--AgentGen END

import java.util.List;

import org.snmp4j.PDU;
import org.snmp4j.agent.DuplicateRegistrationException;
import org.snmp4j.agent.MOGroup;
import org.snmp4j.agent.MOQueryWithSource;
import org.snmp4j.agent.MOServer;
import org.snmp4j.agent.MOServerLookupEvent;
import org.snmp4j.agent.MOServerLookupListener;
import org.snmp4j.agent.mo.DefaultMOFactory;
import org.snmp4j.agent.mo.DefaultMOMutableRow2PC;
import org.snmp4j.agent.mo.MOAccessImpl;
import org.snmp4j.agent.mo.MOColumn;
import org.snmp4j.agent.mo.MOFactory;
import org.snmp4j.agent.mo.MOMutableTableModel;
import org.snmp4j.agent.mo.MOTable;
import org.snmp4j.agent.mo.MOTableIndex;
import org.snmp4j.agent.mo.MOTableIndexValidator;
import org.snmp4j.agent.mo.MOTableModel;
import org.snmp4j.agent.mo.MOTableRowFactory;
import org.snmp4j.agent.mo.MOTableSubIndex;
import org.snmp4j.agent.request.SnmpRequest;
import org.snmp4j.log.LogAdapter;
import org.snmp4j.log.LogFactory;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.SMIConstants;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

import com.nms.snmp.ninteface.framework.MOHandlerMgr;


//--AgentGen BEGIN=_IMPORT
//--AgentGen END

public class PerformanceMib 
//--AgentGen BEGIN=_EXTENDS
//--AgentGen END
implements MOGroup, MOServerLookupListener
//--AgentGen BEGIN=_IMPLEMENTS
//--AgentGen END
{

  private static final LogAdapter LOGGER = 
      LogFactory.getLogger(PerformanceMib.class);

//--AgentGen BEGIN=_STATIC
//--AgentGen END

  // Factory
  private MOFactory moFactory = 
    DefaultMOFactory.getInstance();

  // Constants 

  /**
   * OID of this MIB module for usage which can be 
   * used for its identification.
   */
  public static final OID oidPerformance =
    new OID(new int[] { 1,3,6,1,4,1,44484 });

  // Identities
  // Scalars
  // Tables

  // Notifications

  // Enumerations




  // TextualConventions
  private static final String TC_MODULE_SNMPV2_TC = "SNMPv2-TC";
  private static final String TC_DISPLAYSTRING = "DisplayString";

  // Scalars

  // Tables
  public static final OID oidPerformanceEntry = 
    new OID(new int[] { 1,3,6,1,4,1,44484,1,6,2,1 });

  // Index OID definitions
  public static final OID oidPerforindex =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,6,2,1,1 });

  // Column TC definitions for performanceEntry:
  public static final String tcModuleSNMPv2Tc = "SNMPv2-TC";
  public static final String tcDefDisplayString = "DisplayString";
    
  // Column sub-identifer definitions for performanceEntry:
  public static final int colSiteId = 2;
  public static final int colSlotId = 3;
  public static final int colObjectid = 4;
  public static final int colObjectname = 5;
  public static final int colObjecttype = 6;
  public static final int colPerformancecode = 7;
  public static final int colPerformancevalue = 8;
  public static final int colPerformancetime = 9;
  public static final int colPerformanceEndtime = 10;
  public static final int colTaskId = 11;
  public static final int colIsCardOrSite = 12;
  public static final int colStartTime = 13;

  // Column index definitions for performanceEntry:
  public static final int idxSiteId = 0;
  public static final int idxSlotId = 1;
  public static final int idxObjectid = 2;
  public static final int idxObjectname = 3;
  public static final int idxObjecttype = 4;
  public static final int idxPerformancecode = 5;
  public static final int idxPerformancevalue = 6;
  public static final int idxPerformancetime = 7;
  public static final int idxPerformanceEndtime = 8;
  public static final int idxTaskId = 9;
  public static final int idxIsCardOrSite = 10;
  public static final int idxStartTime = 11;

  private MOTableSubIndex[] performanceEntryIndexes;
  private MOTableIndex performanceEntryIndex;
  
  private MOTable<PerformanceEntryRow,
                  MOColumn,
                  MOTableModel<PerformanceEntryRow>> performanceEntry;
  private MOTableModel<PerformanceEntryRow> performanceEntryModel;


//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  /**
   * Constructs a PERFORMANCE instance without actually creating its
   * <code>ManagedObject</code> instances. This has to be done in a
   * sub-class constructor or after construction by calling 
   * {@link #createMO(MOFactory moFactory)}. 
   */
  protected PerformanceMib() {
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

  /**
   * Constructs a PERFORMANCE instance and actually creates its
   * <code>ManagedObject</code> instances using the supplied 
   * <code>MOFactory</code> (by calling
   * {@link #createMO(MOFactory moFactory)}).
   * @param moFactory
   *    the <code>MOFactory</code> to be used to create the
   *    managed objects for this module.
   */
  public PerformanceMib(MOFactory moFactory) {
  	this();
    createMO(moFactory);
//--AgentGen BEGIN=_FACTORYCONSTRUCTOR
//--AgentGen END
  }

//--AgentGen BEGIN=_CONSTRUCTORS
//--AgentGen END

  /**
   * Create the ManagedObjects defined for this MIB module
   * using the specified {@link MOFactory}.
   * @param moFactory
   *    the <code>MOFactory</code> instance to use for object 
   *    creation.
   */
  protected void createMO(MOFactory moFactory) {
    addTCsToFactory(moFactory);
    createPerformanceEntry(moFactory);
  }



  public MOTable<PerformanceEntryRow,MOColumn,MOTableModel<PerformanceEntryRow>> getPerformanceEntry() {
    return performanceEntry;
  }


  @SuppressWarnings(value={"unchecked"})
  private void createPerformanceEntry(MOFactory moFactory) {
    // Index definition
    performanceEntryIndexes = 
      new MOTableSubIndex[] {
      moFactory.createSubIndex(oidPerforindex, 
                               SMIConstants.SYNTAX_OCTET_STRING, 0, 255)
    };

    performanceEntryIndex = 
      moFactory.createIndex(performanceEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
      public boolean isValidIndex(OID index) {
        boolean isValidIndex = true;
     //--AgentGen BEGIN=performanceEntry::isValidIndex
     //--AgentGen END
        return isValidIndex;
      }
    });

    // Columns
    MOColumn[] performanceEntryColumns = new MOColumn[12];
    performanceEntryColumns[idxSiteId] = 
      moFactory.createColumn(colSiteId, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    performanceEntryColumns[idxSlotId] = 
      moFactory.createColumn(colSlotId, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    performanceEntryColumns[idxObjectid] = 
      moFactory.createColumn(colObjectid, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    performanceEntryColumns[idxObjectname] = 
      moFactory.createColumn(colObjectname, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    performanceEntryColumns[idxObjecttype] = 
      moFactory.createColumn(colObjecttype, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    performanceEntryColumns[idxPerformancecode] = 
      moFactory.createColumn(colPerformancecode, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    performanceEntryColumns[idxPerformancevalue] = 
      moFactory.createColumn(colPerformancevalue, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    performanceEntryColumns[idxPerformancetime] = 
      moFactory.createColumn(colPerformancetime, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    performanceEntryColumns[idxPerformanceEndtime] = 
      moFactory.createColumn(colPerformanceEndtime, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    performanceEntryColumns[idxTaskId] = 
      moFactory.createColumn(colTaskId, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    performanceEntryColumns[idxIsCardOrSite] = 
      moFactory.createColumn(colIsCardOrSite, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    performanceEntryColumns[idxStartTime] = 
      moFactory.createColumn(colStartTime, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    // Table model
    performanceEntryModel = 
      moFactory.createTableModel(oidPerformanceEntry,
                                 performanceEntryIndex,
                                 performanceEntryColumns);
    ((MOMutableTableModel<PerformanceEntryRow>)performanceEntryModel).setRowFactory(
      new PerformanceEntryRowFactory());
    performanceEntry = 
      moFactory.createTable(oidPerformanceEntry,
                            performanceEntryIndex,
                            performanceEntryColumns,
                            performanceEntryModel);
  }



  public void registerMOs(MOServer server, OctetString context) 
    throws DuplicateRegistrationException 
  {
    // Scalar Objects
    server.register(this.performanceEntry, context);
    server.addLookupListener(this, this.performanceEntry);
//--AgentGen BEGIN=_registerMOs
//--AgentGen END
  }

  public void unregisterMOs(MOServer server, OctetString context) {
    // Scalar Objects
    server.unregister(this.performanceEntry, context);
//--AgentGen BEGIN=_unregisterMOs
//--AgentGen END
  }

  // Notifications

  // Scalars

  // Value Validators


  // Rows and Factories

  public class PerformanceEntryRow extends DefaultMOMutableRow2PC {

     //--AgentGen BEGIN=performanceEntry::RowMembers
     //--AgentGen END

    public PerformanceEntryRow(OID index, Variable[] values) {
      super(index, values);
     //--AgentGen BEGIN=performanceEntry::RowConstructor
     //--AgentGen END
    }
    
    public OctetString getSiteId() {
     //--AgentGen BEGIN=performanceEntry::getSiteId
     //--AgentGen END
      return (OctetString) super.getValue(idxSiteId);
    }  
    
    public void setSiteId(OctetString newValue) {
     //--AgentGen BEGIN=performanceEntry::setSiteId
     //--AgentGen END
      super.setValue(idxSiteId, newValue);
    }
    
    public OctetString getSlotId() {
     //--AgentGen BEGIN=performanceEntry::getSlotId
     //--AgentGen END
      return (OctetString) super.getValue(idxSlotId);
    }  
    
    public void setSlotId(OctetString newValue) {
     //--AgentGen BEGIN=performanceEntry::setSlotId
     //--AgentGen END
      super.setValue(idxSlotId, newValue);
    }
    
    public OctetString getObjectid() {
     //--AgentGen BEGIN=performanceEntry::getObjectid
     //--AgentGen END
      return (OctetString) super.getValue(idxObjectid);
    }  
    
    public void setObjectid(OctetString newValue) {
     //--AgentGen BEGIN=performanceEntry::setObjectid
     //--AgentGen END
      super.setValue(idxObjectid, newValue);
    }
    
    public OctetString getObjectname() {
     //--AgentGen BEGIN=performanceEntry::getObjectname
     //--AgentGen END
      return (OctetString) super.getValue(idxObjectname);
    }  
    
    public void setObjectname(OctetString newValue) {
     //--AgentGen BEGIN=performanceEntry::setObjectname
     //--AgentGen END
      super.setValue(idxObjectname, newValue);
    }
    
    public OctetString getObjecttype() {
     //--AgentGen BEGIN=performanceEntry::getObjecttype
     //--AgentGen END
      return (OctetString) super.getValue(idxObjecttype);
    }  
    
    public void setObjecttype(OctetString newValue) {
     //--AgentGen BEGIN=performanceEntry::setObjecttype
     //--AgentGen END
      super.setValue(idxObjecttype, newValue);
    }
    
    public OctetString getPerformancecode() {
     //--AgentGen BEGIN=performanceEntry::getPerformancecode
     //--AgentGen END
      return (OctetString) super.getValue(idxPerformancecode);
    }  
    
    public void setPerformancecode(OctetString newValue) {
     //--AgentGen BEGIN=performanceEntry::setPerformancecode
     //--AgentGen END
      super.setValue(idxPerformancecode, newValue);
    }
    
    public OctetString getPerformancevalue() {
     //--AgentGen BEGIN=performanceEntry::getPerformancevalue
     //--AgentGen END
      return (OctetString) super.getValue(idxPerformancevalue);
    }  
    
    public void setPerformancevalue(OctetString newValue) {
     //--AgentGen BEGIN=performanceEntry::setPerformancevalue
     //--AgentGen END
      super.setValue(idxPerformancevalue, newValue);
    }
    
    public OctetString getPerformancetime() {
     //--AgentGen BEGIN=performanceEntry::getPerformancetime
     //--AgentGen END
      return (OctetString) super.getValue(idxPerformancetime);
    }  
    
    public void setPerformancetime(OctetString newValue) {
     //--AgentGen BEGIN=performanceEntry::setPerformancetime
     //--AgentGen END
      super.setValue(idxPerformancetime, newValue);
    }
    
    public OctetString getPerformanceEndtime() {
     //--AgentGen BEGIN=performanceEntry::getPerformanceEndtime
     //--AgentGen END
      return (OctetString) super.getValue(idxPerformanceEndtime);
    }  
    
    public void setPerformanceEndtime(OctetString newValue) {
     //--AgentGen BEGIN=performanceEntry::setPerformanceEndtime
     //--AgentGen END
      super.setValue(idxPerformanceEndtime, newValue);
    }
    
    public OctetString getTaskId() {
     //--AgentGen BEGIN=performanceEntry::getTaskId
     //--AgentGen END
      return (OctetString) super.getValue(idxTaskId);
    }  
    
    public void setTaskId(OctetString newValue) {
     //--AgentGen BEGIN=performanceEntry::setTaskId
     //--AgentGen END
      super.setValue(idxTaskId, newValue);
    }
    
    public OctetString getIsCardOrSite() {
     //--AgentGen BEGIN=performanceEntry::getIsCardOrSite
     //--AgentGen END
      return (OctetString) super.getValue(idxIsCardOrSite);
    }  
    
    public void setIsCardOrSite(OctetString newValue) {
     //--AgentGen BEGIN=performanceEntry::setIsCardOrSite
     //--AgentGen END
      super.setValue(idxIsCardOrSite, newValue);
    }
    
    public OctetString getStartTime() {
     //--AgentGen BEGIN=performanceEntry::getStartTime
     //--AgentGen END
      return (OctetString) super.getValue(idxStartTime);
    }  
    
    public void setStartTime(OctetString newValue) {
     //--AgentGen BEGIN=performanceEntry::setStartTime
     //--AgentGen END
      super.setValue(idxStartTime, newValue);
    }
    
    public Variable getValue(int column) {
     //--AgentGen BEGIN=performanceEntry::RowGetValue
     //--AgentGen END
      switch(column) {
        case idxSiteId: 
        	return getSiteId();
        case idxSlotId: 
        	return getSlotId();
        case idxObjectid: 
        	return getObjectid();
        case idxObjectname: 
        	return getObjectname();
        case idxObjecttype: 
        	return getObjecttype();
        case idxPerformancecode: 
        	return getPerformancecode();
        case idxPerformancevalue: 
        	return getPerformancevalue();
        case idxPerformancetime: 
        	return getPerformancetime();
        case idxPerformanceEndtime: 
        	return getPerformanceEndtime();
        case idxTaskId: 
        	return getTaskId();
        case idxIsCardOrSite: 
        	return getIsCardOrSite();
        case idxStartTime: 
        	return getStartTime();
        default:
          return super.getValue(column);
      }
    }
    
    public void setValue(int column, Variable value) {
     //--AgentGen BEGIN=performanceEntry::RowSetValue
     //--AgentGen END
      switch(column) {
        case idxSiteId: 
        	setSiteId((OctetString)value);
        	break;
        case idxSlotId: 
        	setSlotId((OctetString)value);
        	break;
        case idxObjectid: 
        	setObjectid((OctetString)value);
        	break;
        case idxObjectname: 
        	setObjectname((OctetString)value);
        	break;
        case idxObjecttype: 
        	setObjecttype((OctetString)value);
        	break;
        case idxPerformancecode: 
        	setPerformancecode((OctetString)value);
        	break;
        case idxPerformancevalue: 
        	setPerformancevalue((OctetString)value);
        	break;
        case idxPerformancetime: 
        	setPerformancetime((OctetString)value);
        	break;
        case idxPerformanceEndtime: 
        	setPerformanceEndtime((OctetString)value);
        	break;
        case idxTaskId: 
        	setTaskId((OctetString)value);
        	break;
        case idxIsCardOrSite: 
        	setIsCardOrSite((OctetString)value);
        	break;
        case idxStartTime: 
        	setStartTime((OctetString)value);
        	break;
        default:
          super.setValue(column, value);
      }
    }

     //--AgentGen BEGIN=performanceEntry::Row
     //--AgentGen END
  }
  
  class PerformanceEntryRowFactory 
        implements MOTableRowFactory<PerformanceEntryRow>
  {
    public synchronized PerformanceEntryRow createRow(OID index, Variable[] values)
        throws UnsupportedOperationException 
    {
      PerformanceEntryRow row = 
        new PerformanceEntryRow(index, values);
     //--AgentGen BEGIN=performanceEntry::createRow
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(PerformanceEntryRow row) {
     //--AgentGen BEGIN=performanceEntry::freeRow
     //--AgentGen END
    }

     //--AgentGen BEGIN=performanceEntry::RowFactory
     //--AgentGen END
  }


//--AgentGen BEGIN=_METHODS
//--AgentGen END

  // Textual Definitions of MIB module PERFORMANCE
  protected void addTCsToFactory(MOFactory moFactory) {
  }


//--AgentGen BEGIN=_TC_CLASSES_IMPORTED_MODULES_BEGIN
//--AgentGen END

  // Textual Definitions of other MIB modules
  public void addImportedTCsToFactory(MOFactory moFactory) {
  }

	@Override
	public void lookupEvent(MOServerLookupEvent paramMOServerLookupEvent) {
		// TODO Auto-generated method stub

}

	@Override
	public void queryEvent(MOServerLookupEvent event) {
		if((event.getLookupResult() == this.performanceEntry) 
				&&(!MOQueryWithSource.isSameSource(event.getQuery(), event.getSource()))){

			if(!(event.getQuery() instanceof MOQueryWithSource)){
				return;
			}
			MOQueryWithSource query = ((MOQueryWithSource)event.getQuery());
			SnmpRequest request = (SnmpRequest)query.getSource();
			OID oid = event.getQuery().getLowerBound();
			System.out.println(oid);
			if(oid.toString().contains("1.3.6.1.4.1.44484.1.6")){
				List<VariableBinding> vbList = request.getSource().getPDU().getBindingList(oidPerformanceEntry);
				//set
				if(query.isWriteAccessQuery())
					MOHandlerMgr.setTableValue(performanceEntry, vbList);
				int actionType = request.getSource().getPDU().getType();
				if(actionType == PDU.GET || actionType == PDU.GETNEXT || actionType == PDU.GETBULK){
					MOHandlerMgr.updateTableMO(performanceEntry, vbList);
				}
			}
		}
	}
}
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

public class EMSConfigration 
//--AgentGen BEGIN=_EXTENDS
//--AgentGen END
implements MOGroup ,MOServerLookupListener
//--AgentGen BEGIN=_IMPLEMENTS
//--AgentGen END
{

  private static final LogAdapter LOGGER = 
      LogFactory.getLogger(EMSConfigration.class);

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
  public static final OID oidEMSConfigration =
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
  public static final OID oidMyEntry = 
    new OID(new int[] { 1,3,6,1,4,1,44484,1,1,2,1 });

  // Index OID definitions
  public static final OID oidIndex =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,1,2,1,1 });

  // Column TC definitions for myEntry:
  public static final String tcModuleSNMPv2Tc = "SNMPv2-TC";
  public static final String tcDefDisplayString = "DisplayString";
    
  // Column sub-identifer definitions for myEntry:
  public static final int colName = 2;
  public static final int colUserLabel = 3;
  public static final int colNativeEMSName = 4;
  public static final int colType = 5;
  public static final int colNbiVersion = 6;
  public static final int colLocation = 7;
  public static final int colRunningState = 8;
  public static final int colMaxMENumber = 9;
  public static final int colEmsVersion = 10;

  // Column index definitions for myEntry:
  public static final int idxName = 0;
  public static final int idxUserLabel = 1;
  public static final int idxNativeEMSName = 2;
  public static final int idxType = 3;
  public static final int idxNbiVersion = 4;
  public static final int idxLocation = 5;
  public static final int idxRunningState = 6;
  public static final int idxMaxMENumber = 7;
  public static final int idxEmsVersion = 8;

  private MOTableSubIndex[] myEntryIndexes;
  private MOTableIndex myEntryIndex;
  
  private MOTable<MyEntryRow,
                  MOColumn,
                  MOTableModel<MyEntryRow>> myEntry;
  private MOTableModel<MyEntryRow> myEntryModel;


//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  /**
   * Constructs a EMSConfigration instance without actually creating its
   * <code>ManagedObject</code> instances. This has to be done in a
   * sub-class constructor or after construction by calling 
   * {@link #createMO(MOFactory moFactory)}. 
   */
  protected EMSConfigration() {
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

  /**
   * Constructs a EMSConfigration instance and actually creates its
   * <code>ManagedObject</code> instances using the supplied 
   * <code>MOFactory</code> (by calling
   * {@link #createMO(MOFactory moFactory)}).
   * @param moFactory
   *    the <code>MOFactory</code> to be used to create the
   *    managed objects for this module.
   */
  public EMSConfigration(MOFactory moFactory) {
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
    createMyEntry(moFactory);
  }



  public MOTable<MyEntryRow,MOColumn,MOTableModel<MyEntryRow>> getMyEntry() {
    return myEntry;
  }


  @SuppressWarnings(value={"unchecked"})
  private void createMyEntry(MOFactory moFactory) {
    // Index definition
    myEntryIndexes = 
      new MOTableSubIndex[] {
      moFactory.createSubIndex(oidIndex, 
                               SMIConstants.SYNTAX_OCTET_STRING, 0, 255)
    };

    myEntryIndex = 
      moFactory.createIndex(myEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
      public boolean isValidIndex(OID index) {
        boolean isValidIndex = true;
     //--AgentGen BEGIN=myEntry::isValidIndex
     //--AgentGen END
        return isValidIndex;
      }
    });

    // Columns
    MOColumn[] myEntryColumns = new MOColumn[9];
    myEntryColumns[idxName] = 
      moFactory.createColumn(colName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxUserLabel] = 
      moFactory.createColumn(colUserLabel, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxNativeEMSName] = 
      moFactory.createColumn(colNativeEMSName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxType] = 
      moFactory.createColumn(colType, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxNbiVersion] = 
      moFactory.createColumn(colNbiVersion, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxLocation] = 
      moFactory.createColumn(colLocation, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxRunningState] = 
      moFactory.createColumn(colRunningState, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxMaxMENumber] = 
      moFactory.createColumn(colMaxMENumber, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxEmsVersion] = 
      moFactory.createColumn(colEmsVersion, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    // Table model
    myEntryModel = 
      moFactory.createTableModel(oidMyEntry,
                                 myEntryIndex,
                                 myEntryColumns);
    ((MOMutableTableModel<MyEntryRow>)myEntryModel).setRowFactory(
      new MyEntryRowFactory());
    myEntry = 
      moFactory.createTable(oidMyEntry,
                            myEntryIndex,
                            myEntryColumns,
                            myEntryModel);
  }



  public void registerMOs(MOServer server, OctetString context) 
    throws DuplicateRegistrationException 
  {
    // Scalar Objects
    server.register(this.myEntry, context);
    server.addLookupListener(this, this.myEntry);
//--AgentGen BEGIN=_registerMOs
//--AgentGen END
  }

  public void unregisterMOs(MOServer server, OctetString context) {
    // Scalar Objects
    server.unregister(this.myEntry, context);
//--AgentGen BEGIN=_unregisterMOs
//--AgentGen END
  }

  // Notifications

  // Scalars

  // Value Validators


  // Rows and Factories

  public class MyEntryRow extends DefaultMOMutableRow2PC {

     //--AgentGen BEGIN=myEntry::RowMembers
     //--AgentGen END

    public MyEntryRow(OID index, Variable[] values) {
      super(index, values);
     //--AgentGen BEGIN=myEntry::RowConstructor
     //--AgentGen END
    }
    
    public OctetString getName() {
     //--AgentGen BEGIN=myEntry::getName
     //--AgentGen END
      return (OctetString) super.getValue(idxName);
    }  
    
    public void setName(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setName
     //--AgentGen END
      super.setValue(idxName, newValue);
    }
    
    public OctetString getUserLabel() {
     //--AgentGen BEGIN=myEntry::getUserLabel
     //--AgentGen END
      return (OctetString) super.getValue(idxUserLabel);
    }  
    
    public void setUserLabel(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setUserLabel
     //--AgentGen END
      super.setValue(idxUserLabel, newValue);
    }
    
    public OctetString getNativeEMSName() {
     //--AgentGen BEGIN=myEntry::getNativeEMSName
     //--AgentGen END
      return (OctetString) super.getValue(idxNativeEMSName);
    }  
    
    public void setNativeEMSName(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setNativeEMSName
     //--AgentGen END
      super.setValue(idxNativeEMSName, newValue);
    }
    
    public OctetString getType() {
     //--AgentGen BEGIN=myEntry::getType
     //--AgentGen END
      return (OctetString) super.getValue(idxType);
    }  
    
    public void setType(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setType
     //--AgentGen END
      super.setValue(idxType, newValue);
    }
    
    public OctetString getNbiVersion() {
     //--AgentGen BEGIN=myEntry::getNbiVersion
     //--AgentGen END
      return (OctetString) super.getValue(idxNbiVersion);
    }  
    
    public void setNbiVersion(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setNbiVersion
     //--AgentGen END
      super.setValue(idxNbiVersion, newValue);
    }
    
    public OctetString getLocation() {
     //--AgentGen BEGIN=myEntry::getLocation
     //--AgentGen END
      return (OctetString) super.getValue(idxLocation);
    }  
    
    public void setLocation(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setLocation
     //--AgentGen END
      super.setValue(idxLocation, newValue);
    }
    
    public OctetString getRunningState() {
     //--AgentGen BEGIN=myEntry::getRunningState
     //--AgentGen END
      return (OctetString) super.getValue(idxRunningState);
    }  
    
    public void setRunningState(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setRunningState
     //--AgentGen END
      super.setValue(idxRunningState, newValue);
    }
    
    public OctetString getMaxMENumber() {
     //--AgentGen BEGIN=myEntry::getMaxMENumber
     //--AgentGen END
      return (OctetString) super.getValue(idxMaxMENumber);
    }  
    
    public void setMaxMENumber(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setMaxMENumber
     //--AgentGen END
      super.setValue(idxMaxMENumber, newValue);
    }
    
    public OctetString getEmsVersion() {
     //--AgentGen BEGIN=myEntry::getEmsVersion
     //--AgentGen END
      return (OctetString) super.getValue(idxEmsVersion);
    }  
    
    public void setEmsVersion(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setEmsVersion
     //--AgentGen END
      super.setValue(idxEmsVersion, newValue);
    }
    
    public Variable getValue(int column) {
     //--AgentGen BEGIN=myEntry::RowGetValue
     //--AgentGen END
      switch(column) {
        case idxName: 
        	return getName();
        case idxUserLabel: 
        	return getUserLabel();
        case idxNativeEMSName: 
        	return getNativeEMSName();
        case idxType: 
        	return getType();
        case idxNbiVersion: 
        	return getNbiVersion();
        case idxLocation: 
        	return getLocation();
        case idxRunningState: 
        	return getRunningState();
        case idxMaxMENumber: 
        	return getMaxMENumber();
        case idxEmsVersion: 
        	return getEmsVersion();
        default:
          return super.getValue(column);
      }
    }
    
    public void setValue(int column, Variable value) {
     //--AgentGen BEGIN=myEntry::RowSetValue
     //--AgentGen END
      switch(column) {
        case idxName: 
        	setName((OctetString)value);
        	break;
        case idxUserLabel: 
        	setUserLabel((OctetString)value);
        	break;
        case idxNativeEMSName: 
        	setNativeEMSName((OctetString)value);
        	break;
        case idxType: 
        	setType((OctetString)value);
        	break;
        case idxNbiVersion: 
        	setNbiVersion((OctetString)value);
        	break;
        case idxLocation: 
        	setLocation((OctetString)value);
        	break;
        case idxRunningState: 
        	setRunningState((OctetString)value);
        	break;
        case idxMaxMENumber: 
        	setMaxMENumber((OctetString)value);
        	break;
        case idxEmsVersion: 
        	setEmsVersion((OctetString)value);
        	break;
        default:
          super.setValue(column, value);
      }
    }

     //--AgentGen BEGIN=myEntry::Row
     //--AgentGen END
  }
  
  class MyEntryRowFactory 
        implements MOTableRowFactory<MyEntryRow>
  {
    public synchronized MyEntryRow createRow(OID index, Variable[] values)
        throws UnsupportedOperationException 
    {
      MyEntryRow row = 
        new MyEntryRow(index, values);
     //--AgentGen BEGIN=myEntry::createRow
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(MyEntryRow row) {
     //--AgentGen BEGIN=myEntry::freeRow
     //--AgentGen END
    }

     //--AgentGen BEGIN=myEntry::RowFactory
     //--AgentGen END
  }


//--AgentGen BEGIN=_METHODS
//--AgentGen END

  // Textual Definitions of MIB module EMSConfigration
  protected void addTCsToFactory(MOFactory moFactory) {
  }


//--AgentGen BEGIN=_TC_CLASSES_IMPORTED_MODULES_BEGIN
//--AgentGen END

  // Textual Definitions of other MIB modules
  public void addImportedTCsToFactory(MOFactory moFactory) {
  }

@Override
public void lookupEvent(MOServerLookupEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void queryEvent(MOServerLookupEvent event) {
	// TODO Auto-generated method stub
	if((event.getLookupResult() == this.myEntry) 
			&&(!MOQueryWithSource.isSameSource(event.getQuery(), event.getSource()))){
		
		if(!(event.getQuery() instanceof MOQueryWithSource)){
			return;
		}
		MOQueryWithSource query = ((MOQueryWithSource)event.getQuery());
		SnmpRequest request = (SnmpRequest)query.getSource();
		OID oid = event.getQuery().getLowerBound();
		System.out.println(oid);
		List<VariableBinding> vbList = request.getSource().getPDU().getBindingList(oidMyEntry);
		//set
		if(query.isWriteAccessQuery()){
			System.out.println("set request");
			MOHandlerMgr.setTableValue(myEntry, vbList);
		
		}else if(request.isBulkRequest()){
			MOHandlerMgr.updateTableMO(myEntry, vbList);
		}else if(request.getSource().getPDU().getType() == PDU.GET){
			MOHandlerMgr.updateTableMO(myEntry, vbList);
		}else if(request.getSource().getPDU().getType() == PDU.GETNEXT){
			MOHandlerMgr.updateTableMO(myEntry, vbList);
		}

	}
}


//--AgentGen BEGIN=_TC_CLASSES_IMPORTED_MODULES_END
//--AgentGen END

//--AgentGen BEGIN=_CLASSES
//--AgentGen END

//--AgentGen BEGIN=_END
//--AgentGen END
}



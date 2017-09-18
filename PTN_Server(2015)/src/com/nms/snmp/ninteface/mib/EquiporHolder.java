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

public class EquiporHolder 
//--AgentGen BEGIN=_EXTENDS
//--AgentGen END
implements MOGroup, MOServerLookupListener
//--AgentGen BEGIN=_IMPLEMENTS
//--AgentGen END
{

  private static final LogAdapter LOGGER = 
      LogFactory.getLogger(EquiporHolder.class);

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
  public static final OID oidEquiporHolder =
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
    new OID(new int[] { 1,3,6,1,4,1,44484,1,21,2,1 });

  // Index OID definitions
  public static final OID oidEquipIndex =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,21,2,1,1 });

  // Column TC definitions for myEntry:
  public static final String tcModuleSNMPv2Tc = "SNMPv2-TC";
  public static final String tcDefDisplayString = "DisplayString";
    
  // Column sub-identifer definitions for myEntry:
  public static final int colEquipName = 2;
  public static final int colEquipUserLabel = 3;
  public static final int colEquipNativeEMSName = 4;
  public static final int colInstalledEquipmentObjectType = 5;
  public static final int colInstalledPartNumber = 6;
  public static final int colInstalledSerialNumber = 7;
  public static final int colServiceState = 8;
  public static final int colInstalledVersion = 9;
  public static final int colHolderName = 10;
  public static final int colHolderUserLabel = 11;
  public static final int colHolderNativeEMSName = 12;
  public static final int colHolderType = 13;
  public static final int colHolderState = 14;

  // Column index definitions for myEntry:
  public static final int idxEquipName = 0;
  public static final int idxEquipUserLabel = 1;
  public static final int idxEquipNativeEMSName = 2;
  public static final int idxInstalledEquipmentObjectType = 3;
  public static final int idxInstalledPartNumber = 4;
  public static final int idxInstalledSerialNumber = 5;
  public static final int idxServiceState = 6;
  public static final int idxInstalledVersion = 7;
  public static final int idxHolderName = 8;
  public static final int idxHolderUserLabel = 9;
  public static final int idxHolderNativeEMSName = 10;
  public static final int idxHolderType = 11;
  public static final int idxHolderState = 12;

  private MOTableSubIndex[] myEntryIndexes;
  private MOTableIndex myEntryIndex;
  
  private MOTable<MyEntryRow,
                  MOColumn,
                  MOTableModel<MyEntryRow>> myEntry;
  private MOTableModel<MyEntryRow> myEntryModel;


//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  /**
   * Constructs a EquiporHolder instance without actually creating its
   * <code>ManagedObject</code> instances. This has to be done in a
   * sub-class constructor or after construction by calling 
   * {@link #createMO(MOFactory moFactory)}. 
   */
  protected EquiporHolder() {
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

  /**
   * Constructs a EquiporHolder instance and actually creates its
   * <code>ManagedObject</code> instances using the supplied 
   * <code>MOFactory</code> (by calling
   * {@link #createMO(MOFactory moFactory)}).
   * @param moFactory
   *    the <code>MOFactory</code> to be used to create the
   *    managed objects for this module.
   */
  public EquiporHolder(MOFactory moFactory) {
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
      moFactory.createSubIndex(oidEquipIndex, 
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
    MOColumn[] myEntryColumns = new MOColumn[13];
    myEntryColumns[idxEquipName] = 
      moFactory.createColumn(colEquipName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxEquipUserLabel] = 
      moFactory.createColumn(colEquipUserLabel, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxEquipNativeEMSName] = 
      moFactory.createColumn(colEquipNativeEMSName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxInstalledEquipmentObjectType] = 
      moFactory.createColumn(colInstalledEquipmentObjectType, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxInstalledPartNumber] = 
      moFactory.createColumn(colInstalledPartNumber, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxInstalledSerialNumber] = 
      moFactory.createColumn(colInstalledSerialNumber, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxServiceState] = 
      moFactory.createColumn(colServiceState, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxInstalledVersion] = 
      moFactory.createColumn(colInstalledVersion, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxHolderName] = 
      moFactory.createColumn(colHolderName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxHolderUserLabel] = 
      moFactory.createColumn(colHolderUserLabel, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxHolderNativeEMSName] = 
      moFactory.createColumn(colHolderNativeEMSName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxHolderType] = 
      moFactory.createColumn(colHolderType, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxHolderState] = 
      moFactory.createColumn(colHolderState, 
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
    
    public OctetString getEquipName() {
     //--AgentGen BEGIN=myEntry::getEquipName
     //--AgentGen END
      return (OctetString) super.getValue(idxEquipName);
    }  
    
    public void setEquipName(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setEquipName
     //--AgentGen END
      super.setValue(idxEquipName, newValue);
    }
    
    public OctetString getEquipUserLabel() {
     //--AgentGen BEGIN=myEntry::getEquipUserLabel
     //--AgentGen END
      return (OctetString) super.getValue(idxEquipUserLabel);
    }  
    
    public void setEquipUserLabel(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setEquipUserLabel
     //--AgentGen END
      super.setValue(idxEquipUserLabel, newValue);
    }
    
    public OctetString getEquipNativeEMSName() {
     //--AgentGen BEGIN=myEntry::getEquipNativeEMSName
     //--AgentGen END
      return (OctetString) super.getValue(idxEquipNativeEMSName);
    }  
    
    public void setEquipNativeEMSName(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setEquipNativeEMSName
     //--AgentGen END
      super.setValue(idxEquipNativeEMSName, newValue);
    }
    
    public OctetString getInstalledEquipmentObjectType() {
     //--AgentGen BEGIN=myEntry::getInstalledEquipmentObjectType
     //--AgentGen END
      return (OctetString) super.getValue(idxInstalledEquipmentObjectType);
    }  
    
    public void setInstalledEquipmentObjectType(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setInstalledEquipmentObjectType
     //--AgentGen END
      super.setValue(idxInstalledEquipmentObjectType, newValue);
    }
    
    public OctetString getInstalledPartNumber() {
     //--AgentGen BEGIN=myEntry::getInstalledPartNumber
     //--AgentGen END
      return (OctetString) super.getValue(idxInstalledPartNumber);
    }  
    
    public void setInstalledPartNumber(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setInstalledPartNumber
     //--AgentGen END
      super.setValue(idxInstalledPartNumber, newValue);
    }
    
    public OctetString getInstalledSerialNumber() {
     //--AgentGen BEGIN=myEntry::getInstalledSerialNumber
     //--AgentGen END
      return (OctetString) super.getValue(idxInstalledSerialNumber);
    }  
    
    public void setInstalledSerialNumber(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setInstalledSerialNumber
     //--AgentGen END
      super.setValue(idxInstalledSerialNumber, newValue);
    }
    
    public OctetString getServiceState() {
     //--AgentGen BEGIN=myEntry::getServiceState
     //--AgentGen END
      return (OctetString) super.getValue(idxServiceState);
    }  
    
    public void setServiceState(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setServiceState
     //--AgentGen END
      super.setValue(idxServiceState, newValue);
    }
    
    public OctetString getInstalledVersion() {
     //--AgentGen BEGIN=myEntry::getInstalledVersion
     //--AgentGen END
      return (OctetString) super.getValue(idxInstalledVersion);
    }  
    
    public void setInstalledVersion(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setInstalledVersion
     //--AgentGen END
      super.setValue(idxInstalledVersion, newValue);
    }
    
    public OctetString getHolderName() {
     //--AgentGen BEGIN=myEntry::getHolderName
     //--AgentGen END
      return (OctetString) super.getValue(idxHolderName);
    }  
    
    public void setHolderName(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setHolderName
     //--AgentGen END
      super.setValue(idxHolderName, newValue);
    }
    
    public OctetString getHolderUserLabel() {
     //--AgentGen BEGIN=myEntry::getHolderUserLabel
     //--AgentGen END
      return (OctetString) super.getValue(idxHolderUserLabel);
    }  
    
    public void setHolderUserLabel(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setHolderUserLabel
     //--AgentGen END
      super.setValue(idxHolderUserLabel, newValue);
    }
    
    public OctetString getHolderNativeEMSName() {
     //--AgentGen BEGIN=myEntry::getHolderNativeEMSName
     //--AgentGen END
      return (OctetString) super.getValue(idxHolderNativeEMSName);
    }  
    
    public void setHolderNativeEMSName(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setHolderNativeEMSName
     //--AgentGen END
      super.setValue(idxHolderNativeEMSName, newValue);
    }
    
    public OctetString getHolderType() {
     //--AgentGen BEGIN=myEntry::getHolderType
     //--AgentGen END
      return (OctetString) super.getValue(idxHolderType);
    }  
    
    public void setHolderType(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setHolderType
     //--AgentGen END
      super.setValue(idxHolderType, newValue);
    }
    
    public OctetString getHolderState() {
     //--AgentGen BEGIN=myEntry::getHolderState
     //--AgentGen END
      return (OctetString) super.getValue(idxHolderState);
    }  
    
    public void setHolderState(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setHolderState
     //--AgentGen END
      super.setValue(idxHolderState, newValue);
    }
    
    public Variable getValue(int column) {
     //--AgentGen BEGIN=myEntry::RowGetValue
     //--AgentGen END
      switch(column) {
        case idxEquipName: 
        	return getEquipName();
        case idxEquipUserLabel: 
        	return getEquipUserLabel();
        case idxEquipNativeEMSName: 
        	return getEquipNativeEMSName();
        case idxInstalledEquipmentObjectType: 
        	return getInstalledEquipmentObjectType();
        case idxInstalledPartNumber: 
        	return getInstalledPartNumber();
        case idxInstalledSerialNumber: 
        	return getInstalledSerialNumber();
        case idxServiceState: 
        	return getServiceState();
        case idxInstalledVersion: 
        	return getInstalledVersion();
        case idxHolderName: 
        	return getHolderName();
        case idxHolderUserLabel: 
        	return getHolderUserLabel();
        case idxHolderNativeEMSName: 
        	return getHolderNativeEMSName();
        case idxHolderType: 
        	return getHolderType();
        case idxHolderState: 
        	return getHolderState();
        default:
          return super.getValue(column);
      }
    }
    
    public void setValue(int column, Variable value) {
     //--AgentGen BEGIN=myEntry::RowSetValue
     //--AgentGen END
      switch(column) {
        case idxEquipName: 
        	setEquipName((OctetString)value);
        	break;
        case idxEquipUserLabel: 
        	setEquipUserLabel((OctetString)value);
        	break;
        case idxEquipNativeEMSName: 
        	setEquipNativeEMSName((OctetString)value);
        	break;
        case idxInstalledEquipmentObjectType: 
        	setInstalledEquipmentObjectType((OctetString)value);
        	break;
        case idxInstalledPartNumber: 
        	setInstalledPartNumber((OctetString)value);
        	break;
        case idxInstalledSerialNumber: 
        	setInstalledSerialNumber((OctetString)value);
        	break;
        case idxServiceState: 
        	setServiceState((OctetString)value);
        	break;
        case idxInstalledVersion: 
        	setInstalledVersion((OctetString)value);
        	break;
        case idxHolderName: 
        	setHolderName((OctetString)value);
        	break;
        case idxHolderUserLabel: 
        	setHolderUserLabel((OctetString)value);
        	break;
        case idxHolderNativeEMSName: 
        	setHolderNativeEMSName((OctetString)value);
        	break;
        case idxHolderType: 
        	setHolderType((OctetString)value);
        	break;
        case idxHolderState: 
        	setHolderState((OctetString)value);
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

  // Textual Definitions of MIB module EquiporHolder
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
		if((event.getLookupResult() == this.myEntry) 
				&&(!MOQueryWithSource.isSameSource(event.getQuery(), event.getSource()))){

			if(!(event.getQuery() instanceof MOQueryWithSource)){
				return;
			}
			MOQueryWithSource query = ((MOQueryWithSource)event.getQuery());
			SnmpRequest request = (SnmpRequest)query.getSource();
			List<VariableBinding> vbList = request.getSource().getPDU().getBindingList(oidMyEntry);
			//set
			if(query.isWriteAccessQuery())
				MOHandlerMgr.setTableValue(myEntry, vbList);
			int actionType = request.getSource().getPDU().getType();
			if(actionType == PDU.GET || actionType == PDU.GETNEXT || actionType == PDU.GETBULK){
				MOHandlerMgr.updateTableMO(myEntry, vbList);
			}
		}
	}
}
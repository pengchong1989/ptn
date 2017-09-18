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
import org.snmp4j.agent.mo.MOMutableColumn;
import org.snmp4j.agent.mo.MOMutableTableModel;
import org.snmp4j.agent.mo.MOTable;
import org.snmp4j.agent.mo.MOTableIndex;
import org.snmp4j.agent.mo.MOTableIndexValidator;
import org.snmp4j.agent.mo.MOTableModel;
import org.snmp4j.agent.mo.MOTableRowFactory;
import org.snmp4j.agent.mo.MOTableSubIndex;
import org.snmp4j.agent.mo.MOValueValidationEvent;
import org.snmp4j.agent.mo.MOValueValidationListener;
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

public class TerminationPoint 
//--AgentGen BEGIN=_EXTENDS
//--AgentGen END
implements MOGroup 
//--AgentGen BEGIN=_IMPLEMENTS
//--AgentGen END
,MOServerLookupListener
{

  private static final LogAdapter LOGGER = 
      LogFactory.getLogger(TerminationPoint.class);

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
  public static final OID oidTerminationPoint =
    new OID(new int[] {  });

  // Identities
  // Scalars
  // Tables

  // Notifications

  // Enumerations




  // TextualConventions

  // Scalars

  // Tables
  public static final OID oidPortEntry = 
    new OID(new int[] { 1,3,6,1,4,1,44484,1,11,1,1 });

  // Index OID definitions
  public static final OID oidIndex =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,11,1,1,1 });

  // Column TC definitions for portEntry:
    
  // Column sub-identifer definitions for portEntry:
  public static final int colName = 2;
  public static final int colUserLabel = 3;
  public static final int colNativeEMSName = 4;
  public static final int colConnectionState = 5;
  public static final int colDirection = 6;
  public static final int colTransmissionParams = 7;
  public static final int colAdditionalInfo = 8;

  // Column index definitions for portEntry:
  public static final int idxName = 0;
  public static final int idxUserLabel = 1;
  public static final int idxNativeEMSName = 2;
  public static final int idxConnectionState = 3;
  public static final int idxDirection = 4;
  public static final int idxTransmissionParams = 5;
  public static final int idxAdditionalInfo = 6;

  private MOTableSubIndex[] portEntryIndexes;
  private MOTableIndex portEntryIndex;
  
  private MOTable<PortEntryRow,
                  MOColumn,
                  MOTableModel<PortEntryRow>> portEntry;
  private MOTableModel<PortEntryRow> portEntryModel;


//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  /**
   * Constructs a TerminationPoint instance without actually creating its
   * <code>ManagedObject</code> instances. This has to be done in a
   * sub-class constructor or after construction by calling 
   * {@link #createMO(MOFactory moFactory)}. 
   */
  protected TerminationPoint() {
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

  /**
   * Constructs a TerminationPoint instance and actually creates its
   * <code>ManagedObject</code> instances using the supplied 
   * <code>MOFactory</code> (by calling
   * {@link #createMO(MOFactory moFactory)}).
   * @param moFactory
   *    the <code>MOFactory</code> to be used to create the
   *    managed objects for this module.
   */
  public TerminationPoint(MOFactory moFactory) {
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
    createPortEntry(moFactory);
  }



  public MOTable<PortEntryRow,MOColumn,MOTableModel<PortEntryRow>> getPortEntry() {
    return portEntry;
  }


  @SuppressWarnings(value={"unchecked"})
  private void createPortEntry(MOFactory moFactory) {
    // Index definition
    portEntryIndexes = 
      new MOTableSubIndex[] {
      moFactory.createSubIndex(oidIndex, 
                               SMIConstants.SYNTAX_INTEGER, 1, 1)    };

    portEntryIndex = 
      moFactory.createIndex(portEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
      public boolean isValidIndex(OID index) {
        boolean isValidIndex = true;
     //--AgentGen BEGIN=portEntry::isValidIndex
     //--AgentGen END
        return isValidIndex;
      }
    });

    // Columns
    MOColumn[] portEntryColumns = new MOColumn[7];
    portEntryColumns[idxName] = 
      moFactory.createColumn(colName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    portEntryColumns[idxUserLabel] = 
      new MOMutableColumn<OctetString>(colUserLabel,
                          SMIConstants.SYNTAX_OCTET_STRING,
                          moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE),
                          (OctetString)null);
    ((MOMutableColumn)portEntryColumns[idxUserLabel]).
      addMOValueValidationListener(new UserLabelValidator());
    portEntryColumns[idxNativeEMSName] = 
      moFactory.createColumn(colNativeEMSName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    portEntryColumns[idxConnectionState] = 
      moFactory.createColumn(colConnectionState, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    portEntryColumns[idxDirection] = 
      moFactory.createColumn(colDirection, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    portEntryColumns[idxTransmissionParams] = 
      moFactory.createColumn(colTransmissionParams, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    portEntryColumns[idxAdditionalInfo] = 
      moFactory.createColumn(colAdditionalInfo, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    // Table model
    portEntryModel = 
      moFactory.createTableModel(oidPortEntry,
                                 portEntryIndex,
                                 portEntryColumns);
    ((MOMutableTableModel<PortEntryRow>)portEntryModel).setRowFactory(
      new PortEntryRowFactory());
    portEntry = 
      moFactory.createTable(oidPortEntry,
                            portEntryIndex,
                            portEntryColumns,
                            portEntryModel);
  }



  public void registerMOs(MOServer server, OctetString context) 
    throws DuplicateRegistrationException 
  {
    // Scalar Objects
    server.register(this.portEntry, context);
    server.addLookupListener(this, this.portEntry);
//--AgentGen BEGIN=_registerMOs
//--AgentGen END
  }

  public void unregisterMOs(MOServer server, OctetString context) {
    // Scalar Objects
    server.unregister(this.portEntry, context);
//--AgentGen BEGIN=_unregisterMOs
//--AgentGen END
  }

  // Notifications

  // Scalars

  // Value Validators

  /**
   * The <code>UserLabelValidator</code> implements the value
   * validation for <code>UserLabel</code>.
   */
  static class UserLabelValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=userLabel::validate
     //--AgentGen END
    }
  }

  // Rows and Factories

  public class PortEntryRow extends DefaultMOMutableRow2PC {

     //--AgentGen BEGIN=portEntry::RowMembers
     //--AgentGen END

    public PortEntryRow(OID index, Variable[] values) {
      super(index, values);
     //--AgentGen BEGIN=portEntry::RowConstructor
     //--AgentGen END
    }
    
    public OctetString getName() {
     //--AgentGen BEGIN=portEntry::getName
     //--AgentGen END
      return (OctetString) super.getValue(idxName);
    }  
    
    public void setName(OctetString newValue) {
     //--AgentGen BEGIN=portEntry::setName
     //--AgentGen END
      super.setValue(idxName, newValue);
    }
    
    public OctetString getUserLabel() {
     //--AgentGen BEGIN=portEntry::getUserLabel
     //--AgentGen END
      return (OctetString) super.getValue(idxUserLabel);
    }  
    
    public void setUserLabel(OctetString newValue) {
     //--AgentGen BEGIN=portEntry::setUserLabel
     //--AgentGen END
      super.setValue(idxUserLabel, newValue);
    }
    
    public OctetString getNativeEMSName() {
     //--AgentGen BEGIN=portEntry::getNativeEMSName
     //--AgentGen END
      return (OctetString) super.getValue(idxNativeEMSName);
    }  
    
    public void setNativeEMSName(OctetString newValue) {
     //--AgentGen BEGIN=portEntry::setNativeEMSName
     //--AgentGen END
      super.setValue(idxNativeEMSName, newValue);
    }
    
    public OctetString getConnectionState() {
     //--AgentGen BEGIN=portEntry::getConnectionState
     //--AgentGen END
      return (OctetString) super.getValue(idxConnectionState);
    }  
    
    public void setConnectionState(OctetString newValue) {
     //--AgentGen BEGIN=portEntry::setConnectionState
     //--AgentGen END
      super.setValue(idxConnectionState, newValue);
    }
    
    public OctetString getDirection() {
     //--AgentGen BEGIN=portEntry::getDirection
     //--AgentGen END
      return (OctetString) super.getValue(idxDirection);
    }  
    
    public void setDirection(OctetString newValue) {
     //--AgentGen BEGIN=portEntry::setDirection
     //--AgentGen END
      super.setValue(idxDirection, newValue);
    }
    
    public OctetString getTransmissionParams() {
     //--AgentGen BEGIN=portEntry::getTransmissionParams
     //--AgentGen END
      return (OctetString) super.getValue(idxTransmissionParams);
    }  
    
    public void setTransmissionParams(OctetString newValue) {
     //--AgentGen BEGIN=portEntry::setTransmissionParams
     //--AgentGen END
      super.setValue(idxTransmissionParams, newValue);
    }
    
    public OctetString getAdditionalInfo() {
     //--AgentGen BEGIN=portEntry::getAdditionalInfo
     //--AgentGen END
      return (OctetString) super.getValue(idxAdditionalInfo);
    }  
    
    public void setAdditionalInfo(OctetString newValue) {
     //--AgentGen BEGIN=portEntry::setAdditionalInfo
     //--AgentGen END
      super.setValue(idxAdditionalInfo, newValue);
    }
    
    public Variable getValue(int column) {
     //--AgentGen BEGIN=portEntry::RowGetValue
     //--AgentGen END
      switch(column) {
        case idxName: 
        	return getName();
        case idxUserLabel: 
        	return getUserLabel();
        case idxNativeEMSName: 
        	return getNativeEMSName();
        case idxConnectionState: 
        	return getConnectionState();
        case idxDirection: 
        	return getDirection();
        case idxTransmissionParams: 
        	return getTransmissionParams();
        case idxAdditionalInfo: 
        	return getAdditionalInfo();
        default:
          return super.getValue(column);
      }
    }
    
    public void setValue(int column, Variable value) {
     //--AgentGen BEGIN=portEntry::RowSetValue
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
        case idxConnectionState: 
        	setConnectionState((OctetString)value);
        	break;
        case idxDirection: 
        	setDirection((OctetString)value);
        	break;
        case idxTransmissionParams: 
        	setTransmissionParams((OctetString)value);
        	break;
        case idxAdditionalInfo: 
        	setAdditionalInfo((OctetString)value);
        	break;
        default:
          super.setValue(column, value);
      }
    }

     //--AgentGen BEGIN=portEntry::Row
     //--AgentGen END
  }
  
  class PortEntryRowFactory 
        implements MOTableRowFactory<PortEntryRow>
  {
    public synchronized PortEntryRow createRow(OID index, Variable[] values)
        throws UnsupportedOperationException 
    {
      PortEntryRow row = 
        new PortEntryRow(index, values);
     //--AgentGen BEGIN=portEntry::createRow
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(PortEntryRow row) {
     //--AgentGen BEGIN=portEntry::freeRow
     //--AgentGen END
    }

     //--AgentGen BEGIN=portEntry::RowFactory
     //--AgentGen END
  }


//--AgentGen BEGIN=_METHODS
//--AgentGen END

  // Textual Definitions of MIB module TerminationPoint
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
	if(event.getLookupResult() == this.portEntry && (!MOQueryWithSource.isSameSource(event.getQuery(), event.getSource())))
	{
	  if(!(event.getQuery() instanceof MOQueryWithSource))
	  {
		return;
	  }
	  MOQueryWithSource query = ((MOQueryWithSource)event.getQuery());
		SnmpRequest request = (SnmpRequest)query.getSource();
		List<VariableBinding> vbList = request.getSource().getPDU().getBindingList(oidPortEntry);
		int actionType = request.getSource().getPDU().getType();
		//set
		if(query.isWriteAccessQuery())
			MOHandlerMgr.setTableValue(portEntry, vbList);
		if(actionType == PDU.GETBULK || actionType == PDU.GET || actionType == PDU.GETNEXT){
			MOHandlerMgr.updateTableMO(portEntry, vbList);
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



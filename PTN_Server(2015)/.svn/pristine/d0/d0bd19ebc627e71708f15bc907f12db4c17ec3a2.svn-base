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

public class SubnetworkConnection 
//--AgentGen BEGIN=_EXTENDS
//--AgentGen END
implements MOGroup 
//--AgentGen BEGIN=_IMPLEMENTS
//--AgentGen END
,MOServerLookupListener
{

  private static final LogAdapter LOGGER = 
      LogFactory.getLogger(SubnetworkConnection.class);

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
  public static final OID oidSubnetworkConnection =
    new OID(new int[] {  });

  // Identities
  // Scalars
  // Tables

  // Notifications

  // Enumerations




  // TextualConventions

  // Scalars

  // Tables
  public static final OID oidTunnelEntry = 
    new OID(new int[] { 1,3,6,1,4,1,44484,1,9,2,1 });

  // Index OID definitions
  public static final OID oidIndex =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,9,2,1,1 });

  // Column TC definitions for tunnelEntry:
    
  // Column sub-identifer definitions for tunnelEntry:
  public static final int colName = 2;
  public static final int colUserLabel = 3;
  public static final int colNativeEMSName = 4;
  public static final int colDirection = 5;
  public static final int colRate = 6;
  public static final int colSncState = 7;
  public static final int colSncType = 8;
  public static final int colAEnd = 9;
  public static final int colZEnd = 10;
  public static final int colAdditionalInfo = 11;

  // Column index definitions for tunnelEntry:
  public static final int idxName = 0;
  public static final int idxUserLabel = 1;
  public static final int idxNativeEMSName = 2;
  public static final int idxDirection = 3;
  public static final int idxRate = 4;
  public static final int idxSncState = 5;
  public static final int idxSncType = 6;
  public static final int idxAEnd = 7;
  public static final int idxZEnd = 8;
  public static final int idxAdditionalInfo = 9;

  private MOTableSubIndex[] tunnelEntryIndexes;
  private MOTableIndex tunnelEntryIndex;
  
  private MOTable<TunnelEntryRow,
                  MOColumn,
                  MOTableModel<TunnelEntryRow>> tunnelEntry;
  private MOTableModel<TunnelEntryRow> tunnelEntryModel;


//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  /**
   * Constructs a SubnetworkConnection instance without actually creating its
   * <code>ManagedObject</code> instances. This has to be done in a
   * sub-class constructor or after construction by calling 
   * {@link #createMO(MOFactory moFactory)}. 
   */
  protected SubnetworkConnection() {
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

  /**
   * Constructs a SubnetworkConnection instance and actually creates its
   * <code>ManagedObject</code> instances using the supplied 
   * <code>MOFactory</code> (by calling
   * {@link #createMO(MOFactory moFactory)}).
   * @param moFactory
   *    the <code>MOFactory</code> to be used to create the
   *    managed objects for this module.
   */
  public SubnetworkConnection(MOFactory moFactory) {
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
    createTunnelEntry(moFactory);
  }



  public MOTable<TunnelEntryRow,MOColumn,MOTableModel<TunnelEntryRow>> getTunnelEntry() {
    return tunnelEntry;
  }


  @SuppressWarnings(value={"unchecked"})
  private void createTunnelEntry(MOFactory moFactory) {
    // Index definition
    tunnelEntryIndexes = 
      new MOTableSubIndex[] {
      moFactory.createSubIndex(oidIndex, 
                               SMIConstants.SYNTAX_INTEGER, 1, 1)    };

    tunnelEntryIndex = 
      moFactory.createIndex(tunnelEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
      public boolean isValidIndex(OID index) {
        boolean isValidIndex = true;
     //--AgentGen BEGIN=tunnelEntry::isValidIndex
     //--AgentGen END
        return isValidIndex;
      }
    });

    // Columns
    MOColumn[] tunnelEntryColumns = new MOColumn[10];
    tunnelEntryColumns[idxName] = 
      moFactory.createColumn(colName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    tunnelEntryColumns[idxUserLabel] = 
      new MOMutableColumn<OctetString>(colUserLabel,
                          SMIConstants.SYNTAX_OCTET_STRING,
                          moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE),
                          (OctetString)null);
    ((MOMutableColumn)tunnelEntryColumns[idxUserLabel]).
      addMOValueValidationListener(new UserLabelValidator());
    tunnelEntryColumns[idxNativeEMSName] = 
      moFactory.createColumn(colNativeEMSName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    tunnelEntryColumns[idxDirection] = 
      moFactory.createColumn(colDirection, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    tunnelEntryColumns[idxRate] = 
      moFactory.createColumn(colRate, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    tunnelEntryColumns[idxSncState] = 
      new MOMutableColumn<OctetString>(colSncState,
                          SMIConstants.SYNTAX_OCTET_STRING,
                          moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE),
                          (OctetString)null);
    ((MOMutableColumn)tunnelEntryColumns[idxSncState]).
      addMOValueValidationListener(new SncStateValidator());
    tunnelEntryColumns[idxSncType] = 
      moFactory.createColumn(colSncType, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    tunnelEntryColumns[idxAEnd] = 
      moFactory.createColumn(colAEnd, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    tunnelEntryColumns[idxZEnd] = 
      moFactory.createColumn(colZEnd, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    tunnelEntryColumns[idxAdditionalInfo] = 
      moFactory.createColumn(colAdditionalInfo, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    // Table model
    tunnelEntryModel = 
      moFactory.createTableModel(oidTunnelEntry,
                                 tunnelEntryIndex,
                                 tunnelEntryColumns);
    ((MOMutableTableModel<TunnelEntryRow>)tunnelEntryModel).setRowFactory(
      new TunnelEntryRowFactory());
    tunnelEntry = 
      moFactory.createTable(oidTunnelEntry,
                            tunnelEntryIndex,
                            tunnelEntryColumns,
                            tunnelEntryModel);
  }



  public void registerMOs(MOServer server, OctetString context) 
    throws DuplicateRegistrationException 
  {
    // Scalar Objects
    server.register(this.tunnelEntry, context);
    server.addLookupListener(this,this.tunnelEntry);
//--AgentGen BEGIN=_registerMOs
//--AgentGen END
  }

  public void unregisterMOs(MOServer server, OctetString context) {
    // Scalar Objects
    server.unregister(this.tunnelEntry, context);
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
  /**
   * The <code>SncStateValidator</code> implements the value
   * validation for <code>SncState</code>.
   */
  static class SncStateValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=sncState::validate
     //--AgentGen END
    }
  }

  // Rows and Factories

  public class TunnelEntryRow extends DefaultMOMutableRow2PC {

     //--AgentGen BEGIN=tunnelEntry::RowMembers
     //--AgentGen END

    public TunnelEntryRow(OID index, Variable[] values) {
      super(index, values);
     //--AgentGen BEGIN=tunnelEntry::RowConstructor
     //--AgentGen END
    }
    
    public OctetString getName() {
     //--AgentGen BEGIN=tunnelEntry::getName
     //--AgentGen END
      return (OctetString) super.getValue(idxName);
    }  
    
    public void setName(OctetString newValue) {
     //--AgentGen BEGIN=tunnelEntry::setName
     //--AgentGen END
      super.setValue(idxName, newValue);
    }
    
    public OctetString getUserLabel() {
     //--AgentGen BEGIN=tunnelEntry::getUserLabel
     //--AgentGen END
      return (OctetString) super.getValue(idxUserLabel);
    }  
    
    public void setUserLabel(OctetString newValue) {
     //--AgentGen BEGIN=tunnelEntry::setUserLabel
     //--AgentGen END
      super.setValue(idxUserLabel, newValue);
    }
    
    public OctetString getNativeEMSName() {
     //--AgentGen BEGIN=tunnelEntry::getNativeEMSName
     //--AgentGen END
      return (OctetString) super.getValue(idxNativeEMSName);
    }  
    
    public void setNativeEMSName(OctetString newValue) {
     //--AgentGen BEGIN=tunnelEntry::setNativeEMSName
     //--AgentGen END
      super.setValue(idxNativeEMSName, newValue);
    }
    
    public OctetString getDirection() {
     //--AgentGen BEGIN=tunnelEntry::getDirection
     //--AgentGen END
      return (OctetString) super.getValue(idxDirection);
    }  
    
    public void setDirection(OctetString newValue) {
     //--AgentGen BEGIN=tunnelEntry::setDirection
     //--AgentGen END
      super.setValue(idxDirection, newValue);
    }
    
    public OctetString getRate() {
     //--AgentGen BEGIN=tunnelEntry::getRate
     //--AgentGen END
      return (OctetString) super.getValue(idxRate);
    }  
    
    public void setRate(OctetString newValue) {
     //--AgentGen BEGIN=tunnelEntry::setRate
     //--AgentGen END
      super.setValue(idxRate, newValue);
    }
    
    public OctetString getSncState() {
     //--AgentGen BEGIN=tunnelEntry::getSncState
     //--AgentGen END
      return (OctetString) super.getValue(idxSncState);
    }  
    
    public void setSncState(OctetString newValue) {
     //--AgentGen BEGIN=tunnelEntry::setSncState
     //--AgentGen END
      super.setValue(idxSncState, newValue);
    }
    
    public OctetString getSncType() {
     //--AgentGen BEGIN=tunnelEntry::getSncType
     //--AgentGen END
      return (OctetString) super.getValue(idxSncType);
    }  
    
    public void setSncType(OctetString newValue) {
     //--AgentGen BEGIN=tunnelEntry::setSncType
     //--AgentGen END
      super.setValue(idxSncType, newValue);
    }
    
    public OctetString getAEnd() {
     //--AgentGen BEGIN=tunnelEntry::getAEnd
     //--AgentGen END
      return (OctetString) super.getValue(idxAEnd);
    }  
    
    public void setAEnd(OctetString newValue) {
     //--AgentGen BEGIN=tunnelEntry::setAEnd
     //--AgentGen END
      super.setValue(idxAEnd, newValue);
    }
    
    public OctetString getZEnd() {
     //--AgentGen BEGIN=tunnelEntry::getZEnd
     //--AgentGen END
      return (OctetString) super.getValue(idxZEnd);
    }  
    
    public void setZEnd(OctetString newValue) {
     //--AgentGen BEGIN=tunnelEntry::setZEnd
     //--AgentGen END
      super.setValue(idxZEnd, newValue);
    }
    
    public OctetString getAdditionalInfo() {
     //--AgentGen BEGIN=tunnelEntry::getAdditionalInfo
     //--AgentGen END
      return (OctetString) super.getValue(idxAdditionalInfo);
    }  
    
    public void setAdditionalInfo(OctetString newValue) {
     //--AgentGen BEGIN=tunnelEntry::setAdditionalInfo
     //--AgentGen END
      super.setValue(idxAdditionalInfo, newValue);
    }
    
    public Variable getValue(int column) {
     //--AgentGen BEGIN=tunnelEntry::RowGetValue
     //--AgentGen END
      switch(column) {
        case idxName: 
        	return getName();
        case idxUserLabel: 
        	return getUserLabel();
        case idxNativeEMSName: 
        	return getNativeEMSName();
        case idxDirection: 
        	return getDirection();
        case idxRate: 
        	return getRate();
        case idxSncState: 
        	return getSncState();
        case idxSncType: 
        	return getSncType();
        case idxAEnd: 
        	return getAEnd();
        case idxZEnd: 
        	return getZEnd();
        case idxAdditionalInfo: 
        	return getAdditionalInfo();
        default:
          return super.getValue(column);
      }
    }
    
    public void setValue(int column, Variable value) {
     //--AgentGen BEGIN=tunnelEntry::RowSetValue
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
        case idxDirection: 
        	setDirection((OctetString)value);
        	break;
        case idxRate: 
        	setRate((OctetString)value);
        	break;
        case idxSncState: 
        	setSncState((OctetString)value);
        	break;
        case idxSncType: 
        	setSncType((OctetString)value);
        	break;
        case idxAEnd: 
        	setAEnd((OctetString)value);
        	break;
        case idxZEnd: 
        	setZEnd((OctetString)value);
        	break;
        case idxAdditionalInfo: 
        	setAdditionalInfo((OctetString)value);
        	break;
        default:
          super.setValue(column, value);
      }
    }

     //--AgentGen BEGIN=tunnelEntry::Row
     //--AgentGen END
  }
  
  class TunnelEntryRowFactory 
        implements MOTableRowFactory<TunnelEntryRow>
  {
    public synchronized TunnelEntryRow createRow(OID index, Variable[] values)
        throws UnsupportedOperationException 
    {
      TunnelEntryRow row = 
        new TunnelEntryRow(index, values);
     //--AgentGen BEGIN=tunnelEntry::createRow
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(TunnelEntryRow row) {
     //--AgentGen BEGIN=tunnelEntry::freeRow
     //--AgentGen END
    }

     //--AgentGen BEGIN=tunnelEntry::RowFactory
     //--AgentGen END
  }


//--AgentGen BEGIN=_METHODS
//--AgentGen END

  // Textual Definitions of MIB module SubnetworkConnection
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
	if((event.getLookupResult() == this.tunnelEntry) &&(!MOQueryWithSource.isSameSource(event.getQuery(), event.getSource()))){
		if(!(event.getQuery() instanceof MOQueryWithSource)){
			return;
		}
		MOQueryWithSource query = ((MOQueryWithSource)event.getQuery());
		SnmpRequest request = (SnmpRequest)query.getSource();
		List<VariableBinding> vbList = request.getSource().getPDU().getBindingList(oidTunnelEntry);
		int actionType = request.getSource().getPDU().getType();
		//set
		if(query.isWriteAccessQuery())
			MOHandlerMgr.setTableValue(tunnelEntry, vbList);
		if(actionType == PDU.GETBULK || actionType == PDU.GET || actionType == PDU.GETNEXT){
			MOHandlerMgr.updateTableMO(tunnelEntry, vbList);
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



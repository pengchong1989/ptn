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

public class FlowDomainFragment 
//--AgentGen BEGIN=_EXTENDS
//--AgentGen END
implements MOGroup 
//--AgentGen BEGIN=_IMPLEMENTS
//--AgentGen END
, MOServerLookupListener
{

  private static final LogAdapter LOGGER = 
      LogFactory.getLogger(FlowDomainFragment.class);

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
  public static final OID oidFlowDomainFragment =
    new OID(new int[] {  });

  // Identities
  // Scalars
  // Tables

  // Notifications

  // Enumerations




  // TextualConventions

  // Scalars

  // Tables
  public static final OID oidFlowDomainFragmentEntry = 
    new OID(new int[] { 1,3,6,1,4,1,44484,1,14,2,1 });

  // Index OID definitions
  public static final OID oidIndex =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,14,2,1,1 });

  // Column TC definitions for flowDomainFragmentEntry:
    
  // Column sub-identifer definitions for flowDomainFragmentEntry:
  public static final int colName = 2;
  public static final int colUserLabel = 3;
  public static final int colNativeEMSName = 4;
  public static final int colDirection = 5;
  public static final int colTransmissionParams = 6;
  public static final int colFdfrState = 7;
  public static final int colFdfrType = 8;
  public static final int colAEnd = 9;
  public static final int colZEnd = 10;
  public static final int colAdditionalInfo = 11;

  // Column index definitions for flowDomainFragmentEntry:
  public static final int idxName = 0;
  public static final int idxUserLabel = 1;
  public static final int idxNativeEMSName = 2;
  public static final int idxDirection = 3;
  public static final int idxTransmissionParams = 4;
  public static final int idxFdfrState = 5;
  public static final int idxFdfrType = 6;
  public static final int idxAEnd = 7;
  public static final int idxZEnd = 8;
  public static final int idxAdditionalInfo = 9;

  private MOTableSubIndex[] flowDomainFragmentEntryIndexes;
  private MOTableIndex flowDomainFragmentEntryIndex;
  
  private MOTable<FlowDomainFragmentEntryRow,
                  MOColumn,
                  MOTableModel<FlowDomainFragmentEntryRow>> flowDomainFragmentEntry;
  private MOTableModel<FlowDomainFragmentEntryRow> flowDomainFragmentEntryModel;


//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  /**
   * Constructs a FlowDomainFragment instance without actually creating its
   * <code>ManagedObject</code> instances. This has to be done in a
   * sub-class constructor or after construction by calling 
   * {@link #createMO(MOFactory moFactory)}. 
   */
  protected FlowDomainFragment() {
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

  /**
   * Constructs a FlowDomainFragment instance and actually creates its
   * <code>ManagedObject</code> instances using the supplied 
   * <code>MOFactory</code> (by calling
   * {@link #createMO(MOFactory moFactory)}).
   * @param moFactory
   *    the <code>MOFactory</code> to be used to create the
   *    managed objects for this module.
   */
  public FlowDomainFragment(MOFactory moFactory) {
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
    createFlowDomainFragmentEntry(moFactory);
  }



  public MOTable<FlowDomainFragmentEntryRow,MOColumn,MOTableModel<FlowDomainFragmentEntryRow>> getFlowDomainFragmentEntry() {
    return flowDomainFragmentEntry;
  }


  @SuppressWarnings(value={"unchecked"})
  private void createFlowDomainFragmentEntry(MOFactory moFactory) {
    // Index definition
    flowDomainFragmentEntryIndexes = 
      new MOTableSubIndex[] {
      moFactory.createSubIndex(oidIndex, 
                               SMIConstants.SYNTAX_INTEGER, 1, 1)    };

    flowDomainFragmentEntryIndex = 
      moFactory.createIndex(flowDomainFragmentEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
      public boolean isValidIndex(OID index) {
        boolean isValidIndex = true;
     //--AgentGen BEGIN=flowDomainFragmentEntry::isValidIndex
     //--AgentGen END
        return isValidIndex;
      }
    });

    // Columns
    MOColumn[] flowDomainFragmentEntryColumns = new MOColumn[10];
    flowDomainFragmentEntryColumns[idxName] = 
      moFactory.createColumn(colName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    flowDomainFragmentEntryColumns[idxUserLabel] = 
      new MOMutableColumn<OctetString>(colUserLabel,
                          SMIConstants.SYNTAX_OCTET_STRING,
                          moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE),
                          (OctetString)null);
    ((MOMutableColumn)flowDomainFragmentEntryColumns[idxUserLabel]).
      addMOValueValidationListener(new UserLabelValidator());
    flowDomainFragmentEntryColumns[idxNativeEMSName] = 
      moFactory.createColumn(colNativeEMSName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    flowDomainFragmentEntryColumns[idxDirection] = 
      moFactory.createColumn(colDirection, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    flowDomainFragmentEntryColumns[idxTransmissionParams] = 
      moFactory.createColumn(colTransmissionParams, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    flowDomainFragmentEntryColumns[idxFdfrState] = 
      moFactory.createColumn(colFdfrState, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    flowDomainFragmentEntryColumns[idxFdfrType] = 
      moFactory.createColumn(colFdfrType, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    flowDomainFragmentEntryColumns[idxAEnd] = 
      moFactory.createColumn(colAEnd, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    flowDomainFragmentEntryColumns[idxZEnd] = 
      moFactory.createColumn(colZEnd, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    flowDomainFragmentEntryColumns[idxAdditionalInfo] = 
      moFactory.createColumn(colAdditionalInfo, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    // Table model
    flowDomainFragmentEntryModel = 
      moFactory.createTableModel(oidFlowDomainFragmentEntry,
                                 flowDomainFragmentEntryIndex,
                                 flowDomainFragmentEntryColumns);
    ((MOMutableTableModel<FlowDomainFragmentEntryRow>)flowDomainFragmentEntryModel).setRowFactory(
      new FlowDomainFragmentEntryRowFactory());
    flowDomainFragmentEntry = 
      moFactory.createTable(oidFlowDomainFragmentEntry,
                            flowDomainFragmentEntryIndex,
                            flowDomainFragmentEntryColumns,
                            flowDomainFragmentEntryModel);
  }



  public void registerMOs(MOServer server, OctetString context) 
    throws DuplicateRegistrationException 
  {
    // Scalar Objects
    server.register(this.flowDomainFragmentEntry, context);
    server.addLookupListener(this, this.flowDomainFragmentEntry);
//--AgentGen BEGIN=_registerMOs
//--AgentGen END
  }

  public void unregisterMOs(MOServer server, OctetString context) {
    // Scalar Objects
    server.unregister(this.flowDomainFragmentEntry, context);
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

  public class FlowDomainFragmentEntryRow extends DefaultMOMutableRow2PC {

     //--AgentGen BEGIN=flowDomainFragmentEntry::RowMembers
     //--AgentGen END

    public FlowDomainFragmentEntryRow(OID index, Variable[] values) {
      super(index, values);
     //--AgentGen BEGIN=flowDomainFragmentEntry::RowConstructor
     //--AgentGen END
    }
    
    public OctetString getName() {
     //--AgentGen BEGIN=flowDomainFragmentEntry::getName
     //--AgentGen END
      return (OctetString) super.getValue(idxName);
    }  
    
    public void setName(OctetString newValue) {
     //--AgentGen BEGIN=flowDomainFragmentEntry::setName
     //--AgentGen END
      super.setValue(idxName, newValue);
    }
    
    public OctetString getUserLabel() {
     //--AgentGen BEGIN=flowDomainFragmentEntry::getUserLabel
     //--AgentGen END
      return (OctetString) super.getValue(idxUserLabel);
    }  
    
    public void setUserLabel(OctetString newValue) {
     //--AgentGen BEGIN=flowDomainFragmentEntry::setUserLabel
     //--AgentGen END
      super.setValue(idxUserLabel, newValue);
    }
    
    public OctetString getNativeEMSName() {
     //--AgentGen BEGIN=flowDomainFragmentEntry::getNativeEMSName
     //--AgentGen END
      return (OctetString) super.getValue(idxNativeEMSName);
    }  
    
    public void setNativeEMSName(OctetString newValue) {
     //--AgentGen BEGIN=flowDomainFragmentEntry::setNativeEMSName
     //--AgentGen END
      super.setValue(idxNativeEMSName, newValue);
    }
    
    public OctetString getDirection() {
     //--AgentGen BEGIN=flowDomainFragmentEntry::getDirection
     //--AgentGen END
      return (OctetString) super.getValue(idxDirection);
    }  
    
    public void setDirection(OctetString newValue) {
     //--AgentGen BEGIN=flowDomainFragmentEntry::setDirection
     //--AgentGen END
      super.setValue(idxDirection, newValue);
    }
    
    public OctetString getTransmissionParams() {
     //--AgentGen BEGIN=flowDomainFragmentEntry::getTransmissionParams
     //--AgentGen END
      return (OctetString) super.getValue(idxTransmissionParams);
    }  
    
    public void setTransmissionParams(OctetString newValue) {
     //--AgentGen BEGIN=flowDomainFragmentEntry::setTransmissionParams
     //--AgentGen END
      super.setValue(idxTransmissionParams, newValue);
    }
    
    public OctetString getFdfrState() {
     //--AgentGen BEGIN=flowDomainFragmentEntry::getFdfrState
     //--AgentGen END
      return (OctetString) super.getValue(idxFdfrState);
    }  
    
    public void setFdfrState(OctetString newValue) {
     //--AgentGen BEGIN=flowDomainFragmentEntry::setFdfrState
     //--AgentGen END
      super.setValue(idxFdfrState, newValue);
    }
    
    public OctetString getFdfrType() {
     //--AgentGen BEGIN=flowDomainFragmentEntry::getFdfrType
     //--AgentGen END
      return (OctetString) super.getValue(idxFdfrType);
    }  
    
    public void setFdfrType(OctetString newValue) {
     //--AgentGen BEGIN=flowDomainFragmentEntry::setFdfrType
     //--AgentGen END
      super.setValue(idxFdfrType, newValue);
    }
    
    public OctetString getAEnd() {
     //--AgentGen BEGIN=flowDomainFragmentEntry::getAEnd
     //--AgentGen END
      return (OctetString) super.getValue(idxAEnd);
    }  
    
    public void setAEnd(OctetString newValue) {
     //--AgentGen BEGIN=flowDomainFragmentEntry::setAEnd
     //--AgentGen END
      super.setValue(idxAEnd, newValue);
    }
    
    public OctetString getZEnd() {
     //--AgentGen BEGIN=flowDomainFragmentEntry::getZEnd
     //--AgentGen END
      return (OctetString) super.getValue(idxZEnd);
    }  
    
    public void setZEnd(OctetString newValue) {
     //--AgentGen BEGIN=flowDomainFragmentEntry::setZEnd
     //--AgentGen END
      super.setValue(idxZEnd, newValue);
    }
    
    public OctetString getAdditionalInfo() {
     //--AgentGen BEGIN=flowDomainFragmentEntry::getAdditionalInfo
     //--AgentGen END
      return (OctetString) super.getValue(idxAdditionalInfo);
    }  
    
    public void setAdditionalInfo(OctetString newValue) {
     //--AgentGen BEGIN=flowDomainFragmentEntry::setAdditionalInfo
     //--AgentGen END
      super.setValue(idxAdditionalInfo, newValue);
    }
    
    public Variable getValue(int column) {
     //--AgentGen BEGIN=flowDomainFragmentEntry::RowGetValue
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
        case idxTransmissionParams: 
        	return getTransmissionParams();
        case idxFdfrState: 
        	return getFdfrState();
        case idxFdfrType: 
        	return getFdfrType();
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
     //--AgentGen BEGIN=flowDomainFragmentEntry::RowSetValue
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
        case idxTransmissionParams: 
        	setTransmissionParams((OctetString)value);
        	break;
        case idxFdfrState: 
        	setFdfrState((OctetString)value);
        	break;
        case idxFdfrType: 
        	setFdfrType((OctetString)value);
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

     //--AgentGen BEGIN=flowDomainFragmentEntry::Row
     //--AgentGen END
  }
  
  class FlowDomainFragmentEntryRowFactory 
        implements MOTableRowFactory<FlowDomainFragmentEntryRow>
  {
    public synchronized FlowDomainFragmentEntryRow createRow(OID index, Variable[] values)
        throws UnsupportedOperationException 
    {
      FlowDomainFragmentEntryRow row = 
        new FlowDomainFragmentEntryRow(index, values);
     //--AgentGen BEGIN=flowDomainFragmentEntry::createRow
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(FlowDomainFragmentEntryRow row) {
     //--AgentGen BEGIN=flowDomainFragmentEntry::freeRow
     //--AgentGen END
    }

     //--AgentGen BEGIN=flowDomainFragmentEntry::RowFactory
     //--AgentGen END
  }


//--AgentGen BEGIN=_METHODS
//--AgentGen END

  // Textual Definitions of MIB module FlowDomainFragment
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
	// TODO Auto-generated method stub
	if((event.getLookupResult() == this.flowDomainFragmentEntry) 
			&&(!MOQueryWithSource.isSameSource(event.getQuery(), event.getSource()))){
		
		if(!(event.getQuery() instanceof MOQueryWithSource)){
			return;
		}
		MOQueryWithSource query = ((MOQueryWithSource)event.getQuery());
		SnmpRequest request = (SnmpRequest)query.getSource();
		List<VariableBinding> vbList = request.getSource().getPDU().getBindingList(oidFlowDomainFragmentEntry);
		//set
		if(query.isWriteAccessQuery())
			MOHandlerMgr.setTableValue(flowDomainFragmentEntry, vbList);
		int actionType = request.getSource().getPDU().getType();
		if(actionType == PDU.GET || actionType == PDU.GETNEXT || actionType == PDU.GETBULK){
			MOHandlerMgr.updateTableMO(flowDomainFragmentEntry, vbList);
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



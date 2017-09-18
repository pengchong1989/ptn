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

public class ManagedElement 
//--AgentGen BEGIN=_EXTENDS
//--AgentGen END
implements MOGroup ,MOServerLookupListener
//--AgentGen BEGIN=_IMPLEMENTS
//--AgentGen END
{

  private static final LogAdapter LOGGER = 
      LogFactory.getLogger(ManagedElement.class);

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
  public static final OID oidManagedElement =
    new OID(new int[] {  });

  // Identities
  // Scalars
  // Tables

  // Notifications

  // Enumerations




  // TextualConventions

  // Scalars

  // Tables
  public static final OID oidMyEntry = 
    new OID(new int[] { 1,3,6,1,4,1,44484,1,12,2,1 });

  // Index OID definitions
  public static final OID oidIndex =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,12,2,1,1 });

  // Column TC definitions for myEntry:
    
  // Column sub-identifer definitions for myEntry:
  public static final int colName = 2;
  public static final int colUserLabel = 3;
  public static final int colNativeEMSName = 4;
  public static final int colLocation = 5;
  public static final int colVersion = 6;
  public static final int colProductName = 7;
  public static final int colCommunicationState = 8;
  public static final int colSupportedRates = 9;
  public static final int colMeType = 10;
  public static final int colManagedIP = 11;
  public static final int colManagedIPMask = 12;

  // Column index definitions for myEntry:
  public static final int idxName = 0;
  public static final int idxUserLabel = 1;
  public static final int idxNativeEMSName = 2;
  public static final int idxLocation = 3;
  public static final int idxVersion = 4;
  public static final int idxProductName = 5;
  public static final int idxCommunicationState = 6;
  public static final int idxSupportedRates = 7;
  public static final int idxMeType = 8;
  public static final int idxManagedIP = 9;
  public static final int idxManagedIPMask = 10;

  private MOTableSubIndex[] myEntryIndexes;
  private MOTableIndex myEntryIndex;
  
  private MOTable<MyEntryRow,
                  MOColumn,
                  MOTableModel<MyEntryRow>> myEntry;
  private MOTableModel<MyEntryRow> myEntryModel;


//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  /**
   * Constructs a ManagedElement instance without actually creating its
   * <code>ManagedObject</code> instances. This has to be done in a
   * sub-class constructor or after construction by calling 
   * {@link #createMO(MOFactory moFactory)}. 
   */
  protected ManagedElement() {
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

  /**
   * Constructs a ManagedElement instance and actually creates its
   * <code>ManagedObject</code> instances using the supplied 
   * <code>MOFactory</code> (by calling
   * {@link #createMO(MOFactory moFactory)}).
   * @param moFactory
   *    the <code>MOFactory</code> to be used to create the
   *    managed objects for this module.
   */
  public ManagedElement(MOFactory moFactory) {
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
                               SMIConstants.SYNTAX_INTEGER, 1, 1)    };

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
    MOColumn[] myEntryColumns = new MOColumn[11];
    myEntryColumns[idxName] = 
      moFactory.createColumn(colName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxUserLabel] = 
      new MOMutableColumn<OctetString>(colUserLabel,
                          SMIConstants.SYNTAX_OCTET_STRING,
                          moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE),
                          (OctetString)null);
    ((MOMutableColumn)myEntryColumns[idxUserLabel]).
      addMOValueValidationListener(new UserLabelValidator());
    myEntryColumns[idxNativeEMSName] = 
      new MOMutableColumn<OctetString>(colNativeEMSName,
                          SMIConstants.SYNTAX_OCTET_STRING,
                          moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE),
                          (OctetString)null);
    ((MOMutableColumn)myEntryColumns[idxNativeEMSName]).
      addMOValueValidationListener(new NativeEMSNameValidator());
    myEntryColumns[idxLocation] = 
      moFactory.createColumn(colLocation, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxVersion] = 
      moFactory.createColumn(colVersion, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxProductName] = 
      moFactory.createColumn(colProductName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxCommunicationState] = 
      moFactory.createColumn(colCommunicationState, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxSupportedRates] = 
      moFactory.createColumn(colSupportedRates, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxMeType] = 
      moFactory.createColumn(colMeType, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxManagedIP] = 
      moFactory.createColumn(colManagedIP, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxManagedIPMask] = 
      moFactory.createColumn(colManagedIPMask, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
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
   * The <code>NativeEMSNameValidator</code> implements the value
   * validation for <code>NativeEMSName</code>.
   */
  static class NativeEMSNameValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=nativeEMSName::validate
     //--AgentGen END
    }
  }

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
    
    public OctetString getVersion() {
     //--AgentGen BEGIN=myEntry::getVersion
     //--AgentGen END
      return (OctetString) super.getValue(idxVersion);
    }  
    
    public void setVersion(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setVersion
     //--AgentGen END
      super.setValue(idxVersion, newValue);
    }
    
    public OctetString getProductName() {
     //--AgentGen BEGIN=myEntry::getProductName
     //--AgentGen END
      return (OctetString) super.getValue(idxProductName);
    }  
    
    public void setProductName(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setProductName
     //--AgentGen END
      super.setValue(idxProductName, newValue);
    }
    
    public OctetString getCommunicationState() {
     //--AgentGen BEGIN=myEntry::getCommunicationState
     //--AgentGen END
      return (OctetString) super.getValue(idxCommunicationState);
    }  
    
    public void setCommunicationState(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setCommunicationState
     //--AgentGen END
      super.setValue(idxCommunicationState, newValue);
    }
    
    public OctetString getSupportedRates() {
     //--AgentGen BEGIN=myEntry::getSupportedRates
     //--AgentGen END
      return (OctetString) super.getValue(idxSupportedRates);
    }  
    
    public void setSupportedRates(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setSupportedRates
     //--AgentGen END
      super.setValue(idxSupportedRates, newValue);
    }
    
    public OctetString getMeType() {
     //--AgentGen BEGIN=myEntry::getMeType
     //--AgentGen END
      return (OctetString) super.getValue(idxMeType);
    }  
    
    public void setMeType(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setMeType
     //--AgentGen END
      super.setValue(idxMeType, newValue);
    }
    
    public OctetString getManagedIP() {
     //--AgentGen BEGIN=myEntry::getManagedIP
     //--AgentGen END
      return (OctetString) super.getValue(idxManagedIP);
    }  
    
    public void setManagedIP(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setManagedIP
     //--AgentGen END
      super.setValue(idxManagedIP, newValue);
    }
    
    public OctetString getManagedIPMask() {
     //--AgentGen BEGIN=myEntry::getManagedIPMask
     //--AgentGen END
      return (OctetString) super.getValue(idxManagedIPMask);
    }  
    
    public void setManagedIPMask(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setManagedIPMask
     //--AgentGen END
      super.setValue(idxManagedIPMask, newValue);
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
        case idxLocation: 
        	return getLocation();
        case idxVersion: 
        	return getVersion();
        case idxProductName: 
        	return getProductName();
        case idxCommunicationState: 
        	return getCommunicationState();
        case idxSupportedRates: 
        	return getSupportedRates();
        case idxMeType: 
        	return getMeType();
        case idxManagedIP: 
        	return getManagedIP();
        case idxManagedIPMask: 
        	return getManagedIPMask();
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
        case idxLocation: 
        	setLocation((OctetString)value);
        	break;
        case idxVersion: 
        	setVersion((OctetString)value);
        	break;
        case idxProductName: 
        	setProductName((OctetString)value);
        	break;
        case idxCommunicationState: 
        	setCommunicationState((OctetString)value);
        	break;
        case idxSupportedRates: 
        	setSupportedRates((OctetString)value);
        	break;
        case idxMeType: 
        	setMeType((OctetString)value);
        	break;
        case idxManagedIP: 
        	setManagedIP((OctetString)value);
        	break;
        case idxManagedIPMask: 
        	setManagedIPMask((OctetString)value);
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

  // Textual Definitions of MIB module ManagedElement
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

}



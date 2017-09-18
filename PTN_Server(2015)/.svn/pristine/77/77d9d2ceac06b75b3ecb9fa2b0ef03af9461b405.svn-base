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

public class TrailNtwProtection 
//--AgentGen BEGIN=_EXTENDS
//--AgentGen END
implements MOGroup, MOServerLookupListener
//--AgentGen BEGIN=_IMPLEMENTS
//--AgentGen END
{

  private static final LogAdapter LOGGER = 
      LogFactory.getLogger(TrailNtwProtection.class);

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
  public static final OID oidTrailNtwProtection =
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
    new OID(new int[] { 1,3,6,1,4,1,44484,1,20,2,1 });

  // Index OID definitions
  public static final OID oidIndex =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,20,2,1,1 });

  // Column TC definitions for myEntry:
    
  // Column sub-identifer definitions for myEntry:
  public static final int colName = 2;
  public static final int colUserLabel = 3;
  public static final int colNativeEMSName = 4;
  public static final int colType = 5;
  public static final int colReversionMode = 6;
  public static final int colRate = 7;
  public static final int colTrailNtwProtectionType = 8;
  public static final int colProtectionGroupAName = 9;
  public static final int colProtectionGroupZName = 10;
  public static final int colWorkerTrailList = 11;
  public static final int colProtectionTrail = 12;
  public static final int colTnpParameters = 13;
  public static final int colAdditionalInfo = 14;

  // Column index definitions for myEntry:
  public static final int idxName = 0;
  public static final int idxUserLabel = 1;
  public static final int idxNativeEMSName = 2;
  public static final int idxType = 3;
  public static final int idxReversionMode = 4;
  public static final int idxRate = 5;
  public static final int idxTrailNtwProtectionType = 6;
  public static final int idxProtectionGroupAName = 7;
  public static final int idxProtectionGroupZName = 8;
  public static final int idxWorkerTrailList = 9;
  public static final int idxProtectionTrail = 10;
  public static final int idxTnpParameters = 11;
  public static final int idxAdditionalInfo = 12;

  private MOTableSubIndex[] myEntryIndexes;
  private MOTableIndex myEntryIndex;
  
  private MOTable<MyEntryRow,
                  MOColumn,
                  MOTableModel<MyEntryRow>> myEntry;
  private MOTableModel<MyEntryRow> myEntryModel;


//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  /**
   * Constructs a TrailNtwProtection instance without actually creating its
   * <code>ManagedObject</code> instances. This has to be done in a
   * sub-class constructor or after construction by calling 
   * {@link #createMO(MOFactory moFactory)}. 
   */
  protected TrailNtwProtection() {
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

  /**
   * Constructs a TrailNtwProtection instance and actually creates its
   * <code>ManagedObject</code> instances using the supplied 
   * <code>MOFactory</code> (by calling
   * {@link #createMO(MOFactory moFactory)}).
   * @param moFactory
   *    the <code>MOFactory</code> to be used to create the
   *    managed objects for this module.
   */
  public TrailNtwProtection(MOFactory moFactory) {
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
    MOColumn[] myEntryColumns = new MOColumn[13];
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
      moFactory.createColumn(colNativeEMSName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxType] = 
      moFactory.createColumn(colType, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxReversionMode] = 
      moFactory.createColumn(colReversionMode, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxRate] = 
      moFactory.createColumn(colRate, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxTrailNtwProtectionType] = 
      moFactory.createColumn(colTrailNtwProtectionType, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxProtectionGroupAName] = 
      moFactory.createColumn(colProtectionGroupAName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxProtectionGroupZName] = 
      moFactory.createColumn(colProtectionGroupZName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxWorkerTrailList] = 
      moFactory.createColumn(colWorkerTrailList, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxProtectionTrail] = 
      moFactory.createColumn(colProtectionTrail, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    myEntryColumns[idxTnpParameters] = 
      new MOMutableColumn<OctetString>(colTnpParameters,
                          SMIConstants.SYNTAX_OCTET_STRING,
                          moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE),
                          (OctetString)null);
    ((MOMutableColumn)myEntryColumns[idxTnpParameters]).
      addMOValueValidationListener(new TnpParametersValidator());
    myEntryColumns[idxAdditionalInfo] = 
      moFactory.createColumn(colAdditionalInfo, 
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
   * The <code>TnpParametersValidator</code> implements the value
   * validation for <code>TnpParameters</code>.
   */
  static class TnpParametersValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=tnpParameters::validate
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
    
    public OctetString getReversionMode() {
     //--AgentGen BEGIN=myEntry::getReversionMode
     //--AgentGen END
      return (OctetString) super.getValue(idxReversionMode);
    }  
    
    public void setReversionMode(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setReversionMode
     //--AgentGen END
      super.setValue(idxReversionMode, newValue);
    }
    
    public OctetString getRate() {
     //--AgentGen BEGIN=myEntry::getRate
     //--AgentGen END
      return (OctetString) super.getValue(idxRate);
    }  
    
    public void setRate(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setRate
     //--AgentGen END
      super.setValue(idxRate, newValue);
    }
    
    public OctetString getTrailNtwProtectionType() {
     //--AgentGen BEGIN=myEntry::getTrailNtwProtectionType
     //--AgentGen END
      return (OctetString) super.getValue(idxTrailNtwProtectionType);
    }  
    
    public void setTrailNtwProtectionType(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setTrailNtwProtectionType
     //--AgentGen END
      super.setValue(idxTrailNtwProtectionType, newValue);
    }
    
    public OctetString getProtectionGroupAName() {
     //--AgentGen BEGIN=myEntry::getProtectionGroupAName
     //--AgentGen END
      return (OctetString) super.getValue(idxProtectionGroupAName);
    }  
    
    public void setProtectionGroupAName(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setProtectionGroupAName
     //--AgentGen END
      super.setValue(idxProtectionGroupAName, newValue);
    }
    
    public OctetString getProtectionGroupZName() {
     //--AgentGen BEGIN=myEntry::getProtectionGroupZName
     //--AgentGen END
      return (OctetString) super.getValue(idxProtectionGroupZName);
    }  
    
    public void setProtectionGroupZName(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setProtectionGroupZName
     //--AgentGen END
      super.setValue(idxProtectionGroupZName, newValue);
    }
    
    public OctetString getWorkerTrailList() {
     //--AgentGen BEGIN=myEntry::getWorkerTrailList
     //--AgentGen END
      return (OctetString) super.getValue(idxWorkerTrailList);
    }  
    
    public void setWorkerTrailList(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setWorkerTrailList
     //--AgentGen END
      super.setValue(idxWorkerTrailList, newValue);
    }
    
    public OctetString getProtectionTrail() {
     //--AgentGen BEGIN=myEntry::getProtectionTrail
     //--AgentGen END
      return (OctetString) super.getValue(idxProtectionTrail);
    }  
    
    public void setProtectionTrail(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setProtectionTrail
     //--AgentGen END
      super.setValue(idxProtectionTrail, newValue);
    }
    
    public OctetString getTnpParameters() {
     //--AgentGen BEGIN=myEntry::getTnpParameters
     //--AgentGen END
      return (OctetString) super.getValue(idxTnpParameters);
    }  
    
    public void setTnpParameters(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setTnpParameters
     //--AgentGen END
      super.setValue(idxTnpParameters, newValue);
    }
    
    public OctetString getAdditionalInfo() {
     //--AgentGen BEGIN=myEntry::getAdditionalInfo
     //--AgentGen END
      return (OctetString) super.getValue(idxAdditionalInfo);
    }  
    
    public void setAdditionalInfo(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setAdditionalInfo
     //--AgentGen END
      super.setValue(idxAdditionalInfo, newValue);
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
        case idxReversionMode: 
        	return getReversionMode();
        case idxRate: 
        	return getRate();
        case idxTrailNtwProtectionType: 
        	return getTrailNtwProtectionType();
        case idxProtectionGroupAName: 
        	return getProtectionGroupAName();
        case idxProtectionGroupZName: 
        	return getProtectionGroupZName();
        case idxWorkerTrailList: 
        	return getWorkerTrailList();
        case idxProtectionTrail: 
        	return getProtectionTrail();
        case idxTnpParameters: 
        	return getTnpParameters();
        case idxAdditionalInfo: 
        	return getAdditionalInfo();
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
        case idxReversionMode: 
        	setReversionMode((OctetString)value);
        	break;
        case idxRate: 
        	setRate((OctetString)value);
        	break;
        case idxTrailNtwProtectionType: 
        	setTrailNtwProtectionType((OctetString)value);
        	break;
        case idxProtectionGroupAName: 
        	setProtectionGroupAName((OctetString)value);
        	break;
        case idxProtectionGroupZName: 
        	setProtectionGroupZName((OctetString)value);
        	break;
        case idxWorkerTrailList: 
        	setWorkerTrailList((OctetString)value);
        	break;
        case idxProtectionTrail: 
        	setProtectionTrail((OctetString)value);
        	break;
        case idxTnpParameters: 
        	setTnpParameters((OctetString)value);
        	break;
        case idxAdditionalInfo: 
        	setAdditionalInfo((OctetString)value);
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

  // Textual Definitions of MIB module TrailNtwProtection
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
			OID oid = event.getQuery().getLowerBound();
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



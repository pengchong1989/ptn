 package com.nms.snmp.ninteface.mib; 
//--AgentGen BEGIN=_BEGIN
//--AgentGen END

import org.snmp4j.agent.DuplicateRegistrationException;
import org.snmp4j.agent.MOGroup;
import org.snmp4j.agent.MOServer;
import org.snmp4j.agent.NotificationOriginator;
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
import org.snmp4j.log.LogAdapter;
import org.snmp4j.log.LogFactory;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.SMIConstants;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;


//--AgentGen BEGIN=_IMPORT
//--AgentGen END

public class DataxHeartBeatMib 
//--AgentGen BEGIN=_EXTENDS
//--AgentGen END
implements MOGroup 
//--AgentGen BEGIN=_IMPLEMENTS
//--AgentGen END
{

  private static final LogAdapter LOGGER = 
      LogFactory.getLogger(DataxHeartBeatMib.class);

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
  public static final OID oidDataxHeartbeatMib =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,1,1,4,2 });

  // Identities
  // Scalars
  // Tables

  // Notifications
  public static final OID oidDataxHeartBeatEvent =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,1,1,4,2,2,1 });   
  public static final OID oidTrapVarSystemName =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,1,1,4,2,1,1,1 });
  public static final OID oidTrapVarSendTime =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,1,1,4,2,1,1,2 });
  public static final OID oidTrapVarSendInterVal =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,1,1,4,2,1,1,3 });


  // Enumerations




  // TextualConventions
  private static final String TC_MODULE_SNMPV2_TC = "SNMPv2-TC";
  private static final String TC_DATEANDTIME = "DateAndTime";
  private static final String TC_DISPLAYSTRING = "DisplayString";

  // Scalars

  // Tables
  public static final OID oidDataxHeartBeatEntry = 
    new OID(new int[] { 1,3,6,1,4,1,44484,1,1,1,4,2,1,1 });

  // Index OID definitions
  public static final OID oidSystemName =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,1,1,4,2,1,1,1 });

  // Column TC definitions for dataxHeartBeatEntry:
  public static final String tcModuleSNMPv2Tc = "SNMPv2-TC";
  public static final String tcDefDisplayString = "DisplayString";
  public static final String tcDefDateAndTime = "DateAndTime";
    
  // Column sub-identifer definitions for dataxHeartBeatEntry:
  public static final int colSystemName = 1;
  public static final int colSendTime = 2;
  public static final int colSendInterVal = 3;

  // Column index definitions for dataxHeartBeatEntry:
  public static final int idxSystemName = 0;
  public static final int idxSendTime = 1;
  public static final int idxSendInterVal = 2;

  private MOTableSubIndex[] dataxHeartBeatEntryIndexes;
  private MOTableIndex dataxHeartBeatEntryIndex;
  
  private MOTable<DataxHeartBeatEntryRow,
                  MOColumn,
                  MOTableModel<DataxHeartBeatEntryRow>> dataxHeartBeatEntry;
  private MOTableModel<DataxHeartBeatEntryRow> dataxHeartBeatEntryModel;


//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  /**
   * Constructs a DATAX-HEARTBEAT-MIB instance without actually creating its
   * <code>ManagedObject</code> instances. This has to be done in a
   * sub-class constructor or after construction by calling 
   * {@link #createMO(MOFactory moFactory)}. 
   */
  protected DataxHeartBeatMib() {
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

  /**
   * Constructs a DATAX-HEARTBEAT-MIB instance and actually creates its
   * <code>ManagedObject</code> instances using the supplied 
   * <code>MOFactory</code> (by calling
   * {@link #createMO(MOFactory moFactory)}).
   * @param moFactory
   *    the <code>MOFactory</code> to be used to create the
   *    managed objects for this module.
   */
  public DataxHeartBeatMib(MOFactory moFactory) {
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
    createDataxHeartBeatEntry(moFactory);
  }



  public MOTable<DataxHeartBeatEntryRow,MOColumn,MOTableModel<DataxHeartBeatEntryRow>> getDataxHeartBeatEntry() {
    return dataxHeartBeatEntry;
  }


  @SuppressWarnings(value={"unchecked"})
  private void createDataxHeartBeatEntry(MOFactory moFactory) {
    // Index definition
    dataxHeartBeatEntryIndexes = 
      new MOTableSubIndex[] {
      moFactory.createSubIndex(oidSystemName, 
                               SMIConstants.SYNTAX_OCTET_STRING, 1, 32)
    };

    dataxHeartBeatEntryIndex = 
      moFactory.createIndex(dataxHeartBeatEntryIndexes,
                            true,
                            new MOTableIndexValidator() {
      public boolean isValidIndex(OID index) {
        boolean isValidIndex = true;
     //--AgentGen BEGIN=dataxHeartBeatEntry::isValidIndex
     //--AgentGen END
        return isValidIndex;
      }
    });

    // Columns
    MOColumn[] dataxHeartBeatEntryColumns = new MOColumn[3];
    dataxHeartBeatEntryColumns[idxSystemName] = 
      moFactory.createColumn(colSystemName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    dataxHeartBeatEntryColumns[idxSendTime] = 
      moFactory.createColumn(colSendTime, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDateAndTime);
    dataxHeartBeatEntryColumns[idxSendInterVal] = 
      new MOMutableColumn<Integer32>(colSendInterVal,
                          SMIConstants.SYNTAX_INTEGER,
                          moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE),
                          (Integer32)null);
    ((MOMutableColumn)dataxHeartBeatEntryColumns[idxSendInterVal]).
      addMOValueValidationListener(new SendInterValValidator());
    // Table model
    dataxHeartBeatEntryModel = 
      moFactory.createTableModel(oidDataxHeartBeatEntry,
                                 dataxHeartBeatEntryIndex,
                                 dataxHeartBeatEntryColumns);
    ((MOMutableTableModel<DataxHeartBeatEntryRow>)dataxHeartBeatEntryModel).setRowFactory(
      new DataxHeartBeatEntryRowFactory());
    dataxHeartBeatEntry = 
      moFactory.createTable(oidDataxHeartBeatEntry,
                            dataxHeartBeatEntryIndex,
                            dataxHeartBeatEntryColumns,
                            dataxHeartBeatEntryModel);
  }



  public void registerMOs(MOServer server, OctetString context) 
    throws DuplicateRegistrationException 
  {
    // Scalar Objects
    server.register(this.dataxHeartBeatEntry, context);
//--AgentGen BEGIN=_registerMOs
//--AgentGen END
  }

  public void unregisterMOs(MOServer server, OctetString context) {
    // Scalar Objects
    server.unregister(this.dataxHeartBeatEntry, context);
//--AgentGen BEGIN=_unregisterMOs
//--AgentGen END
  }

  // Notifications
  public void dataxHeartBeatEvent(NotificationOriginator notificationOriginator,
                                  OctetString context, VariableBinding[] vbs) {
    if (vbs.length < 3) {
      throw new IllegalArgumentException("Too few notification objects: "+
                                         vbs.length+"<3");
    }
    if (!(vbs[0].getOid().startsWith(oidTrapVarSystemName))) {
      throw new IllegalArgumentException("Variable 0 has wrong OID: "+vbs[0].getOid()+
                                         " does not start with "+oidTrapVarSystemName);
    }
    if (!dataxHeartBeatEntryIndex.isValidIndex(dataxHeartBeatEntry.getIndexPart(vbs[0].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 0 specified: "+
                                         dataxHeartBeatEntry.getIndexPart(vbs[0].getOid()));
    }
    if (!(vbs[1].getOid().startsWith(oidTrapVarSendTime))) {
      throw new IllegalArgumentException("Variable 1 has wrong OID: "+vbs[1].getOid()+
                                         " does not start with "+oidTrapVarSendTime);
    }
    if (!dataxHeartBeatEntryIndex.isValidIndex(dataxHeartBeatEntry.getIndexPart(vbs[1].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 1 specified: "+
                                         dataxHeartBeatEntry.getIndexPart(vbs[1].getOid()));
    }
    if (!(vbs[2].getOid().startsWith(oidTrapVarSendInterVal))) {
      throw new IllegalArgumentException("Variable 2 has wrong OID: "+vbs[2].getOid()+
                                         " does not start with "+oidTrapVarSendInterVal);
    }
    if (!dataxHeartBeatEntryIndex.isValidIndex(dataxHeartBeatEntry.getIndexPart(vbs[2].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 2 specified: "+
                                         dataxHeartBeatEntry.getIndexPart(vbs[2].getOid()));
    }
    notificationOriginator.notify(context, oidDataxHeartBeatEvent, vbs);
  }


  // Scalars

  // Value Validators

  /**
   * The <code>SendInterValValidator</code> implements the value
   * validation for <code>SendInterVal</code>.
   */
  static class SendInterValValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=sendInterVal::validate
     //--AgentGen END
    }
  }

  // Rows and Factories

  public class DataxHeartBeatEntryRow extends DefaultMOMutableRow2PC {

     //--AgentGen BEGIN=dataxHeartBeatEntry::RowMembers
     //--AgentGen END

    public DataxHeartBeatEntryRow(OID index, Variable[] values) {
      super(index, values);
     //--AgentGen BEGIN=dataxHeartBeatEntry::RowConstructor
     //--AgentGen END
    }
    
    public OctetString getSystemName() {
     //--AgentGen BEGIN=dataxHeartBeatEntry::getSystemName
     //--AgentGen END
      return (OctetString) super.getValue(idxSystemName);
    }  
    
    public void setSystemName(OctetString newValue) {
     //--AgentGen BEGIN=dataxHeartBeatEntry::setSystemName
     //--AgentGen END
      super.setValue(idxSystemName, newValue);
    }
    
    public OctetString getSendTime() {
     //--AgentGen BEGIN=dataxHeartBeatEntry::getSendTime
     //--AgentGen END
      return (OctetString) super.getValue(idxSendTime);
    }  
    
    public void setSendTime(OctetString newValue) {
     //--AgentGen BEGIN=dataxHeartBeatEntry::setSendTime
     //--AgentGen END
      super.setValue(idxSendTime, newValue);
    }
    
    public Integer32 getSendInterVal() {
     //--AgentGen BEGIN=dataxHeartBeatEntry::getSendInterVal
     //--AgentGen END
      return (Integer32) super.getValue(idxSendInterVal);
    }  
    
    public void setSendInterVal(Integer32 newValue) {
     //--AgentGen BEGIN=dataxHeartBeatEntry::setSendInterVal
     //--AgentGen END
      super.setValue(idxSendInterVal, newValue);
    }
    
    public Variable getValue(int column) {
     //--AgentGen BEGIN=dataxHeartBeatEntry::RowGetValue
     //--AgentGen END
      switch(column) {
        case idxSystemName: 
        	return getSystemName();
        case idxSendTime: 
        	return getSendTime();
        case idxSendInterVal: 
        	return getSendInterVal();
        default:
          return super.getValue(column);
      }
    }
    
    public void setValue(int column, Variable value) {
     //--AgentGen BEGIN=dataxHeartBeatEntry::RowSetValue
     //--AgentGen END
      switch(column) {
        case idxSystemName: 
        	setSystemName((OctetString)value);
        	break;
        case idxSendTime: 
        	setSendTime((OctetString)value);
        	break;
        case idxSendInterVal: 
        	setSendInterVal((Integer32)value);
        	break;
        default:
          super.setValue(column, value);
      }
    }

     //--AgentGen BEGIN=dataxHeartBeatEntry::Row
     //--AgentGen END
  }
  
  class DataxHeartBeatEntryRowFactory 
        implements MOTableRowFactory<DataxHeartBeatEntryRow>
  {
    public synchronized DataxHeartBeatEntryRow createRow(OID index, Variable[] values)
        throws UnsupportedOperationException 
    {
      DataxHeartBeatEntryRow row = 
        new DataxHeartBeatEntryRow(index, values);
     //--AgentGen BEGIN=dataxHeartBeatEntry::createRow
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(DataxHeartBeatEntryRow row) {
     //--AgentGen BEGIN=dataxHeartBeatEntry::freeRow
     //--AgentGen END
    }

     //--AgentGen BEGIN=dataxHeartBeatEntry::RowFactory
     //--AgentGen END
  }


//--AgentGen BEGIN=_METHODS
//--AgentGen END

  // Textual Definitions of MIB module DATAX-HEARTBEAT-MIB
  protected void addTCsToFactory(MOFactory moFactory) {
  }


//--AgentGen BEGIN=_TC_CLASSES_IMPORTED_MODULES_BEGIN
//--AgentGen END

  // Textual Definitions of other MIB modules
  public void addImportedTCsToFactory(MOFactory moFactory) {
  }


//--AgentGen BEGIN=_TC_CLASSES_IMPORTED_MODULES_END
//--AgentGen END

//--AgentGen BEGIN=_CLASSES
//--AgentGen END

//--AgentGen BEGIN=_END
//--AgentGen END
}



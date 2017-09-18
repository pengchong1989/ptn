package com.nms.snmp.ninteface.mib;
//--AgentGen BEGIN=_BEGIN
//--AgentGen END

import org.snmp4j.agent.DuplicateRegistrationException;
import org.snmp4j.agent.MOAccess;
import org.snmp4j.agent.MOGroup;
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
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.agent.mo.MOTable;
import org.snmp4j.agent.mo.MOTableIndex;
import org.snmp4j.agent.mo.MOTableIndexValidator;
import org.snmp4j.agent.mo.MOTableModel;
import org.snmp4j.agent.mo.MOTableRowFactory;
import org.snmp4j.agent.mo.MOTableSubIndex;
import org.snmp4j.agent.mo.MOValueValidationEvent;
import org.snmp4j.agent.mo.MOValueValidationListener;
import org.snmp4j.agent.mo.snmp.DateAndTime;
import org.snmp4j.agent.mo.snmp.DateAndTimeScalar;
import org.snmp4j.agent.mo.snmp.RowStatus;
import org.snmp4j.agent.mo.snmp.StorageType;
import org.snmp4j.agent.mo.snmp.smi.Constraint;
import org.snmp4j.agent.mo.snmp.smi.ConstraintsImpl;
import org.snmp4j.agent.mo.snmp.smi.EnumerationConstraint;
import org.snmp4j.agent.mo.snmp.smi.ValueConstraint;
import org.snmp4j.agent.mo.snmp.smi.ValueConstraintValidator;
import org.snmp4j.agent.request.SubRequest;
import org.snmp4j.log.LogAdapter;
import org.snmp4j.log.LogFactory;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.SMIConstants;
import org.snmp4j.smi.UnsignedInteger32;
import org.snmp4j.smi.Variable;


//--AgentGen BEGIN=_IMPORT
//--AgentGen END

public class Snmp_HeartBeat_Mib 
//--AgentGen BEGIN=_EXTENDS
//--AgentGen END
implements MOGroup 
//--AgentGen BEGIN=_IMPLEMENTS
,MOServerLookupListener
//--AgentGen END
{

  private static final LogAdapter LOGGER = 
      LogFactory.getLogger(Snmp_HeartBeat_Mib.class);

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
  public static final OID oidSnmp4jHeartbeatMib =
    new OID(new int[] { 1,3,6,1,4,1,4976,10,1,1,42,2 });

  // Identities
  // Scalars
  public static final OID oidSnmp4jAgentHBRefTime = 
    new OID(new int[] { 1,3,6,1,4,1,4976,10,1,1,42,2,1,1,0 });
  // Tables

  // Notifications

  // Enumerations




  // TextualConventions
  private static final String TC_MODULE_SNMPV2_TC = "SNMPv2-TC";
  private static final String TC_DATEANDTIME = "DateAndTime";
  private static final String TC_STORAGETYPE = "StorageType";
  private static final String TC_ROWSTATUS = "RowStatus";

  // Scalars
  private MOScalar<OctetString> snmp4jAgentHBRefTime;

  // Tables
  public static final OID oidSnmp4jAgentHBCtrlEntry = 
    new OID(new int[] { 1,3,6,1,4,1,4976,10,1,1,42,2,1,2,1 });

  // Index OID definitions
  public static final OID oidSnmp4jAgentHBCtrlName =
    new OID(new int[] { 1,3,6,1,4,1,4976,10,1,1,42,2,1,2,1,1 });

  // Column TC definitions for snmp4jAgentHBCtrlEntry:
  public static final String tcModuleSNMPv2Tc = "SNMPv2-TC";
  public static final String tcDefDateAndTime = "DateAndTime";
  public static final String tcDefStorageType = "StorageType";
  public static final String tcDefRowStatus = "RowStatus";
    
  // Column sub-identifer definitions for snmp4jAgentHBCtrlEntry:
  public static final int colSnmp4jAgentHBCtrlStartTime = 2;
  public static final int colSnmp4jAgentHBCtrlDelay = 3;
  public static final int colSnmp4jAgentHBCtrlPeriod = 4;
  public static final int colSnmp4jAgentHBCtrlMaxEvents = 5;
  public static final int colSnmp4jAgentHBCtrlStorageType = 6;
  public static final int colSnmp4jAgentHBCtrlRowStatus = 7;

  // Column index definitions for snmp4jAgentHBCtrlEntry:
  public static final int idxSnmp4jAgentHBCtrlStartTime = 0;
  public static final int idxSnmp4jAgentHBCtrlDelay = 1;
  public static final int idxSnmp4jAgentHBCtrlPeriod = 2;
  public static final int idxSnmp4jAgentHBCtrlMaxEvents = 3;
  public static final int idxSnmp4jAgentHBCtrlStorageType = 4;
  public static final int idxSnmp4jAgentHBCtrlRowStatus = 5;

  private MOTableSubIndex[] snmp4jAgentHBCtrlEntryIndexes;
  private MOTableIndex snmp4jAgentHBCtrlEntryIndex;
  
  private MOTable<Snmp4jAgentHBCtrlEntryRow,
                  MOColumn,
                  MOTableModel<Snmp4jAgentHBCtrlEntryRow>> snmp4jAgentHBCtrlEntry;
  private MOTableModel<Snmp4jAgentHBCtrlEntryRow> snmp4jAgentHBCtrlEntryModel;


//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  /**
   * Constructs a SNMP4J-HEARTBEAT-MIB instance without actually creating its
   * <code>ManagedObject</code> instances. This has to be done in a
   * sub-class constructor or after construction by calling 
   * {@link #createMO(MOFactory moFactory)}. 
   */
  protected Snmp_HeartBeat_Mib() {
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

  /**
   * Constructs a SNMP4J-HEARTBEAT-MIB instance and actually creates its
   * <code>ManagedObject</code> instances using the supplied 
   * <code>MOFactory</code> (by calling
   * {@link #createMO(MOFactory moFactory)}).
   * @param moFactory
   *    the <code>MOFactory</code> to be used to create the
   *    managed objects for this module.
   */
  public Snmp_HeartBeat_Mib(MOFactory moFactory) {
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
    snmp4jAgentHBRefTime = 
      new Snmp4jAgentHBRefTime(oidSnmp4jAgentHBRefTime, 
                               moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE));
    snmp4jAgentHBRefTime.addMOValueValidationListener(new Snmp4jAgentHBRefTimeValidator());
    createSnmp4jAgentHBCtrlEntry(moFactory);
  }

  public MOScalar<OctetString> getSnmp4jAgentHBRefTime() {
    return snmp4jAgentHBRefTime;
  }


  public MOTable<Snmp4jAgentHBCtrlEntryRow,MOColumn,MOTableModel<Snmp4jAgentHBCtrlEntryRow>> getSnmp4jAgentHBCtrlEntry() {
    return snmp4jAgentHBCtrlEntry;
  }


  @SuppressWarnings(value={"unchecked"})
  private void createSnmp4jAgentHBCtrlEntry(MOFactory moFactory) {
    // Index definition
    snmp4jAgentHBCtrlEntryIndexes = 
      new MOTableSubIndex[] {
      moFactory.createSubIndex(oidSnmp4jAgentHBCtrlName, 
                               SMIConstants.SYNTAX_OCTET_STRING, 1, 32)
    };

    snmp4jAgentHBCtrlEntryIndex = 
      moFactory.createIndex(snmp4jAgentHBCtrlEntryIndexes,
                            true,
                            new MOTableIndexValidator() {
      public boolean isValidIndex(OID index) {
        boolean isValidIndex = true;
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::isValidIndex
     //--AgentGen END
        return isValidIndex;
      }
    });

    // Columns
    MOColumn[] snmp4jAgentHBCtrlEntryColumns = new MOColumn[6];
    snmp4jAgentHBCtrlEntryColumns[idxSnmp4jAgentHBCtrlStartTime] = 
      new DateAndTime(colSnmp4jAgentHBCtrlStartTime,
                      moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_CREATE),
                      (OctetString)null);
    ValueConstraint snmp4jAgentHBCtrlStartTimeVC = new ConstraintsImpl();
    ((ConstraintsImpl)snmp4jAgentHBCtrlStartTimeVC).add(new Constraint(8L, 8L));
    ((ConstraintsImpl)snmp4jAgentHBCtrlStartTimeVC).add(new Constraint(11L, 11L));
    ((MOMutableColumn)snmp4jAgentHBCtrlEntryColumns[idxSnmp4jAgentHBCtrlStartTime]).
      addMOValueValidationListener(new ValueConstraintValidator(snmp4jAgentHBCtrlStartTimeVC));                                  
    ((MOMutableColumn)snmp4jAgentHBCtrlEntryColumns[idxSnmp4jAgentHBCtrlStartTime]).
      addMOValueValidationListener(new Snmp4jAgentHBCtrlStartTimeValidator());
    snmp4jAgentHBCtrlEntryColumns[idxSnmp4jAgentHBCtrlDelay] = 
      new MOMutableColumn<UnsignedInteger32>(colSnmp4jAgentHBCtrlDelay,
                          SMIConstants.SYNTAX_GAUGE32,
                          moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_CREATE),
                          new UnsignedInteger32(1000));
    ((MOMutableColumn)snmp4jAgentHBCtrlEntryColumns[idxSnmp4jAgentHBCtrlDelay]).
      addMOValueValidationListener(new Snmp4jAgentHBCtrlDelayValidator());
    snmp4jAgentHBCtrlEntryColumns[idxSnmp4jAgentHBCtrlPeriod] = 
      new MOMutableColumn<UnsignedInteger32>(colSnmp4jAgentHBCtrlPeriod,
                          SMIConstants.SYNTAX_GAUGE32,
                          moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_CREATE),
                          new UnsignedInteger32(60000));
    ((MOMutableColumn)snmp4jAgentHBCtrlEntryColumns[idxSnmp4jAgentHBCtrlPeriod]).
      addMOValueValidationListener(new Snmp4jAgentHBCtrlPeriodValidator());
    snmp4jAgentHBCtrlEntryColumns[idxSnmp4jAgentHBCtrlMaxEvents] = 
      new MOMutableColumn<UnsignedInteger32>(colSnmp4jAgentHBCtrlMaxEvents,
                          SMIConstants.SYNTAX_GAUGE32,
                          moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_CREATE),
                          new UnsignedInteger32(0));
    ((MOMutableColumn)snmp4jAgentHBCtrlEntryColumns[idxSnmp4jAgentHBCtrlMaxEvents]).
      addMOValueValidationListener(new Snmp4jAgentHBCtrlMaxEventsValidator());
    snmp4jAgentHBCtrlEntryColumns[idxSnmp4jAgentHBCtrlStorageType] = 
      new StorageType(colSnmp4jAgentHBCtrlStorageType,
                      moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_CREATE),
                      new Integer32(3));
    ValueConstraint snmp4jAgentHBCtrlStorageTypeVC = new EnumerationConstraint(
      new int[] { 1,
                  2,
                  3,
                  4,
                  5 });
    ((MOMutableColumn)snmp4jAgentHBCtrlEntryColumns[idxSnmp4jAgentHBCtrlStorageType]).
      addMOValueValidationListener(new ValueConstraintValidator(snmp4jAgentHBCtrlStorageTypeVC));                                  
    ((MOMutableColumn)snmp4jAgentHBCtrlEntryColumns[idxSnmp4jAgentHBCtrlStorageType]).
      addMOValueValidationListener(new Snmp4jAgentHBCtrlStorageTypeValidator());
    snmp4jAgentHBCtrlEntryColumns[idxSnmp4jAgentHBCtrlRowStatus] = 
      new RowStatus(colSnmp4jAgentHBCtrlRowStatus);
    ValueConstraint snmp4jAgentHBCtrlRowStatusVC = new EnumerationConstraint(
      new int[] { 1,
                  2,
                  3,
                  4,
                  5,
                  6 });
    ((MOMutableColumn)snmp4jAgentHBCtrlEntryColumns[idxSnmp4jAgentHBCtrlRowStatus]).
      addMOValueValidationListener(new ValueConstraintValidator(snmp4jAgentHBCtrlRowStatusVC));                                  
    ((MOMutableColumn)snmp4jAgentHBCtrlEntryColumns[idxSnmp4jAgentHBCtrlRowStatus]).
      addMOValueValidationListener(new Snmp4jAgentHBCtrlRowStatusValidator());
    // Table model
    snmp4jAgentHBCtrlEntryModel = 
      moFactory.createTableModel(oidSnmp4jAgentHBCtrlEntry,
                                 snmp4jAgentHBCtrlEntryIndex,
                                 snmp4jAgentHBCtrlEntryColumns);
    ((MOMutableTableModel<Snmp4jAgentHBCtrlEntryRow>)snmp4jAgentHBCtrlEntryModel).setRowFactory(
      new Snmp4jAgentHBCtrlEntryRowFactory());
       
    snmp4jAgentHBCtrlEntry = 
      moFactory.createTable(oidSnmp4jAgentHBCtrlEntry,
                            snmp4jAgentHBCtrlEntryIndex,
                            snmp4jAgentHBCtrlEntryColumns,
                            snmp4jAgentHBCtrlEntryModel);
  }

  public void registerMOs(MOServer server, OctetString context) 
    throws DuplicateRegistrationException 
  {
    // Scalar Objects
    server.register(this.snmp4jAgentHBRefTime, context);
    server.register(this.snmp4jAgentHBCtrlEntry, context);
//--AgentGen BEGIN=_registerMOs
    server.addLookupListener(this, this.snmp4jAgentHBRefTime);
    server.addLookupListener(this, this.snmp4jAgentHBCtrlEntry);
//--AgentGen END
  }

  public void unregisterMOs(MOServer server, OctetString context) {
    // Scalar Objects
    server.unregister(this.snmp4jAgentHBRefTime, context);
    server.unregister(this.snmp4jAgentHBCtrlEntry, context);
//--AgentGen BEGIN=_unregisterMOs
//--AgentGen END
  }

  // Notifications

  // Scalars
  public class Snmp4jAgentHBRefTime extends DateAndTimeScalar<OctetString> {
    Snmp4jAgentHBRefTime(OID oid, MOAccess access) {
      super(oid, access, new OctetString());
//--AgentGen BEGIN=snmp4jAgentHBRefTime
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
      OctetString os = (OctetString)newValue;
      if (!(((os.length() >= 8) && (os.length() <= 8)) ||
        ((os.length() >= 11) && (os.length() <= 11)))) {
        valueOK = SnmpConstants.SNMP_ERROR_WRONG_LENGTH;
      }
     //--AgentGen BEGIN=snmp4jAgentHBRefTime::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public OctetString getValue() {
     //--AgentGen BEGIN=snmp4jAgentHBRefTime::getValue
    	setValue(new OctetString("xiech_set"));
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(OctetString newValue) {
     //--AgentGen BEGIN=snmp4jAgentHBRefTime::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=snmp4jAgentHBRefTime::_METHODS
     //--AgentGen END

  }


  // Value Validators
  /**
   * The <code>Snmp4jAgentHBRefTimeValidator</code> implements the value
   * validation for <code>Snmp4jAgentHBRefTime</code>.
   */
  static class Snmp4jAgentHBRefTimeValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (!(((os.length() >= 8) && (os.length() <= 8)) ||
        ((os.length() >= 11) && (os.length() <= 11)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=snmp4jAgentHBRefTime::validate
     //--AgentGen END
    }
  }

  /**
   * The <code>Snmp4jAgentHBCtrlStartTimeValidator</code> implements the value
   * validation for <code>Snmp4jAgentHBCtrlStartTime</code>.
   */
  static class Snmp4jAgentHBCtrlStartTimeValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (!(((os.length() >= 8) && (os.length() <= 8)) ||
        ((os.length() >= 11) && (os.length() <= 11)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=snmp4jAgentHBCtrlStartTime::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>Snmp4jAgentHBCtrlDelayValidator</code> implements the value
   * validation for <code>Snmp4jAgentHBCtrlDelay</code>.
   */
  static class Snmp4jAgentHBCtrlDelayValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=snmp4jAgentHBCtrlDelay::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>Snmp4jAgentHBCtrlPeriodValidator</code> implements the value
   * validation for <code>Snmp4jAgentHBCtrlPeriod</code>.
   */
  static class Snmp4jAgentHBCtrlPeriodValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=snmp4jAgentHBCtrlPeriod::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>Snmp4jAgentHBCtrlMaxEventsValidator</code> implements the value
   * validation for <code>Snmp4jAgentHBCtrlMaxEvents</code>.
   */
  static class Snmp4jAgentHBCtrlMaxEventsValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=snmp4jAgentHBCtrlMaxEvents::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>Snmp4jAgentHBCtrlStorageTypeValidator</code> implements the value
   * validation for <code>Snmp4jAgentHBCtrlStorageType</code>.
   */
  static class Snmp4jAgentHBCtrlStorageTypeValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=snmp4jAgentHBCtrlStorageType::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>Snmp4jAgentHBCtrlRowStatusValidator</code> implements the value
   * validation for <code>Snmp4jAgentHBCtrlRowStatus</code>.
   */
  static class Snmp4jAgentHBCtrlRowStatusValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=snmp4jAgentHBCtrlRowStatus::validate
     //--AgentGen END
    }
  }

  // Rows and Factories

  public class Snmp4jAgentHBCtrlEntryRow extends DefaultMOMutableRow2PC {

     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::RowMembers
     //--AgentGen END

    public Snmp4jAgentHBCtrlEntryRow(OID index, Variable[] values) {
      super(index, values);
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::RowConstructor
     //--AgentGen END
    }
    
    public OctetString getSnmp4jAgentHBCtrlStartTime() {
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::getSnmp4jAgentHBCtrlStartTime
     //--AgentGen END
      return (OctetString) super.getValue(idxSnmp4jAgentHBCtrlStartTime);
    }  
    
    public void setSnmp4jAgentHBCtrlStartTime(OctetString newValue) {
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::setSnmp4jAgentHBCtrlStartTime
     //--AgentGen END
      super.setValue(idxSnmp4jAgentHBCtrlStartTime, newValue);
    }
    
    public UnsignedInteger32 getSnmp4jAgentHBCtrlDelay() {
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::getSnmp4jAgentHBCtrlDelay
     //--AgentGen END
      return (UnsignedInteger32) super.getValue(idxSnmp4jAgentHBCtrlDelay);
    }  
    
    public void setSnmp4jAgentHBCtrlDelay(UnsignedInteger32 newValue) {
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::setSnmp4jAgentHBCtrlDelay
     //--AgentGen END
      super.setValue(idxSnmp4jAgentHBCtrlDelay, newValue);
    }
    
    public UnsignedInteger32 getSnmp4jAgentHBCtrlPeriod() {
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::getSnmp4jAgentHBCtrlPeriod
     //--AgentGen END
      return (UnsignedInteger32) super.getValue(idxSnmp4jAgentHBCtrlPeriod);
    }  
    
    public void setSnmp4jAgentHBCtrlPeriod(UnsignedInteger32 newValue) {
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::setSnmp4jAgentHBCtrlPeriod
     //--AgentGen END
      super.setValue(idxSnmp4jAgentHBCtrlPeriod, newValue);
    }
    
    public UnsignedInteger32 getSnmp4jAgentHBCtrlMaxEvents() {
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::getSnmp4jAgentHBCtrlMaxEvents
     //--AgentGen END
      return (UnsignedInteger32) super.getValue(idxSnmp4jAgentHBCtrlMaxEvents);
    }  
    
    public void setSnmp4jAgentHBCtrlMaxEvents(UnsignedInteger32 newValue) {
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::setSnmp4jAgentHBCtrlMaxEvents
     //--AgentGen END
      super.setValue(idxSnmp4jAgentHBCtrlMaxEvents, newValue);
    }
    
    public Integer32 getSnmp4jAgentHBCtrlStorageType() {
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::getSnmp4jAgentHBCtrlStorageType
     //--AgentGen END
      return (Integer32) super.getValue(idxSnmp4jAgentHBCtrlStorageType);
    }  
    
    public void setSnmp4jAgentHBCtrlStorageType(Integer32 newValue) {
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::setSnmp4jAgentHBCtrlStorageType
     //--AgentGen END
      super.setValue(idxSnmp4jAgentHBCtrlStorageType, newValue);
    }
    
    public Integer32 getSnmp4jAgentHBCtrlRowStatus() {
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::getSnmp4jAgentHBCtrlRowStatus
     //--AgentGen END
      return (Integer32) super.getValue(idxSnmp4jAgentHBCtrlRowStatus);
    }  
    
    public void setSnmp4jAgentHBCtrlRowStatus(Integer32 newValue) {
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::setSnmp4jAgentHBCtrlRowStatus
     //--AgentGen END
      super.setValue(idxSnmp4jAgentHBCtrlRowStatus, newValue);
    }
    
    public Variable getValue(int column) {
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::RowGetValue
     //--AgentGen END
      switch(column) {
        case idxSnmp4jAgentHBCtrlStartTime: 
        	return getSnmp4jAgentHBCtrlStartTime();
        case idxSnmp4jAgentHBCtrlDelay: 
        	return getSnmp4jAgentHBCtrlDelay();
        case idxSnmp4jAgentHBCtrlPeriod: 
        	return getSnmp4jAgentHBCtrlPeriod();
        case idxSnmp4jAgentHBCtrlMaxEvents: 
        	return getSnmp4jAgentHBCtrlMaxEvents();
        case idxSnmp4jAgentHBCtrlStorageType: 
        	return getSnmp4jAgentHBCtrlStorageType();
        case idxSnmp4jAgentHBCtrlRowStatus: 
        	return getSnmp4jAgentHBCtrlRowStatus();
        default:
          return super.getValue(column);
      }
    }
    
    public void setValue(int column, Variable value) {
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::RowSetValue
     //--AgentGen END
      switch(column) {
        case idxSnmp4jAgentHBCtrlStartTime: 
        	setSnmp4jAgentHBCtrlStartTime((OctetString)value);
        	break;
        case idxSnmp4jAgentHBCtrlDelay: 
        	setSnmp4jAgentHBCtrlDelay((UnsignedInteger32)value);
        	break;
        case idxSnmp4jAgentHBCtrlPeriod: 
        	setSnmp4jAgentHBCtrlPeriod((UnsignedInteger32)value);
        	break;
        case idxSnmp4jAgentHBCtrlMaxEvents: 
        	setSnmp4jAgentHBCtrlMaxEvents((UnsignedInteger32)value);
        	break;
        case idxSnmp4jAgentHBCtrlStorageType: 
        	setSnmp4jAgentHBCtrlStorageType((Integer32)value);
        	break;
        case idxSnmp4jAgentHBCtrlRowStatus: 
        	setSnmp4jAgentHBCtrlRowStatus((Integer32)value);
        	break;
        default:
          super.setValue(column, value);
      }
    }

     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::Row
     //--AgentGen END
  }
  
  class Snmp4jAgentHBCtrlEntryRowFactory 
        implements MOTableRowFactory<Snmp4jAgentHBCtrlEntryRow>
  {
    public synchronized Snmp4jAgentHBCtrlEntryRow createRow(OID index, Variable[] values)
        throws UnsupportedOperationException 
    {
      Snmp4jAgentHBCtrlEntryRow row = 
        new Snmp4jAgentHBCtrlEntryRow(index, values);
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::createRow
      
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(Snmp4jAgentHBCtrlEntryRow row) {
     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::freeRow
     //--AgentGen END
    }

     //--AgentGen BEGIN=snmp4jAgentHBCtrlEntry::RowFactory
     //--AgentGen END
  }


//--AgentGen BEGIN=_METHODS
//--AgentGen END

  // Textual Definitions of MIB module SNMP4J-HEARTBEAT-MIB
  protected void addTCsToFactory(MOFactory moFactory) {
  }


//--AgentGen BEGIN=_TC_CLASSES_IMPORTED_MODULES_BEGIN
//--AgentGen END

  // Textual Definitions of other MIB modules
  public void addImportedTCsToFactory(MOFactory moFactory) {
  }

	@Override
	public void lookupEvent(MOServerLookupEvent event) {
		// TODO Auto-generated method stub
		if(event.getLookupResult() == this.snmp4jAgentHBCtrlEntry){
			UpdatableManagedObject();
		}
	}
	
	@Override
	public void queryEvent(MOServerLookupEvent event) {
		// TODO Auto-generated method stub
		if(event.getLookupResult() == this.snmp4jAgentHBCtrlEntry){
			UpdatableManagedObject();
		}
	}
	//--AgentGen BEGIN=_TC_CLASSES_IMPORTED_MODULES_END
	//--AgentGen END

	private void UpdatableManagedObject() {
		// TODO Auto-generated method stub
	   MOMutableTableModel model = (MOMutableTableModel) snmp4jAgentHBCtrlEntry.getModel();
	    Variable[] rowValues = new Variable[] {
	            new OctetString("test"),
	            new UnsignedInteger32(1500),
	            new UnsignedInteger32(100000000),
	            new UnsignedInteger32(12),
	            new Integer32(1),
	            new Integer32(1)
	    };
	    Snmp4jAgentHBCtrlEntryRow row = new Snmp4jAgentHBCtrlEntryRow(new OID("3"),rowValues);
	    snmp4jAgentHBCtrlEntry.addRow(row);
	    model.addRow(new DefaultMOMutableRow2PC(new OID("4"), rowValues));
	}
	
	//--AgentGen BEGIN=_CLASSES
	//--AgentGen END
	
	//--AgentGen BEGIN=_END
	//--AgentGen END
}



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
import org.snmp4j.agent.mo.MOMutableTableModel;
import org.snmp4j.agent.mo.MOTable;
import org.snmp4j.agent.mo.MOTableIndex;
import org.snmp4j.agent.mo.MOTableIndexValidator;
import org.snmp4j.agent.mo.MOTableModel;
import org.snmp4j.agent.mo.MOTableRowFactory;
import org.snmp4j.agent.mo.MOTableSubIndex;
import org.snmp4j.log.LogAdapter;
import org.snmp4j.log.LogFactory;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.SMIConstants;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;


//--AgentGen BEGIN=_IMPORT
//--AgentGen END

public class DataxAlarmModel 
//--AgentGen BEGIN=_EXTENDS
//--AgentGen END
implements MOGroup 
//--AgentGen BEGIN=_IMPLEMENTS
//--AgentGen END
{

  private static final LogAdapter LOGGER = 
      LogFactory.getLogger(DataxAlarmModel.class);

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
  public static final OID oidDataxalarmmodel =
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
    new OID(new int[] { 1,3,6,1,4,1,44484,1,3,2,1 });

  // Index OID definitions
  public static final OID oidAlarmindex =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,3,2,1,1 });

//Notifications
  public static final OID oidDataxAlarmEvent =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,3,1,2,1,2,1 });   
  public static final OID oidTrapVarAlarmId =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,3,1,2,1,1,1,1 });
  public static final OID oidTrapVarAlarmObjectName =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,3,1,2,1,1,1,2 });
  public static final OID oidTrapNativeProbableCause =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,3,1,2,1,1,1,3 });
  public static final OID oidTrapProbableCauseQualifier =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,3,1,2,1,1,1,4 });
  public static final OID oidTrapVarAlarmObjectType =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,3,1,2,1,1,1,5 });
  public static final OID oidTrapVarAlarmObjectTypeQualifier =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,3,1,2,1,1,1,6 });
  public static final OID oidTrapVarAlarmEmsTime =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,3,1,2,1,1,1,7 });
  public static final OID oidTrapVarAlarmNeTime  =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,1,1,2,1,1,1,8 });
  public static final OID oidTrapVarAlarmlayerRate  =
	new OID(new int[] { 1,3,6,1,4,1,44484,1,3,1,2,1,1,1,9 });
  public static final OID oidTrapVarAlarmlaProbableCauseOid  =
	  new OID(new int[] { 1,3,6,1,4,1,44484,1,3,1,2,1,1,1,10 });
  public static final OID oidTrapVarAlarmlaServiceAffectingOid  =
	  new OID(new int[] { 1,3,6,1,4,1,44484,1,3,1,2,1,1,1,11 });
  public static final OID oidTrapVarAlarmlPerceivedSeverityOid =
	  new OID(new int[] { 1,3,6,1,4,1,44484,1,3,1,2,1,1,1,12 });
  public static final OID oidTrapVarAlarmlSpecificProblemsOid =
	  new OID(new int[] { 1,3,6,1,4,1,44484,1,3,1,2,1,1,1,13 });
  public static final OID oidTrapVarAlarmlProposedRepairActionsOid =
	  new OID(new int[] { 1,3,6,1,4,1,44484,1,3,1,2,1,1,1,14 });
  public static final OID oidTrapVarAlarmlEventTypeOid =
	  new OID(new int[] { 1,3,6,1,4,1,44484,1,3,1,2,1,1,1,15 });
  public static final OID oidTrapVarAlarmAcknowledgeIndicationOid =
	  new OID(new int[] { 1,3,6,1,4,1,44484,1,3,1,2,1,1,1,16 });
  public static final OID oidTrapVarAlarmAcknowledgeTimeOid =
	  new OID(new int[] { 1,3,6,1,4,1,44484,1,3,1,2,1,1,1,17 });
  // Column TC definitions for myEntry:
  public static final String tcModuleSNMPv2Tc = "SNMPv2-TC";
  public static final String tcDefDisplayString = "DisplayString";
    
  // Column sub-identifer definitions for myEntry:
  public static final int colAlarmIndex = 1;
  public static final int colAlarmID = 2;
  public static final int colObjectName = 3;
  public static final int colNativeProbableCause = 4;
  public static final int colProbableCauseQualifier = 5;
  public static final int colObjectType = 6;
  public static final int colObjectTypeQualifier = 7;
  public static final int colEmsTime = 8;
  public static final int colNeTime = 9;
  public static final int colLayerRate = 10;
  public static final int colProbableCause = 11;
  public static final int colServiceAffecting = 12;
  public static final int colPerceivedSeverity = 13;
  public static final int colSpecificProblems = 14;
  public static final int colProposedRepairActions = 15;
  public static final int colEventType = 16;
  public static final int colAcknowledgeIndication = 17;
  public static final int colAcknowledgeTime = 18;

  // Column index definitions for myEntry:
  public static final int idxAlarmID = 0;
  public static final int idxObjectName = 1;
  public static final int idxNativeProbableCause = 2;
  public static final int idxProbableCauseQualifier = 3;
  public static final int idxObjectType = 4;
  public static final int idxObjectTypeQualifier = 5;
  public static final int idxEmsTime = 6;
  public static final int idxNeTime = 7;
  public static final int idxLayerRate = 8;
  public static final int idxProbableCause = 9;
  public static final int idxServiceAffecting = 10;
  public static final int idxPerceivedSeverity = 11;
  public static final int idxSpecificProblems = 12;
  public static final int idxProposedRepairActions = 13;
  public static final int idxEventType = 14;
  public static final int idxAcknowledgeIndication = 15;
  public static final int idxAcknowledgeTime = 16;

  private MOTableSubIndex[] myEntryIndexes;
  private MOTableIndex myEntryIndex;
  
  private MOTable<MyEntryRow,
                  MOColumn,
                  MOTableModel<MyEntryRow>> myEntry;
  private MOTableModel<MyEntryRow> myEntryModel;


//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  /**
   * Constructs a DATAXALARMMODEL instance without actually creating its
   * <code>ManagedObject</code> instances. This has to be done in a
   * sub-class constructor or after construction by calling 
   * {@link #createMO(MOFactory moFactory)}. 
   */
  protected DataxAlarmModel() {
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

  /**
   * Constructs a DATAXALARMMODEL instance and actually creates its
   * <code>ManagedObject</code> instances using the supplied 
   * <code>MOFactory</code> (by calling
   * {@link #createMO(MOFactory moFactory)}).
   * @param moFactory
   *    the <code>MOFactory</code> to be used to create the
   *    managed objects for this module.
   */
  public DataxAlarmModel(MOFactory moFactory) {
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
      moFactory.createSubIndex(oidAlarmindex, 
                               SMIConstants.SYNTAX_OCTET_STRING, 0, 255)
    };

    myEntryIndex = 
      moFactory.createIndex(myEntryIndexes,
                            true,
                            new MOTableIndexValidator() {
      public boolean isValidIndex(OID index) {
        boolean isValidIndex = true;
     //--AgentGen BEGIN=myEntry::isValidIndex
     //--AgentGen END
        return isValidIndex;
      }
    });

    // Columns
    MOColumn[] myEntryColumns = new MOColumn[17];
    myEntryColumns[idxAlarmID] = 
      moFactory.createColumn(colAlarmID, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxObjectName] = 
      moFactory.createColumn(colObjectName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxNativeProbableCause] = 
      moFactory.createColumn(colNativeProbableCause, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxProbableCauseQualifier] = 
      moFactory.createColumn(colProbableCauseQualifier, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxObjectType] = 
      moFactory.createColumn(colObjectType, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxObjectTypeQualifier] = 
      moFactory.createColumn(colObjectTypeQualifier, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxEmsTime] = 
      moFactory.createColumn(colEmsTime, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxNeTime] = 
      moFactory.createColumn(colNeTime, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxLayerRate] = 
      moFactory.createColumn(colLayerRate, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxProbableCause] = 
      moFactory.createColumn(colProbableCause, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxServiceAffecting] = 
      moFactory.createColumn(colServiceAffecting, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxPerceivedSeverity] = 
      moFactory.createColumn(colPerceivedSeverity, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxSpecificProblems] = 
      moFactory.createColumn(colSpecificProblems, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxProposedRepairActions] = 
      moFactory.createColumn(colProposedRepairActions, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxEventType] = 
      moFactory.createColumn(colEventType, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxAcknowledgeIndication] = 
      moFactory.createColumn(colAcknowledgeIndication, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    myEntryColumns[idxAcknowledgeTime] = 
      moFactory.createColumn(colAcknowledgeTime, 
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

//Notifications
  public void dataxAlarmEvent(NotificationOriginator notificationOriginator, OctetString context, VariableBinding[] vbs) {
    notificationOriginator.notify(context, oidDataxAlarmEvent, vbs);
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
    
    public OctetString getAlarmID() {
     //--AgentGen BEGIN=myEntry::getAlarmID
     //--AgentGen END
      return (OctetString) super.getValue(idxAlarmID);
    }  
    
    public void setAlarmID(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setAlarmID
     //--AgentGen END
      super.setValue(idxAlarmID, newValue);
    }
    
    public OctetString getObjectName() {
     //--AgentGen BEGIN=myEntry::getObjectName
     //--AgentGen END
      return (OctetString) super.getValue(idxObjectName);
    }  
    
    public void setObjectName(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setObjectName
     //--AgentGen END
      super.setValue(idxObjectName, newValue);
    }
    
    public OctetString getNativeProbableCause() {
     //--AgentGen BEGIN=myEntry::getNativeProbableCause
     //--AgentGen END
      return (OctetString) super.getValue(idxNativeProbableCause);
    }  
    
    public void setNativeProbableCause(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setNativeProbableCause
     //--AgentGen END
      super.setValue(idxNativeProbableCause, newValue);
    }
    
    public OctetString getProbableCauseQualifier() {
     //--AgentGen BEGIN=myEntry::getProbableCauseQualifier
     //--AgentGen END
      return (OctetString) super.getValue(idxProbableCauseQualifier);
    }  
    
    public void setProbableCauseQualifier(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setProbableCauseQualifier
     //--AgentGen END
      super.setValue(idxProbableCauseQualifier, newValue);
    }
    
    public OctetString getObjectType() {
     //--AgentGen BEGIN=myEntry::getObjectType
     //--AgentGen END
      return (OctetString) super.getValue(idxObjectType);
    }  
    
    public void setObjectType(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setObjectType
     //--AgentGen END
      super.setValue(idxObjectType, newValue);
    }
    
    public OctetString getObjectTypeQualifier() {
     //--AgentGen BEGIN=myEntry::getObjectTypeQualifier
     //--AgentGen END
      return (OctetString) super.getValue(idxObjectTypeQualifier);
    }  
    
    public void setObjectTypeQualifier(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setObjectTypeQualifier
     //--AgentGen END
      super.setValue(idxObjectTypeQualifier, newValue);
    }
    
    public OctetString getEmsTime() {
     //--AgentGen BEGIN=myEntry::getEmsTime
     //--AgentGen END
      return (OctetString) super.getValue(idxEmsTime);
    }  
    
    public void setEmsTime(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setEmsTime
     //--AgentGen END
      super.setValue(idxEmsTime, newValue);
    }
    
    public OctetString getNeTime() {
     //--AgentGen BEGIN=myEntry::getNeTime
     //--AgentGen END
      return (OctetString) super.getValue(idxNeTime);
    }  
    
    public void setNeTime(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setNeTime
     //--AgentGen END
      super.setValue(idxNeTime, newValue);
    }
    
    public OctetString getLayerRate() {
     //--AgentGen BEGIN=myEntry::getLayerRate
     //--AgentGen END
      return (OctetString) super.getValue(idxLayerRate);
    }  
    
    public void setLayerRate(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setLayerRate
     //--AgentGen END
      super.setValue(idxLayerRate, newValue);
    }
    
    public OctetString getProbableCause() {
     //--AgentGen BEGIN=myEntry::getProbableCause
     //--AgentGen END
      return (OctetString) super.getValue(idxProbableCause);
    }  
    
    public void setProbableCause(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setProbableCause
     //--AgentGen END
      super.setValue(idxProbableCause, newValue);
    }
    
    public OctetString getServiceAffecting() {
     //--AgentGen BEGIN=myEntry::getServiceAffecting
     //--AgentGen END
      return (OctetString) super.getValue(idxServiceAffecting);
    }  
    
    public void setServiceAffecting(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setServiceAffecting
     //--AgentGen END
      super.setValue(idxServiceAffecting, newValue);
    }
    
    public OctetString getPerceivedSeverity() {
     //--AgentGen BEGIN=myEntry::getPerceivedSeverity
     //--AgentGen END
      return (OctetString) super.getValue(idxPerceivedSeverity);
    }  
    
    public void setPerceivedSeverity(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setPerceivedSeverity
     //--AgentGen END
      super.setValue(idxPerceivedSeverity, newValue);
    }
    
    public OctetString getSpecificProblems() {
     //--AgentGen BEGIN=myEntry::getSpecificProblems
     //--AgentGen END
      return (OctetString) super.getValue(idxSpecificProblems);
    }  
    
    public void setSpecificProblems(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setSpecificProblems
     //--AgentGen END
      super.setValue(idxSpecificProblems, newValue);
    }
    
    public OctetString getProposedRepairActions() {
     //--AgentGen BEGIN=myEntry::getProposedRepairActions
     //--AgentGen END
      return (OctetString) super.getValue(idxProposedRepairActions);
    }  
    
    public void setProposedRepairActions(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setProposedRepairActions
     //--AgentGen END
      super.setValue(idxProposedRepairActions, newValue);
    }
    
    public OctetString getEventType() {
     //--AgentGen BEGIN=myEntry::getEventType
     //--AgentGen END
      return (OctetString) super.getValue(idxEventType);
    }  
    
    public void setEventType(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setEventType
     //--AgentGen END
      super.setValue(idxEventType, newValue);
    }
    
    public OctetString getAcknowledgeIndication() {
     //--AgentGen BEGIN=myEntry::getAcknowledgeIndication
     //--AgentGen END
      return (OctetString) super.getValue(idxAcknowledgeIndication);
    }  
    
    public void setAcknowledgeIndication(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setAcknowledgeIndication
     //--AgentGen END
      super.setValue(idxAcknowledgeIndication, newValue);
    }
    
    public OctetString getAcknowledgeTime() {
     //--AgentGen BEGIN=myEntry::getAcknowledgeTime
     //--AgentGen END
      return (OctetString) super.getValue(idxAcknowledgeTime);
    }  
    
    public void setAcknowledgeTime(OctetString newValue) {
     //--AgentGen BEGIN=myEntry::setAcknowledgeTime
     //--AgentGen END
      super.setValue(idxAcknowledgeTime, newValue);
    }
    
    public Variable getValue(int column) {
     //--AgentGen BEGIN=myEntry::RowGetValue
     //--AgentGen END
      switch(column) {
        case idxAlarmID: 
        	return getAlarmID();
        case idxObjectName: 
        	return getObjectName();
        case idxNativeProbableCause: 
        	return getNativeProbableCause();
        case idxProbableCauseQualifier: 
        	return getProbableCauseQualifier();
        case idxObjectType: 
        	return getObjectType();
        case idxObjectTypeQualifier: 
        	return getObjectTypeQualifier();
        case idxEmsTime: 
        	return getEmsTime();
        case idxNeTime: 
        	return getNeTime();
        case idxLayerRate: 
        	return getLayerRate();
        case idxProbableCause: 
        	return getProbableCause();
        case idxServiceAffecting: 
        	return getServiceAffecting();
        case idxPerceivedSeverity: 
        	return getPerceivedSeverity();
        case idxSpecificProblems: 
        	return getSpecificProblems();
        case idxProposedRepairActions: 
        	return getProposedRepairActions();
        case idxEventType: 
        	return getEventType();
        case idxAcknowledgeIndication: 
        	return getAcknowledgeIndication();
        case idxAcknowledgeTime: 
        	return getAcknowledgeTime();
        default:
          return super.getValue(column);
      }
    }
    
    public void setValue(int column, Variable value) {
     //--AgentGen BEGIN=myEntry::RowSetValue
     //--AgentGen END
      switch(column) {
        case idxAlarmID: 
        	setAlarmID((OctetString)value);
        	break;
        case idxObjectName: 
        	setObjectName((OctetString)value);
        	break;
        case idxNativeProbableCause: 
        	setNativeProbableCause((OctetString)value);
        	break;
        case idxProbableCauseQualifier: 
        	setProbableCauseQualifier((OctetString)value);
        	break;
        case idxObjectType: 
        	setObjectType((OctetString)value);
        	break;
        case idxObjectTypeQualifier: 
        	setObjectTypeQualifier((OctetString)value);
        	break;
        case idxEmsTime: 
        	setEmsTime((OctetString)value);
        	break;
        case idxNeTime: 
        	setNeTime((OctetString)value);
        	break;
        case idxLayerRate: 
        	setLayerRate((OctetString)value);
        	break;
        case idxProbableCause: 
        	setProbableCause((OctetString)value);
        	break;
        case idxServiceAffecting: 
        	setServiceAffecting((OctetString)value);
        	break;
        case idxPerceivedSeverity: 
        	setPerceivedSeverity((OctetString)value);
        	break;
        case idxSpecificProblems: 
        	setSpecificProblems((OctetString)value);
        	break;
        case idxProposedRepairActions: 
        	setProposedRepairActions((OctetString)value);
        	break;
        case idxEventType: 
        	setEventType((OctetString)value);
        	break;
        case idxAcknowledgeIndication: 
        	setAcknowledgeIndication((OctetString)value);
        	break;
        case idxAcknowledgeTime: 
        	setAcknowledgeTime((OctetString)value);
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

  // Textual Definitions of MIB module DATAXALARMMODEL
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



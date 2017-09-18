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
import org.snmp4j.agent.request.SnmpRequest;
import org.snmp4j.log.LogAdapter;
import org.snmp4j.log.LogFactory;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.SMIConstants;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

import com.nms.snmp.ninteface.framework.MOHandlerMgr;
import com.nms.snmp.ninteface.framework.trap.SnmpNotifyMgr;
import com.nms.ui.manager.ExceptionManage;


//--AgentGen BEGIN=_IMPORT
//--AgentGen END

public class HisAlarm 
//--AgentGen BEGIN=_EXTENDS
//--AgentGen END
implements MOGroup 
//--AgentGen BEGIN=_IMPLEMENTS
//--AgentGen END
,MOServerLookupListener
{

  private static final LogAdapter LOGGER = 
      LogFactory.getLogger(HisAlarm.class);

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
  public static final OID oidHisAlarm =
    new OID(new int[] {  });

  // Identities
  // Scalars
  // Tables

  // Notifications

  // Enumerations




  // TextualConventions

  // Scalars

  // Tables
  public static final OID oidHisAlarmEntry = 
    new OID(new int[] { 1,3,6,1,4,1,44484,1,4,2,1 });

  // Index OID definitions
  public static final OID oidIndex =
    new OID(new int[] { 1,3,6,1,4,1,44484,1,4,2,1,1 });

  
//Notifications
  public static final OID oidHisAlarmEvent =
	    new OID(new int[] { 1,3,6,1,4,1,44484,1,4,1,2,1,2,1 });  
  
  public static final int colAlarmIndex = 1;
  // Column TC definitions for hisAlarmEntry:
    
  // Column sub-identifer definitions for hisAlarmEntry:
  public static final int colTime = 2;
  public static final int colFileInfo = 3;
  public static final int colFile = 4;

  // Column index definitions for hisAlarmEntry:
  public static final int idxTime = 0;
  public static final int idxFileInfo = 1;
  public static final int idxFile = 2;

  private MOTableSubIndex[] hisAlarmEntryIndexes;
  private MOTableIndex hisAlarmEntryIndex;
  
  private MOTable<HisAlarmEntryRow, MOColumn, MOTableModel<HisAlarmEntryRow>> hisAlarmEntry;
  private MOTableModel<HisAlarmEntryRow> hisAlarmEntryModel;
  


//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  /**
   * Constructs a HisAlarm instance without actually creating its
   * <code>ManagedObject</code> instances. This has to be done in a
   * sub-class constructor or after construction by calling 
   * {@link #createMO(MOFactory moFactory)}. 
   */
  protected HisAlarm() {
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

  /**
   * Constructs a HisAlarm instance and actually creates its
   * <code>ManagedObject</code> instances using the supplied 
   * <code>MOFactory</code> (by calling
   * {@link #createMO(MOFactory moFactory)}).
   * @param moFactory
   *    the <code>MOFactory</code> to be used to create the
   *    managed objects for this module.
   */
  public HisAlarm(MOFactory moFactory) {
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
    createHisAlarmEntry(moFactory);
  }



  public MOTable<HisAlarmEntryRow,MOColumn,MOTableModel<HisAlarmEntryRow>> getHisAlarmEntry() {
    return hisAlarmEntry;
  }


  @SuppressWarnings(value={"unchecked"})
  private void createHisAlarmEntry(MOFactory moFactory) {
    // Index definition
    hisAlarmEntryIndexes = 
      new MOTableSubIndex[] {
      moFactory.createSubIndex(oidIndex, 
                               SMIConstants.SYNTAX_INTEGER, 1, 1)    };

    hisAlarmEntryIndex = 
      moFactory.createIndex(hisAlarmEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
      public boolean isValidIndex(OID index) {
        boolean isValidIndex = true;
     //--AgentGen BEGIN=hisAlarmEntry::isValidIndex
     //--AgentGen END
        return isValidIndex;
      }
    });

    // Columns
    MOColumn[] hisAlarmEntryColumns = new MOColumn[3];
    hisAlarmEntryColumns[idxTime] = 
      new MOMutableColumn<OctetString>(colTime,
                          SMIConstants.SYNTAX_OCTET_STRING,
                          moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE),
                          (OctetString)null);
    ((MOMutableColumn)hisAlarmEntryColumns[idxTime]).
      addMOValueValidationListener(new TimeValidator());
    hisAlarmEntryColumns[idxFileInfo] = 
      moFactory.createColumn(colFileInfo, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    hisAlarmEntryColumns[idxFile] = 
      moFactory.createColumn(colFile, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    // Table model
    hisAlarmEntryModel = 
      moFactory.createTableModel(oidHisAlarmEntry,
                                 hisAlarmEntryIndex,
                                 hisAlarmEntryColumns);
    ((MOMutableTableModel<HisAlarmEntryRow>)hisAlarmEntryModel).setRowFactory(
      new HisAlarmEntryRowFactory());
    hisAlarmEntry = 
      moFactory.createTable(oidHisAlarmEntry,
                            hisAlarmEntryIndex,
                            hisAlarmEntryColumns,
                            hisAlarmEntryModel);
  }



  public void registerMOs(MOServer server, OctetString context) 
    throws DuplicateRegistrationException 
  {
    // Scalar Objects
    server.register(this.hisAlarmEntry, context);
    server.addLookupListener(this, this.hisAlarmEntry);
//--AgentGen BEGIN=_registerMOs
//--AgentGen END
  }

  public void unregisterMOs(MOServer server, OctetString context) {
    // Scalar Objects
    server.unregister(this.hisAlarmEntry, context);
//--AgentGen BEGIN=_unregisterMOs
//--AgentGen END
  }

  // Notifications

  // Scalars

  // Value Validators

  /**
   * The <code>TimeValidator</code> implements the value
   * validation for <code>Time</code>.
   */
  static class TimeValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=time::validate
     //--AgentGen END
    }
  }

  // Rows and Factories

  public class HisAlarmEntryRow extends DefaultMOMutableRow2PC {

     //--AgentGen BEGIN=hisAlarmEntry::RowMembers
     //--AgentGen END

    public HisAlarmEntryRow(OID index, Variable[] values) {
      super(index, values);
     //--AgentGen BEGIN=hisAlarmEntry::RowConstructor
     //--AgentGen END
    }
    
    public OctetString getTime() {
     //--AgentGen BEGIN=hisAlarmEntry::getTime
     //--AgentGen END
      return (OctetString) super.getValue(idxTime);
    }  
    
    public void setTime(OctetString newValue) {
     //--AgentGen BEGIN=hisAlarmEntry::setTime
     //--AgentGen END
      super.setValue(idxTime, newValue);
    }
    
    public OctetString getFileInfo() {
     //--AgentGen BEGIN=hisAlarmEntry::getFileInfo
     //--AgentGen END
      return (OctetString) super.getValue(idxFileInfo);
    }  
    
    public void setFileInfo(OctetString newValue) {
     //--AgentGen BEGIN=hisAlarmEntry::setFileInfo
     //--AgentGen END
      super.setValue(idxFileInfo, newValue);
    }
    
    public OctetString getFile() {
     //--AgentGen BEGIN=hisAlarmEntry::getFile
     //--AgentGen END
      return (OctetString) super.getValue(idxFile);
    }  
    
    public void setFile(OctetString newValue) {
     //--AgentGen BEGIN=hisAlarmEntry::setFile
     //--AgentGen END
      super.setValue(idxFile, newValue);
    }
    
    public Variable getValue(int column) {
     //--AgentGen BEGIN=hisAlarmEntry::RowGetValue
     //--AgentGen END
      switch(column) {
        case idxTime: 
        	return getTime();
        case idxFileInfo: 
        	return getFileInfo();
        case idxFile: 
        	return getFile();
        default:
          return super.getValue(column);
      }
    }
    
    public void setValue(int column, Variable value) {
     //--AgentGen BEGIN=hisAlarmEntry::RowSetValue
     //--AgentGen END
      switch(column) {
        case idxTime: 
        	setTime((OctetString)value);
        	break;
        case idxFileInfo: 
        	setFileInfo((OctetString)value);
        	break;
        case idxFile: 
        	setFile((OctetString)value);
        	break;
        default:
          super.setValue(column, value);
      }
    }

     //--AgentGen BEGIN=hisAlarmEntry::Row
     //--AgentGen END
  }
  
  class HisAlarmEntryRowFactory 
        implements MOTableRowFactory<HisAlarmEntryRow>
  {
    public synchronized HisAlarmEntryRow createRow(OID index, Variable[] values)
        throws UnsupportedOperationException 
    {
      HisAlarmEntryRow row = 
        new HisAlarmEntryRow(index, values);
     //--AgentGen BEGIN=hisAlarmEntry::createRow
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(HisAlarmEntryRow row) {
     //--AgentGen BEGIN=hisAlarmEntry::freeRow
     //--AgentGen END
    }
     //--AgentGen BEGIN=hisAlarmEntry::RowFactory
     //--AgentGen END
  }

//--AgentGen BEGIN=_METHODS
//--AgentGen END

  // Textual Definitions of MIB module HisAlarm
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
   if((event.getLookupResult() == this.hisAlarmEntry) &&(!MOQueryWithSource.isSameSource(event.getQuery(), event.getSource()))){
		if(!(event.getQuery() instanceof MOQueryWithSource)){
			return;
		}
		//201408261841,201408271851
		MOQueryWithSource query = ((MOQueryWithSource)event.getQuery());
		SnmpRequest request = (SnmpRequest)query.getSource();
		List<VariableBinding> vbList = request.getSource().getPDU().getBindingList(oidHisAlarmEntry);
		int actionType = request.getSource().getPDU().getType();
		//set
		if(query.isWriteAccessQuery()){
			try {
			 ExceptionManage.infor("hisalarmTABLE 开始", this.getClass());
			 MOHandlerMgr.setTableValue(hisAlarmEntry, vbList);
			 ExceptionManage.infor("已结束", this.getClass());
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
			//生成完文件之后给snmp上报一个文件生成的标准给众合网管
			ExceptionManage.infor("上报开始", this.getClass());
			OID alarmIndexOid = new OID(oidHisAlarmEvent);
			alarmIndexOid.append(colAlarmIndex);
			VariableBinding[] vbs = new VariableBinding[1];
			vbs[0] = new VariableBinding(alarmIndexOid, new OctetString("file is ready"));
			dataxAlarmEvent(SnmpNotifyMgr.getInstance().getAgent().getNotificationOriginator(),new OctetString(),vbs);
			ExceptionManage.infor("上报结束", this.getClass());
		}
		if(actionType == PDU.GETBULK || actionType == PDU.GET || actionType == PDU.GETNEXT){
			MOHandlerMgr.updateTableMO(hisAlarmEntry, vbList);
		}
	}
}
//用于上报一个准备好的通知
  public void dataxAlarmEvent(NotificationOriginator notificationOriginator, OctetString context, VariableBinding[] vbs) {
    notificationOriginator.notify(context, oidHisAlarmEvent, vbs);
   }
}



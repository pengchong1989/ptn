package com.nms.model.system.loginlog;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.nms.db.bean.system.loginlog.LoginLog;
import com.nms.db.dao.system.loginlog.LoginLogMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.ServiceFactory;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.util.Mybatis_DBManager;

/**表login_log
 * 登陆日志记录，操作DAO。
 * @author songyang
 *
 */
public class LoginLogServiece_Mb extends ObjectService_Mybatis{
	
	private final int MAXID=1;//上次登陆信息，是否成功
	private List<LoginLog> loginlogList=null;
	private LoginLog log=null;
	private LoginLogMapper loginLogMapper = null;
    public LoginLogMapper getMapper() {
		return loginLogMapper;
	}

	public void setMapper(LoginLogMapper loginLogMapper) {
		this.loginLogMapper = loginLogMapper;
	}

	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
  
	/**
	 * 新增 login_log(用户登陆日志表)
	 * 
	 * @param loginlog
	 *            实体
	 * @throws Exception
	 */	
	public LoginLog insertSuccessLoginLog(LoginLog loginlog)throws Exception{
		if (loginlog == null) {
			throw new Exception("userInst is null");
		}
		LoginLog log=null;
		int result=0;
		try {
			LoginLog log1 = new LoginLog();
			log1.setUser_id(loginlog.getUser_id());
			String date = DateUtil.getDate(DateUtil.FULLTIME);
			log1.setLoginTime(date);
			log1.setState(loginlog.getState());
			log1.setLoginState(loginlog.getLoginState());
			log1.setIP(InetAddress.getLocalHost().getHostAddress());
			result=loginLogMapper.insertLoginLog(log1);
			sqlSession.commit();
			if(result>0){
				log=log1;
			}			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return log;
	}
	
	/**
	 * 用户退出时，修改outTime，state
	 * @param loginlog
	 * @param logoutState 
	 * @throws Exception
	 */
	
	public int updateExitLoginLog(LoginLog loginlog, int logoutState)throws Exception{
		if (loginlog == null) {
			throw new Exception("loginlog is null");
		}
	
		int result=0;
		try {
			//MAXID =1.退出时只查看上一次登录的ID号。
			  loginlogList = new ArrayList<LoginLog>();
			  loginlogList =loginLogMapper.findState(loginlog, this.MAXID);			
			  if(loginlogList != null && loginlogList.size()>0){
				log=(LoginLog)loginlogList.get(0);
				log.setLogoutState(logoutState);
				String outTime=DateUtil.getDate(DateUtil.FULLTIME);
				log.setOutTime(outTime);
				result=loginLogMapper.updateExitLoginLog(log);	
				sqlSession.commit();
			  }
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			loginlogList=null;
		}
		return result;
	}
	
	/**
	 * 注销时调用
	 * 返回flag=true时已经登陆
	 * 提取loginTime
	 * @return
	 * @throws Exception
	 */
		public boolean findLoginTime(LoginLog loginlog)throws Exception{
			if (loginlog == null) 
			{
				throw new Exception("loginlog is null");
			}
			boolean flag=false;
			try {
				
				if(loginlog.getUser_id()>0)
				{
					if(loginlog.getLoginTime()!=null)
					{
						List<LoginLog> login = new ArrayList<LoginLog>();
						login=this.loginLogMapper.findLoginTime(loginlog);						
						if(login!=null && login.size()>0){							
						   LoginLog log =login.get(0);
							if(log!= null){										
								if(((null==log.getOutTime())||("".equals(log.getOutTime())))&&(log.getState()==1)){
									flag=true;
								}						
						    }
						}
						
					}
				}	
				
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}finally{
				log = null;
				
			}
			return flag;
		}
	
	/**
	 * 查询全部
	 * 在线用户
	 * @return List<SiteInst>集合
	 * @throws Exception
	 */
	public List<LoginLog> select(LoginLog loginlog) throws Exception {
		List<LoginLog> loginLogList = null;		
		int isSelect=0;
		try {
			loginLogList = new ArrayList<LoginLog>();
			if(loginlog.isSelect()){
				isSelect=1;
			}else{
				isSelect=0;
			}
			loginLogList = this.loginLogMapper.queryByCondition(loginlog,isSelect);
			if(loginLogList!=null && loginLogList.size()>0){
				for(int i=0;i<loginLogList.size();i++){
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String logtime =loginLogList.get(i).getOutTime();
					Date logindate = df.parse(loginLogList.get(i).getLoginTime().trim());
					if (null == logtime || "".equals(logtime)) {
						loginLogList.get(i).setOnLineTime("");
					} else {
						Date outdate = df.parse(loginLogList.get(i).getOutTime().trim());				
						long time = outdate.getTime() - logindate.getTime();
	
						long hour = time / (60 * 60 * 1000);
						long minute = (time - hour * 60 * 60 * 1000) / (60 * 1000);
						long second = (time - hour * 60 * 60 * 1000 - minute * 60 * 1000) / 1000;
						if (second >= 60) {
							second = second % 60;
							minute += second / 60;
						}
						if (minute >= 60) {
							minute = minute % 60;
							hour += minute / 60;
						}
						String sh = "";
						String sm = "";
						String ss = "";
						if (hour < 10) {
							sh = "0" + String.valueOf(hour);
						} else {
							sh = String.valueOf(hour);
						}
						if (minute < 10) {
							sm = "0" + String.valueOf(minute);
						} else {
							sm = String.valueOf(minute);
						}
						if (second < 10) {
							ss = "0" + String.valueOf(second);
						} else {
							ss = String.valueOf(second);
						}
						loginLogList.get(i).setOnLineTime(sh + ":" + sm + ":" + ss);
					}
					
				 }
			}			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return loginLogList;
	}
	/**
	 * 移除 网元的shi失败的状态
	 * @param removeTime
	 * @return
	 * @throws Exception
	 */
	public void deleteBySite(LoginLog loginlog)throws Exception{
		try {
			this.loginLogMapper.deleteFailState(loginlog);
			this.sqlSession.commit();
		} catch (Exception e) { 
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 查看在线用户
	 * @return
	 * @throws Exception
	 */
	public List<LoginLog> selectOnLine() throws Exception {
		List<LoginLog> loginLogList = null;
		try {
			loginLogList=new ArrayList<LoginLog>();
			loginLogList = this.loginLogMapper.selectOnLine();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return loginLogList;
	}
	
	
	/**
	 * 
	 * 返回flag=true时已经登陆
	 * @return
	 * @throws Exception
	 */
		public boolean findLoginLog(LoginLog loginlog)throws Exception{
			if (loginlog == null) {
				throw new Exception("loginlog is null");
			}
			boolean flag=false;
			try {
				//MAXID =1.只查看上一次登录的ID号。
					if(loginlog.getUser_id()>0){
						loginlogList= new ArrayList<LoginLog>();
						loginlogList=this.loginLogMapper.findState(loginlog,this.MAXID);
						if(loginlogList!=null && loginlogList.size()>0){
							log=(LoginLog)loginlogList.get(0);		
							if(((null==log.getOutTime())||("".equals(log.getOutTime())))&&(log.getState()==1)){
								flag=true;					
							}
						}			
					}				
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}finally{
				log=null;
				loginlogList=null;
			}
			return flag;
		}
		
		/**
		 * 移除  某一时间 以前的  登陆信息
		 * @param removeTime
		 * @return
		 * @throws Exception
		 */
		public int delete(String removeTime)throws Exception{
			int result=0;
			List<LoginLog> loginLogList=null;
			List<Integer> idList=null;
			try {
				//查询在线用户
				loginLogList = new ArrayList<LoginLog>();
				loginLogList=this.loginLogMapper.selectOnLine();
				if(loginLogList!=null && loginLogList.size()>0){
					idList = new ArrayList<Integer>();
					for(int i=0;i<loginLogList.size();i++){
						idList.add(loginLogList.get(i).getId());
					}
				  
				}
				 result = this.loginLogMapper.deleteByIdsAndTime(idList,removeTime);
				 this.sqlSession.commit();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
			return result;
		}
		
		/**
		 * 查看日志总数
		 * @return
		 * @throws Exception
		 */
		public int selectLogCount() throws Exception {
	        int count = 0;
			try {
				count = this.loginLogMapper.selectLogCount();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
			return count;
		}
		
		
		/**
		 * 根据 -- 操作日志记录表--主键集合，批量删除
		 * 
		 * @param idList
		 * @return
		 * @throws Exception
		 */
		public int delete(List<Integer> idList) throws Exception {
			int result = 0;
			try {
				if (idList == null || idList.size() == 0) {
					return 0;
				}
				result = this.loginLogMapper.deleteByIds(idList);
				 this.sqlSession.commit();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
			return result;
		}
		public static void main(String[] args) {
			try {
				Mybatis_DBManager.init("10.18.1.5");
				ConstantUtil.serviceFactory = new ServiceFactory();
				LoginLogServiece_Mb loginLogServiece_Mb = (LoginLogServiece_Mb) ConstantUtil.serviceFactory.newService_MB(Services.LOGINLOGSERVIECE);
				LoginLog log=new LoginLog();
				log.setUser_id(1);
				log.setLoginTime("2015-08-26 16:11:17");
				LoginLog log1=new LoginLog();
				for (int i = 0; i < 10; i++) {					
					log1=loginLogServiece_Mb.getMapper().findLoginTime(log).get(0);
					System.out.println(log1.getOutTime()+"kkk"+log1.getLogoutState());
					Thread.sleep(2000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.exit(0);
			}
		}
}
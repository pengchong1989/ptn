package com.nms.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.ibatis.common.resources.Resources;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.nms.ui.manager.ExceptionManage;


/**
 * 初始化Mybatis
 * @author Administrator
 *
 */
public class Mybatis_DBManager {
	
	public static Mybatis_DBManager mybatis_DBManager;
	private static SqlSessionFactory sqlSessionFactory;
	private ComboPooledDataSource dataSource;
	public Mybatis_DBManager(String ip) {
		try {
			
			//加载数据库的IP信息
			Properties props = new Properties();
			InputStream propsIs = Mybatis_DBManager.class.getClassLoader().getResourceAsStream("config/config.properties");
			props.load(propsIs);
			props.setProperty("jdbc.url", props.getProperty("jdbc.url").replace("{?}", ip));
			String resource ="conf.xml";
			//加载mybatis 的配置文件（它也加载关联的映射文件）
			Reader reader = Resources.getResourceAsReader(resource);
			//构建sqlSession 的工厂
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader,props);
			dataSource=(ComboPooledDataSource) sqlSessionFactory.getConfiguration().getEnvironment().getDataSource();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化
	 * @param ip
	 */
	public static void init(String ip){
		if(mybatis_DBManager == null){
			mybatis_DBManager = new Mybatis_DBManager(ip);
		}
	}
	
	public final static Mybatis_DBManager getInstance() throws Exception{
		if(null==mybatis_DBManager){
			throw new Exception("uninitialized");
		}
		return mybatis_DBManager;
	}
	
	public final Connection getConnection() throws SQLException, Exception {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			ExceptionManage.infor("已经用了的最大连接数"+Mybatis_DBManager.getInstance().getDataSource().getNumBusyConnections(), this.getClass());
			throw new RuntimeException("无法从数据源获取连接 ", e);
		}
	}
	public ComboPooledDataSource getDataSource(){		
		return this.dataSource;
	}
	/**
	 * 获取数据库连接
	 * @return
	 */
	public static SqlSession getSqlSession(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
	
	public static void main(String[] args) throws Exception {
//		Mybatis_DBManager.init("10.18.1.5");
//		ConstantUtil.serviceFactory = new ServiceFactory();
//		LspInfoService_MB siteServiceMB = (LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO);
//		LoginLog log=new LoginLog();
//		log.setUser_id(1);
//		log.setLoginTime("2015-08-26 14:55:01");
//		Lsp lsp = new Lsp();
//		lsp.setSegmentId(16);
//		List<Lsp> lspList = siteServiceMB.select(lsp);
//	
	}
}

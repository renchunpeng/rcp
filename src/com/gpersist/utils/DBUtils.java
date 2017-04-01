package com.gpersist.utils;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBUtils {
	private static SqlSessionFactory factory;
  private static SqlSession sqlSession = null;
  
  static {
    try {
//    	Reader reader = Resources.getResourceAsReader(PropertiesUtils.pps.getProperty(Consts.CONFIG_DATABASE_FILE));
    	Reader reader = Resources.getResourceAsReader("sqlgpersist.xml");

//    	factory = new SqlSessionFactoryBuilder().build(reader, PropertiesUtils.pps.getProperty(Consts.CONFIG_DATABASE_ENV));
    	factory = new SqlSessionFactoryBuilder().build(reader, "gpersistsql");
    	reader.close();
    } catch (Exception e) {
    	e.printStackTrace();
    }
  }
  
  public static SqlSession getFactory() {
  	return factory.openSession();
  }
  
  public static SqlSession getSession() {
  	if(sqlSession != null){
  		sqlSession = factory.openSession();
  	}
	
  	return sqlSession;
  }
  
  public static <T> T getMapper(SqlSession session, Class<T> mapper) {
  	return (T) session.getMapper(mapper);
  }
  
  public static <T> T getMapper(Class<T> mapper) {
    SqlSession session = getSession();
    return (T) session.getMapper(mapper);
  }
  
  public static void commit(){
  	sqlSession.commit();
  }
  
  public static void rollback(){
  	sqlSession.rollback();
  }
  
  public static void close(){
  	if(sqlSession != null){
  		sqlSession.close();
  		sqlSession = null;
  	}
  }
  
  public static void clearCache() {
  	if (sqlSession != null)
  		sqlSession.clearCache();
  }
}

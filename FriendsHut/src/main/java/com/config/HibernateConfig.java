package com.config;


import java.util.Properties;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@ComponentScan("com")
public class HibernateConfig {


	public static SessionFactory sessionFactory = null;
	
	//Creating a DataSource Bean
	@Bean("h2DataSource")
	public DataSource getH2DataSource()
	{
		DriverManagerDataSource driverMgrDataSource=new DriverManagerDataSource();
		driverMgrDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		driverMgrDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		driverMgrDataSource.setUsername("ch");
		driverMgrDataSource.setPassword("ch");
		return driverMgrDataSource;
	}
	
	//Session Factory Bean Created.
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory()
	{
		LocalSessionFactoryBuilder localSessionFacBuilder=null;
	
		try{
		Properties hibernateProperties=new Properties();
		hibernateProperties.put("hibernate.temp.use_jdbc_metadata_defaults","false");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto","update");
		hibernateProperties.put("hibernate.show_sql", "true");
		hibernateProperties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		
		localSessionFacBuilder=new LocalSessionFactoryBuilder(getH2DataSource());
		localSessionFacBuilder.addProperties(hibernateProperties);
		//localSessionFacBuilder.scanPackages("com.Model");
		/*localSessionFacBuilder.addAnnotatedClass(User.class);
		localSessionFacBuilder.addAnnotatedClass(UserProfile.class);
		localSessionFacBuilder.addAnnotatedClass(Category.class);
		localSessionFacBuilder.addAnnotatedClass(Supplier.class);
		localSessionFacBuilder.addAnnotatedClass(Product.class);
		localSessionFacBuilder.addAnnotatedClass(Cart.class);
		localSessionFacBuilder.addAnnotatedClass(Order.class);*/
		
		sessionFactory=localSessionFacBuilder.buildSessionFactory();
		System.out.println("Session Factory Object Created");
		
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());
		}
		return sessionFactory;
		
	}
	
	//Transaction Bean Object
	@Autowired 
	@Bean //(name="txName")
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager hibernateTranMgr=new HibernateTransactionManager();
		hibernateTranMgr.setSessionFactory(sessionFactory);
		return hibernateTranMgr;
	}
	
}
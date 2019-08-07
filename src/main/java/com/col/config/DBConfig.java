package com.col.config;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;
import javax.persistence.SynchronizationType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;
import javax.sql.DataSource;

import org.hibernate.Cache;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.StatelessSessionBuilder;
import org.hibernate.TypeHelper;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.col.model.Blog;
import com.col.model.BlogComment;
import com.col.model.Friend;
import com.col.model.Job;
import com.col.model.UserDetails;

@Configuration
@ComponentScan("com.col")
@EnableTransactionManagement
public class DBConfig {
	
	public DataSource getOracleDataSourse() {
		DriverManagerDataSource dataSourse
		=new DriverManagerDataSource();
		dataSourse.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSourse.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSourse.setUsername("kuhudb");
		dataSourse.setPassword("kuhu");
		System.out.println("========data sourse created==========");
		return dataSourse;
	}
	
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory() {
		//Configuration config=new Configuration();
		Properties hibernateProp=new Properties();
		hibernateProp.put("hibernate.hbm2ddl.auto", "update");
		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		/*hibernateProp.put(Environment.HBM2DDL_AUTO, "create-update");
		hibernateProp.put(Environment.d, "org.hibernate.dilect.Oracle11gDilect");*/
		LocalSessionFactoryBuilder factoryBuilder
		=new LocalSessionFactoryBuilder(getOracleDataSourse());
		factoryBuilder.addProperties(hibernateProp);
		System.out.println("hello=========");
		factoryBuilder.addAnnotatedClass(Blog.class);
		factoryBuilder.addAnnotatedClass(UserDetails.class);
		factoryBuilder.addAnnotatedClass(Friend.class);
		factoryBuilder.addAnnotatedClass(BlogComment.class);
		factoryBuilder.addAnnotatedClass(Job.class);
		SessionFactory sessionFactory=factoryBuilder.buildSessionFactory();
		System.out.println("====Session factory created====");
		return sessionFactory;                              
	}
	
	@Bean
	public HibernateTransactionManager getHiberateTransactionManager(SessionFactory sessionFactory) {
		System.out.println("====hibernate transaction manager====");
		return new HibernateTransactionManager(sessionFactory);
	}
}

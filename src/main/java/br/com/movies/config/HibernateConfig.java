package br.com.movies.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableTransactionManagement
@PropertySource({ ("classpath:application.properties") })
@ComponentScan({ "com.*", ", br.com.*" })
@Slf4j
@Getter
@Setter
public class HibernateConfig {

	@Value("${db.driverClassName}")
	private String driverClassName;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String userName;
	@Value("${db.password}")
	private String password;
	@Value("${db.maxIdle}")
	private String maxIdle;
	@Value("${db.validationQuery}")
	private String validationQuery;
	@Value("${db.removeAbandonedTimeout}")
	private String removeAbandonedTimeout;
	@Value("${db.logAbandoned}")
	private String logAbandoned;

	@Value("${hibernate.dialect}")
	private String dialect;
	@Value("${hibernate.hbm2ddl.auto}")
	private String hbm2ddlAuto;
	@Value("${hibernate.show_sql}")
	private String showSql;
	@Value("${hibernate.connection.release_mode}")
	private String releaseMode;
	@Value("${hibernate.cache.use_second_level_cache}")
	private String levelCache;
	@Value("${hibernate.hikari.connectionTimeout}")
	private String connectionTimeout;
	@Value("${hibernate.hikari.minimumIdle}")
	private String minimumIdle;
	@Value("${hibernate.hikari.maximumPoolSize}")
	private String maximumPoolSize;
	@Value("${hibernate.hikari.idleTimeout}")
	private String idleTimeout;

	/**
	 * Data source.
	 */
	@Bean
	public DataSource dataSource() {

		log.info("[Configuration]-Data source");

		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		dataSource.setMaxIdle(Integer.valueOf(maxIdle));
		dataSource.setValidationQuery(validationQuery);
		dataSource.setRemoveAbandonedTimeout(Integer.valueOf(removeAbandonedTimeout));
		dataSource.setLogAbandoned(Boolean.parseBoolean(logAbandoned));

		return dataSource;
	}

	/**
	 * Hibernate properties
	 */
	Properties hibernateProperties() {
		return new Properties() {

			/**
			 * Serial version ID.
			 */
			private static final long serialVersionUID = 8190909342590347523L;

			{
				setProperty("hibernate.dialect", dialect);
				setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
				setProperty("hibernate.show_sql", showSql);
				setProperty("hibernate.connection.release_mode", releaseMode);
				setProperty("hibernate.cache.use_second_level_cache", levelCache);
				setProperty("hibernate.hikari.connectionTimeout", connectionTimeout);
				setProperty("hibernate.hikari.minimumIdle", minimumIdle);
				setProperty("hibernate.hikari.maximumPoolSize", maximumPoolSize);
				setProperty("hibernate.hikari.idleTimeout", idleTimeout);
			}
		};
	}

	/**
	 * Session factory.
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory() {

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.*", "br.com.*" });
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	/**
	 * Transaction.
	 * 
	 * Spring (Autowired).
	 */
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}

}

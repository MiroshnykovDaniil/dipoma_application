//package com.diploma.application.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.annotation.Resource;
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import com.mysql.cj.jdbc.Driver;
//
//import java.sql.SQLException;
//
//
//@Configuration
//@EnableJpaRepositories(basePackages = { "com.diploma.application.repository" })
//
//
//
//@PropertySource("classpath:application.yml")
//
//public class JpaConfig {
//
//
//    private static final String PROPERTY_NAME_DATABASE_DRIVER = "com.mysql.cj.jdbc.driver";
//    private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/diploma";
//    private static final String PROPERTY_NAME_DATABASE_USERNAME = "root";
//    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "";
//
//    private final Driver driver = new Driver();
//
//
//
//    @Resource
//    private Environment environment;
//
//    public JpaConfig() throws SQLException {
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//        dataSource.setDriverClassName(driver.getClass().getName());
//        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/diploma");
//        dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
//        dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);
//
//        return dataSource;
//    }
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        EntityManagerFactory factory = entityManagerFactory();
//        return new JpaTransactionManager(factory);
//    }
//
//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(Boolean.TRUE);
//        vendorAdapter.setShowSql(Boolean.TRUE);
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("com.sample.proj.domain");
//        factory.setDataSource(dataSource());
//        factory.afterPropertiesSet();
//        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
//        return factory.getObject();
//    }
//
//    @Bean
//    public HibernateExceptionTranslator hibernateExceptionTranslator() {
//        return new HibernateExceptionTranslator();
//    }
//
//
//
//}

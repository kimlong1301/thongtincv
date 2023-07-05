package com.god.store_manager.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.god.store_manager.repository.auth",
        entityManagerFactoryRef = "tStoreAuthEntityManagerFactory",
        transactionManagerRef = "tStoreAuthTransactionManager"
)
public class DatabaseTStoreAuthConfig {
    @Primary
    @Bean(name = "tStoreAuthDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.auth")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "tStoreAuthEntityManagerFactory")
    public LocalSessionFactoryBean entityManagerFactory(
            @Qualifier("tStoreAuthDataSource") DataSource dataSource
    ){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("com.god.store_manager.model.auth");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        return sessionFactoryBean;
    }

    @Primary
    @Bean(name = "tStoreAuthTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("tStoreAuthEntityManagerFactory") SessionFactory sessionFactory
            ){
        return new HibernateTransactionManager(sessionFactory);
    }
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }
}

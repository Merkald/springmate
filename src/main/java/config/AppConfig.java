package config;

import java.util.Properties;
import javax.sql.DataSource;
import model.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@ComponentScan(basePackages = {
        "dao","service"
})
public class AppConfig {
    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/springmate?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("mate");
        return dataSource;
    }

    @Bean
    public Properties getProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql","true");
        properties.put("hibernate.hbm2ddl.auto","create-drop");
        return properties;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setHibernateProperties(getProperties());
        sessionFactory.setAnnotatedClasses(User.class);
        return sessionFactory;
    }
}

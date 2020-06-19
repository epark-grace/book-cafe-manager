package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig implements TransactionManagementConfigurer {

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig("/application.properties");
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
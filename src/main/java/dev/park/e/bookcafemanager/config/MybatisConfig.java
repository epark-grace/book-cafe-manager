package dev.park.e.bookcafemanager.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan("dev.park.e.bookcafemanager.mapper")
public class MybatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("mapper/*Mapper.xml"));
        SqlSessionFactory sqlSessionFactory = factoryBean.getObject();
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        org.apache.ibatis.logging.LogFactory.useSlf4jLogging();
        return sqlSessionFactory;
    }
}
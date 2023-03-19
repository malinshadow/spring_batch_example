package com.example.springbatch.config;

import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Data
@Configuration
@ConfigurationProperties(prefix = "datasource.source1", ignoreUnknownFields = false)
@PropertySource(value = "classpath:commonConfig.yml", encoding = "utf-8", factory = YamlPropertySourceFactory.class)
@MapperScan(basePackages = {
        "com.example.springbatch.mapper.source1" }, sqlSessionFactoryRef = "sqlSessionFactory")

public class DataSource1Config {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    /**
     * generate datasource.
     * 
     * @return datasource
     */
    @Bean(name = "source1DataSource")
    @Primary
    public DataSource getDateSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * generate SqlSessionFactory.
     * 
     * @param DataSource datasource
     * @return SqlSessionFactory
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("source1DataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCallSettersOnNulls(true);
        bean.setConfiguration(configuration);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:com/example/springbatch/mapper/source1/*.xml"));
        return bean.getObject();
    }

    /**
     * sqlSessionTemplate
     * 
     * @param SqlSessionFactory sessionfactory
     * @return SqlSessionTemplate
     */
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("sqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}

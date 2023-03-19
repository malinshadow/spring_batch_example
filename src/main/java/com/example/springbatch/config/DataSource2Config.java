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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Data
@Configuration
@ConfigurationProperties(prefix = "datasource.source2", ignoreUnknownFields = false)
@PropertySource(value = "classpath:commonConfig.yml", encoding = "utf-8", factory = YamlPropertySourceFactory.class)
@MapperScan(basePackages = {"com.example.springbatch.source2" }, sqlSessionFactoryRef = "source2SqlSessionFactory")

public class DataSource2Config {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    /**
     * generate datasource.
     * 
     * @return datasource
     */
    @Bean(name = "source2DataSource")
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
    @Bean(name = "source2SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("source2DataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCallSettersOnNulls(true);
        bean.setConfiguration(configuration);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:com/example/springbatch/mapper/source2/*.xml"));
        return bean.getObject();
    }

    /**
     * sqlSessionTemplate
     * 
     * @param SqlSessionFactory sessionfactory
     * @return SqlSessionTemplate
     */
    @Bean(name = "source2SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("source2SqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}

package cn.djjboom.dynamic_datasource.config;

import cn.djjboom.dynamic_datasource.datasource.DataSourceName;
import cn.djjboom.dynamic_datasource.datasource.DynamicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author djj
 * @Date 2019/7/3
 * @Time 22:41
 */
@Configuration
@MapperScan("cn.djjboom.dynamic_datasource.mapper")
public class MybatisConfig {

    @Resource(name = DataSourceName.MASTER)
    DataSource masterDataSource;

    @Resource(name = DataSourceName.SLAVE)
    DataSource slaveDataSource;

    /**
     * 配置动态数据源dynamicDataSource
     * @return
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        Map targetDataSources = new HashMap(2);
        targetDataSources.put(DataSourceName.MASTER,masterDataSource);
        targetDataSources.put(DataSourceName.SLAVE,slaveDataSource);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    /**
     * 配置SqlSessionFactoryBean
     * @param dataSource
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dynamicDataSource") DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

}

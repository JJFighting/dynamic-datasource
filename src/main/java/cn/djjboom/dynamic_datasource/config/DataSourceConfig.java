package cn.djjboom.dynamic_datasource.config;

import cn.djjboom.dynamic_datasource.datasource.DataSourceName;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author djj
 * @Date 2019/7/3
 * @Time 22:31
 */
@Configuration
public class DataSourceConfig {

    @Bean(destroyMethod = "close",name = DataSourceName.MASTER)
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(destroyMethod = "close",name = DataSourceName.SLAVE)
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource(){
        return DruidDataSourceBuilder.create().build();
    }
}

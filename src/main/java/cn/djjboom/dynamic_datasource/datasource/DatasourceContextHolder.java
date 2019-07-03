package cn.djjboom.dynamic_datasource.datasource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author djj
 * @Date 2019/7/3
 * @Time 23:15
 */
@Slf4j
public class DatasourceContextHolder {
    public static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSource(String dataSourceName){
        log.debug("切换数据库 {}",dataSourceName);
        contextHolder.set(dataSourceName);
    }

    public static String getDataSource(){
        log.debug("获得数据源名");
        return contextHolder.get();
    }

    public static void removeDataSource(){
        log.debug("清除数据源名");
         contextHolder.remove();
    }
}

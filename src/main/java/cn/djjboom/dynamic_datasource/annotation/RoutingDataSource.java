package cn.djjboom.dynamic_datasource.annotation;

import cn.djjboom.dynamic_datasource.datasource.DataSourceName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author djj
 * @Date 2019/7/3
 * @Time 23:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RoutingDataSource {
    /**
     *
     * @return datasource key映射
     */
    String value() default DataSourceName.MASTER;
}

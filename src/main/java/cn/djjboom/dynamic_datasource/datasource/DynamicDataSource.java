package cn.djjboom.dynamic_datasource.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author djj
 * @Date 2019/7/3
 * @Time 23:08
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DatasourceContextHolder.getDataSource();
    }
}

package com.yufenghui.mybatis.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 数据源 工厂
 *
 * @author yufenghui
 * @date 2022/6/25 22:41
 */
public interface DataSourceFactory {

    /**
     * 设置属性
     * @param props
     */
    void setProperties(Properties props);

    /**
     * 获取数据源
     * @return DataSource
     */
    DataSource getDataSource();

}

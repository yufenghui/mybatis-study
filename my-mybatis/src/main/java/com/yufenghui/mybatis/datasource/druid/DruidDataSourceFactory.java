package com.yufenghui.mybatis.datasource.druid;

import com.yufenghui.mybatis.datasource.DataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Druid 数据源 工厂
 *
 * @author yufenghui
 * @date 2022/6/25 22:43
 */
public class DruidDataSourceFactory implements DataSourceFactory {

    private static final Logger log = LoggerFactory.getLogger(DruidDataSourceFactory.class);

    protected Properties properties;

    protected DataSource dataSource;


    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
        try {
            this.dataSource = com.alibaba.druid.pool.DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            log.error("创建Druid数据源失败。", e);
        }
    }

    @Override
    public DataSource getDataSource() {
        return this.dataSource;
    }

}

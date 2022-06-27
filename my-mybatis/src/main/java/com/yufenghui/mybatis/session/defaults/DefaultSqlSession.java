package com.yufenghui.mybatis.session.defaults;

import cn.hutool.core.util.ReflectUtil;
import com.yufenghui.mybatis.exceptions.TooManyResultsException;
import com.yufenghui.mybatis.mapping.BoundSql;
import com.yufenghui.mybatis.mapping.Environment;
import com.yufenghui.mybatis.mapping.MappedStatement;
import com.yufenghui.mybatis.session.Configuration;
import com.yufenghui.mybatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * SqlSession 默认实现
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/22 14:15
 */
public class DefaultSqlSession implements SqlSession {

    private static final Logger log = LoggerFactory.getLogger(DefaultSqlSession.class);

    /**
     * 配置类
     */
    private final Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return selectOne(statement, null);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        try {
            MappedStatement mappedStatement = configuration.getMappedStatement(statement);
            Environment environment = configuration.getEnvironment();

            Connection connection = environment.getDataSource().getConnection();
            BoundSql boundSql = mappedStatement.getBoundSql();

            PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());
            Object[] parameterArray = (Object[]) parameter;
            preparedStatement.setLong(1, Long.parseLong(parameterArray[0].toString()));

            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> objList = resultSet2Obj(resultSet, Class.forName(mappedStatement.getResultType()));

            if (objList.size() == 1) {
                return objList.get(0);
            } else if (objList.size() > 1) {
                throw new TooManyResultsException("Expected one result (or null) to be returned by selectOne(), but found: " + objList.size());
            }
        } catch (Exception e) {
            log.error("执行数据库操作失败。", e);
        }

        return null;
    }

    private <T> List<T> resultSet2Obj(ResultSet resultSet, Class<?> resultType) throws SQLException {

        List<T> objList = new ArrayList<>();

        while (resultSet.next()) {
            T obj = (T) ReflectUtil.newInstance(resultType);

            Field[] fields = ReflectUtil.getFields(resultType);
            for (Field field :fields) {
                String name = field.getName();
                Object value = resultSet.getObject(name, field.getType());

                ReflectUtil.setFieldValue(obj, field, value);
            }

            objList.add(obj);
        }

        return objList;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

}

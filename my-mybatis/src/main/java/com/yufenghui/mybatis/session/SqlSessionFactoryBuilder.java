package com.yufenghui.mybatis.session;

import com.yufenghui.mybatis.builder.xml.XMLConfigBuilder;
import com.yufenghui.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * SqlSessionFactory 构建器
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/24 16:26
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder parser = new XMLConfigBuilder(reader);
        return build(parser.parse());
    }

    public SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }

}

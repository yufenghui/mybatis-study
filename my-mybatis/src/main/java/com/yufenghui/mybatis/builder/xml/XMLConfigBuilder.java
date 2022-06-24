package com.yufenghui.mybatis.builder.xml;

import cn.hutool.core.util.EnumUtil;
import com.yufenghui.mybatis.builder.BaseBuilder;
import com.yufenghui.mybatis.io.Resources;
import com.yufenghui.mybatis.mapping.MappedStatement;
import com.yufenghui.mybatis.mapping.SqlCommandType;
import com.yufenghui.mybatis.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.Reader;
import java.util.List;

/**
 * XML 配置构建器
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/24 16:04
 */
public class XMLConfigBuilder extends BaseBuilder {

    private Element root;

    public XMLConfigBuilder(Reader reader) {
        // 1. 调用父类初始化Configuration
        super(new Configuration());

        // 2. dom4j 处理 xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(reader);
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public Configuration parse() {
        try {
            // 解析映射器
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }

    private void mapperElement(Element mappers) throws Exception {
        List<Element> mapperList = mappers.elements("mapper");
        for (Element mapper : mapperList) {
            // 解析处理，具体参照源码
            String resource = mapper.attribute("resource").getValue();
            Reader reader = Resources.getResourceAsReader(resource);
            // dom4j 处理 xml
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(reader);
            root = document.getRootElement();

            // 注册Mapper映射器
            String namespace = root.attribute("namespace").getValue();
            configuration.addMapper(Resources.classForName(namespace));

            for (Element element : root.elements()) {
                String id = namespace + "." + element.attribute("id").getValue();

                SqlCommandType sqlCommandType = EnumUtil.fromString(SqlCommandType.class, element.getName().toUpperCase());
                String sql = element.getText();

                MappedStatement.Builder builder = new MappedStatement.Builder(configuration, id, sqlCommandType, sql);
                builder.parameterType(element.attribute("parameterType").getValue());
                builder.resultType(element.attribute("resultType").getValue());
                // 添加解析 SQL
                configuration.addMappedStatement(builder.build());
            }

        }

    }

}

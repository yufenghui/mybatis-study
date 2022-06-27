package com.yufenghui.mybatis.builder;

import com.yufenghui.mybatis.session.Configuration;
import com.yufenghui.mybatis.type.TypeAliasRegistry;

/**
 * 基类 构建器
 * <p/>
 *
 * @author yufenghui
 * @date 2022/6/24 16:04
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    protected final TypeAliasRegistry typeAliasRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = configuration.getTypeAliasRegistry();
    }

}

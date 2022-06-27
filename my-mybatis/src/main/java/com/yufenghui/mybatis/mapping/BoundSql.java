package com.yufenghui.mybatis.mapping;

import cn.hutool.core.util.ReUtil;

/**
 * 绑定的SQL,是从SqlSource而来，将动态内容都处理完成得到的SQL语句字符串，其中包括?,还有绑定的参数
 *
 * @author yufenghui
 * @date 2022/6/25 23:19
 */
public class BoundSql {

    private final String sql;

    private final String parameterType;

    private final String resultType;

    public BoundSql(String sql, String parameterType, String resultType) {

        String formatSql = ReUtil.replaceAll(sql, "\\#\\{\\w+\\}", "?");

        this.sql = formatSql;
        this.parameterType = parameterType;
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public String getParameterType() {
        return parameterType;
    }

    public String getResultType() {
        return resultType;
    }

}

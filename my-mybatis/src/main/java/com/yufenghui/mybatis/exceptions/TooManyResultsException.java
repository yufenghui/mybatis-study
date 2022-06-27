package com.yufenghui.mybatis.exceptions;

/**
 * 结果太多异常, 一般是预想select出一条记录，结果得到多于一条记录时会抛此异常
 *
 * @author yufenghui
 * @date 2022/6/26 9:55
 */
public class TooManyResultsException extends RuntimeException {

    public TooManyResultsException() {
    }

    public TooManyResultsException(String message) {
        super(message);
    }

}

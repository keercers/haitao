package com.thinvent.zhhd.task;

public interface IMQOperator {

    boolean ensureConnection();

    boolean releaseConnection();
}

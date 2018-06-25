package com.thinvent.zhhd.model;


import java.util.Date;
import java.util.Map;


public interface IAsyncResult {

    void setStatus(String status);

    /**
     * status状态 pending: 准备中, start: 开始, success: 成功, failed: 失败
     * @return
     */
    String getStatus();

    Map<String, Object> getParameters();

    String getMessageId();

    void setResult(Object result);

    Object getResult();

    void setErrorMessage(String errorMessage);

    String getErrorMessage();

    void setTargetId(String targetId);

    String getTargetId();

    Date getTimestamp();

    void complete();

    boolean start(String oldMessageId);

    boolean success();

    boolean failed();
}

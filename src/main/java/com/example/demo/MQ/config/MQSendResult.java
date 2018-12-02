package com.example.demo.MQ.config;

/**
 * mq发送结果
 * MQSendResult.java 1.0.0 2018/12/02  09:46
 * Copyright © 2014-2017,xulihuazj.com.All rights reserved
 * history :
 * 1. 2018/12/02  09:46 created by 徐礼华
 */
public class MQSendResult {

    /**
     * 是否发送成功
     */
    private boolean isSuccess;

    /**
     * 已发送消息的ID
     */
    private String messageId;

    /**
     * 已发送消息的主题
     */
    private String topic;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

}

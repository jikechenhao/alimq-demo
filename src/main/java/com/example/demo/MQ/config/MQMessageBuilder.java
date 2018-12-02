package com.example.demo.MQ.config;

/**
 * MQMessageBuilder.java 1.0.0 2018/12/02  09:46
 * Copyright © 2014-2017,xulihuazj.com.All rights reserved
 * history :
 * 1. 2018/12/02  09:46 created by 徐礼华
 */
public class MQMessageBuilder {

    private MQMessage mqMessage;

    public MQMessageBuilder() {
        mqMessage = new MQMessage();
    }

    public MQMessageBuilder setMessageId(String messageId) {
        this.mqMessage.setMessageId(messageId);
        return this;
    }

    public MQMessageBuilder setTopic(String topic) {
        this.mqMessage.setTopic(topic);
        return this;
    }

    public MQMessageBuilder setTag(String tag) {
        this.mqMessage.setTag(tag);
        return this;
    }


    public MQMessageBuilder setMessageKey(String messageKey) {
        this.mqMessage.setMessageKey(messageKey);
        return this;
    }


    public MQMessageBuilder setBody(String body) {
        this.mqMessage.setBody(body);
        return this;
    }

    public MQMessage build() {
        return this.mqMessage;
    }


}

package com.example.demo.MQ.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * MQConfigBase.java 1.0.0 2018/12/02  09:46
 * Copyright © 2014-2017,xulihuazj.com.All rights reserved
 * history :
 * 1. 2018/12/02  09:46 created by 徐礼华
 */
public class MQConfigBase {

    @Value("${aliyun_mq_accessKey}")
    protected String accessKey;

    @Value("${aliyun_mq_secretKey}")
    protected String secretKey;

    @Value("${aliyun_mq_send_timeout}")
    protected String sendMsgTimeoutMillis;

    @Value("${aliyun_mq_onsAddr}")
    protected String onsAddr;

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getSendMsgTimeoutMillis() {
        return sendMsgTimeoutMillis;
    }

    public String getOnsAddr() {
        return onsAddr;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setSendMsgTimeoutMillis(String sendMsgTimeoutMillis) {
        this.sendMsgTimeoutMillis = sendMsgTimeoutMillis;
    }

    public void setOnsAddr(String onsAddr) {
        this.onsAddr = onsAddr;
    }
}

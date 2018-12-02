package com.example.demo.MQ.producer;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.example.demo.MQ.config.MQConfigBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * ProducerConfig.java 1.0.0 2018/12/02  09:46
 * Copyright © 2014-2017,xulihuazj.com.All rights reserved
 * history :
 * 1. 2018/12/02  09:46 created by 徐礼华
 */
@Configuration
public class ProducerConfig extends MQConfigBase {

    private final String pmsTest1Producer = "pmsTest1Producer";

    @Value("${PID_pms_test1}")
    public String pidPmsTest1;

    @Bean(name = pmsTest1Producer, initMethod = "start", destroyMethod = "shutdown")
    public ProducerBean getPmsTest1Producer() {
        ProducerBean producer = new ProducerBean();
        Properties properties = this.getProducerProperties(pidPmsTest1);
        producer.setProperties(properties);
        return producer;
    }

    private Properties getProducerProperties(String pid) {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.AccessKey, accessKey);
        properties.setProperty(PropertyKeyConst.SecretKey, secretKey);
        // 生产者PID
        properties.setProperty(PropertyKeyConst.ProducerId, pid);
        // 超时时间
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, sendMsgTimeoutMillis);
        // 设置 TCP 接入域名，进入 MQ 控制台的生产者管理页面，在右侧操作列单击获取接入点获取
        // 此处以公有云公网地域接入点为例
        properties.setProperty(PropertyKeyConst.ONSAddr, onsAddr);
        return properties;
    }

}

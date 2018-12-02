package com.example.demo.MQ.subscription.impl;

import com.example.demo.MQ.MQSubscriptionComponent;
import com.example.demo.MQ.config.MQMessage;
import com.example.demo.MQ.subscription.AbstractSubscription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * PmsTest1Subscription.java 1.0.0 2018/12/02  09:46
 * Copyright © 2014-2017,xulihuazj.com.All rights reserved
 * history :
 * 1. 2018/12/02  09:46 created by 徐礼华
 */
@Component(value = "topic_pms_test1")
@MQSubscriptionComponent( topic = "topic_pms_test1")
public class PmsTest1Subscription extends AbstractSubscription {

    private Logger logger = LogManager.getLogger(PmsTest1Subscription.class);

    @Override
    public void consume(MQMessage message) {
        if (message != null) {
            try {
                String resultMessage = message.getBody();
                System.out.println("消费成功：" + resultMessage);
                /**
                 * TODO 实现自己的业务逻辑
                 */
                return;
            } catch (Exception ex) {
                logger.error("LeaseAuditSubscription exception：", ex);
                throw new RuntimeException("消费异常，重发消息");
            }
        }
    }

}

package com.example.demo.MQ.subscription;

import com.aliyun.openservices.ons.api.bean.Subscription;
import com.example.demo.MQ.config.MQMessage;

/**
 * 每个实现者都是一个观察者。订阅对象是Topic
 * AbstractSubscription.java 1.0.0 2018/12/02  09:46
 * Copyright © 2014-2017,xulihuazj.com.All rights reserved
 * history :
 * 1. 2018/12/02  09:46 created by 徐礼华
 */
public abstract class AbstractSubscription extends Subscription {


    /**
     * 多个消费者消费
     *
     * @param message
     */
    public abstract void consume(MQMessage message);
}

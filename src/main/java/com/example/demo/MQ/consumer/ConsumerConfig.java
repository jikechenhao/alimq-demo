package com.example.demo.MQ.consumer;

import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.PropertyValueConst;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.example.demo.MQ.config.MQConfigBase;
import com.example.demo.MQ.listener.MQConsumerListener;
import com.example.demo.MQ.subscription.AbstractSubscription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ConsumerConfig.java 1.0.0 2018/12/02  09:46
 * Copyright © 2014-2017,xulihuazj.com.All rights reserved
 * history :
 * 1. 2018/12/02  09:46 created by 徐礼华
 */
@Configuration
public class ConsumerConfig extends MQConfigBase {

    private Logger logger = LogManager.getLogger(ConsumerConfig.class);

    private final String pmsTest1Consumer = "pmsTest1Consumer";
    private final String pmsTest1ConsumerV2 = "pmsTest1ConsumerV2";

    // 顺序消息消费失败进行重试前的等待时间，单位(毫秒)
    private final String suspendTimeMillis = "15000";
    // 消息消费失败时的最大重试次数
    private final String maxReconsumeTimes = "20";
    private final String consumeTimeout = "5000";

    @Value("${cid_pms_test1}")
    private String cidPmsTest1;
    @Value("${cid_pms_test1_v2}")
    private String cidPmsTestV2;

    @Resource
    private MQConsumerListener mqConsumerListener;
    @Resource
    private Map<String, Subscription> subscriptionMap;

    @Bean(name = pmsTest1Consumer, initMethod = "start", destroyMethod = "shutdown")
    public ConsumerBean getPmsTest1Consumer() {
        ConsumerBean consumer = new ConsumerBean();
        Properties properties = this.getConsumerProperties(cidPmsTest1);
        consumer.setProperties(properties);
        // 消费者订阅Topic
        consumer.setSubscriptionTable(subscriptionTable());
        return consumer;
    }

    @Bean(name = pmsTest1ConsumerV2, initMethod = "start", destroyMethod = "shutdown")
    public ConsumerBean getPmsTest1ConsumerV2() {
        ConsumerBean consumer = new ConsumerBean();
        Properties properties = this.getConsumerProperties(cidPmsTestV2);
        consumer.setProperties(properties);
        // 消费者订阅Topic
        consumer.setSubscriptionTable(subscriptionTable());
        return consumer;
    }


    /**
     * 构造注册表
     *
     * @return
     */
    Map<Subscription, MessageListener> subscriptionTable() {
        // 注册表
        Map<Subscription, MessageListener> subscriptionTable;
        if (null != subscriptionMap) {
            logger.info("【MQ消息】【消费者注册】消息数量：" + subscriptionMap.size());
            subscriptionTable = new HashMap<>(subscriptionMap.size());
            for (Map.Entry<String, Subscription> subMap : subscriptionMap.entrySet()) {
                Subscription subscription = subMap.getValue();
                subscription.setTopic(subMap.getKey());
//                subscription.setExpression(MessageType.Normal_Msg.name());
                // 放入注册表
                subscriptionTable.put(subscription, mqConsumerListener);
                logger.info("【MQ消息】【消费者注册】消费者注册详情：subscription=" + subscription.toString() + "，subscription_class=" + subscription.getClass().getName());
                if (subscription instanceof AbstractSubscription) {
                    // 订阅者 注册到监听器中
                    mqConsumerListener.registerSubscription((AbstractSubscription) subscription);
                } else {
                    logger.info("【MQ消息】消费者类型非AbstractSubscription，将无法执行消息消费：" + subscription);
                }
            }
        } else {
            logger.info("【MQ消息】消费者无注册信息：" + subscriptionMap.size());
            subscriptionTable = new HashMap<>(1);
        }
        return subscriptionTable;

    }


    private Properties getConsumerProperties(String cid) {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.AccessKey, accessKey);
        properties.setProperty(PropertyKeyConst.SecretKey, secretKey);
        properties.setProperty(PropertyKeyConst.ConsumerId, cid);
        properties.setProperty(PropertyKeyConst.ONSAddr, onsAddr);
        properties.setProperty(PropertyKeyConst.ConsumeTimeout, consumeTimeout);
        properties.setProperty(PropertyKeyConst.SuspendTimeMillis, suspendTimeMillis);
        properties.setProperty(PropertyKeyConst.MaxReconsumeTimes, maxReconsumeTimes);
        // 消费者采用集群模式
        properties.setProperty(PropertyKeyConst.MessageModel, PropertyValueConst.BROADCASTING);
        return properties;
    }


}

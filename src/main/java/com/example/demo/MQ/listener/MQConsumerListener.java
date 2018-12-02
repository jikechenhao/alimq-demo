package com.example.demo.MQ.listener;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.example.demo.MQ.config.MQMessage;
import com.example.demo.MQ.config.MQMessageBuilder;
import com.example.demo.MQ.subscription.AbstractSubscription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 需要调用方保证消费的幂等性
 * MQConsumerListener.java 1.0.0 2018/12/02  09:46
 * Copyright © 2014-2017,xulihuazj.com.All rights reserved
 * history :
 * 1. 2018/12/02  09:46 created by 徐礼华
 */
@Component
public class MQConsumerListener implements MessageListener {

    private Logger logger = LogManager.getLogger(MQConsumerListener.class);

    // 所有订阅者（key：topic，value：订阅者）
    private Map<String, AbstractSubscription> subscriptionMap = new HashMap<>();

    @Override
    public Action consume(Message message, ConsumeContext consumeContext) {
        String resultMessage = "";
        try {
            resultMessage = new String(message.getBody(), "UTF-8");
            logger.info("收到订阅信息：" + resultMessage);
            // 如果订阅中心包含此topic
            if (subscriptionMap != null && subscriptionMap.containsKey(message.getTopic())) {
                // 封装消息
                MQMessage mqMessage = new MQMessageBuilder().setTopic(message.getTopic()).setMessageId(message.getMsgID())
                        .setMessageKey(message.getKey()).setTag(message.getTag()).setBody(resultMessage).build();
                // 消费
                subscriptionMap.get(message.getTopic()).consume(mqMessage);
                // 确认消费成功
                return Action.CommitMessage;
            } else {
                logger.error(String.format("topic:%0 无消费者", message.getTopic()));
                return Action.ReconsumeLater;
            }
        } catch (Exception e) {
            //消费失败
            logger.error("：消费失败" + resultMessage);
            return Action.ReconsumeLater;
        }
    }

    // 注册订阅
    public void registerSubscription(AbstractSubscription subscription) {
        subscriptionMap.put(subscription.getTopic(), subscription);
    }

}

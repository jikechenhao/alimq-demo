package com.example.demo.MQ.producer;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSONObject;
import com.example.demo.MQ.MQSendService;
import com.example.demo.MQ.config.MQSendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Test1MQSendServiceImpl.java 1.0.0 2018/12/02  09:46
 * Copyright © 2014-2017,xulihuazj.com.All rights reserved
 * history :
 * 1. 2018/12/02  09:46 created by 徐礼华
 */
@Service
public class Test1MQSendServiceImpl implements MQSendService {

    @Autowired
    @Qualifier("pmsTest1Producer")
    private Producer pmsTest1Producer;
    @Value("${PID_pms_test1}")
    public String pidPmsTest1;

    @Override
    public MQSendResult sendMsg(String content, String messageKey, String tag) {
        Message message = new Message(pidPmsTest1, tag, messageKey, JSONObject.toJSONBytes(content));
        SendResult sendResult = pmsTest1Producer.send(message);
        MQSendResult result = new MQSendResult();
        if (sendResult != null) {
            result.setMessageId(sendResult.getMessageId());
            result.setTopic(sendResult.getTopic());
        } else {
            result.setSuccess(false);
        }
        return result;
    }
}

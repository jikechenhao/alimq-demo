package com.example.demo.MQ;

import com.example.demo.MQ.config.MQSendResult;

/**
 * MQSendService.java 1.0.0 2018/12/02  09:46
 * Copyright © 2014-2017,xulihuazj.com.All rights reserved
 * history :
 * 1. 2018/12/02  09:46 created by 徐礼华
 */
public interface MQSendService {

    /**
     * 发送消息
     *
     * @param content 内容
     * @param  tag 二级标签
     * @return
     */
    MQSendResult sendMsg(String content, String messageKey, String tag);
}

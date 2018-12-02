package com.example.demo.MQ;

import java.lang.annotation.*;

/**
 * MQSubscriptionComponent.java 1.0.0 2018/12/02  09:46
 * Copyright © 2014-2017,xulihuazj.com.All rights reserved
 * history :
 * 1. 2018/12/02  09:46 created by 徐礼华
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MQSubscriptionComponent {
    String topic() default "";
}

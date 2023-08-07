package com.nflj.rabbitmq.rabbitMq.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.Resource;

/**
 * @Author: zp.wei
 * @DATE: 2020/11/17 14:26
 */
@Slf4j
public class DirectProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    private static final String exchangeName = "exchange.direct";

    private final String[] keys = {"orange", "black", "green"};

    public void send(int index) {
        int limitIndex = index % 3;
        String key = keys[limitIndex];
        String message = "Hello to " + key + ' ' + (index + 1);
        rabbitTemplate.convertAndSend(exchangeName, key, message);
        log.info(" [路由模式生产者发送消息] Sent '{}'", message);
    }
}

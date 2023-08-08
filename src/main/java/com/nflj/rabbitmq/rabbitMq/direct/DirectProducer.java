package com.nflj.rabbitmq.rabbitMq.direct;

import com.nflj.rabbitmq.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author: zp.wei
 * @DATE: 2020/11/17 14:26
 */
@Slf4j
@Configuration
public class DirectProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(int index) {
        int limitIndex = index % 3;
        String key = CommonConstants.DIRECT_KEYS[limitIndex];
        String message = "Hello to " + (index + 1);
        rabbitTemplate.convertAndSend(CommonConstants.DIRECT_EXCHANGE_NAME, key, message);
        log.info(" [路由模式{}生产者发送消息] Sent '{}'", key, message);
    }
}

package com.nflj.rabbitmq.rabbitMq.topic;

import com.nflj.rabbitmq.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author: zp.wei
 * @DATE: 2020/11/17 14:42
 */
@Slf4j
@Configuration
public class TopicProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;


    public void send(int index) {
        int limitIndex = index % CommonConstants.TOPIC_KEYS.length;
        String key = CommonConstants.TOPIC_KEYS[limitIndex];
        String message = "Hello to " + (index + 1);
        rabbitTemplate.convertAndSend(CommonConstants.TOPIC_EXCHANGE_NAME, key, message);
        log.info("[通配符{}发送消息] Sent '{}'", key, message);
    }
}

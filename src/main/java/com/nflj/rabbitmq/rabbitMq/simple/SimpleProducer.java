package com.nflj.rabbitmq.rabbitMq.simple;

import com.nflj.rabbitmq.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 生产者
 *
 * @Author: zp.wei
 * @DATE: 2020/11/17 13:14
 */
@Slf4j
@Configuration
public class SimpleProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 简单模式
     */
    public void send() {
        String message = "Hello World!";
        this.rabbitTemplate.convertAndSend(CommonConstants.SIMPLE_QUEUE_NAME, message);
        log.info(" [简单模式发送消息] Sent '{}'", message);
    }

}

package com.nflj.rabbitmq.rabbitMq.direct;

import com.nflj.rabbitmq.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
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
        log.info(" [路由模式{}生产者发送消息] Sent '{}'", key, message);
        rabbitTemplate.convertAndSend(CommonConstants.DIRECT_EXCHANGE_NAME, key, message, message1 -> {
            message1.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return message1;
        });
    }


    /**
     * 设置生产者消息确认监听，当消息成功发到交换机 ack = true，没有发送到交换机 ack = false
     */
    @PostConstruct
    public void enableConfirmCallback() {
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                log.error("生产者发送消息失败,失败原因:{}", cause);
            }
        });
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            log.info("路由键{}未路由到队列:{}", returnedMessage.getRoutingKey(),returnedMessage.getMessage());
        });
    }


}

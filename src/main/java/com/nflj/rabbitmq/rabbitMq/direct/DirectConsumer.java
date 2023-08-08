package com.nflj.rabbitmq.rabbitMq.direct;

import com.nflj.rabbitmq.constants.CommonConstants;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @Author: zp.wei
 * @DATE: 2020/11/17 14:27
 */
@Slf4j
@Configuration
public class DirectConsumer {


    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = CommonConstants.DIRECT_QUEUE_NAME),
            exchange = @Exchange(value = CommonConstants.DIRECT_EXCHANGE_NAME),
            key = {CommonConstants.DIRECT_QUEUE_KEY1, CommonConstants.DIRECT_QUEUE_KEY3}))
    public void receive1(Message message, Channel channel) {
        receive(message, channel, 1);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = CommonConstants.DIRECT_QUEUE_NAME),
            exchange = @Exchange(value = CommonConstants.DIRECT_EXCHANGE_NAME),
            key = {CommonConstants.DIRECT_QUEUE_KEY2, CommonConstants.DIRECT_QUEUE_KEY3}))
    public void receive2(Message message, Channel channel) {
        receive(message, channel, 2);
    }


    private void receive(Message message, Channel channel, int receiver) {
        log.info("[消费者{}接收消息] Received '{}'", receiver, new String(message.getBody()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

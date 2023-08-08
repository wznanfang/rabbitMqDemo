package com.nflj.rabbitmq.rabbitMq.fanout;

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
 * 消费者
 *
 * @Author: zp.wei
 * @DATE: 2020/11/17 13:16
 */
@Slf4j
@Configuration
public class FanoutConsumer {


    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = CommonConstants.FANOUT_QUEUE_NAME + 1),
            exchange = @Exchange(value = CommonConstants.FANOUT_EXCHANGE_NAME, type = CommonConstants.FANOUT_EXCHANGE)))
    public void receive1(Message message, Channel channel) {
        receive(message, channel, 1);
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = CommonConstants.FANOUT_QUEUE_NAME + 2),
            exchange = @Exchange(value = CommonConstants.FANOUT_EXCHANGE_NAME, type = CommonConstants.FANOUT_EXCHANGE)))
    public void receive2(Message message, Channel channel) {
        receive(message, channel, 2);
    }


    public void receive(Message message, Channel channel, Integer receiver) {
        log.info(" [消费者{}接收到消息] Received '{}'", receiver, new String(message.getBody()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

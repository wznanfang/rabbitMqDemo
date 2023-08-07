package com.nflj.rabbitmq.rabbitMq.work;

import com.nflj.rabbitmq.constants.CommonConstants;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
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
public class WorkConsumer1 {


    /**
     * 工作模式
     */
    @RabbitListener(queues = CommonConstants.WORK_QUEUE_NAME)
    public void receive(Message message, Channel channel) throws IOException {
        log.info(" [消费者{}接收到消息] Received '{}'", 1, new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}

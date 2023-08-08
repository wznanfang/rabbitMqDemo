package com.nflj.rabbitmq.rabbitMq.fanout;

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
public class FanoutProducer {

    @Resource
    private RabbitTemplate template;

    public void send(int index) {
        StringBuilder builder = new StringBuilder("发布订阅");
        int limitIndex = index % 3 + 1;
        for (int i = 0; i < limitIndex; i++) {
            builder.append('.');
        }
        builder.append(index + 1);
        String message = builder.toString();
        template.convertAndSend(CommonConstants.FANOUT_EXCHANGE_NAME, "", message);
        log.info(" [发布订阅模式生产者发送消息] Sent '{}'", message);
    }

}

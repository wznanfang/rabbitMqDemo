package com.nflj.rabbitmq.controller;

import com.nflj.rabbitmq.rabbitMq.direct.DirectProducer;
import com.nflj.rabbitmq.rabbitMq.fanout.FanoutProducer;
import com.nflj.rabbitmq.rabbitMq.simple.SimpleProducer;
import com.nflj.rabbitmq.rabbitMq.topic.TopicProducer;
import com.nflj.rabbitmq.rabbitMq.work.WorkProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: zp.wei
 * @DATE: 2021/06/30 13:16
 */
@Slf4j
@RestController
@RequestMapping("/rabbitMq")
public class RabbitController {

    @Resource
    private SimpleProducer simpleProducer;
    @Resource
    private WorkProducer workProducer;
    @Resource
    private FanoutProducer fanoutProducer;
    @Resource
    private DirectProducer directProducer;
    @Resource
    private TopicProducer topicProducer;


    /**
     * 简单模式
     *
     * @return
     */
    @GetMapping("/simple")
    public Object simpleTest() {
        try {
            for (int i = 0; i < 5; i++) {
                simpleProducer.send();
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }


    /**
     * 工作模式
     *
     * @return
     */
    @GetMapping("/work")
    public Object workTest() {
        try {
            for (int i = 0; i < 5; i++) {
                workProducer.send(i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }


    /**
     * 发布/订阅模式
     *
     * @return
     */
    @GetMapping("/fanout")
    public Object fanoutTest() {
        try {
            for (int i = 0; i < 5; i++) {
                fanoutProducer.send(i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "ok";
    }


    /**
     * 路由模式
     *
     * @return
     */
    @GetMapping("direct")
    public Object directTest() {
        try {
            for (int i = 0; i < 5; i++) {
                directProducer.send(i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }


    /**
     * 通配符模式
     *
     * @return
     */
    @GetMapping("/topic")
    public Object topicTest() {
        try {
            for (int i = 0; i < 5; i++) {
                topicProducer.send(i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }


}

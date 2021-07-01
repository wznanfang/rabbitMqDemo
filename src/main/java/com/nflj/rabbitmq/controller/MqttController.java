package com.nflj.rabbitmq.controller;

import com.nflj.rabbitmq.mqtt.MqttGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: zp.wei
 * @DATE: 2020/11/18 10:26
 */
@Slf4j
@RestController
@RequestMapping("/Mqtt")
public class MqttController {

    @Autowired
    private MqttGateway mqttGateway;


    /**
     * 向默认主题发送消息
     *
     * @param map
     * @return
     */
    @PostMapping("/defaultTopic")
    public Object sendToDefaultTopic(@RequestBody Map<String, String> map) {
        mqttGateway.sendToDefaultMqtt(map.get("payload"));
        return "ok";
    }


    /**
     * 向指定主题发送消息
     *
     * @param map
     * @return
     */
    @PostMapping("/assignMqtt")
    public Object AssignMqtt(@RequestBody Map<String, String> map) {
        mqttGateway.sendToAssignMqtt(map.get("topic"), map.get("payload"));
        log.info(map.get("topic"));
        log.info(map.get("payload"));
        return "ok";
    }


    /**
     * 向指定主题发送消息,并设置服务质量参数Qos
     *
     * @param map
     * @return
     */
    @PostMapping("/assignMqttAndQOS")
    public Object AssignMqttAndQOS(@RequestBody Map<String, String> map) {
        mqttGateway.sendToAssignMqttAndQOS(map.get("topic"), 1, map.get("payload"));
        log.info(map.get("topic"));
        log.info(map.get("payload"));
        return "ok";
    }

}

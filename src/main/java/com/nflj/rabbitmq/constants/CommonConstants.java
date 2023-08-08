package com.nflj.rabbitmq.constants;

/**
 * @author zp.wei
 * @date 2023/8/7 17:04
 */
public class CommonConstants {


    public static final String SIMPLE_QUEUE_NAME = "simple";

    public static final String WORK_QUEUE_NAME = "work";

    public static final String FANOUT_EXCHANGE = "fanout";
    public static final String FANOUT_EXCHANGE_NAME = "exchange.fanout";
    public static final String FANOUT_QUEUE_NAME = "queue.fanout";

    public static final String DIRECT_EXCHANGE_NAME = "exchange.direct";
    public static final String DIRECT_QUEUE_NAME = "queue.direct";
    public static final String[] DIRECT_KEYS = {"orange", "black", "green"};
    public static final String DIRECT_QUEUE_KEY1 = "orange";
    public static final String DIRECT_QUEUE_KEY2 = "black";
    public static final String DIRECT_QUEUE_KEY3 = "green";

    public static final String TOPIC_EXCHANGE = "topic";
    public static final String TOPIC_EXCHANGE_NAME = "exchange.topic";
    public static final String TOPIC_QUEUE_NAME = "queue.topic";
    public static final String[] TOPIC_KEYS = {"quick.orange.rabbit", "quick.orange.fox", "lazy.brown.rabbit", "lazy.pink.rabbit"};
    public static final String TOPIC_QUEUE_KEY1 = "*.orange.*";
    public static final String TOPIC_QUEUE_KEY2 = "*.*.rabbit";

}

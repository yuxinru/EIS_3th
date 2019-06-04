package com.trader.sender;


import com.trader.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

import static org.apache.kafka.common.requests.DeleteAclsResponse.log;

@Component
//@Slf4j
public class KafkaSender {

//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    private Gson gson = new GsonBuilder().create();
//
//    //发送消息方法
//    public void send() {
//        Message message = new Message();
//        message.setId(System.currentTimeMillis());
//        message.setMsg(UUID.randomUUID().toString());
//        message.setSendTime(new Date());
//        log.info("+++++++++++++++++++++  message = {}", gson.toJson(message));
//        kafkaTemplate.send("zhisheng", gson.toJson(message));
//    }
}
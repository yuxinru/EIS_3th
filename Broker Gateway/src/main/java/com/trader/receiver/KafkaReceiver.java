package com.trader.receiver;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.apache.kafka.common.requests.DeleteAclsResponse.log;

@Component
//@Slf4j
public class KafkaReceiver {

//    @KafkaListener(topics = {"zhisheng"})
//    public void listen(ConsumerRecord<?, ?> record) {
//
//        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
//
//        if (kafkaMessage.isPresent()) {
//
//            Object message = kafkaMessage.get();
//
//            log.info("----------------- record =" + record);
//            log.info("------------------ message =" + message);
//        }
//
//    }
}
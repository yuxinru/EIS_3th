package com.trader.config;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

@Configuration
public class ActiveMqConfig {

    @Bean(name = "broker1ConnectionFactory")
    @Primary
    public ActiveMQConnectionFactory firstConnectionFactory(
            @Value("${spring.broker1.activemq.broker-url}") String brokerUrl,
            @Value("${spring.broker1.activemq.user}") String username,
            @Value("${spring.broker1.activemq.password}") String password) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerUrl);
        factory.setUserName(username);
        factory.setPassword(password);
        return factory;
    }

    @Bean(name = "broker2ConnectionFactory")
    public ActiveMQConnectionFactory secondConnectionFactory(
            @Value("${spring.broker2.activemq.broker-url}") String brokerUrl,
            @Value("${spring.broker2.activemq.user}") String username,
            @Value("${spring.broker2.activemq.password}") String password) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerUrl);
        factory.setUserName(username);
        factory.setPassword(password);
        return factory;
    }

    @Bean(name = "broker1ActivemqTemplate")
    @Primary
    public JmsMessagingTemplate broker1ActivemqTemplate(
            @Qualifier("broker1ConnectionFactory") ActiveMQConnectionFactory connectionFactory) {
        JmsMessagingTemplate template = new JmsMessagingTemplate(connectionFactory);
        return template;
    }

    @Bean(name = "broker2ActivemqTemplate")
    public JmsMessagingTemplate broker2ActivemqTemplate(
            @Qualifier("broker2ConnectionFactory") ActiveMQConnectionFactory connectionFactory) {
        JmsMessagingTemplate template = new JmsMessagingTemplate(connectionFactory);
        return template;
    }

    @Bean(name = "broker1Factory")
    public JmsListenerContainerFactory broker1Factory(
            @Qualifier("broker1ConnectionFactory") ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean(name = "broker2Factory")
    public JmsListenerContainerFactory broker2Factory(
            @Qualifier("broker2ConnectionFactory") ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

}

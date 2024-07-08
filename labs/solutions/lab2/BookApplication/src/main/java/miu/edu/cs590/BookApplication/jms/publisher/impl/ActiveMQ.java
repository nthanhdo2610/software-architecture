package miu.edu.cs590.BookApplication.jms.publisher.impl;

import miu.edu.cs590.BookApplication.jms.publisher.MessageSender;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service("ActiveMQ")
public class ActiveMQ implements MessageSender {

    private final JmsTemplate jmsTemplate;

    public ActiveMQ(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(String destination, String message) {
        jmsTemplate.convertAndSend(destination, message);
    }
}

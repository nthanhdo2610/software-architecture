package miu.edu.cs590.BookApplication.jms.subscriber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import miu.edu.cs590.BookApplication.model.Book;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static miu.edu.cs590.BookApplication.jms.QueueConstant.ACTIVE_MQ_BOOK_QUEUE;

@Component
public class BookListener {

    @JmsListener(destination = ACTIVE_MQ_BOOK_QUEUE)
    public void receiveMessage(final String bookAsString) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Book book = objectMapper.readValue(bookAsString, Book.class);
            System.out.println("JMS receiver received message:" + book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

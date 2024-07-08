package miu.edu.cs590.BookApplication.jms.publisher;

public interface MessageSender {
    void send(String destination, String message);
}

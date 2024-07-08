package miu.edu.cs590.BookApplication.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import miu.edu.cs590.BookApplication.dao.BookDao;
import miu.edu.cs590.BookApplication.jms.publisher.MessageSender;
import miu.edu.cs590.BookApplication.model.Book;
import miu.edu.cs590.BookApplication.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

import static miu.edu.cs590.BookApplication.jms.QueueConstant.ACTIVE_MQ_BOOK_QUEUE;

@Service
public class BookServiceImpl implements BookService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final MessageSender jmsSender;
    private final BookDao bookDao;

    public BookServiceImpl(MessageSender messageSender, BookDao bookDao) {
        this.jmsSender = messageSender;
        this.bookDao = bookDao;
    }

    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    public Book getBook(String isbn) {
        return bookDao.findByIsbn(isbn);
    }

    public void addBook(Book book) {
        bookDao.save(book);
        try {
            jmsSender.send(ACTIVE_MQ_BOOK_QUEUE, mapper.writeValueAsString(book));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBook(Book book) {
        bookDao.save(book);
        try {
            jmsSender.send(ACTIVE_MQ_BOOK_QUEUE, mapper.writeValueAsString(book));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBook(String isbn) {
        Book book = bookDao.findByIsbn(isbn);
        bookDao.delete(isbn);
        try {
            jmsSender.send(ACTIVE_MQ_BOOK_QUEUE, mapper.writeValueAsString(book));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

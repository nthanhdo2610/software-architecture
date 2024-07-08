package miu.edu.cs590.BookApplication.dao.impl;

import miu.edu.cs590.BookApplication.dao.BookDao;
import miu.edu.cs590.BookApplication.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoImpl implements BookDao {

    private final Map<String, Book> bookStore = new HashMap<>();

    public List<Book> findAll() {
        return new ArrayList<>(bookStore.values());
    }

    public Book findByIsbn(String isbn) {
        return bookStore.get(isbn);
    }

    public void save(Book book) {
        bookStore.put(book.getIsbn(), book);
    }

    public void delete(String isbn) {
        bookStore.remove(isbn);
    }
}

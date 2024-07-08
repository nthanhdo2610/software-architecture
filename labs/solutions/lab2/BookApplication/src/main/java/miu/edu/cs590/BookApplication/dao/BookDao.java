package miu.edu.cs590.BookApplication.dao;

import miu.edu.cs590.BookApplication.model.Book;

import java.util.List;

public interface BookDao {

    List<Book> findAll();
    Book findByIsbn(String isbn);
    void save(Book book);
    void delete(String isbn);

}

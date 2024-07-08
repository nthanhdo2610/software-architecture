package miu.edu.cs590.BookApplication.service;

import miu.edu.cs590.BookApplication.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    Book getBook(String isbn);
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(String isbn);

}

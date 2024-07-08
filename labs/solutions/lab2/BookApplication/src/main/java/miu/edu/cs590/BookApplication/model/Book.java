package miu.edu.cs590.BookApplication.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {
    private String isbn;
    private String author;
    private String title;
    private double price;
}

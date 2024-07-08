package miu.edu.cs590.BookClient;

import com.github.javafaker.Faker;
import miu.edu.cs590.BookClient.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BookClientApplication implements CommandLineRunner {

    @Autowired
    private RestOperations restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(BookClientApplication.class, args);
    }

    @Override
    public void run(String... args) {

        Faker faker = new Faker();

        String serverUrl = "http://localhost:8080/books";

        // Add a book
        Book book1 = new Book(
                faker.number().digits(10),
                faker.book().author(),
                faker.book().title(),
                Double.parseDouble(faker.commerce().price(5.0, 25.0)));
        restTemplate.postForLocation(serverUrl, book1);
        System.out.println("Book 1: " + book1);
        Book book2 = new Book(
                faker.number().digits(10),
                faker.book().author(),
                faker.book().title(),
                Double.parseDouble(faker.commerce().price(5.0, 25.0)));
        System.out.println("Book 2: " + book2);
        restTemplate.postForLocation(serverUrl, book2);

        // Get a book by ISBN
        Book book = restTemplate.getForObject(serverUrl + "/{isbn}", Book.class, book1.getIsbn());
        System.out.println("Retrieved Book: " + book.getTitle());

        // Update a book
        book.setPrice(9.99);
        restTemplate.put(serverUrl, book);

        // Get all books
        Book[] books = restTemplate.getForObject(serverUrl, Book[].class);
        System.out.println("Listing all books:");
        for (Book b : books) {
            System.out.println(b.getTitle() + " by " + b.getAuthor());
        }

        // Delete a book
        restTemplate.delete(serverUrl + "/{isbn}", "1234567890");
    }

    @Bean
    RestOperations restTemplate() {
        return new RestTemplate();
    }
}

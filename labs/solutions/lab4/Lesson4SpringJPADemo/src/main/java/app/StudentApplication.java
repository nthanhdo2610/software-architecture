package app;

import domain.Address;
import domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import repositories.StudentRepository;

@SpringBootApplication
@ComponentScan(basePackages = {"*"})
public class StudentApplication implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Create addresses
        Address address1 = new Address("123 Main St", "New York", "10001");
        Address address2 = new Address("456 Maple St", "Los Angeles", "90001");
        Address address3 = new Address("789 Oak St", "Chicago", "60601");
        Address address4 = new Address("101 Pine St", "Houston", "77001");
        Address address5 = new Address("202 Elm St", "Phoenix", "85001");

        // Create students
        Student student1 = new Student("John Doe", "1234567890", "john.doe@example.com", address1);
        Student student2 = new Student("Jane Smith", "0987654321", "jane.smith@example.com", address2);
        Student student3 = new Student("Alice Johnson", "1231231234", "alice.johnson@example.com", address3);
        Student student4 = new Student("Bob Brown", "3213214321", "bob.brown@example.com", address4);
        Student student5 = new Student("Charlie White", "2312312312", "charlie.white@example.com", address5);

        // Save students to database
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);
        studentRepository.save(student5);

        // Get all students
        System.out.println("----------- All Students ----------------");
        studentRepository.findAll().forEach(System.out::println);

        // Get students by name
        System.out.println("----------- Students with name 'John Doe' ----------------");
        studentRepository.findByName("John Doe").forEach(System.out::println);

        // Get student by phone number
        System.out.println("----------- Student with phone number '1234567890' ----------------");
        studentRepository.findByPhoneNumber("1234567890").ifPresent(System.out::println);

        // Get students by city
        System.out.println("----------- Students from city 'New York' ----------------");
        studentRepository.findByAddress_City("New York").forEach(System.out::println);

        // Update student
        Student student = studentRepository.findByPhoneNumber("1234567890").orElse(null);
        if (student != null) {
            student.setEmail("new.email@example.com");
            studentRepository.save(student);
            System.out.println("----------- Updated student ----------------");
            System.out.println(studentRepository.findByPhoneNumber("1234567890").get());
        }
    }
}

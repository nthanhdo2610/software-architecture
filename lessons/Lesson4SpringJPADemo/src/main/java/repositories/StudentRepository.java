package repositories;

import domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);
    Optional<Student> findByPhoneNumber(String phoneNumber);
    List<Student> findByAddress_City(String city);
}

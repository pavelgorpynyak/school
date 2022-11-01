package ruhogwartsschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ruhogwartsschool.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}

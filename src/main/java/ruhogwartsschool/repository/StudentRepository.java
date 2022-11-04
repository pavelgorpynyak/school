package ruhogwartsschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ruhogwartsschool.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    public Collection<Student> findStudentByAgeBetween( int min, int max );
}

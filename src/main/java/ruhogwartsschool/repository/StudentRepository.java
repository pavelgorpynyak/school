package ruhogwartsschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ruhogwartsschool.model.Student;
import ruhogwartsschool.model.StudentAvarageAge;
import ruhogwartsschool.model.StudentGetAmount;
import ruhogwartsschool.model.StudentLastInTheTurn;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    public Collection<Student> findStudentByAgeBetween( int min, int max );

    @Query(value = "SELECT COUNT(name)as name FROM student ", nativeQuery = true)
    List<StudentGetAmount> getAllStudentsQuantity();

    @Query(value = "SELECT AVG(age) as age FROM student", nativeQuery = true)
    List<StudentAvarageAge> getAvarageAgeByStudent();

    @Query(value = "SELECT * FROM student ORDER BY id desc LIMIT 5", nativeQuery = true)
    List<StudentLastInTheTurn> getStudentsByTurn();

}

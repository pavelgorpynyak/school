package ruhogwartsschool.service;

import org.springframework.stereotype.Service;
import ruhogwartsschool.exception.NotFoundException;
import ruhogwartsschool.model.Student;
import ruhogwartsschool.model.StudentAvarageAge;
import ruhogwartsschool.model.StudentGetAmount;
import ruhogwartsschool.model.StudentLastInTheTurn;
import ruhogwartsschool.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    public StudentService( StudentRepository studentRepository ) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent( Student student ) {
        return studentRepository.save(student);
    }

    public Student findStudent( long id ) {
        Optional<Student> searchingStudent = studentRepository.findById(id);
        if (searchingStudent.isEmpty()) {
            throw new NotFoundException(" Student not found");
        }
        return searchingStudent.get();
    }

    public Student editStudent( Student student ) {
        return studentRepository.save(student);
    }

    public void deleteStudent( long id ) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getStudentByAge( int ageMin, int ageMax ) {
        return studentRepository.findStudentByAgeBetween(ageMin, ageMax);
    }

    public List<StudentGetAmount> getAllStudentsQuantity() {
        return studentRepository.getAllStudentsQuantity();
    }

    public List<StudentAvarageAge> getAvarageAge() {
        return studentRepository.getAvarageAgeByStudent();
    }
    public List<StudentLastInTheTurn> getStudentLastTurn() {
        return studentRepository.getStudentsByTurn();
    }

}

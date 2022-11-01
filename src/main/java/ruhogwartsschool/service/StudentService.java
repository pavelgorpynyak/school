package ruhogwartsschool.service;

import org.springframework.stereotype.Service;
import ruhogwartsschool.exception.NotFoundException;
import ruhogwartsschool.model.Student;
import ruhogwartsschool.repository.StudentRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService{


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

    public Collection<Student> getStudentByAge( int age ) {
        return studentRepository.findAll().stream()
                .filter(a -> a.getAge() == age)
                .collect(Collectors.toList());
    }
}

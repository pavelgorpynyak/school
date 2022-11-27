package ruhogwartsschool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ruhogwartsschool.exception.NotFoundException;
import ruhogwartsschool.model.Student;
import ruhogwartsschool.repository.StudentRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);


    private final StudentRepository studentRepository;

    public StudentService( StudentRepository studentRepository ) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent( Student student ) {
        return studentRepository.save(student);
    }

    public Student findStudent( long id ) {
        LOG.debug("Method find");
        Optional<Student> searchingStudent = studentRepository.findById(id);
        if (searchingStudent.isEmpty()) {
            throw new NotFoundException(" Student not found");
        }
        return searchingStudent.get();
    }

    public Student editStudent( Student student ) {
        LOG.debug("Method edit");
        return studentRepository.save(student);
    }

    public void deleteStudent( long id ) {
        LOG.debug("Method delete");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getStudentByAge( int ageMin, int ageMax ) {
        LOG.debug("Method get student by age");
        return studentRepository.findStudentByAgeBetween(ageMin, ageMax);
    }
}

package ruhogwartsschool.service;

import org.springframework.stereotype.Service;
import ruhogwartsschool.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();
    private long lastId = 0;

    public Student createStudent( Student student ) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    public Student findStudent( long id ) {
        return students.get(id);
    }

    public Student editStudent( Student student ) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent( long id ) {
        return students.remove(id);
    }

    public Collection<Student> getStudentByAge( int age ) {
        return students.values()
                .stream()
                .filter(a -> a.getAge() == age)
                .collect(Collectors.toList());
    }
}

package ruhogwartsschool.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ruhogwartsschool.model.Student;
import ruhogwartsschool.service.StudentService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    public StudentController( StudentService studentService ) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo( @PathVariable long id ) {
        Student student = studentService.findStudent(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getStudentsByAge( @RequestParam int age ) {
        return ResponseEntity.ok(studentService.getStudentByAge(age));
    }

    @PostMapping
    public Student createStudent( @RequestBody Student student ) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent( @RequestBody Student student ) {
        Student fountStudent = studentService.editStudent(student);
        return ResponseEntity.ok(fountStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent( @PathVariable long id ) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

}

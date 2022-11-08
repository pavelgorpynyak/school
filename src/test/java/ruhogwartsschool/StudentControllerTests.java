package ruhogwartsschool;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ruhogwartsschool.controller.StudentController;
import ruhogwartsschool.model.Student;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.registerCustomDateFormat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        assertThat(studentController).isNotNull();
    }

    @Test
    public void testGetStudentsById() throws Exception {
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/students/id", String.class))
                .isNotNull();
    }

    @Test
    public void testEditStudent() throws Exception {
        assertThat(restTemplate.postForObject("http://localhost:" + port + "/students",
                createTestStudent("TestStudent"),
                String.class))
                .isNotNull();
    }

    @Test
    public void testDeleteStudent() throws Exception {
        createTestStudent("TestStudent");
        assertThat(restTemplate.exchange("http://localhost:" + port + "/students/20", HttpMethod.DELETE,
                null, String.class));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        long id = createTestStudent("TestStudent1").getId();
        Student testStudent = new Student();
        testStudent.setName("TestStudent2");
        HttpEntity<Student> entity = new HttpEntity<Student>(testStudent);

        ResponseEntity<Student> response = restTemplate.exchange("http://localhost:" + port + "/students/id"
                , HttpMethod.PUT, entity, Student.class, id);
        assertThat(response.getStatusCode().compareTo(HttpStatus.OK));
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
    }

    private Student createTestStudent( String name ) {
        Student student = new Student();
        student.setId(20L);
        student.setAge(20);
        student.setName(name);
        return student;
    }

}

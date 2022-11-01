package ruhogwartsschool.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ruhogwartsschool.model.Student;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static ruhogwartsschool.constants.StudentServiceTestConstants.*;

class StudentServiceTest {

    private StudentService out;

    @BeforeEach
    public void setUp() {
        out = new StudentService();
    }


    @Test
    public void shouldReturnCorrectList_WhenFilterByAge() {
        STUDENT_LIST.forEach(
                s -> assertDoesNotThrow(() -> out.createStudent(s))
        );
        assertIterableEquals(List.of(STUDENT_1),
                out.getStudentByAge(20));
        assertIterableEquals(List.of(STUDENT_4_1, STUDENT_4_2),
                out.getStudentByAge(23));
        assertIterableEquals(List.of(STUDENT_4_3, STUDENT_4_4),
                out.getStudentByAge(24));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForCorrectDeleteTest")
    public void shouldDeleteCorrectEntry( Student s1, Student s2 ) {
        assertDoesNotThrow(() -> out.createStudent(s1));
        assertDoesNotThrow(() -> out.createStudent(s2));
        assertDoesNotThrow(() -> out.deleteStudent(1L));
        assertEquals(s2, out.findStudent(2L));
        assertDoesNotThrow(() -> out.createStudent(s1));
        assertDoesNotThrow(() -> out.deleteStudent(2L));
        assertEquals(s1, out.findStudent(3L));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForDoesntExistUpdateTest")
    public void shouldUpdateCorrectly( Student s1, Student s2 ) {
        assertDoesNotThrow(() -> out.createStudent(s1));
        s2.setId(1L);
        assertDoesNotThrow(() -> out.editStudent(s2));
        assertEquals(s2, out.findStudent(1L));
    }

    @Test
    public void shouldGetCorrectly() {
        STUDENT_LIST.forEach(
                s -> assertDoesNotThrow(() -> out.createStudent(s))
        );
        for (long i = 1; i <= STUDENT_LIST.size(); i++) {
            assertEquals(STUDENT_LIST.get((int) (i - 1)),
                    out.findStudent(i));
        }
    }

    public static Stream<Arguments> provideParamsForDoesntExistTests() {
        return Stream.of(
                Arguments.of(new Student(0L, "example", 10), 0L),
                Arguments.of(new Student(0L, "example", 10), 2L),
                Arguments.of(new Student(0L, "example", 10), 3L),
                Arguments.of(new Student(0L, "example", 10), -1L),
                Arguments.of(new Student(0L, "example", 10), -5L)
        );
    }

    public static Stream<Arguments> provideParamsForDoesntExistUpdateTest() {
        return Stream.of(
                Arguments.of(
                        new Student(0L, "example", 30),
                        new Student(0L, "changed", 31)
                ),
                Arguments.of(
                        new Student(0L, "example", 30),
                        new Student(2L, "changed", 31)
                ));
    }

    public static Stream<Arguments> provideParamsForCorrectDeleteTest() {
        return Stream.of(
                Arguments.of(STUDENT_1, STUDENT_2),
                Arguments.of(STUDENT_2, STUDENT_3),
                Arguments.of(STUDENT_4_1, STUDENT_4_2)
        );
    }

}
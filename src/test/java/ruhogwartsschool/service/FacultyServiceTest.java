package ruhogwartsschool.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ruhogwartsschool.model.Faculty;
import ruhogwartsschool.model.Student;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static ruhogwartsschool.constants.FacultyServiceTestConstants.*;


class FacultyServiceTest {

    private FacultyService out;


    @BeforeEach
    public void setUp() {
        out = new FacultyService();
    }

    @Test
    public void shouldReturnCorrectList_WhenFilterByColor() {
        FACULTY_LIST.forEach(
                s -> assertDoesNotThrow(() -> out.createFaculty(s))
        );
        assertIterableEquals(List.of(FACULTY_1),
                out.getFacultyByColor("Red"));
        assertIterableEquals(List.of(FACULTY_3,FACULTY_3_1, FACULTY_3_2),
                out.getFacultyByColor("Green"));
        assertIterableEquals(List.of(FACULTY_4,FACULTY_4_1, FACULTY_4_2),
                out.getFacultyByColor("Blue"));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForCorrectDeleteTest")
    public void shouldDeleteCorrectEntry( Faculty s1, Faculty s2) {
        assertDoesNotThrow(() -> out.createFaculty(s1));
        assertDoesNotThrow(() -> out.createFaculty(s2));
        assertDoesNotThrow(() -> out.deleteFaculty(1L));
        assertEquals(s2, out.findFaculty(2L));
        assertDoesNotThrow(() -> out.createFaculty(s1));
        assertDoesNotThrow(() -> out.deleteFaculty(2L));
        assertEquals(s1, out.findFaculty(3L));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForDoesntExistUpdateTest")
    public void shouldUpdateCorrectly(Faculty s1, Faculty s2) {
        assertDoesNotThrow(() -> out.createFaculty(s1));
        s2.setId(1L);
        assertDoesNotThrow(() -> out.editFaculty(s2));
        assertEquals(s2, out.findFaculty(1L));
    }

    @Test
    public void shouldGetCorrectly() {
        FACULTY_LIST.forEach(
                s -> assertDoesNotThrow(() -> out.createFaculty(s))
        );
        for (long i = 1; i <= FACULTY_LIST.size(); i++) {
            assertEquals(FACULTY_LIST.get((int) (i - 1)),
                    out.findFaculty(i));
        }
    }

    public static Stream<Arguments> provideParamsForDoesntExistTests() {
        return Stream.of(
                Arguments.of(new Faculty(0L, "example", "White"), 0L),
                Arguments.of(new Faculty(0L, "example", "White"), 2L),
                Arguments.of(new Faculty(0L, "example", "White"), 3L),
                Arguments.of(new Faculty(0L, "example", "White"), -1L),
                Arguments.of(new Faculty(0L, "example", "White"), -5L)
        );
    }

    public static Stream<Arguments> provideParamsForDoesntExistUpdateTest() {
        return Stream.of(
                Arguments.of(
                        new Faculty(0L, "example", "White"),
                        new Faculty(0L, "changed", "Black")
                ),
                Arguments.of(
                        new Faculty(0L, "example", "White"),
                        new Faculty(2L, "changed", "Black")
                ));
    }

    public static Stream<Arguments> provideParamsForCorrectDeleteTest() {
        return Stream.of(
                Arguments.of(FACULTY_1, FACULTY_2),
                Arguments.of(FACULTY_2, FACULTY_3),
                Arguments.of(FACULTY_3_1, FACULTY_3_2)
        );
    }

}
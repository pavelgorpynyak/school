package ruhogwartsschool.constants;

import ruhogwartsschool.model.Student;

import java.util.List;

public class StudentServiceTestConstants {

    public static final Student STUDENT_1 = new Student(0L, "Ivan", 20);
    public static final Student STUDENT_2 = new Student(0L, "Ivan", 21);
    public static final Student STUDENT_3 = new Student(0L, "Ivan", 22);
    public static final Student STUDENT_4_1 = new Student(0L, "Ivan", 23);
    public static final Student STUDENT_4_2 = new Student(0L, "Ivan", 23);
    public static final Student STUDENT_4_3 = new Student(0L, "Ivan", 24);
    public static final Student STUDENT_4_4 = new Student(0L, "Ivan", 24);

    public static final List<Student> STUDENT_LIST = List.of(STUDENT_1, STUDENT_2, STUDENT_3
            , STUDENT_4_1, STUDENT_4_2, STUDENT_4_3, STUDENT_4_4);

}

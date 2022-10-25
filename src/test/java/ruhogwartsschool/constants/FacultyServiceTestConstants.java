package ruhogwartsschool.constants;

import ruhogwartsschool.model.Faculty;

import java.util.List;

public class FacultyServiceTestConstants {

    public static Faculty FACULTY_1 = new Faculty(0L, "Pufenduy", "Red");
    public static Faculty FACULTY_2 = new Faculty(0L, "Cogtevran", "Yellow");
    public static Faculty FACULTY_3 = new Faculty(0L, "Grifendor", "Green");
    public static Faculty FACULTY_3_1 = new Faculty(0L, "Grifendor_1", "Green");
    public static Faculty FACULTY_3_2 = new Faculty(0L, "Grifendor_2", "Green");
    public static Faculty FACULTY_4 = new Faculty(0L, "Slizerin", "Blue");
    public static Faculty FACULTY_4_1 = new Faculty(0L, "Slizerin_1", "Blue");
    public static Faculty FACULTY_4_2 = new Faculty(0L, "Slizerin_2", "Blue");

    public static final List<Faculty> FACULTY_LIST = List.of(FACULTY_1, FACULTY_2, FACULTY_3
            , FACULTY_4, FACULTY_3_1, FACULTY_3_2, FACULTY_4_1, FACULTY_4_2);
}

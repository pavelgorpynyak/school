package ruhogwartsschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ruhogwartsschool.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    public Collection<Faculty> findFacultyByNameIgnoreCaseOrColorIgnoreCase( String name, String color );
}

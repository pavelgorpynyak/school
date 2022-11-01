package ruhogwartsschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ruhogwartsschool.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
}

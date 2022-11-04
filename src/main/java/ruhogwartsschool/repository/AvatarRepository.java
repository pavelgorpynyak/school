package ruhogwartsschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ruhogwartsschool.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudentId( Long id );
}

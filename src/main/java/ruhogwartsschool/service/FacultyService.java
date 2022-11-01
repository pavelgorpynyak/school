package ruhogwartsschool.service;

import org.springframework.stereotype.Service;
import ruhogwartsschool.exception.NotFoundException;
import ruhogwartsschool.model.Faculty;
import ruhogwartsschool.repository.FacultyRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService( FacultyRepository facultyRepository ) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty( Faculty faculty ) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty( long id ) {
        Optional<Faculty> searchingFaculty = facultyRepository.findById(id);
        if (searchingFaculty.isEmpty()) {
            throw new NotFoundException("Faculty not found!");
        }
        return searchingFaculty.get();
    }

    public Faculty editFaculty( Faculty faculty ) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty( long id ) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getFacultyByColor( String color ) {
        return facultyRepository.findAll()
                .stream()
                .filter(c -> c.getColor().equals(color))
                .collect(Collectors.toList());
    }
}

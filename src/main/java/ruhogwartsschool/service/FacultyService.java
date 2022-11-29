package ruhogwartsschool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ruhogwartsschool.exception.NotFoundException;
import ruhogwartsschool.model.Faculty;
import ruhogwartsschool.repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private static final Logger LOG = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService( FacultyRepository facultyRepository ) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty( Faculty faculty ) {
        LOG.debug("Method create");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty( long id ) {
        LOG.debug("Method find");
        Optional<Faculty> searchingFaculty = facultyRepository.findById(id);
        if (searchingFaculty.isEmpty()) {
            throw new NotFoundException("Faculty not found!");
        }
        return searchingFaculty.get();
    }

    public Faculty editFaculty( Faculty faculty ) {
        LOG.debug("Method edit");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty( long id ) {
        LOG.debug("Method delete");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getFacultyByNameAndColor( String name, String color ) {
        LOG.debug("Method get faculty by name and color");
        return facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    public String findLongestFacultyName() {
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElseThrow(NotFoundException::new);
    }
}

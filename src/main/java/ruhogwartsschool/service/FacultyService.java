package ruhogwartsschool.service;

import org.springframework.stereotype.Service;
import ruhogwartsschool.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final HashMap<Long, Faculty> facultys = new HashMap<>();
    private long lastId = 0;

    public Faculty createFaculty( Faculty faculty ) {
        faculty.setId(++lastId);
        facultys.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty( long id ) {
        return facultys.get(id);
    }

    public Faculty editFaculty( Faculty faculty ) {
        if (facultys.containsKey(faculty.getId())) {
            facultys.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty( long id ) {
        return facultys.remove(id);
    }

    public Collection<Faculty> getFacultyByColor( String color ) {
        return facultys.values()
                .stream()
                .filter(c -> c.getColor() == color)
                .collect(Collectors.toList());
    }
}

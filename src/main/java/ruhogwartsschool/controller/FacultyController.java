package ruhogwartsschool.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ruhogwartsschool.model.Faculty;
import ruhogwartsschool.service.FacultyService;

import javax.websocket.server.PathParam;
import java.util.Collection;

@RestController
@RequestMapping("facultys")
public class FacultyController {

    FacultyService facultyService;

    public FacultyController( FacultyService facultyService ) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo( @PathVariable long id ) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getFacultiesByColor( @RequestParam String color ) {
        return ResponseEntity.ok(facultyService.getFacultyByColor(color));
    }

    @PostMapping
    public Faculty createFaculty( @RequestBody Faculty faculty ) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty( @RequestBody Faculty faculty ) {
        Faculty findFaculty = facultyService.editFaculty(faculty);
        if (findFaculty == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(findFaculty);
    }

    @DeleteMapping("{id}")
    public Faculty deleteFaculty( @PathVariable long id ) {
        return facultyService.deleteFaculty(id);
    }
}

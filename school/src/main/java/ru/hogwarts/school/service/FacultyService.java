package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

@Service
public class FacultyService {
    private HashMap<Long, Faculty> facultys = new HashMap<>();
    private long id = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++id);
        facultys.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty getFaculty(Long id) {
        return facultys.get(id);
    }

    public Faculty editeFaculty(Faculty faculty) {
        if(facultys.containsKey(faculty.getId())) {
            facultys.put(id, faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(Long id) {
        return facultys.remove(id);
    }

    public Collection<Faculty> getAllFaculties() {
        return facultys.values();
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        return facultys.values().stream().filter(f -> f.getColor().equals(color)).toList();
    }
}

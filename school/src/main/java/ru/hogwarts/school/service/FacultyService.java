package ru.hogwarts.school.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositiry.FacultyRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

@Service
public class FacultyService {
    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(Long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty editeFaculty(Faculty faculty) {
        Faculty existing = facultyRepository.findById(faculty.getId())
                .orElseThrow(() -> new EntityNotFoundException("Faculty not found"));

        existing.setName(existing.getName());
        existing.setColor(existing.getColor());

        return facultyRepository.save(existing);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findFacultyByColorIgnoreCase(color);
    }

    public Collection<Faculty> getFacultyByNameAndColor(String name, String color) {
        return facultyRepository.findFacultyByNameAndColorIgnoreCase(name, color);
    }
}

package ru.hogwarts.school.service;

import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositiry.FacultyRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

@Service
public class FacultyService {
    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Работает метод createFaculty");

        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(Long id) {
        logger.info("Работает метод getFaculty с id = {}",  id);

        return facultyRepository.findById(id).get();
    }

    public Faculty editeFaculty(Faculty faculty) {
        logger.info("Работает метод editeFaculty с факультетом = {}",  faculty);

        Faculty existing = facultyRepository.findById(faculty.getId())
                .orElseThrow(() -> new EntityNotFoundException("Faculty not found"));

        existing.setName(existing.getName());
        existing.setColor(existing.getColor());

        return facultyRepository.save(existing);
    }

    public void deleteFaculty(Long id) {
        logger.info("Работает метод deleteFaculty с id = {}",  id);

        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties() {
        logger.info("Работает метод getAllFaculties");

        return facultyRepository.findAll();
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        logger.info("Работает метод getFacultyByColor с цветом = {}",  color);

        return facultyRepository.findFacultyByColorIgnoreCase(color);
    }

    public Collection<Faculty> getFacultyByNameAndColor(String name, String color) {
        logger.info("Работает метод getFacultyByNameAndColor с именем = {} и цветом = {}",  name, color);

        return facultyRepository.findFacultyByNameAndColorIgnoreCase(name, color);
    }

    public String getTheLongestFaculty() {
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse("nan");
    }
}

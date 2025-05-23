package ru.hogwarts.school.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findFacultyByColorIgnoreCase(String color);
    Collection<Faculty> findFacultyByNameAndColorIgnoreCase(String name, String color);
}

package ru.hogwarts.school.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}

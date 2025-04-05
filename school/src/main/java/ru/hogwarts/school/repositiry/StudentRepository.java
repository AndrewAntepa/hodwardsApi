package ru.hogwarts.school.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findStudentsByAgeBetween(int min, int max);

    Collection<Student> findStudentByNameIgnoreCase(String name);
}

package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositiry.StudentRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class StudentService {
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student  createStudent(Student student) {
        logger.info("Работает метод createStudent для студента = {}", student);

        return studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        logger.info("Работает метод getStudent с id = {}", id);
        return studentRepository.findById(id).get();
    }

    public Student editeStudent(Student student) {
        logger.info("Работает метод editeStudent для студента = {}", student);
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.info("Работает метод deleteStudent с id = {}", id);

        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.info("Работает метод getAllStudents");

        return studentRepository.findAll();
    }


    public Collection<Student> getStudentsByAge(int age) {
        logger.info("Работает метод getStudentsByAge с age = {}", age);

        return studentRepository.findAll().stream().filter(s -> s.getAge() == age).toList();
    }

    public Collection<Student> getStudentsByAgeBetween(int min, int max) {
        logger.info("Работает метод getStudentsByAgeBetween с min = {} и max = {}", min, max);

        return studentRepository.findStudentsByAgeBetween(min, max);
    }

    public Collection<Student> getStudentByName(String name) {
        logger.info("Работает метод getStudentByName с именеем = {}", name);
        return studentRepository.findStudentByNameIgnoreCase(name);
    }

    public Integer getAmountOfStudents() {
        logger.info("Работает метод getAmountOfStudents");

        return studentRepository.getAmountOfStudents();
    }

    public Integer getAverageAgeOfStudents() {
        logger.info("Работает метод getAverageAgeOfStudents");

        return studentRepository.getAverageAgeOfStudents();
    }

    public List<Student> getLastFiveStudents(){
        logger.info("Работает метод getLastFiveStudents");

        return studentRepository.getLastFiveStudents();
    }

    public Collection<Student> getStudentByNameStartsWithA() {
        return studentRepository.findAll().stream()
                .filter(s -> s.getName().toUpperCase().startsWith("A"))
                .sorted(Comparator.comparing(Student::getName)).toList();
    }

    public double averageAgeOfStudents() {
        return studentRepository.findAll().stream()
                .mapToInt(Student :: getAge)
                .average()
                .orElse(0);
    }



    public int tryMethod() {
        return IntStream.range(0, 1_000_000).sum();
    }
}

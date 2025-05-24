package ru.hogwarts.school.controller;

import com.sun.tools.javac.Main;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<Collection<Student>> getStudentsByAge(@PathVariable int age) {
        return ResponseEntity.ok(studentService.getStudentsByAge(age));
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editeStudent(@RequestBody Student book) {
        Student findStudent = studentService.editeStudent(book);
        if (findStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(findStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/ageBetween")
    public Collection<Student> getStudentsByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.getStudentsByAgeBetween(min, max);
    }


    @GetMapping("/amount-of-students")
    public Integer getAmountOfStudents() {
        return studentService.getAmountOfStudents();
    }

    @GetMapping("/average-age-of-students")
    public Integer getAverageAgeOfStudents() {
        return studentService.getAverageAgeOfStudents();
    }

    @GetMapping("last-five-students")
    public List<Student> getLastFiveStudents() {
        return studentService.getLastFiveStudents();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Collection<Student>> getStudentByName(@PathVariable String name) {
        return ResponseEntity.ok(studentService.getStudentByName(name));
    }

    @GetMapping("name/starts-A")
    public Collection<Student> getStudentByNameStartsWithA() {
        return studentService.getStudentByNameStartsWithA();
    }

    @GetMapping("name/avg-age")
    public double averageAgeOfStudents() {
        return studentService.averageAgeOfStudents();
    }

    @GetMapping("students/print-parallel")
    public void printParallel() {
        List<Student> students = (List<Student>) studentService.getAllStudents();

        System.out.println("1. " + students.get(0).getName());
        System.out.println("2. " + students.get(1).getName());

        new Thread(() -> {
            System.out.println("3. " + students.get(2).getName());
            System.out.println("4. " + students.get(3).getName());
        }).start();

        new Thread(() -> {
            System.out.println("5. " + students.get(4).getName());
            System.out.println("6. " + students.get(5).getName());
        }).start();
    }

    @GetMapping("/students/print-synchronized")
    public void printSynchronized() {
        List<Student> students = (List<Student>) studentService.getAllStudents();

        System.out.println("1. " + printNameOfStudent(students.get(0)));
        System.out.println("2. " + printNameOfStudent(students.get(1)));

        new Thread(() -> {
            System.out.println("3. " + printNameOfStudent(students.get(2)));
            System.out.println("4. " + printNameOfStudent(students.get(3)));
        }).start();

        new Thread(() -> {
            System.out.println("5. " + printNameOfStudent(students.get(4)));
            System.out.println("6. " + printNameOfStudent(students.get(5)));
        }).start();
    }

    private synchronized String printNameOfStudent(Student student){
        return student.getName();
    }
}

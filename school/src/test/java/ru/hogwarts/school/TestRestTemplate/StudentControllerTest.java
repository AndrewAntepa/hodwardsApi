package ru.hogwarts.school.TestRestTemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void testGetStudentInfo() {
        ResponseEntity<Student> response = restTemplate.getForEntity("http://localhost:" + port + "/student/id/2", Student.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetStudentsByAge() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("http://localhost:" + port + "/student/age/20", Student[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCreateStudent() {
        Student student = new Student(1L, "try test", 20);
        ResponseEntity<Student> response = restTemplate.postForEntity("http://localhost:" + port + "/student", student, Student.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testEditStudent() {
        Student student = new Student(1L, "John Doe", 21);
        ResponseEntity<Student> response = restTemplate.exchange("http://localhost:" + port + "/student", HttpMethod.PUT, new HttpEntity<>(student), Student.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteStudent() {
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/student/1", HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetAllStudents() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("http://localhost:" + port + "/student/all", Student[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetStudentsByAgeBetween() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("http://localhost:" + port + "/student/ageBetween?min=18&max=25", Student[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

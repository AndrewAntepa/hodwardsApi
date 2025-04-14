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
import ru.hogwarts.school.model.Faculty;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void testGetFacultyInfo() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity("http://localhost:" + port + "/faculty/id/2", Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetFacultyByColor() {
        ResponseEntity<Faculty[]> response = restTemplate.getForEntity("http://localhost:" + port + "/faculty/color/red", Faculty[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCreateFaculty() {
        Faculty faculty = new Faculty(1L, "Engineering", "blue");
        ResponseEntity<Faculty> response = restTemplate.postForEntity("http://localhost:" + port + "/faculty", faculty, Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testEditFaculty() {
        Faculty faculty = new Faculty(1L, "Science", "green");
        ResponseEntity<Faculty> response = restTemplate.exchange("http://localhost:" + port + "/faculty", HttpMethod.PUT, new HttpEntity<>(faculty), Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteFaculty() {
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/faculty/1", HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetAllFaculties() {
        ResponseEntity<Faculty[]> response = restTemplate.getForEntity("http://localhost:" + port + "/faculty/all", Faculty[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

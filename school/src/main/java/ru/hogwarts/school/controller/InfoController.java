package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.repositiry.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@Profile("production")
public class InfoController {
    StudentService studentService;

    @Value("${server.port}")
    String port;

    @GetMapping("/port")
    public String getPort() {
        return "port = " + port;
    }

    @GetMapping("try")
    public int tryMethod() {
        return studentService.tryMethod();
    }
}

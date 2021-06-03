package ru.job4j.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.auth.model.Employee;
import ru.job4j.auth.model.Person;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private RestTemplate restTemplate;
    private static final String API = "http://localhost:8080/person/";
    private static final String API_ID = "http://localhost:8080/person/{id}";

    @GetMapping("/")
    public ResponseEntity<Employee> getEmployees() {
        List<Person> persons = restTemplate.exchange(API, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Person>>() { }).getBody();

        Employee employee = Employee.of("Вася", "Пупкин", 4512, persons);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person rsl = restTemplate.postForObject(API, person, Person.class);
        return new ResponseEntity<>(
                rsl,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        restTemplate.put(API, person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        restTemplate.delete(API_ID, id);
        return ResponseEntity.ok().build();
    }






}

package ru.job4j.auth.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer inn;
    private LocalDateTime hiringDate;
    private List<Person> persons;
    public static AtomicInteger counter = new AtomicInteger(0);

    public Employee() {
    }

    public static Employee of(String firstName, String lastName,
                    Integer inn, List<Person> persons) {
        Employee employee = new Employee();
        employee.id = counter.addAndGet(1);
        employee.firstName = firstName;
        employee.lastName = lastName;
        employee.inn = inn;
        employee.hiringDate = LocalDateTime.now().withNano(0).withSecond(0);
        employee.persons = persons;
        return employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getInn() {
        return inn;
    }

    public void setInn(Integer inn) {
        this.inn = inn;
    }

    public LocalDateTime getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDateTime hiringDate) {
        this.hiringDate = hiringDate;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}

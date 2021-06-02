package ru.job4j.auth.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.repo.PersonRepository;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonRepository personRepository;

    @Test
    public void whenGetMethodFindAll() throws Exception {
        mockMvc.perform(get("/person/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    public void whenFindByIdThenResultStatus200() throws Exception {
        Mockito.when(personRepository.findById(anyInt())).thenReturn(Optional.of(
                Person.of(1, "root", "root")));
        mockMvc.perform(get("/person/{id}", anyInt()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{id:1, login:root, password:root}"));
    }
    @Test
    public void whenPostMethodThenCreatePersonAndReturnStatus201() throws Exception {
        mockMvc.perform(
                post("/person/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"login\":\"user\", \"password\":\"user\"}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);
        Mockito.verify(personRepository).save(captor.capture());
        assertEquals(captor.getValue().getLogin(), "user");
        assertEquals(captor.getValue().getPassword(), "user");
    }

    @Test
    public void whenPutMethodThenUpdatePersonAndReturnStatus() throws Exception {
        mockMvc.perform(
                put("/person/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"login\":\"user\", \"password\":\"user\"}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenDeleteMethodThenDeletePerson() throws Exception {
        mockMvc.perform(delete("/person/{id}", anyInt()))
                .andDo(print())
                .andExpect(status().isOk());
    }



}
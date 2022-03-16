package com.kuiteul.dbeaver.controller;


import com.kuiteul.dbeaver.domain.Person;
import com.kuiteul.dbeaver.service.IPersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/service")
public class PersonController {

    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Collection<Person> getById(@PathVariable ("id") int id) {
        return personService.getPerson(id);
    }

    @GetMapping(value = "/person/{id}", produces = "application/json")
    public Collection<Person> getPersonById(@PathVariable("id") int id) {
        return personService.getPersons(id);
    }
}

package com.kuiteul.dbeaver.service;

import com.kuiteul.dbeaver.domain.Person;
import com.kuiteul.dbeaver.orm.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class DefaultPersonService implements IPersonService{

    private final PersonRepository personRepository;

    public DefaultPersonService(PersonRepository iPerson) {
        this.personRepository = iPerson;
    }

    @Override
    public Collection<Person> getPerson(int id) {
        return personRepository.getPersonById(id);
    }

    @Override
    public Collection<Person> getPersons(int id) {
        return personRepository.getPersonsById(id);
    }
}

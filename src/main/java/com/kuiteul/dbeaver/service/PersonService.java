package com.kuiteul.dbeaver.service;

import com.kuiteul.dbeaver.domain.Person;

import java.util.Collection;

public interface PersonService {

    Collection<Person> getPerson(int id);
    Collection<Person> getPersons(int id);
}

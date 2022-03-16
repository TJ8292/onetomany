package com.kuiteul.dbeaver.orm;

import com.kuiteul.dbeaver.domain.Person;

import java.util.Collection;

public interface PersonRepository {

    Collection<Person> getAll();

    Collection<Person> getPersonById(int id);
    Collection<Person> getPersonsById(int id);
}

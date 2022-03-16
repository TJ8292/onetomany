package com.kuiteul.dbeaver.service;

import com.kuiteul.dbeaver.domain.Person;

import java.util.Collection;

public interface IPersonService {

    Collection<Person> getPerson(int id);
    Collection<Person> getPersons(int id);
}

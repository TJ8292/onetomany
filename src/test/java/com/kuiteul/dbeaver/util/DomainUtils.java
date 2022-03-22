package com.kuiteul.dbeaver.util;

import com.kuiteul.dbeaver.domain.Person;

import java.util.Collection;
import java.util.Comparator;

public class DomainUtils {

    public static void sort(Collection<Person> person) {
        person.stream().sorted(Comparator.comparing(v -> v.getId()));

    }
}

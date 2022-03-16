package com.kuiteul.dbeaver.orm;

import com.kuiteul.dbeaver.domain.Person;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.Map;

public class PersonQueryContext {
    public final Map<Integer, Person> personMap;
    public final MapSqlParameterSource parameterSource;


    public PersonQueryContext(Map<Integer, Person> personMap, MapSqlParameterSource parameterSource) {
        this.personMap = personMap;
        this.parameterSource = parameterSource;
    }
}

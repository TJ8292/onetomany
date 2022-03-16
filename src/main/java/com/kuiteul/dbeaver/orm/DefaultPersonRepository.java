package com.kuiteul.dbeaver.orm;

import com.kuiteul.dbeaver.domain.Person;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public class DefaultPersonRepository implements PersonRepository {

    private final Map<Class, AbstractQueryHandler> handlerMap;

    public DefaultPersonRepository(List<AbstractQueryHandler> handlerList) {
        this.handlerMap = handlerList.stream().collect(Collectors.toMap(k -> k.getClass(), v -> v));

    }

    private <T> T getHandler(Class<T> clazz) {
        return (T) handlerMap.get(clazz);
    }

    @Override
    public List<Person> getAll() {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        //parameterSource.addValue("email", email)
        String sql = "select first_name, last_name  from person";
        return null;
    }

    @Override
    public Collection<Person> getPersonById(int id) {
        PersonQueryContext queryContext = getHandler(PersonQueryHandler.class).getPerson(id);
        getHandler(PersonQueryHandler.class).addPet(queryContext);
        getHandler(PersonQueryHandler.class).addCar(queryContext);
        return queryContext.personMap.values();
    }

    @Override
    public Collection<Person> getPersonsById(int id) {

        PersonQueryContext queryContext = getHandler(PersonQueryHandler.class).getPerson(id);
        getHandler(PersonQueryHandler.class).addPet(queryContext);
        getHandler(PersonQueryHandler.class).addCar(queryContext);
        return queryContext.personMap.values();

    }

}

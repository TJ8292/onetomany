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



/*    public class PersonRowMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            Map<Integer, Person> personById = new HashMap<>();
            List<Pet> pets = new ArrayList<>();

            int id = rs.getInt("id");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            Gender gender = getGender(rs, "gender");

            String color = rs.getString("color");
            Long person_id = rs.getLong("person_id");
            String animalName = rs.getString("common_name");
            System.out.format("%d, %s, %s, %s, %s", id, first_name, last_name, gender, animalName);
            Person person = personById.get(id);
            if (person == null) {
                person = new Person(id, first_name, last_name, gender);
                Pet pet = new Pet(animalName, color, person_id);
                pets.add(pet);
                if (person.getPets() == null) {
                    person.setPets(new ArrayList<>());
                }
                person.getPets().addAll(pets);
                personById.put(person.getId(), person);
            }

            return person;
        }
    }*/

//    MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//
//    String sql = "select p.id, p.first_name, p.last_name, p.gender, pet.color, pet.person_id, pet.common_name  from person p " +
//            "left join pet on pet.person_id = p.id " +
//            "where p.id = :id";
//
//
//    String petSql = "select pet.common_name, pet.color from pet " +
//            "left join person p on p.id = pet.person_id " +
//            "where pet.person_id = :id";
//
//        parameterSource.addValue("id", id);
//
//        jdbcTemplate.query(sql, parameterSource, (rs) -> {
//
//        Person person = new Person();
//
//        int guid = rs.getInt("id");
//        String first_name = rs.getString("first_name");
//        String last_name = rs.getString("last_name");
//        Gender gender = getGender(rs, "gender");
//        person.setId(guid);
//        person.setFirst_name(first_name);
//        person.setLast_name(last_name);
//        person.setGender(gender);
//    });

    /*        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        String sql = "select p.id, p.first_name, p.last_name, p.gender, pet.color, pet.person_id, pet.common_name  from person p " +
                "left join pet on pet.person_id = p.id " +
                "where p.id = :id";

        parameterSource.addValue("id", id);

        return jdbcTemplate.queryForObject(sql, parameterSource, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map<Long, Person> personById = new HashMap<>();


                //Person person = new Person();
                int id = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                Gender gender = getGender(rs, "gender");

                String color = rs.getString("color");
                Long person_id = rs.getLong("person_id");
                String animalName = rs.getString("common_name");
                System.out.format("%d, %s, %s, %s, %s", id, first_name, last_name, gender, animalName);
                Person person = personById.get(id);
                if (person == null) {
                    person = new Person(id, first_name, last_name, gender);
                    personById.put(person.getId(), person);
                }

                Pet pet = new Pet(animalName, color, person_id);

                person.getPets().add(pet);


                System.out.println(person.toString());
                return person;
                //return null;
            }
        });*/

}

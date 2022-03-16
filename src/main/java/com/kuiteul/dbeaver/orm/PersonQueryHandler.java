package com.kuiteul.dbeaver.orm;

import com.kuiteul.dbeaver.domain.Car;
import com.kuiteul.dbeaver.domain.Gender;
import com.kuiteul.dbeaver.domain.Person;
import com.kuiteul.dbeaver.domain.Pet;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component
public class PersonQueryHandler extends AbstractQueryHandler{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PersonQueryHandler(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PersonQueryContext getPerson (int id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        Map<Integer, Person> personMap = new HashMap<>();

        String sql = "select p.id, p.first_name, p.last_name, p.gender from person p " +
                "where p.id = :id";

        parameterSource.addValue("id", id);

        jdbcTemplate.query(sql, parameterSource, (rs) -> {

            Person person = new Person();

            int guid = rs.getInt("id");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            Gender gender = getGender(rs, "gender");
            person.setId(guid);
            person.setFirst_name(first_name);
            person.setLast_name(last_name);
            person.setGender(gender);

            personMap.put(person.getId(), person);


        });

        return new PersonQueryContext(personMap, parameterSource);

    }

    public void addPet(PersonQueryContext queryContext) {
        String petSql = "select pet.common_name, pet.color, pet.person_id from pet pet " +
                "left join person p on p.id = pet.person_id " +
                "where pet.person_id = :id";


        jdbcTemplate.query(petSql, queryContext.parameterSource, (rs) -> {
            int person_id = rs.getInt("person_id");
            if(queryContext.personMap.containsKey(person_id)){
                Person person = queryContext.personMap.get(person_id);
                Pet pet = new Pet();
                pet.setPerson_Id(person_id);
                pet.setCommonName(rs.getString("common_name"));
                pet.setColor(rs.getString("color"));

                person.getPets().add(pet);
            }


        });

    }

    public void addCar(PersonQueryContext queryContext) {

        String carSql = "select car.car_id, car.make, car.model, car.color, car.model_year, car.person_id from car car " +

                "where car.person_id = :id";


        jdbcTemplate.query(carSql, queryContext.parameterSource, (rs) -> {
            int person_id = rs.getInt("person_id");
            if (queryContext.personMap.containsKey(person_id)) {
                Person person = queryContext.personMap.get(person_id);

                Car car = new Car();
                car.setCarId(rs.getInt("car_id"));
                car.setMake(rs.getString("make"));
                car.setModel(rs.getString("model"));
                car.setColor(rs.getString("color"));
                car.setModelYear(rs.getString("model_year"));
                car.setPersonId(rs.getInt("person_id"));

                person.getCars().add(car);
            }


        });

    }

    private Gender getGender(ResultSet rs, String Columnlabel) throws SQLException {
        final String string = rs.getString(Columnlabel);

        Gender gender = null;
        switch (string) {
            case "Female":
                gender = Gender.FEMALE;
                break;
            case "Male":
                gender = Gender.MALE;
                break;
            default:
                gender = Gender.valueOf("Invalid genre");
                break;

        }

        return gender;
    }
}

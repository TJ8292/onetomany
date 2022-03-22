package com.kuiteul.dbeaver.service;

import com.kuiteul.dbeaver.domain.Car;
import com.kuiteul.dbeaver.domain.Gender;
import com.kuiteul.dbeaver.domain.Person;
import com.kuiteul.dbeaver.domain.Pet;
import com.kuiteul.dbeaver.orm.DefaultPersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultPersonServiceTest {

    @Mock
    private DefaultPersonRepository repository;


    private DefaultPersonService service;

    @BeforeEach
    void setUp() {

        service = new DefaultPersonService(repository);
    }


    @Test
    void test() {
        Collection<Person> expected = getPerson();

        when(repository.getPersonsById(anyInt())).thenReturn(expected);

        Collection<Person> actual = service.getPersons(1);
        System.out.println(actual);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
        verify(repository).getPersonsById(anyInt());
        verifyNoMoreInteractions(repository);

    }


    private Collection<Person> getPerson() {
        Person person = new Person();
        person.setId(1);
        person.setFirst_name("Farlie");
        person.setLast_name("Itzcovich");
        person.setFirst_name("Farlie");
        person.setGender(Gender.FEMALE);
        Pet pet = new Pet();
        pet.setCommonName("Macaque, pig-tailed");
        pet.setColor("Khaki");
        pet.setPerson_Id(1);
        person.getPets().add(pet);
        Car car = new Car();
        car.setMake("Mercedes-Benz");
        car.setModel("Silvera");
        car.setMake("CLK-Class");
        car.setColor("Violet");
        car.setModelYear("2004");
        car.setPersonId(1);
        person.getCars().add(car);

        Collection<Person> expected = List.of(person);
        return expected;
    }
}
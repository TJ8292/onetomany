package com.kuiteul.dbeaver.controller;

import com.kuiteul.dbeaver.domain.Car;
import com.kuiteul.dbeaver.domain.Gender;
import com.kuiteul.dbeaver.domain.Person;
import com.kuiteul.dbeaver.domain.Pet;
import com.kuiteul.dbeaver.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = {PersonController.class})
//@DirtiesContext
class PersonControllerTest {

    @Mock
    private PersonService service;

    @InjectMocks
    private PersonController controller;

    @BeforeEach
    void setUp() {
        //controller = new PersonController(service);
    }

    @Test
    void test() {
        Collection<Person> expected = getPerson();

        when(service.getPersons(anyInt())).thenReturn(expected);

        Collection<Person> actual = controller.getPersonById(1);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
        verify(service).getPersons(anyInt());
        verifyNoMoreInteractions(service);
    }

    private Collection<Person> getPerson() {
        Person person = new Person();
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
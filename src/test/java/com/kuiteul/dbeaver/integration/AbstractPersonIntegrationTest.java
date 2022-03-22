package com.kuiteul.dbeaver.integration;

import com.kuiteul.dbeaver.domain.Person;
import com.kuiteul.dbeaver.service.DefaultPersonService;
import com.kuiteul.dbeaver.service.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

import static com.kuiteul.dbeaver.util.DomainUtils.sort;

public class AbstractPersonIntegrationTest {

    @TestConfiguration
    static class Config {

        @Bean
        @Primary
        public PersonService sortingPersonService(DefaultPersonService service) {
            return new SortingPersonService(service);
        }

        private class SortingPersonService implements PersonService {

            private DefaultPersonService service;
            public SortingPersonService(DefaultPersonService service) {
                this.service = service;
            }

            @Override
            public Collection<Person> getPerson(int id) {
                return null;
            }

            @Override
            public Collection<Person> getPersons(int id) {

                Collection<Person> persons = service.getPersons(id);
                sort(persons);
                return persons;
            }
        }
    }

    @Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    protected MockMvc mockMvc;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    public ResultMatcher jsonEquals(String jsonFile) {
        return (result) -> {

            String expected = this.readResource(jsonFile);
            String actual = result.getResponse().getContentAsString();
            JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
        };

    }

    private String readResource(String jsonFile) {
        ClassPathResource resource = new ClassPathResource(jsonFile);

        try {
            return Files.readString(Paths.get(resource.getURI()));
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }
    }

}

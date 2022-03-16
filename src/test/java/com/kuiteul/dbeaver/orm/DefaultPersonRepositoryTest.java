package com.kuiteul.dbeaver.orm;

import com.kuiteul.dbeaver.config.JdbcConfig;
import com.kuiteul.dbeaver.domain.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
@DirtiesContext
@SpringBootTest(classes = {JdbcConfig.class,
DataSourceAutoConfiguration.class,
DataSourceTransactionManagerAutoConfiguration.class,
DefaultPersonRepositoryTest.Config.class})
public class DefaultPersonRepositoryTest {

    @TestConfiguration
    @ComponentScan(basePackages = "com.kuiteul.dbeaver.orm")
    public static class Config {
    }


    @Autowired
    private PersonRepository personRepository;


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public DefaultPersonRepositoryTest(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @BeforeEach
    void setUp() {

    }

    @Test
    void test() {
        final Collection<Person> personById = personRepository.getPersonById(1);
        System.out.println(personById);
        Assertions.assertNotNull(personById);
    }
}

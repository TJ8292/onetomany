package com.kuiteul.dbeaver.integration;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(AbstractPersonIntegrationTest.Config.class)
public class GetByIdIntegrationTest extends AbstractPersonIntegrationTest {

    @Test
    void test() throws Exception {


        mockMvc.perform(get("/service/person/1")
                        //.header()
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.parseMediaType("application/json")))
                .andExpect(jsonEquals("json/response/person.json"));
    }
}

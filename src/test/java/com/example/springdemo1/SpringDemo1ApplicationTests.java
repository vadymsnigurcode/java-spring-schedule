package com.example.springdemo1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@ContextConfiguration(classes = TestConfig.class)
class SpringDemo1ApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {

    }
    @Test
    void getMappingReturns200Test() throws Exception {
        mockMvc.perform(get("/name?path=c:\\TMP\\someFile.txt"))
                .andExpect(status().isOk());
    }
    @Test
    void testBadRequestCaseTest() throws Exception {
        mockMvc.perform(get("/name?path=allo"))
                .andExpect(status().isNotFound());
    }
}

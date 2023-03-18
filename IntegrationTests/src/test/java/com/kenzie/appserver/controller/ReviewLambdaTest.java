package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.IntegrationTest;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;


@IntegrationTest
class ReviewLambdaTest {
    @Autowired
    private MockMvc mvc;


    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getById_Exists() throws Exception {
//
//        String name = mockNeat.strings().valStr();
//
//        Review persistedExample = reviewService.addNewExample(name);
//        mvc.perform(get("/example/{id}", persistedExample.getId())
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("id")
//                        .isString())
//                .andExpect(jsonPath("name")
//                        .value(is(name)))
//                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void createExample_CreateSuccessful() throws Exception {

//        String name = mockNeat.strings().valStr();
//
//        ExampleCreateRequest exampleCreateRequest = new ExampleCreateRequest();
//        exampleCreateRequest.setName(name);
//
//        mapper.registerModule(new JavaTimeModule());
//
//        mvc.perform(post("/example")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(exampleCreateRequest)))
//                .andExpect(jsonPath("id")
//                        .exists())
//                .andExpect(jsonPath("name")
//                        .value(is(name)))
//                .andExpect(status().is2xxSuccessful());
    }
}
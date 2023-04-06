package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.IntegrationTest;
import net.andreinc.mockneat.MockNeat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

@IntegrationTest
public class RecipeLambdaTest {
    @Autowired
    private MockMvc mvc;
    private final MockNeat mockNeat = MockNeat.threadLocal();
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void getAllRecipes_success() {

    }
}

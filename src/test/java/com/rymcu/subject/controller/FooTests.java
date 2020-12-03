package com.rymcu.subject.controller;

import com.rymcu.subject.Config;
import com.rymcu.subject.SubjectApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {SubjectApplication.class, Config.class})
@TestInstance(PER_CLASS)
public class FooTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void foo()
            throws Exception {
        this.mockMvc.perform(get("/question/next"))
                    .andExpect(status().isOk());
        this.mockMvc.perform(get("/question/next/132"))
                    .andExpect(status().isOk());

        this.mockMvc.perform(get("/question/next/123"))
                    .andExpect(status().isOk());

        this.mockMvc.perform(get("/question/record/123"))
                    .andExpect(status().isOk());
        this.mockMvc.perform(get("/question/record/1234"))
                    .andExpect(status().isOk());

        this.mockMvc.perform(get("/question/by"))
                    .andExpect(status().isOk());
        this.mockMvc.perform(get("/question/by/132"))
                    .andExpect(status().isOk());
        this.mockMvc.perform(get("/question/by/134"))
                    .andExpect(status().isOk());

    }

}

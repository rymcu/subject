package com.rymcu.subject;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@Configuration
public class Config {


    @Bean
    public MockMvc mockMvc(
            WebApplicationContext context
    ) {
        return webAppContextSetup(context).build();
    }
}

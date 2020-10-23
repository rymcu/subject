package com.rymcu.subject.controller;

import com.rymcu.subject.SubjectApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {SubjectApplication.class})
class FooTests {

    @Test
    public void foo() {
        System.err.println("foo ！！！");
    }

}

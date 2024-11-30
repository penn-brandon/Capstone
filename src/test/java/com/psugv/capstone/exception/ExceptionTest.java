package com.psugv.capstone.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ExceptionTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    public void test() {

        InsertErrorException iee = new InsertErrorException("InsertErrorException");

        assertEquals("Insert to database failed", iee.getMessage());

        NoQueryResultException nqre = new NoQueryResultException("NoQueryResultException");

        assertEquals("No query result found", nqre.getMessage());
    }
}

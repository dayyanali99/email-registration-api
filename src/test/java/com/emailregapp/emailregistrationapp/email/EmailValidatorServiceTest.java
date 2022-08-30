package com.emailregapp.emailregistrationapp.email;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorServiceTest {

    @Test
    void test1() {
        // given
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher("foo@gmail.com");
        // when

        // then
        assertTrue(matcher.find());
    }
}
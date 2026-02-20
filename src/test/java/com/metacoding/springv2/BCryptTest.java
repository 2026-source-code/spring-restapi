package com.metacoding.springv2;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {

    @Test
    public void encode_test() {
        // given
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String rawPassword = "1234";

        // when
        String encPassword = bcrypt.encode(rawPassword);

        // eye
        System.out.println(encPassword);
    }
}

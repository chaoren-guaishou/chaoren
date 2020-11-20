package com.wang.oauth2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author DPJ
 * @since 2020/11/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PassTest {

    @Test
    public void testPwd() {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }

}

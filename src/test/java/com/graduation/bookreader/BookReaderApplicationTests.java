package com.graduation.bookreader;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class BookReaderApplicationTests {

    @Test
    void contextLoads() {
        String s = DigestUtils.md5DigestAsHex("第1章 武魂觉醒 ".trim().getBytes());
        System.out.println(s);
    }

}

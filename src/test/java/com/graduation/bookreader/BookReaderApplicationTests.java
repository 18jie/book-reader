package com.graduation.bookreader;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.Date;

@SpringBootTest
class BookReaderApplicationTests {

    @Test
    void contextLoads() {
        String s = DigestUtils.md5DigestAsHex("123456".trim().getBytes());
        System.out.println(s);
    }

    @Test
    public void test(){
        int aiClassExpiredBuffer = 180;
        Date day = DateUtils.addDays(new Date(), -3);
        Long beginTime = new DateTime(day).withTimeAtStartOfDay().minusDays(aiClassExpiredBuffer).getMillis();
        Long endTime = new DateTime(new Date()).plusDays(1).withTimeAtStartOfDay().plusMillis(-1).minusDays(aiClassExpiredBuffer).getMillis();
        System.out.println("开始时间：" + beginTime);
        System.out.println("结束时间：" + endTime);
    }
}
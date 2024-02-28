package com.vaggietable.server;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@MapperScan
class VeggieTableServerApplicationTests {

    @Test
    void contextLoads() {
    }

}

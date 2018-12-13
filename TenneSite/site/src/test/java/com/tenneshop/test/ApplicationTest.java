package com.tenneshop.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = { "server.port = 18012", "http.server.port = 19012" })
public class ApplicationTest {

	/*
	 * Debugging Tests
	 * Forked Tests
	 * mvn -Dmaven.surefire.debug test
	 * http://maven.apache.org/surefire/maven-surefire-plugin/examples/debugging.html
	 * */
	@Test
    public void testContextLoads() throws Exception {
		assertTrue(true);
    }

}

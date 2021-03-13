package ru.netology.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTest {
    private final String HOST = "http://localhost:";
    @Autowired
    TestRestTemplate restTemplate;
    @Container
    public static GenericContainer<?> devApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    @Container
    public static GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);
    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }


    @Test
    void contextLoads1() {
        var port1 = devApp.getMappedPort(8080);
        ResponseEntity<String> forEntity1 = restTemplate.getForEntity(HOST + port1, String.class);
        System.out.println("Port1: " + port1);
        System.out.println(forEntity1.getBody());
        String msgExpected1 = "Current profile is dev";
        String msg1 = forEntity1.getBody();
        System.out.print("Dev? -> ");
        Assertions.assertEquals(msg1, msgExpected1);
    }

    @Test
    void contextLoads2() {
        var port2 = prodApp.getMappedPort(8081);
        ResponseEntity<String> forEntity2 = restTemplate.getForEntity(HOST + port2, String.class);
        System.out.println("Port2: " + port2);
        System.out.println(forEntity2.getBody());
        String msgExpected2 = "Current profile is production";
        String msg2 = forEntity2.getBody();
        System.out.print("\nProduction? -> ");
        Assertions.assertEquals(msg2, msgExpected2);
    }

}

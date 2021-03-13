package ru.netology.springboot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

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
//    @Test
//    void contextLoads() {
//        var port1 = devApp.getMappedPort(8080);
//        ResponseEntity<String> forEntity1 = restTemplate.getForEntity(HOST + port1, String.class);
//        System.out.println(forEntity1.getBody());
//
//    }

    @Test
    void contextLoads() {
        var port1 = devApp.getMappedPort(8080);
        ResponseEntity<String> forEntity1 = restTemplate.getForEntity(HOST + port1, String.class);
        System.out.println("Port1: " + port1);
        String msgExpected1 = "Current profile is dev";
        String msg1 = forEntity1.getBody();
        System.out.print("Dev? -> ");
        Assert.assertEquals(msg1, msgExpected1);

        var port2 = prodApp.getMappedPort(8080);
        ResponseEntity<String> forEntity2 = restTemplate.getForEntity(HOST + port2, String.class);
        System.out.println("Port2: " + port2);
        String msgExpected2 = "Current profile is production";
        String msg2 = forEntity2.getBody();
        System.out.print("\nProduction? -> ");
        Assert.assertEquals(msg2, msgExpected2);

    }

}
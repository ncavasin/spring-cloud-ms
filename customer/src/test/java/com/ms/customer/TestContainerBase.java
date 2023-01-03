package com.ms.customer;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public abstract class TestContainerBase {
    private static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres:12.13-alpine");

}

package com.ms.customer;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public abstract class TestContainerBase {
//    private static final PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres:14.2-alpine");
        private static final PostgreSQLContainer POSTGRES_SQL_CONTAINER;

    static {
        POSTGRES_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:14.2-alpine");
        POSTGRES_SQL_CONTAINER.start();
    }

    @DynamicPropertySource
    static void overrideTestProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRES_SQL_CONTAINER::getPassword);
    }
}

/*
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class AbstractPostgreSQLTest {

    @Container
    private static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:11.5")
        .withDatabaseName("mydatabase")
        .withUsername("user")
        .withPassword("password");

    protected PostgreSQLContainer getPostgreSQLContainer() {
        return postgres;
    }
}

public class TestcontainerTest extends AbstractPostgreSQLTest {

    @Test
    public void test() {
        // your test code goes here
        PostgreSQLContainer postgres = getPostgreSQLContainer();
        // use postgres in your test code
    }
}


 */

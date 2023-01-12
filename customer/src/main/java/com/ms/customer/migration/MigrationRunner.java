package com.ms.customer.migration;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.internal.util.StopWatch;
import org.slf4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public record MigrationRunner(Logger logger,
                              List<StartUpMigration> startUpMigrations) implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("Migration runner initialised.");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        startUpMigrations.forEach(startUpMigration -> {
            if (startUpMigration.isActive()) {
                log.info("Initialising migration '{}'...", startUpMigration.getName());
                startUpMigration.run(event);
                log.info("Migration '{}' applied successfully.", startUpMigration.getName());
            }
        });
        stopWatch.stop();
        logger.info("Migration runner finished. Elapsed time is '{}' milliseconds.", stopWatch.getTotalTimeMillis());

    }
}

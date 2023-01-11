package com.ms.customer.migration;

import org.springframework.boot.context.event.ApplicationReadyEvent;

public interface StartUpMigration {
    void run(ApplicationReadyEvent applicationReadyEvent);

    String getName();

    boolean isActive();
}

package com.ms.customer.topic.repository;

import com.ms.customer.topic.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, String> {

    Optional<Topic> findByName(String name);

    boolean existsByName(String name);
}

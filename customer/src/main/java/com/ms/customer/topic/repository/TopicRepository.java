package com.ms.customer.topic.repository;

import com.ms.customer.topic.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, String> {

    @Query("select t from Topic t where t.name = UPPER(?1)")
    Optional<Topic> findByName(String name);

    boolean existsByName(String name);
}

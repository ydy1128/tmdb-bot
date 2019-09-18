package com.jyoon.tmdbbot.repository;

import com.jyoon.tmdbbot.engine.Entity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EntityRepository extends CrudRepository<Entity, Integer> {
    Entity findByTypeAndValue(String type, String value);

}

package org.tmf.openapi.catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.tmf.openapi.catalog.domain.catalog.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String>, QuerydslPredicateExecutor<Category> {

}




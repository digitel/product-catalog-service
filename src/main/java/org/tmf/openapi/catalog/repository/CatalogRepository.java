package org.tmf.openapi.catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.tmf.openapi.catalog.domain.catalog.Catalog;

@Repository
public interface CatalogRepository extends MongoRepository<Catalog, String>, QuerydslPredicateExecutor<Catalog> {

}




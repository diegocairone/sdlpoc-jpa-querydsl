package com.cairone.sdlpocjpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cairone.sdlpocjpa.entities.MonedaEntity;

public interface MonedaRepository extends JpaRepository<MonedaEntity, Integer>, QueryDslPredicateExecutor<MonedaEntity> {

}

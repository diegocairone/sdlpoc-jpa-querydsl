package com.cairone.sdlpocjpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cairone.sdlpocjpa.entities.PaisEntity;

public interface PaisRepository extends JpaRepository<PaisEntity, Integer>, QueryDslPredicateExecutor<PaisEntity>
{

}

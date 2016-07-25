package com.cairone.sdlpocjpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cairone.sdlpocjpa.entities.LocalidadEntity;
import com.cairone.sdlpocjpa.entities.LocalidadPKEntity;

public interface LocalidadRepository extends JpaRepository<LocalidadEntity, LocalidadPKEntity>, QueryDslPredicateExecutor<LocalidadEntity>
{

}

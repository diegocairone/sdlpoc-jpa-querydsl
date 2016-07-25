package com.cairone.sdlpocjpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cairone.sdlpocjpa.entities.ProvinciaEntity;
import com.cairone.sdlpocjpa.entities.ProvinciaPKEntity;

public interface ProvinciaRepository extends JpaRepository<ProvinciaEntity, ProvinciaPKEntity>, QueryDslPredicateExecutor<ProvinciaEntity>
{

}

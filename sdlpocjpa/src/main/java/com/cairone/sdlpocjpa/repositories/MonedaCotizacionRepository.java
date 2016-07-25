package com.cairone.sdlpocjpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cairone.sdlpocjpa.entities.MonedaCotizacionEntity;
import com.cairone.sdlpocjpa.entities.MonedaCotizacionPKEntity;

public interface MonedaCotizacionRepository extends JpaRepository<MonedaCotizacionEntity, MonedaCotizacionPKEntity>, QueryDslPredicateExecutor<MonedaCotizacionEntity> {

}

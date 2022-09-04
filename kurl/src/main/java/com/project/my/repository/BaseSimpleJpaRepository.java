package com.project.my.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class BaseSimpleJpaRepository<E, ID extends Serializable> extends SimpleJpaRepository<E, ID> implements BaseJpaRepository<E, ID> {
    public BaseSimpleJpaRepository(JpaEntityInformation<E, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
}

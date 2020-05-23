package com.ecommerce.service;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import lombok.NonNull;

@Transactional
public abstract class AbstractService<T> {
    @Produces
    @PersistenceContext
    protected EntityManager entityManager;
    
    public T create(@NonNull T entity){
        entityManager.persist(entity);
        return entity;
    }
    
    public T update(@NonNull T entity){
        entityManager.merge(entity);
        return entity;
    }
}

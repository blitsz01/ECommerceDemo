package com.ecommerce.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import lombok.NonNull;

@Transactional
public abstract class AbstractService<T> {
    
    @Inject
    EntityManager entityManager;
    
    public T create(@NonNull T entity){
        entityManager.persist(entity);
        return entity;
    }
    
    public T update(@NonNull T entity){
        entityManager.merge(entity);
        return entity;
    }
}

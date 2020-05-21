package com.ecommerce.dao;

import com.ecommerce.entity.Address;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class AddressDao {
    public List<Address> getAddress(EntityManager entityManager){
        return entityManager.createQuery("SELECT a FROM Address a", Address.class).getResultList();
    }
}

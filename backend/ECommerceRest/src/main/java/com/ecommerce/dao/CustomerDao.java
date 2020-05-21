package com.ecommerce.dao;

import com.ecommerce.entity.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class CustomerDao {
    public List<Customer> getCustomer(EntityManager entityManager){
        return entityManager.createQuery("SELECT c FROM CustOrder c", Customer.class).getResultList();
    }
}

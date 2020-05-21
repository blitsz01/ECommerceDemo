package com.ecommerce.dao;

import com.ecommerce.entity.PaymentMethod;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class PaymentMethodDao {
    public List<PaymentMethod> getPaymentMethod(EntityManager entityManager){
        return entityManager.createQuery("SELECT p FROM PaymentMethod p", PaymentMethod.class).getResultList();
    }
}

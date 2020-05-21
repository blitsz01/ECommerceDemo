package com.ecommerce.dao;

import com.ecommerce.entity.CustOrder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class CustOrderDao {
    public List<CustOrder> getCustOrder(EntityManager entityManager){
        return entityManager.createQuery("SELECT c FROM CustOrder c", CustOrder.class).getResultList();
    }
}

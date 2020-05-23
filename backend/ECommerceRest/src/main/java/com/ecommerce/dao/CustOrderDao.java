package com.ecommerce.dao;

import com.ecommerce.entity.CustOrder;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Stateless
public class CustOrderDao {
    /**
     * Get all CustOrder
     *
     * @param entityManager
     * @return 
     */
    public List<CustOrder> getCustOrder(EntityManager entityManager){
        TypedQuery<CustOrder> find = entityManager.createNamedQuery(CustOrder.FIND_ALL, CustOrder.class);
        return find.getResultList();
    }
    
    /**
     * Get all CustOrder
     *
     * @param entityManager
     * @param startDate
     * @param endDate
     * @return 
     */
    public List<CustOrder> searchByDateCustOrder(EntityManager entityManager, LocalDateTime startDate,  LocalDateTime endDate){
        TypedQuery<CustOrder> find = entityManager.createNamedQuery(CustOrder.SEARCH_BY_ORDER_DATE, CustOrder.class);
        find.setParameter("startDate", startDate);
        find.setParameter("endDate", endDate);
        return find.getResultList();
    }
    
    /**
     * Get all CustOrder
     *
     * @param entityManager
     * @param status
     * @return 
     */
    public List<CustOrder> searchByStatusCustOrder(EntityManager entityManager, String status){
        TypedQuery<CustOrder> find = entityManager.createNamedQuery(CustOrder.SEARCH_BY_ORDER_STATUS, CustOrder.class);
        find.setParameter("queryterm", status);
        return find.getResultList();
    }
}

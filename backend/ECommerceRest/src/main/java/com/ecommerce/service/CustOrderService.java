package com.ecommerce.service;

import com.ecommerce.dao.CustOrderDao;
import com.ecommerce.entity.CustOrder;
import java.time.LocalDateTime;
import java.util.List;
import javax.inject.Inject;

public class CustOrderService  extends AbstractService{
    
    @Inject
    CustOrderDao custOrderDao;
    
    public CustOrder createCustOrder(CustOrder custOrder){
        return (CustOrder) create(custOrder);
    }
    
    public CustOrder updateCustOrder(CustOrder custOrder){
        custOrder.setUpdatedOn(LocalDateTime.now());
        return (CustOrder) update(custOrder);
    }
    
    public CustOrder findCustOrderById(Long id) {
        return entityManager.find(CustOrder.class, id);
    }

    public List<CustOrder> getCustOrderList() {
        return custOrderDao.getCustOrder(entityManager);
    }
    
}

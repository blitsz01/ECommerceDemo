package com.ecommerce.service;

import com.ecommerce.dao.PaymentMethodDao;
import com.ecommerce.entity.PaymentMethod;
import java.time.LocalDateTime;
import java.util.List;
import javax.inject.Inject;

public class PaymentMethodService extends AbstractService{
    
    @Inject
    PaymentMethodDao paymentMethodDao;
    
    public PaymentMethod createPaymentMethod(PaymentMethod paymentMethod){
        return (PaymentMethod) create(paymentMethod);
    }
    
    public PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod){
        paymentMethod.setUpdatedOn(LocalDateTime.now());
        return (PaymentMethod) update(paymentMethod);
    }
    
    public PaymentMethod findPaymentMethodById(Long id) {
        return entityManager.find(PaymentMethod.class, id);
    }
    
    public List<PaymentMethod> getPaymentMethodList() {
        return paymentMethodDao.getPaymentMethod(entityManager);
    }
    
}

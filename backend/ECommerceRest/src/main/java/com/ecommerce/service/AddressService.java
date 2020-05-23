package com.ecommerce.service;

import com.ecommerce.dao.AddressDao;
import com.ecommerce.entity.Address;
import java.time.LocalDateTime;
import java.util.List;
import javax.inject.Inject;

public class AddressService extends AbstractService{
    
    @Inject
    AddressDao addressDao;
    
    public Address createAddress(Address address){
        return (Address) create(address);
    }
    
    public Address updateAddress(Address address){
        address.setUpdatedOn(LocalDateTime.now());
        return (Address) update(address);
    } 
    
    public Address findAddressById(Long id) {
        return entityManager.find(Address.class, id);
    }

    public List<Address> getAddressList() {
        return addressDao.getAddress(entityManager);
    }
}

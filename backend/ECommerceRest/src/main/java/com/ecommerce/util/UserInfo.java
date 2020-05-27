package com.ecommerce.util;

import com.ecommerce.entity.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
    private Long id;
    private String email;
    private String token;
    private String name;
    private Boolean isAdmin;
    private Customer customer;
}

package com.ecommerce.util;

import lombok.Data;

@Data
public class UserInfo {
    private Long id;
    private String email;
    private String token;
    private String name;
    private Boolean isAdmin;
}

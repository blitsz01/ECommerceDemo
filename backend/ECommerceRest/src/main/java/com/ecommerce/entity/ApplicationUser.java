package com.ecommerce.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import lombok.Data;

@Entity
@Data
@Table(name = "APPLICATION_USER")
@NamedQuery(name = ApplicationUser.FIND_USER_BY_CREDENTIALS, query = "select u from ApplicationUser  u where  u.email = :email")
public class ApplicationUser extends AbstractEntity{

    public static final String FIND_USER_BY_CREDENTIALS = "User.findUserByCredentials";

    private static final long serialVersionUID = -374680673437253616L;
    
    private String salt;
    
    @Basic(optional = false)
    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 30)
    @Column(name="NAME", length = 30, nullable = false)
    private String name;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_ADMIN", nullable = false)
    private Boolean isAdmin;

    @NotEmpty(message = "Email must be set")
    @Email(message = "The email must be in the form user@domain.com")
    @FormParam("email")
    private String email;


    @NotEmpty(message = "Email must be set")
    @Size(min = 8)
    @FormParam("password")
    private String password;

    


}

package com.ecommerce.service;

import com.ecommerce.entity.ApplicationUser;
import com.ecommerce.util.SecurityUtil;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.net.UnknownServiceException;
import java.util.Map;

@Stateless
public class UserService  extends AbstractService{
    @Inject
    private SecurityUtil securityUtil;

    public void saveUser(ApplicationUser applicationUser) {

        Map<String, String> credMap = securityUtil.hashPassword(applicationUser.getPassword());

        applicationUser.setPassword(credMap.get("hashedPassword"));
        applicationUser.setSalt(credMap.get("salt"));


        if (applicationUser.getId() == null) {
            entityManager.persist(applicationUser);

        } else {
            entityManager.merge(applicationUser);
        }

       // credMap = null;

    }
    
    public boolean authenticateUser(String email, String plainTextPassword) {

        ApplicationUser user = entityManager.createNamedQuery(ApplicationUser.FIND_USER_BY_CREDENTIALS, ApplicationUser.class)
                .setParameter("email", email.toLowerCase()).getResultList().get(0);

        if (user != null) {
            return securityUtil.passwordsMatch(user.getPassword(), user.getSalt(), plainTextPassword);
        }
        return false;

    }
    
    public ApplicationUser findUserByEmail(String email) {
        ApplicationUser user = entityManager.createNamedQuery(ApplicationUser.FIND_USER_BY_CREDENTIALS, ApplicationUser.class)
                .setParameter("email", email.toLowerCase()).getResultList().get(0);
        return user;
    }
}

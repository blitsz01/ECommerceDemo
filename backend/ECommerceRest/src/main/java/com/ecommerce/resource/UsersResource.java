package com.ecommerce.resource;

import com.ecommerce.entity.ApplicationUser;
import com.ecommerce.service.UserService;
import com.ecommerce.util.ApplicationState;
import com.ecommerce.util.SecurityUtil;
import com.ecommerce.util.UserInfo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("user")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    @Inject
    ApplicationState applicationState;
    
   // @Inject
   // JaxRsClient jaxRsClient; 
    
     @Inject
    private SecurityUtil securityUtil; 
     
    @Inject
    UserService userService;

    @Context
    private UriInfo uriInfo;

    @Inject
    private Logger logger;


    /**
     * Resource path =  //api/v1/user/new
     *
     * @param user
     * @return response OK
     */
    @Path("new")
    @POST
    public Response createUser(@Valid ApplicationUser user) {
        userService.saveUser(user);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(user.getId().toString()).build())
                .status(Response.Status.OK).build();
    }


    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("email") @NotEmpty(message = "Email must be set") String email,
                          @NotEmpty(message = "Password must be set") @FormParam("password") String password) {

          if (!securityUtil.authenticateUser(email, password)) {
                throw new SecurityException("Email or password incorrect");
            }
            
            String token = getToken(email);
            ApplicationUser user = userService.findUserByEmail(email);
            applicationState.setEmail(email);
            
            UserInfo userInfo = new UserInfo();
            userInfo.setId(user.getId());
            userInfo.setEmail(email);
            userInfo.setToken(token);
            userInfo.setName(user.getName());
            userInfo.setIsAdmin(user.getIsAdmin());
            
            return Response.ok(userInfo).header(AUTHORIZATION, "Bearer " + token).build();

        }



    private String getToken(String email) {
       Key key = securityUtil.generateKey(email);

        String token = Jwts.builder().setSubject(email).setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date()).setExpiration(securityUtil.toDate(LocalDateTime.now().plusMinutes(120)))
                .signWith(SignatureAlgorithm.HS512, key).setAudience(uriInfo.getBaseUri().toString())
                .compact();

        logger.log(Level.INFO, "Generated token is {0}", token);

        return token;
    }

}





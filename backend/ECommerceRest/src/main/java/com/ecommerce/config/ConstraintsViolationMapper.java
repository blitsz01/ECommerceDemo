package com.ecommerce.config;

import java.util.HashMap;
import java.util.Map;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintsViolationMapper implements ExceptionMapper<ConstraintViolationException>{

    @Override
    public Response toResponse(ConstraintViolationException e) {
        final Map<String, String> constraintsViolations = new HashMap<>();
        
        for(ConstraintViolation cv : e.getConstraintViolations()){
            String path = cv.getPropertyPath().toString();
            constraintsViolations.put(path, cv.getMessage());
        }
        
        return Response.status(Response.Status.PRECONDITION_FAILED).entity(constraintsViolations).build();
    }
    
}

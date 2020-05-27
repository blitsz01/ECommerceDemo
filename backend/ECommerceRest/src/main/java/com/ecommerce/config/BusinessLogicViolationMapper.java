
package com.ecommerce.config;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessLogicViolationMapper  implements ExceptionMapper<BusinessLogicException>{

    @Override
    public Response toResponse(BusinessLogicException e) {
        final Map<String, String> businessViolations = new HashMap<>();
        businessViolations.put("error", e.getMessage());
        return Response.status(Response.Status.PRECONDITION_FAILED).entity(businessViolations).build();
    }
    
}

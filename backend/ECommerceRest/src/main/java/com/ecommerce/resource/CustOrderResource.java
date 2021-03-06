package com.ecommerce.resource;

import com.ecommerce.config.Secure;
import com.ecommerce.entity.CustOrder;
import com.ecommerce.service.CustOrderService;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("custOrder")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustOrderResource {
    
    @Inject
    CustOrderService custOrderService;
    
    @Inject 
    Logger logger;
    /**
     * Resource path = //api/v1/custOrder/new
     *
     * @param custOrder
     * @return custOrder
     */
    @Path("new")
    @POST
    @Secure
    public Response createCustOrder(CustOrder custOrder){
        custOrderService.createCustOrder(custOrder);
        return Response.ok(custOrder).build();
    }
    
    /**
     * Resource path = //api/v1/custOrder/update
     *
     * @param custOrder
     * @return custOrder
     */
    @Path("update")
    @PUT
    @Secure
    public Response updateCustOrder(CustOrder custOrder){
        custOrderService.updateCustOrder(custOrder);
        return Response.ok(custOrder).build();
    }
     
    /**
     * Resource path = //api/v1/custOrder/{id}
     *
     * @param id
     * @return custOrder
     */
    @Path("{id}")
    @GET
    @Secure
    public CustOrder getCustOrderList(@PathParam("id") Long id){
        return custOrderService.findCustOrderById(id);
    }
    
    /**
     * Resource path = //api/v1/custOrder/list
     *
     * @return custOrderList
     */
    @Path("list")
    @GET
    @Secure
    public List<CustOrder> getCustOrderList(){
        return custOrderService.getCustOrderList();
    }
    
    /**
     * Resource path = //api/v1/custOrder/order
     *
     * @param order
     * @return custOrder
     */
    @Path("order")
    @POST
    @Secure
    public Response createOrder(CustOrder custOrder){
       
        custOrderService.createOrder(custOrder);
        
        return Response.ok(custOrder).build();
    }
}

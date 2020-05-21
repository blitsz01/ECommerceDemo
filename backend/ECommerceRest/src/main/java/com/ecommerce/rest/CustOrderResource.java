package com.ecommerce.rest;

import com.ecommerce.entity.CustOrder;
import com.ecommerce.entity.Supplier;
import com.ecommerce.service.CustOrderService;
import java.util.List;
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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustOrderResource {
    
    @Inject
    CustOrderService custOrderService;
    
    @Path("new")
    @POST
    public Response createCustOrder(CustOrder custOrder){
        //api/v1/custOrder/new
        custOrderService.createCustOrder(custOrder);
        return Response.ok(custOrder).build();
    }
    
    @Path("update")
    @PUT
    public Response updateCustOrder(CustOrder custOrder){
        //api/v1/custOrder/update
        custOrderService.updateCustOrder(custOrder);
        return Response.ok(custOrder).build();
    }
    
    @Path("{id}")
    @GET
    public CustOrder getCustOrderList(@PathParam("id") Long id){
        //api/v1/custOrder/{id}
        return custOrderService.findCustOrderById(id);
    }
    
    @Path("list")
    @GET
    public List<CustOrder> getCustOrderList(){
        //api/v1/custOrder/list
        return custOrderService.getCustOrderList();
    }
}

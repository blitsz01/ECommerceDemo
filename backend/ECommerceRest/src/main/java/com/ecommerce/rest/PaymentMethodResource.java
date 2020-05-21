package com.ecommerce.rest;

import com.ecommerce.entity.PaymentMethod;
import com.ecommerce.service.PaymentMethodService;
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

@Path("paymentMethod")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentMethodResource {
     @Inject
    PaymentMethodService paymentMethodService;
    
    @Path("new")
    @POST
    public Response createPaymentMethod(PaymentMethod paymentMethod){
        //api/v1/paymentMethod/new
        System.out.print(paymentMethod);
        paymentMethodService.createPaymentMethod(paymentMethod);
        return Response.ok(paymentMethod).build();
    }
    
    @Path("update")
    @PUT
    public Response updatePaymentMethod(PaymentMethod paymentMethod){
        //api/v1/paymentMethod/update
        paymentMethodService.updatePaymentMethod(paymentMethod);
        return Response.ok(paymentMethod).build();
    }
    
    @Path("{id}")
    @GET
    public PaymentMethod getPaymentMethod(@PathParam("id") Long id){
        //api/v1/paymentMethod/{id}
        return paymentMethodService.findPaymentMethodById(id);
    }
    
    @Path("list")
    @GET
    public List<PaymentMethod> getPaymentMethodList(){
        //api/v1/paymentMethod/list
        return paymentMethodService.getPaymentMethodList();
    }
}

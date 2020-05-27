package com.ecommerce.resource;

import com.ecommerce.config.Secure;
import com.ecommerce.entity.PaymentMethod;
import com.ecommerce.service.PaymentMethodService;
import java.util.List;
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

@Path("paymentMethod")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentMethodResource {
     @Inject
    PaymentMethodService paymentMethodService;
    
    /**
     * Resource path =  //api/v1/paymentMethod/new
     *
     * @param paymentMethod
     * @return paymentMethod
     */
    @Path("new")
    @POST
    public Response createPaymentMethod(PaymentMethod paymentMethod){
        System.out.print(paymentMethod);
        paymentMethodService.createPaymentMethod(paymentMethod);
        return Response.ok(paymentMethod).build();
    }
    
    /**
     * Resource path =  //api/v1/paymentMethod/update
     *
     * @param paymentMethod
     * @return paymentMethod
     */
    @Path("update")
    @PUT
    public Response updatePaymentMethod(PaymentMethod paymentMethod){
        paymentMethodService.updatePaymentMethod(paymentMethod);
        return Response.ok(paymentMethod).build();
    }
    
    /**
     * Resource path =  //api/v1/paymentMethod/{id}
     *
     * @param id
     * @return paymentMethod
     */
    @Path("{id}")
    @GET
    public PaymentMethod getPaymentMethod(@PathParam("id") Long id){
        return paymentMethodService.findPaymentMethodById(id);
    }
    
    /**
     * Resource path =  //api/v1/paymentMethod/{id}
     *
     * @return paymentMethodList
     */
    @Path("list")
    @GET
    public List<PaymentMethod> getPaymentMethodList(){
        //api/v1/paymentMethod/list
        return paymentMethodService.getPaymentMethodList();
    }
}

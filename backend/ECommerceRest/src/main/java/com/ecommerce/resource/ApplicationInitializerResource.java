package com.ecommerce.resource;

import com.ecommerce.entity.ApplicationUser;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Supplier;
import com.ecommerce.entity.enums.ProductCategory;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.SupplierService;
import com.ecommerce.service.UserService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("initial")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ApplicationInitializerResource {
    
    @Inject
    UserService userService;
     
    @Inject
    SupplierService supplierService;
    
    @Inject
    ProductService productService;
     
    /**
     * Resource path =  //api/v1/supplier/new
     *
     * @return response
     */
    @Path("new")
    @POST
    public Response initializeData(){
        //Admin user
        ApplicationUser user = new ApplicationUser();
        Supplier supplier = new Supplier();
        Product product = new Product();
        
        //id=1
        user.setEmail("admin@test.com");
        user.setName("Jonathan Riano");
        user.setPassword("admin123");
        user.setIsAdmin(Boolean.TRUE);
        userService.saveUser(user);
        
        //Initial Supplier
        supplier.setName("Ecommerce Supplier");
        supplier.setDetails("Initial data");
        supplierService.createSupplier(supplier);
        supplier = supplierService.findSupplierById(2L);
        
        //Initial Product
        product = new Product();
        product.setName("Acer Nitro Gaming");
        product.setDescription("Initial data");
        product.setBrand("Acer");
        product.setSupplier(supplier);
        product.setProductCategory(ProductCategory.GADGETS);
        product.setCountInStock(4);
        product.setNumReviews(14);
        product.setRating(4);
        product.setPrice(43000);
        productService.createProduct(product);
        
        product = new Product();
        product.setName("Asus Rog Mobile");
        product.setDescription("Initial data");
        product.setBrand("Asus");
        product.setSupplier(supplier);
        product.setProductCategory(ProductCategory.GADGETS);
        product.setCountInStock(15);
        product.setNumReviews(6);
        product.setRating(4);
        product.setPrice(45000);
        productService.createProduct(product);
        
        product = new Product();
        product.setName("Mobile controller");
        product.setDescription("Initial data");
        product.setBrand("Capdase");
        product.setSupplier(supplier);
        product.setProductCategory(ProductCategory.ACCESSORIES);
        product.setCountInStock(15);
        product.setNumReviews(6);
        product.setRating(4);
        product.setPrice(45000);
        productService.createProduct(product);
        
        product = new Product();
        product.setName("Gold Ring");
        product.setDescription("Initial data");
        product.setBrand("Tiffany & Co.");
        product.setSupplier(supplier);
        product.setProductCategory(ProductCategory.JEWELRY);
        product.setCountInStock(12);
        product.setNumReviews(4);
        product.setRating(3);
        product.setPrice(5000);
        productService.createProduct(product);
        
        product = new Product();
        product.setName("Silver Necklace");
        product.setDescription("Initial data");
        product.setBrand("Tiffany & Co.");
        product.setSupplier(supplier);
        product.setProductCategory(ProductCategory.JEWELRY);
        product.setCountInStock(12);
        product.setNumReviews(13);
        product.setRating(5);
        product.setPrice(3000);
        productService.createProduct(product);
        
        
                
        //Initial Supplier
        supplier = new Supplier();
        supplier.setName("Lazada Supplier");
        supplier.setDetails("Initial data");
        supplierService.createSupplier(supplier);
        supplier = supplierService.findSupplierById(8L);
        
        product = new Product();
        product.setName("White Shirt Nike");
        product.setDescription("Initial data");
        product.setBrand("Nike");
        product.setSupplier(supplier);
        product.setProductCategory(ProductCategory.FASHION);
        product.setCountInStock(17);
        product.setNumReviews(12);
        product.setRating(4);
        product.setPrice(2000);
        productService.createProduct(product);
        
        product = new Product();
        product.setName("Bench Polo");
        product.setDescription("Initial data");
        product.setBrand("Bench");
        product.setSupplier(supplier);
        product.setProductCategory(ProductCategory.FASHION);
        product.setCountInStock(0);
        product.setNumReviews(12);
        product.setRating(4);
        product.setPrice(700);
        productService.createProduct(product);
        
        product = new Product();
        product.setName("Blazer Rainbow");
        product.setDescription("Initial data");
        product.setBrand("Guess");
        product.setSupplier(supplier);
        product.setProductCategory(ProductCategory.FASHION);
        product.setCountInStock(3);
        product.setNumReviews(12);
        product.setRating(4);
        product.setPrice(1200);
        productService.createProduct(product);
        
        product = new Product();
        product.setName("Set Dream Pineapple");
        product.setDescription("Initial data");
        product.setBrand("Natasha");
        product.setSupplier(supplier);
        product.setProductCategory(ProductCategory.FASHION);
        product.setCountInStock(0);
        product.setNumReviews(12);
        product.setRating(5);
        product.setPrice(800);
        productService.createProduct(product);
        
        product = new Product();
        product.setName("White Shoes");
        product.setDescription("Initial data");
        product.setBrand("Marikina and Co");
        product.setSupplier(supplier);
        product.setProductCategory(ProductCategory.SHOES);
        product.setCountInStock(3);
        product.setNumReviews(12);
        product.setRating(4);
        product.setPrice(400);
        productService.createProduct(product);
        
        product = new Product();
        product.setName("Running Shoes");
        product.setDescription("Initial data");
        product.setBrand("Marikina and Co");
        product.setSupplier(supplier);
        product.setProductCategory(ProductCategory.SHOES);
        product.setCountInStock(14);
        product.setNumReviews(15);
        product.setRating(2);
        product.setPrice(500);
        productService.createProduct(product);
        
        return Response.ok().build();
    }
}

package com.ecommerce.util;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import lombok.Data;

@SessionScoped
@Data
public class ApplicationState implements Serializable {

    private static final long serialVersionUID = 4495657636657444254L;
    private String email;
}

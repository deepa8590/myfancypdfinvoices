package com.marcobehler.myfancypdfinvoices.context;

import com.marcobehler.myfancypdfinvoices.service.InvoiceService;
import com.marcobehler.myfancypdfinvoices.service.UserService;

import tools.jackson.databind.ObjectMapper;

public class Application {
    
   //Make sure that the UserService gets created before the InvoiceService
    public static final UserService userService = new UserService();
    public static final InvoiceService invoiceService = new InvoiceService(userService);
    public static final ObjectMapper objectMapper = new ObjectMapper();

    
}

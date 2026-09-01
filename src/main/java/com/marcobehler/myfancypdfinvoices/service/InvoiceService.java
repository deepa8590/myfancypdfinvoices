package com.marcobehler.myfancypdfinvoices.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.marcobehler.myfancypdfinvoices.model.Invoice;
import com.marcobehler.myfancypdfinvoices.model.User;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class InvoiceService {
    //CopyOnWriteArrayList is a thread-safe variant of ArrayList(Whereas ArrayList wuld not be ) in which all mutative operations (add, set, and so on) are implemented by making a fresh copy of the underlying array.
    //use when read opr are more frequent than write operations, and when you need to iterate over the list without worrying about concurrent modifications.
    List<Invoice> invoices = new CopyOnWriteArrayList<>(); // 

    private final UserService userService;
    private final String cdnUrl;

    
    @PostConstruct
    public void init(){
        System.out.println("InvoiceService initialized");
        //only postcosntruct bcs we did not explictily shutdown ApllicationContext, so the destroy method will not be called.
    }

    @PreDestroy
    public void shutdown(){
        System.out.println("InvoiceService destroyed");
    }

    @Autowired
    public InvoiceService(UserService userService , @Value("${cdn.url}") String cdnUrl) {
        this.userService = userService;
        this.cdnUrl = cdnUrl;
    }

    public List<Invoice> findAll() {
        return invoices;
    }

    //Before you create an invoice, you are now constructing a new UserService and checking if that users exists.

    public Invoice create(String userId, Integer amount) {
//   Everyone who tries to construct an InvoiceService needs to pass a UserService
        User user = userService.findById(userId);
         if (user == null) {
            throw new IllegalStateException();
        }


        // TODO real pdf creation and storing it on network server
        Invoice invoice = new Invoice(userId, amount, cdnUrl + "/images/default/sample.pdf");
        invoices.add(invoice);
        return invoice;
    }
}

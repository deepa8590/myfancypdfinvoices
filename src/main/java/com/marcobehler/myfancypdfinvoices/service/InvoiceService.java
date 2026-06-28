package com.marcobehler.myfancypdfinvoices.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.marcobehler.myfancypdfinvoices.model.Invoice;
import com.marcobehler.myfancypdfinvoices.model.User;

public class InvoiceService {
    private final UserService userService;


    //CopyOnWriteArrayList is a thread-safe variant of ArrayList(Whereas ArrayList wuld not be ) in which all mutative operations (add, set, and so on) are implemented by making a fresh copy of the underlying array.
    //use when read opr are more frequent than write operations, and when you need to iterate over the list without worrying about concurrent modifications.
    List<Invoice> invoices = new CopyOnWriteArrayList<>(); // 
    
    public InvoiceService(UserService userService) {
        this.userService = userService;
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
        Invoice invoice = new Invoice(userId, amount, "http://www.africau.edu/images/default/sample.pdf");
        invoices.add(invoice);
        return invoice;
    }
}

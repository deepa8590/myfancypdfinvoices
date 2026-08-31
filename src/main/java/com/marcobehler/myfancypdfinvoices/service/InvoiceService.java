package com.marcobehler.myfancypdfinvoices.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marcobehler.myfancypdfinvoices.model.Invoice;
import com.marcobehler.myfancypdfinvoices.model.User;

@Component
public class InvoiceService {
    private  UserService userService;

    //CopyOnWriteArrayList is a thread-safe variant of ArrayList(Whereas ArrayList wuld not be ) in which all mutative operations (add, set, and so on) are implemented by making a fresh copy of the underlying array.
    //use when read opr are more frequent than write operations, and when you need to iterate over the list without worrying about concurrent modifications.
    List<Invoice> invoices = new CopyOnWriteArrayList<>(); // 
    
    // public InvoiceService(UserService userService) {
    //     this.userService = userService;
    // }

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

    //Setter Injection: If you provide a setter, you can manually call invoiceService.setUserService(mockUserService) in a test when you aren't using Spring to wire beans for you.
    @Autowired
    public void SetUserService(UserService userService) {
        this.userService = userService;
    }
}

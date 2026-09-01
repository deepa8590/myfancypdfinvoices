package com.marcobehler.myfancypdfinvoices.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.marcobehler.myfancypdfinvoices.model.Invoice;

import jakarta.annotation.PostConstruct;

@Service    // for compeleteness, but not really needed, because the InvoiceService is already a Spring Bean
@Profile("dev")  //diff environment, only load this bean in dev environment
public class DummyInvoiceServiceLoader {
     private final InvoiceService invoiceService;

    public DummyInvoiceServiceLoader(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostConstruct
    public void setup() {
        System.out.println("Creating dev invoices...");
        Invoice first = invoiceService.create("someUserId", 50);
        Invoice second = invoiceService.create("someOtherUserId", 100);
        System.out.println("Created first invoice: " + first);
        System.out.println("Created second invoice: " + second);

    }
    
}

package com.marcobehler.myfancypdfinvoices.web;
import com.marcobehler.myfancypdfinvoices.model.Invoice;
import com.marcobehler.myfancypdfinvoices.service.InvoiceService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//so that Spring’s @ComponentScan can find it and make Spring MVC understand: This class can accept HTTP requests
//it consists of the @Controller and @ResponseBody annotation , @ResponseBody tells Spring, that you want to write JSON or XML or plain text directly to the HttpServletOutputstream,
@RestController
public class MyFancyPdfInvoicesController {
    private final InvoiceService invoiceService;

    //constructor-injecting the InvoiceService.
    public MyFancyPdfInvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    //no more manual JSON conversion going on here anymore compared to your servlet. You return plain Java objects and Spring needs to do the conversion for you!
    @GetMapping ("/invoices")
    //@GetMapping("/invoices") is a shorthand annotation fo
    // @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    public List<Invoice> Invoices() {
        return invoiceService.findAll();
    }

    @PostMapping ("/invoices")
    // tell Spring to expect two mandatory parameters with exactly those names (user_id, amount).
    public Invoice createInvoice(@RequestParam("user_id") String userId, @RequestParam("amount") Integer amount) {
        return invoiceService.create(userId, amount);
    }
}

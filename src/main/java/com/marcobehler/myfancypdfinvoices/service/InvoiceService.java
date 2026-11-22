package com.marcobehler.myfancypdfinvoices.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.marcobehler.myfancypdfinvoices.model.Invoice;
import com.marcobehler.myfancypdfinvoices.model.User;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.util.UUID;


@Component
public class InvoiceService {
    //CopyOnWriteArrayList is a thread-safe variant of ArrayList(Whereas ArrayList wuld not be ) in which all mutative operations (add, set, and so on) are implemented by making a fresh copy of the underlying array.
    //use when read opr are more frequent than write operations, and when you need to iterate over the list without worrying about concurrent modifications.
    List<Invoice> invoices = new CopyOnWriteArrayList<>(); // 

    private final JdbcTemplate jdbcTemplate;
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
    public InvoiceService(UserService userService , @Value("${cdn.url}") String cdnUrl , JdbcTemplate jdbcTemplate) {
        this.userService = userService;
        this.cdnUrl = cdnUrl;
        this.jdbcTemplate = jdbcTemplate;
    }


    //jdbcTemplate 's query method, which lets you specify the SQL query as the first parameter, and a row mapper. The row mapper lets you map every returned SQL row into a Java object, 
    @Transactional 
    public List<Invoice> findAll() {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());
        return jdbcTemplate.query("select id, pdf_url, user_id, amount from invoices", (resultSet , rowNum)-> {
            Invoice invoice = new Invoice();
            invoice.setId(resultSet.getString("id"));
            invoice.setPdfUrl(resultSet.getString("pdf_url"));
            invoice.setUserId(resultSet.getString("user_id"));
            invoice.setAmount(resultSet.getInt("amount"));
            return invoice;
        });
    }

    //Before you create an invoice, you are now constructing a new UserService and checking if that users exists.
    @Transactional
    public Invoice create(String userId, Integer amount) {
    System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());
        String generatedPdfUrl = cdnUrl + "/images/default/sample.pdf";
        //DBC driver will make sure to make the generated id available to you via a KeyHolder object, that you need to pass into the JDBCTemplate
        KeyHolder keyHolder = new GeneratedKeyHolder();
       
        jdbcTemplate.update(connection -> {
        PreparedStatement ps =connection.prepareStatement("insert into invoices (pdf_url, user_id, amount) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, userId);
        ps.setString(2, generatedPdfUrl);
        ps.setInt(3, amount);
        return ps;
    }, keyHolder);

    // The GeneratedKeyholder contains a convenience method to easily return numeric generated keys
        String uuid = !keyHolder.getKeys().isEmpty() ? ((UUID) keyHolder.getKeys().values().iterator().next()).toString() : null;

        Invoice invoice = new Invoice();
        invoice.setId(uuid);
        invoice.setPdfUrl(generatedPdfUrl);
        invoice.setUserId(userId);
        invoice.setAmount(amount);
        return invoice;
}
}

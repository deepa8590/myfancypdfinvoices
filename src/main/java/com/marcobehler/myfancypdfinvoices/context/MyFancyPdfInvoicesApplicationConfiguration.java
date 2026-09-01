package com.marcobehler.myfancypdfinvoices.context;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.marcobehler.ApplicationLauncher;
import com.marcobehler.myfancypdfinvoices.service.InvoiceService;
import com.marcobehler.myfancypdfinvoices.service.UserService;

import tools.jackson.databind.ObjectMapper;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
@PropertySource("classpath:application.properties")
@PropertySource(value = "classpath:/application-${spring.profiles.active}.properties", ignoreResourceNotFound = true)
//The order of these PropertySources is important, with the one specified at the bottom having precedence
//if the specified file does not exist, hence you set the ignoreResourceNotFound flag to true.
public class MyFancyPdfInvoicesApplicationConfiguration {
    // // @Bean
    // public InvoiceService invoiceService() {
    //     return new InvoiceService(userService(), cdnUrl());
    // }

    // @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}

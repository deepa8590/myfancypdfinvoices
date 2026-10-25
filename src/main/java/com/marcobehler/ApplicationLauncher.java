package com.marcobehler;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.marcobehler.myfancypdfinvoices.context.MyFancyPdfInvoicesApplicationConfiguration;
import jakarta.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class ApplicationLauncher {

    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8081);
        tomcat.getConnector();

        Context tomcatContext = tomcat.addContext("", null);
        WebApplicationContext applicationContext = createApplicationContext(tomcatContext.getServletContext());
        //The one and only entry point for Spring WebMVC
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        Wrapper servlet = Tomcat.addServlet(tomcatContext, "dispatcherServlet", dispatcherServlet);
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");

        tomcat.start();
    }

    //Creating the ApplicationContext : You need to tell it about your SpringConfiguration, as well as the ServletContext
    public static WebApplicationContext createApplicationContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MyFancyPdfInvoicesApplicationConfiguration.class);
        ctx.setServletContext(servletContext);
        ctx.refresh();
        ctx.registerShutdownHook();
        return ctx;
    }
}
package com.vladimir.curso.springboot.di.factura.springbootdifactura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vladimir.curso.springboot.di.factura.springbootdifactura.models.Client;
import com.vladimir.curso.springboot.di.factura.springbootdifactura.models.Invoice;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {


    @Autowired
    private Invoice invoice;  //? Aqui se inyecta un proxy con el @RequestScope y no el objeot original de Invoice.java

    @GetMapping("/show")
    public Invoice show(){
        return invoice;
    }

    @GetMapping("/shownoproxy")  //Con esto se evita el proxy
    public Invoice shownoproxy(){
        Invoice i = new Invoice();
        Client c = new Client();
        c.setLastname(invoice.getClient().getLastname());
        c.setName(invoice.getClient().getName());
        i.setClient(c);
        i.setDescription(invoice.getDescription());
        i.setItems(invoice.getItems());
        return i;
    }
}

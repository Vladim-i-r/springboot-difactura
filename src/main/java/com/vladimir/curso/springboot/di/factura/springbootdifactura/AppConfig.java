package com.vladimir.curso.springboot.di.factura.springbootdifactura;

import java.util.Arrays;
import java.util.List;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.vladimir.curso.springboot.di.factura.springbootdifactura.models.Item;
import com.vladimir.curso.springboot.di.factura.springbootdifactura.models.Product;

@Configuration
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class AppConfig {

    @Bean                               //?  Con @Bean ya se vuelve componente, por lo que ya se puede inyectar a Invoice
    @Primary
    List<Item> itemsInvoice(){
        Product p1 = new Product("Camara Sony",800);  // Para quitar el name: y price: es ir al folder vscode>settings.json y poner esto: "editor.inlayHints.enabled":"off"
        Product p2= new Product("Bicicleta Giant",1200);
        return Arrays.asList(new Item(p1,2), new Item(p2,4));
    }

    @Bean("office")
    List<Item> itemsInvoiceOffice(){
        Product p1 = new Product("Monitor ASUS 24",1800);  // Para quitar el name: y price: es ir al folder vscode>settings.json y poner esto: "editor.inlayHints.enabled":"off"
        Product p2= new Product("Notebook Google",1300);
        Product p3= new Product("Impresora HP",800);
        Product p4= new Product("Escritorio",400);
        return Arrays.asList(new Item(p1,4), new Item(p2,6), new Item(p3,1), new Item(p4,1));
    }
}

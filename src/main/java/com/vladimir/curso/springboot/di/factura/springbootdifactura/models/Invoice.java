package com.vladimir.curso.springboot.di.factura.springbootdifactura.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@RequestScope                                       // Es compartido con toda la aplicacion por cada request, se genera un proxy
@JsonIgnoreProperties({"targetSource","advisors"})  // Usando Postman, aparece un error y con esto lo omitimos, Ej:  com.vladimir.curso.springboot.di.factura.springbootdifactura.models.Invoice$$SpringCGLIB$$0[\"advisors\"]
public class Invoice { //Factura

    @Autowired
    private Client client;  

    @Value("${invoice.description.office}")
    private String description;

    @Autowired
    @Qualifier("office")
    private List<Item> items; // ! No se puede agregar @Value ya que List es una interfaz, no es componente de SpringB, por lo que se debe crear desde un metodo @Bean en AppConfig y poner @Autowired

    // * CICLO DE VIDA DE BEANS
    @PostConstruct   //  Se usa despues que se haya inyectado o usado, distinto del Constructor vacio
    public void init(){ //  Siempre debajo de los atributos y antes de los getters/setters, se coloca el ciclo de vida
        System.out.println("Creando el componente de la factura");
        client.setName(client.getName().concat(" Alan"));;
        description = description.concat(" del cliente: ").concat(client.getName()).concat(" ").concat(client.getLastname());
    }

    @PreDestroy   //  Cuando finaliza el contexto, manda llamar este metodo, limpieza o algo 
    public void destroy(){
        System.out.println("Destruyendo el componente o bean invoice");
    }

    //* */ ^^^^^^^^ */

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {  
        this.items = items;
    }

    public int getTotal(){  // Sin definir la variable total, el mismo programa la crea y muestra en el json como "total:"
        // int total = 0;
        // for(Item item :  items){
        //     total += item.getImporte(); //? Es lo mismo 
        // }
        int total = items.stream()
        .map(item -> item.getImporte()) //? Convertir el flujo al tipo importe de tipo int
        .reduce(0, (sum, importe) -> sum + importe); //? suma mas el importe
        return total;
    }
    
}

package com.company.springexample;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductRepository {

    public List<Product> getItems(List<Product> items){
        return items.stream()
                .peek(product -> product.setPrice((double) Math.round(30 * Math.random())))
                .peek(p -> System.out.println("Original price of: " + p.getName() + " is " + p.getPrice()))
                .collect(Collectors.toList());
    }
}

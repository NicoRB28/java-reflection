package com.company.springexample;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getFinalPrice(List<Product> items){
        List<Product> list = repo.getItems(items);
        return list.stream()
                .peek(product -> product.setPrice(product.getPrice() * (100 - product.getDiscount())/100))
                .peek(System.out::println)
                .collect(Collectors.toList());
    }
}

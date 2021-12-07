package com.company.springexample;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IllegalAccessException {
        ApplicationContext context = new ApplicationContext(AppConfig.class);

        ProductService service = context.getBean(ProductService.class);
        List<Product> items = Arrays.asList(
                new Product("Chicken", 40),
                new Product("Coffe", 10),
                new Product("Tea",3)
        );
        service.getFinalPrice(items)
                .forEach(System.out::println);
    }
}

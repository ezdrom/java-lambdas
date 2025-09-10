package com.example.service;

import com.example.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    
    private List<Product> products = Arrays.asList(
        new Product(1L, "Laptop", 999.99, "Electronics", true),
        new Product(2L, "Coffee Mug", 12.50, "Kitchen", true),
        new Product(3L, "Smartphone", 699.99, "Electronics", false),
        new Product(4L, "Desk Chair", 199.99, "Furniture", true),
        new Product(5L, "Headphones", 149.99, "Electronics", true),
        new Product(6L, "Water Bottle", 24.99, "Kitchen", false)
    );

    // Lambda demo: Filter products using Predicate functional interface
    public List<Product> getProductsByCategory(String category) {
        Predicate<Product> categoryFilter = product -> product.getCategory().equals(category);
        return products.stream()
                .filter(categoryFilter)
                .collect(Collectors.toList());
    }

    // Lambda demo: Transform products using Function interface
    public List<String> getProductNames() {
        Function<Product, String> nameExtractor = product -> product.getName().toUpperCase();
        return products.stream()
                .map(nameExtractor)
                .collect(Collectors.toList());
    }

    // Lambda demo: Complex filtering and mapping
    public List<Product> getAffordableInStockProducts(double maxPrice) {
        return products.stream()
                .filter(product -> product.isInStock())           // Lambda: check stock
                .filter(product -> product.getPrice() <= maxPrice) // Lambda: price filter
                .sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice())) // Lambda: sort by price
                .collect(Collectors.toList());
    }

    // Lambda demo: Consumer interface for processing
    public void printProductDetails() {
        Consumer<Product> printer = product -> 
            System.out.println("Processing: " + product.getName() + " - $" + product.getPrice());
        
        products.forEach(printer);
    }

    // Lambda demo: Supplier interface for generating data
    public Product createRandomProduct() {
        Supplier<Product> productGenerator = () -> {
            Random random = new Random();
            return new Product(
                (long) random.nextInt(1000),
                "Random Product " + random.nextInt(100),
                random.nextDouble() * 500,
                "Random Category",
                random.nextBoolean()
            );
        };
        return productGenerator.get();
    }

    // Lambda demo: Custom functional interface
    @FunctionalInterface
    public interface PriceCalculator {
        double calculate(double price, double discount);
    }

    public List<Product> applyDiscount(double discountPercent) {
        PriceCalculator calculator = (price, discount) -> price * (1 - discount / 100);
        
        return products.stream()
                .map(product -> {
                    Product discountedProduct = new Product(
                        product.getId(),
                        product.getName(),
                        calculator.calculate(product.getPrice(), discountPercent),
                        product.getCategory(),
                        product.isInStock()
                    );
                    return discountedProduct;
                })
                .collect(Collectors.toList());
    }

    // Lambda demo: Method references
    public Map<String, List<Product>> groupByCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getCategory)); // Method reference
    }

    // Lambda demo: Optional with lambdas
    public Optional<Product> findMostExpensive() {
        return products.stream()
                .max(Comparator.comparing(Product::getPrice)); // Method reference
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
}
package com.example.controller;

import com.example.model.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET /api/products - Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET /api/products/category/{category} - Filter by category using lambda
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    // GET /api/products/names - Get product names (transformed using lambda)
    @GetMapping("/names")
    public List<String> getProductNames() {
        return productService.getProductNames();
    }

    // GET /api/products/affordable?maxPrice=200 - Filter affordable in-stock products
    @GetMapping("/affordable")
    public List<Product> getAffordableProducts(@RequestParam double maxPrice) {
        return productService.getAffordableInStockProducts(maxPrice);
    }

    // POST /api/products/discount?percent=10 - Apply discount using custom functional interface
    @PostMapping("/discount")
    public List<Product> applyDiscount(@RequestParam double percent) {
        return productService.applyDiscount(percent);
    }

    // GET /api/products/grouped - Group products by category using method reference
    @GetMapping("/grouped")
    public Map<String, List<Product>> getGroupedProducts() {
        return productService.groupByCategory();
    }

    // GET /api/products/most-expensive - Find most expensive product using Optional
    @GetMapping("/most-expensive")
    public ResponseEntity<Product> getMostExpensive() {
        Optional<Product> product = productService.findMostExpensive();
        return product.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/products/random - Generate random product using Supplier
    @GetMapping("/random")
    public Product getRandomProduct() {
        return productService.createRandomProduct();
    }

    // POST /api/products/process - Demonstrate Consumer lambda (prints to console)
    @PostMapping("/process")
    public ResponseEntity<String> processProducts() {
        productService.printProductDetails();
        return ResponseEntity.ok("Products processed - check console output");
    }
}
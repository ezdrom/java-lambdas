# Lambda Function Demo Microservice

A Spring Boot microservice demonstrating Java 8+ lambda functions in practical scenarios.

## Lambda Features Demonstrated

### 1. Predicate Interface
```java
Predicate<Product> categoryFilter = product -> product.getCategory().equals(category);
```

### 2. Function Interface
```java
Function<Product, String> nameExtractor = product -> product.getName().toUpperCase();
```

### 3. Consumer Interface
```java
Consumer<Product> printer = product -> 
    System.out.println("Processing: " + product.getName() + " - $" + product.getPrice());
```

### 4. Supplier Interface
```java
Supplier<Product> productGenerator = () -> {
    // Generate random product
};
```

### 5. Custom Functional Interface
```java
@FunctionalInterface
public interface PriceCalculator {
    double calculate(double price, double discount);
}
```

### 6. Method References
```java
.collect(Collectors.groupingBy(Product::getCategory))
```

## API Endpoints

- `GET /api/products` - Get all products
- `GET /api/products/category/{category}` - Filter by category
- `GET /api/products/names` - Get transformed product names
- `GET /api/products/affordable?maxPrice=200` - Get affordable products
- `POST /api/products/discount?percent=10` - Apply discount
- `GET /api/products/grouped` - Group by category
- `GET /api/products/most-expensive` - Find most expensive
- `GET /api/products/random` - Generate random product
- `POST /api/products/process` - Process products (console output)

## Running the Service

```bash
mvn spring-boot:run
```

The service will start on `http://localhost:8080`

## Example Usage

```bash
# Get products by category
curl http://localhost:8080/api/products/category/Electronics

# Get affordable products under $200
curl http://localhost:8080/api/products/affordable?maxPrice=200

# Apply 15% discount
curl -X POST http://localhost:8080/api/products/discount?percent=15
```
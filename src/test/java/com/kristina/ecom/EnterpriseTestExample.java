package com.kristina.ecom;

import org.junit.jupiter.api.Test;

import com.kristina.ecom.pms.domain.Product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ENTERPRISE-GRADE TESTING EXAMPLES
 * 
 * This shows you how real companies write tests:
 * - Clear test names that explain business scenarios
 * - Testing edge cases that could break the system
 * - Testing performance and scalability
 * - Testing data validation
 */
public class EnterpriseTestExample {
    
    private Product[] inventory;
    
    @BeforeEach
    public void setupInventory() {
        // Before each test, create a fresh inventory
        inventory = ProductFactory.createProductList(5);
    }

    @Test
    @DisplayName("Should calculate correct inventory value for business reporting")
    public void testInventoryValuation() {
        // BUSINESS SCENARIO: CFO needs to know total inventory value
        
        // Create realistic products with varied prices
        Product expensiveServer = ProductFactory.builder()
            .withName("Enterprise Server")
            .withPrice(5000.0)
            .withQuantity(2)
            .asComputer()
            .build();
            
        Product cheapCables = ProductFactory.builder()
            .withName("USB Cables")
            .withPrice(5.99)
            .withQuantity(100)
            .asAccessory()
            .build();
        
        // Calculate total value: (price × quantity) for each product
        double serverValue = expensiveServer.getPrice() * expensiveServer.getQuantity(); // 5000 × 2 = 10,000
        double cableValue = cheapCables.getPrice() * cheapCables.getQuantity();         // 5.99 × 100 = 599
        double totalValue = serverValue + cableValue;                                   // 10,599
        
        assertEquals(10599.0, totalValue, 0.01);
        
        // Business rule: High-value inventory should be flagged
        assertTrue(serverValue > 1000, "Expensive items need special handling");
    }

    @Test
    @DisplayName("Should prevent selling out-of-stock items")
    public void testOutOfStockValidation() {
        // BUSINESS SCENARIO: Customer tries to buy something that's sold out
        
        Product soldOutProduct = ProductFactory.createOutOfStock();
        
        // Verify it's actually out of stock
        assertEquals(0, soldOutProduct.getQuantity());
        
        // In real code, you'd have a method like: canPurchase(product, quantity)
        // This test would ensure it returns false for out-of-stock items
        boolean canPurchase = soldOutProduct.getQuantity() > 0;
        assertFalse(canPurchase, "Should not allow purchase of out-of-stock items");
    }

    @Test
    @DisplayName("Should handle bulk discount calculations correctly")
    public void testBulkDiscountScenario() {
        // BUSINESS SCENARIO: Customer buys 10+ items, gets 10% discount
        
        Product mouse = ProductFactory.builder()
            .withName("Bulk Mouse")
            .withPrice(50.0)
            .withQuantity(15)
            .asComponent()
            .build();
        
        int quantityToBuy = 10;
        double originalPrice = mouse.getPrice();
        
        // Business logic: 10+ items = 10% discount
        double discountedPrice = quantityToBuy >= 10 ? originalPrice * 0.9 : originalPrice;
        double totalCost = discountedPrice * quantityToBuy;
        
        assertEquals(45.0, discountedPrice, 0.01); // 50 * 0.9 = 45
        assertEquals(450.0, totalCost, 0.01);      // 45 * 10 = 450
        
        // Verify we have enough stock
        assertTrue(mouse.getQuantity() >= quantityToBuy, "Must have sufficient inventory");
    }

    @Test
    @DisplayName("Should generate realistic product data for load testing")
    public void testDataGenerationForPerformance() {
        // BUSINESS SCENARIO: Test database with realistic data volumes
        
        // Generate 1000 realistic products for performance testing
        Product[] massProducts = new Product[1000];
        for (int i = 0; i < 1000; i++) {
            massProducts[i] = ProductFactory.fake().withId(i + 1).build();
        }
        
        // Verify we got 1000 different products
        assertEquals(1000, massProducts.length);
        
        // Verify they have realistic data (not empty/null)
        for (Product product : massProducts) {
            assertNotNull(product.getName());
            assertNotNull(product.getType());
            assertTrue(product.getPrice() > 0);
            assertTrue(product.getQuantity() >= 0);
        }
        
        // Performance assertion: Processing 1000 products should be fast
        long startTime = System.currentTimeMillis();
        
        // Simulate processing (like calculating total value)
        double totalValue = 0;
        for (Product product : massProducts) {
            totalValue += product.getPrice() * product.getQuantity();
        }
        
        long endTime = System.currentTimeMillis();
        long processingTime = endTime - startTime;
        
        assertTrue(processingTime < 100, "Processing 1000 products should take less than 100ms");
        assertTrue(totalValue > 0, "Total inventory value should be positive");
    }

    @Test
    @DisplayName("Should validate product data integrity")
    public void testDataValidationRules() {
        // BUSINESS SCENARIO: Ensure products meet business rules
        
        // Test each type has appropriate price ranges
        Product component = ProductFactory.fake().buildComponent();
        Product computer = ProductFactory.fake().buildComputer();
        Product accessory = ProductFactory.fake().buildAccessory();
        
        // Business rules for pricing
        assertTrue(component.getPrice() >= 5, "Components should cost at least $5");
        assertTrue(computer.getPrice() >= 500, "Computers should cost at least $500");
        assertTrue(accessory.getPrice() >= 1, "Accessories should cost at least $1");
        
        // Business rules for stock levels
        assertTrue(component.getQuantity() >= 0, "Negative stock is impossible");
        assertTrue(computer.getQuantity() >= 0, "Negative stock is impossible");
        assertTrue(accessory.getQuantity() >= 0, "Negative stock is impossible");
        
        // Business rules for product names
        assertFalse(component.getName().trim().isEmpty(), "Products must have names");
        assertFalse(computer.getName().trim().isEmpty(), "Products must have names");
        assertFalse(accessory.getName().trim().isEmpty(), "Products must have names");
    }

    @Test
    @DisplayName("Should handle extreme edge cases without crashing")
    public void testExtremeEdgeCases() {
        // BUSINESS SCENARIO: System under stress or with bad data
        
        // Test with extreme values
        Product extremeProduct = ProductFactory.random().buildWithExtremeValues();
        
        // System should handle extreme values gracefully
        assertNotNull(extremeProduct);
        assertTrue(extremeProduct.getId() > 0);
        
        // Test with empty strings
        Product emptyProduct = ProductFactory.random().buildWithEmptyStrings();
        assertNotNull(emptyProduct);
        
        // In real systems, you might validate and reject such data
        // But your system should never crash, even with bad input
        String name = emptyProduct.getName();
        assertNotNull(name); // Should be empty string, not null
    }

    @Test
    @DisplayName("Should support different product categories for reporting")
    public void testCategoryBasedReporting() {
        // BUSINESS SCENARIO: Generate sales reports by product category
        
        // Create products in each category
        Product[] components = ProductFactory.createMultiple("component", 3);
        Product[] computers = ProductFactory.createMultiple("computer", 2);
        Product[] accessories = ProductFactory.createMultiple("accessory", 5);
        
        // Verify categories are correctly set
        for (Product component : components) {
            assertEquals("Component", component.getType());
        }
        for (Product computer : computers) {
            assertEquals("Computer", computer.getType());
        }
        for (Product accessory : accessories) {
            assertEquals("Accessory", accessory.getType());
        }
        
        // Business logic: Count products by category
        assertEquals(3, components.length);
        assertEquals(2, computers.length);
        assertEquals(5, accessories.length);
        
        // Total products across all categories
        int totalProducts = components.length + computers.length + accessories.length;
        assertEquals(10, totalProducts);
    }
} 
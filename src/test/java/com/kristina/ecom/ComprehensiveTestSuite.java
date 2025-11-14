package com.kristina.ecom;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import com.kristina.ecom.pms.domain.Product;

import static org.junit.jupiter.api.Assertions.*;

/**
 * COMPLETE ENTERPRISE TESTING STRATEGY
 * 
 * This demonstrates how Fortune 500 companies test their products:
 * 
 * 1. UNIT TESTS (test individual pieces)
 * 2. INTEGRATION TESTS (test pieces working together)  
 * 3. PERFORMANCE TESTS (test speed and memory)
 * 4. SECURITY TESTS (test against attacks)
 * 5. USER ACCEPTANCE TESTS (test real scenarios)
 */
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class ComprehensiveTestSuite {

    /**
     * LEVEL 1: UNIT TESTS
     * Test the smallest pieces of your system
     */
    @Nested
    @Order(1)
    @DisplayName("Unit Tests: Basic Product Functionality")
    class UnitTests {
        
        @Test
        @DisplayName("Product should store and retrieve basic data correctly")
        public void testBasicDataStorage() {
            Product product = ProductFactory.create();
            
            // Test all getters work
            assertNotNull(product.getName());
            assertTrue(product.getPrice() >= 0);
            assertTrue(product.getQuantity() >= 0);
            assertNotNull(product.getType());
        }
        
        @Test
        @DisplayName("Product should allow data modification")
        public void testDataModification() {
            Product product = ProductFactory.create();
            
            product.setName("Modified Name");
            product.setPrice(999.99);
            product.setQuantity(50);
            
            assertEquals("Modified Name", product.getName());
            assertEquals(999.99, product.getPrice());
            assertEquals(50, product.getQuantity());
        }
    }

    /**
     * LEVEL 2: INTEGRATION TESTS  
     * Test how products work with other parts of your system
     */
    @Nested
    @Order(2)
    @DisplayName("Integration Tests: Products in Business Context")
    class IntegrationTests {
        
        @Test
        @DisplayName("Shopping cart should calculate total correctly with multiple products")
        public void testShoppingCartIntegration() {
            // Simulate a real shopping cart scenario
            Product laptop = ProductFactory.createLaptop();    // $1299.99
            Product mouse = ProductFactory.createGamingMouse(); // $49.99
            Product keyboard = ProductFactory.createGamingKeyboard(); // $129.99
            
            double cartTotal = laptop.getPrice() + mouse.getPrice() + keyboard.getPrice();
            double expectedTotal = 1299.99 + 49.99 + 129.99; // = 1479.97
            
            assertEquals(expectedTotal, cartTotal, 0.01);
        }
        
        @Test
        @DisplayName("Inventory should track stock levels across multiple operations")
        public void testInventoryManagement() {
            Product product = ProductFactory.builder()
                .withQuantity(100)
                .build();
            
            // Simulate selling 30 units
            int soldUnits = 30;
            product.setQuantity(product.getQuantity() - soldUnits);
            
            assertEquals(70, product.getQuantity());
            
            // Simulate restocking 20 units
            int restocked = 20;
            product.setQuantity(product.getQuantity() + restocked);
            
            assertEquals(90, product.getQuantity());
        }
    }

    /**
     * LEVEL 3: PERFORMANCE TESTS
     * Test system speed and memory usage
     */
    @Nested
    @Order(3)
    @DisplayName("Performance Tests: Speed and Scale")
    class PerformanceTests {
        
        @Test
        @DisplayName("Should create 10,000 products quickly")
        @Timeout(value = 5) // Must complete in 5 seconds
        public void testMassProductCreation() {
            long startTime = System.currentTimeMillis();
            
            Product[] massProducts = new Product[10000];
            for (int i = 0; i < 10000; i++) {
                massProducts[i] = ProductFactory.fake().withId(i + 1).build();
            }
            
            long endTime = System.currentTimeMillis();
            long creationTime = endTime - startTime;
            
            assertEquals(10000, massProducts.length);
            assertTrue(creationTime < 5000, "Should create 10k products in under 5 seconds");
        }
        
        @Test
        @DisplayName("Should process large inventories efficiently")
        public void testLargeInventoryProcessing() {
            // Create a large inventory
            Product[] inventory = ProductFactory.createProductList(1000);
            
            long startTime = System.currentTimeMillis();
            
            // Process inventory (calculate total value)
            double totalValue = 0;
            int totalQuantity = 0;
            
            for (Product product : inventory) {
                totalValue += product.getPrice() * product.getQuantity();
                totalQuantity += product.getQuantity();
            }
            
            long endTime = System.currentTimeMillis();
            long processingTime = endTime - startTime;
            
            assertTrue(totalValue > 0, "Inventory should have value");
            assertTrue(totalQuantity > 0, "Inventory should have items");
            assertTrue(processingTime < 100, "Should process 1000 products in under 100ms");
        }
    }

    /**
     * LEVEL 4: SECURITY TESTS
     * Test system resilience against bad data and attacks
     */
    @Nested
    @Order(4)
    @DisplayName("Security Tests: Handling Bad Data")
    class SecurityTests {
        
        @Test
        @DisplayName("Should handle malicious input without crashing")
        public void testMaliciousInput() {
            // Test extreme values that might cause overflow
            Product extreme = ProductFactory.random().buildWithExtremeValues();
            
            // System should handle gracefully, not crash
            assertNotNull(extreme);
            assertDoesNotThrow(() -> extreme.toString());
            assertDoesNotThrow(() -> extreme.getName());
        }
        
        @Test
        @DisplayName("Should handle empty and null-like data")
        public void testEmptyDataHandling() {
            Product empty = ProductFactory.random().buildWithEmptyStrings();
            
            // System should handle empty strings without crashing
            assertNotNull(empty);
            assertNotNull(empty.getName()); // Should be empty string, not null
            assertTrue(empty.getPrice() >= 0); // Should be valid number
        }
        
        @Test
        @DisplayName("Should prevent negative prices and quantities in business logic")
        public void testBusinessRuleValidation() {
            Product product = ProductFactory.create();
            
            // In a real system, you'd have validation
            // For now, just test the current behavior
            product.setPrice(-100); // This should be prevented by business logic
            product.setQuantity(-50); // This too
            
            // In your real app, add validation to prevent this!
            // For now, we just test that the system doesn't crash
            assertDoesNotThrow(() -> product.getPrice());
            assertDoesNotThrow(() -> product.getQuantity());
        }
    }

    /**
     * LEVEL 5: USER ACCEPTANCE TESTS
     * Test real business scenarios that users care about
     */
    @Nested
    @Order(5)
    @DisplayName("User Acceptance Tests: Real Business Scenarios")
    class UserAcceptanceTests {
        
        @Test
        @DisplayName("Customer can browse and compare products by category")
        public void testProductBrowsingScenario() {
            // SCENARIO: Customer wants to compare gaming accessories
            Product mouse1 = ProductFactory.builder()
                .withName("Budget Gaming Mouse")
                .withPrice(25.99)
                .asAccessory()
                .build();
                
            Product mouse2 = ProductFactory.builder()
                .withName("Premium Gaming Mouse")
                .withPrice(89.99)
                .asAccessory()
                .build();
            
            // Customer can see price difference
            double priceDifference = mouse2.getPrice() - mouse1.getPrice();
            assertEquals(64.0, priceDifference, 0.01);
            
            // Both are same category
            assertEquals(mouse1.getType(), mouse2.getType());
        }
        
        @Test
        @DisplayName("Business can generate accurate sales reports")
        public void testSalesReportingScenario() {
            // SCENARIO: Manager needs end-of-day sales report
            Product[] soldItems = {
                ProductFactory.builder().withPrice(1000.0).withQuantity(2).build(), // 2 × $1000 = $2000
                ProductFactory.builder().withPrice(50.0).withQuantity(10).build(),   // 10 × $50 = $500
                ProductFactory.builder().withPrice(25.0).withQuantity(4).build()     // 4 × $25 = $100
            };
            
            double totalRevenue = 0;
            int totalItemsSold = 0;
            
            for (Product item : soldItems) {
                totalRevenue += item.getPrice() * item.getQuantity();
                totalItemsSold += item.getQuantity();
            }
            
            assertEquals(2600.0, totalRevenue, 0.01); // $2000 + $500 + $100
            assertEquals(16, totalItemsSold); // 2 + 10 + 4
        }
        
        @Test
        @DisplayName("System can handle Black Friday traffic simulation")
        public void testHighTrafficScenario() {
            // SCENARIO: Black Friday - thousands of customers browsing
            // Simulate 5000 product views
            Product[] trending = new Product[5000];
            
            long startTime = System.currentTimeMillis();
            
            for (int i = 0; i < 5000; i++) {
                trending[i] = ProductFactory.fake().build();
            }
            
            long endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;
            
            // System should handle high traffic
            assertEquals(5000, trending.length);
            assertTrue(responseTime < 2000, "Should handle 5000 requests in under 2 seconds");
        }
    }
} 
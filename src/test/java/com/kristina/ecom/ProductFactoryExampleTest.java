package com.kristina.ecom;

import org.junit.jupiter.api.Test;

import com.kristina.ecom.pms.domain.Product;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LEARNING PRODUCTFACTORY: From Basic to Advanced
 * 
 * This shows you how ProductFactory makes testing MUCH easier!
 */
public class ProductFactoryExampleTest {

    /**
     * BEFORE FACTORY: The Old, Hard Way
     * Look how much typing and thinking this requires!
     */
    @Test
    public void testWithoutFactory() {
        // So much typing for each product! 😰
        Product mouse = new Product(1, "Component", "Gaming Mouse", 49.99, 15, "gaming_mouse.png");
        Product keyboard = new Product(2, "Component", "Mechanical Keyboard", 129.99, 8, "keyboard.png");
        Product laptop = new Product(3, "Computer", "Gaming Laptop", 1299.99, 5, "laptop.png");
        
        // Now test them...
        assertEquals("Gaming Mouse", mouse.getName());
        assertEquals("Mechanical Keyboard", keyboard.getName());
        assertEquals("Gaming Laptop", laptop.getName());
    }

    /**
     * AFTER FACTORY: The New, Easy Way!
     * Look how simple and readable this is!
     */
    @Test
    public void testWithFactory() {
        // So much easier! 😍
        Product mouse = ProductFactory.createGamingMouse();
        Product keyboard = ProductFactory.createGamingKeyboard();
        Product laptop = ProductFactory.createLaptop();
        
        // Same tests, less typing!
        assertEquals("Gaming Mouse", mouse.getName());
        assertEquals("Mechanical Keyboard", keyboard.getName());
        assertEquals("Gaming Laptop", laptop.getName());
    }

    /**
     * FACTORY BENEFIT #1: Pre-made Products
     * The factory has ready-made products for common test scenarios
     */
    @Test
    public void testPreMadeProducts() {
        Product mouse = ProductFactory.createGamingMouse();
        Product accessory = ProductFactory.createAccessory();
        Product component = ProductFactory.createComponent();
        
        // All these come with sensible default values!
        assertTrue(mouse.getPrice() > 0);
        assertTrue(accessory.getQuantity() > 0);
        assertNotNull(component.getName());
    }

    /**
     * FACTORY BENEFIT #2: Builder Pattern
     * Want to customize? Use the builder for ultimate flexibility!
     */
    @Test
    public void testBuilderPattern() {
        // Create a custom product step by step
        Product customProduct = ProductFactory.builder()
            .withName("Super Gaming Mouse")
            .withPrice(199.99)
            .withQuantity(100)
            .asComponent()
            .build();
        
        assertEquals("Super Gaming Mouse", customProduct.getName());
        assertEquals(199.99, customProduct.getPrice());
        assertEquals(100, customProduct.getQuantity());
        assertEquals("Component", customProduct.getType());
    }

    /**
     * FACTORY BENEFIT #3: Testing Edge Cases
     * The factory helps you test weird scenarios easily
     */
    @Test
    public void testEdgeCases() {
        // Test expensive products
        Product expensive = ProductFactory.createExpensive();
        assertTrue(expensive.getPrice() >= 999.99);
        
        // Test out of stock products
        Product outOfStock = ProductFactory.createOutOfStock();
        assertEquals(0, outOfStock.getQuantity());
        
        // Test cheap products
        Product cheap = ProductFactory.createCheap();
        assertEquals(9.99, cheap.getPrice());
    }

    /**
     * FACTORY BENEFIT #4: Realistic Fake Data
     * For when you need lots of varied test data
     */
    @Test
    public void testFakeData() {
        // Create 5 random but realistic products
        Product product1 = ProductFactory.fake().build();
        Product product2 = ProductFactory.fake().build();
        Product product3 = ProductFactory.fake().build();
        Product product4 = ProductFactory.fake().build();
        Product product5 = ProductFactory.fake().build();
        
        // They should all be different
        assertNotEquals(product1.getName(), product2.getName());
        assertTrue(product1.getPrice() > 0);
        assertTrue(product2.getQuantity() > 0);
        assertNotNull(product3.getImg());
    }

    /**
     * FACTORY BENEFIT #5: Testing Collections
     * Need many products for testing lists or databases?
     */
    @Test
    public void testProductCollections() {
        // Create an array of 10 products instantly!
        Product[] products = ProductFactory.createProductList(10);
        
        assertEquals(10, products.length);
        
        // Check they all have unique IDs
        for (int i = 0; i < products.length; i++) {
            assertEquals(i + 1, products[i].getId());
        }
    }

    /**
     * REAL WORLD EXAMPLE: Testing a Shopping Cart
     * This shows how factories make complex tests simple
     */
    @Test
    public void testShoppingCartScenario() {
        // Scenario: Customer adds expensive and cheap items to cart
        Product expensiveLaptop = ProductFactory.builder()
            .withName("Gaming Laptop")
            .withPrice(2000.0)
            .asComputer()
            .inStock()
            .build();
            
        Product cheapMouse = ProductFactory.builder()
            .withName("Basic Mouse")
            .withPrice(10.0)
            .asAccessory()
            .inStock()
            .build();
        
        // Test the scenario
        double totalValue = expensiveLaptop.getPrice() + cheapMouse.getPrice();
        assertEquals(2010.0, totalValue);
        
        // Both should be in stock
        assertTrue(expensiveLaptop.getQuantity() > 0);
        assertTrue(cheapMouse.getQuantity() > 0);
    }
} 
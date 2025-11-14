package com.kristina.ecom;

import org.junit.jupiter.api.Test;

import com.kristina.ecom.pms.domain.Product;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BEGINNER'S GUIDE TO TESTING
 * 
 * This file shows you the absolute basics of testing.
 * We'll start simple and build up to more complex examples.
 */
public class BasicProductTest {

    /**
     * TEST #1: The Most Basic Test Possible
     * 
     * This test creates a product and checks if it has the right name.
     * It's like asking: "If I create a mouse, is it actually called 'Gaming Mouse'?"
     */
    @Test
    public void testProductName() {
        // STEP 1: CREATE (Arrange) - Set up what you need for the test
        Product mouse = new Product(1, "Component", "Gaming Mouse", 49.99, 10, "mouse.png");
        
        // STEP 2: ACT (Act) - Do the thing you want to test
        String actualName = mouse.getName();
        
        // STEP 3: CHECK (Assert) - Verify it worked correctly
        assertEquals("Gaming Mouse", actualName);
        // This line says: "I expect the name to be 'Gaming Mouse'. If it's not, this test fails."
    }

    /**
     * TEST #2: Testing Multiple Properties
     * 
     * Let's check if ALL the product properties are set correctly
     */
    @Test
    public void testProductCreation() {
        // Arrange: Create a product with specific values
        Product laptop = new Product(2, "Computer", "Gaming Laptop", 1299.99, 5, "laptop.png");
        
        // Act & Assert: Check each property
        assertEquals(2, laptop.getId());
        assertEquals("Computer", laptop.getType());
        assertEquals("Gaming Laptop", laptop.getName());
        assertEquals(1299.99, laptop.getPrice(), 0.01); // 0.01 allows tiny rounding differences
        assertEquals(5, laptop.getQuantity());
        assertEquals("laptop.png", laptop.getImg());
    }

    /**
     * TEST #3: Testing Edge Cases
     * 
     * What happens with weird data? This is important for real-world use!
     */
    @Test
    public void testProductWithZeroPrice() {
        // What if someone accidentally sets price to 0?
        Product freeProduct = new Product(3, "Component", "Free Sample", 0.0, 1, "sample.png");
        
        assertEquals(0.0, freeProduct.getPrice());
        // In a real app, you might want to prevent this, but for now we just test what happens
    }

    /**
     * TEST #4: Testing Modifications
     * 
     * Can we change product properties after creating it?
     */
    @Test
    public void testProductModification() {
        // Create a product
        Product product = new Product(4, "Component", "Old Name", 50.0, 10, "old.png");
        
        // Change its name
        product.setName("New Name");
        
        // Check if the change worked
        assertEquals("New Name", product.getName());
    }
} 
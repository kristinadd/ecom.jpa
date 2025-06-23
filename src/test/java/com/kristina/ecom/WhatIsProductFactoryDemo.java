package com.kristina.ecom;

import com.kristina.ecom.domain.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * SIMPLE DEMO: What is ProductFactory?
 * 
 * This shows you exactly what ProductFactory is and why it's useful
 */
public class WhatIsProductFactoryDemo {

    @Test
    public void demonstrateWhatProductFactoryIs() {
        System.out.println("=== WHAT IS PRODUCTFACTORY? ===");
        
        // 1. ProductFactory is a CLASS that creates Product objects for you
        System.out.println("\n1. ProductFactory creates products easily:");
        
        Product mouse = ProductFactory.createGamingMouse();
        System.out.println("Created: " + mouse.getName() + " for $" + mouse.getPrice());
        
        Product laptop = ProductFactory.createLaptop();
        System.out.println("Created: " + laptop.getName() + " for $" + laptop.getPrice());
        
        // 2. It has MANY different ways to create products
        System.out.println("\n2. Different creation methods:");
        
        // Simple pre-made products
        Product accessory = ProductFactory.createAccessory();
        System.out.println("Pre-made: " + accessory.getName());
        
        // Custom products with builder
        Product custom = ProductFactory.builder()
            .withName("My Custom Product")
            .withPrice(99.99)
            .build();
        System.out.println("Custom: " + custom.getName());
        
        // Random realistic products
        Product fake = ProductFactory.fake().build();
        System.out.println("Random: " + fake.getName());
        
        // 3. It saves you from typing long constructor calls
        System.out.println("\n3. Compare the work:");
        
        // WITHOUT ProductFactory (lots of typing!)
        Product manual = new Product(1, "Component", "Manual Mouse", 29.99, 10, "mouse.png");
        System.out.println("Manual creation: " + manual.getName());
        
        // WITH ProductFactory (easy!)
        Product easy = ProductFactory.createGamingMouse();
        System.out.println("Factory creation: " + easy.getName());
        
        // 4. It helps with testing scenarios
        System.out.println("\n4. Testing scenarios:");
        
        Product expensive = ProductFactory.createExpensive();
        System.out.println("Expensive product: $" + expensive.getPrice());
        
        Product outOfStock = ProductFactory.createOutOfStock();
        System.out.println("Out of stock quantity: " + outOfStock.getQuantity());
        
        // Verify everything works
        assertNotNull(mouse);
        assertNotNull(laptop);
        assertNotNull(custom);
        assertEquals("My Custom Product", custom.getName());
        assertEquals(0, outOfStock.getQuantity());
        
        System.out.println("\n✅ ProductFactory makes testing easier and faster!");
    }

    @Test
    public void showProductFactoryBenefits() {
        System.out.println("\n=== WHY USE PRODUCTFACTORY? ===");
        
        // Benefit 1: Speed
        long startTime = System.currentTimeMillis();
        Product[] quickProducts = ProductFactory.createProductList(10);
        long endTime = System.currentTimeMillis();
        
        System.out.println("1. SPEED: Created 10 products in " + (endTime - startTime) + "ms");
        
        // Benefit 2: Consistency
        Product mouse1 = ProductFactory.createGamingMouse();
        Product mouse2 = ProductFactory.createGamingMouse();
        
        System.out.println("2. CONSISTENCY: Both mice have same name: " + 
                         (mouse1.getName().equals(mouse2.getName())));
        
        // Benefit 3: Variety
        Product component = ProductFactory.fake().buildComponent();
        Product computer = ProductFactory.fake().buildComputer();
        Product accessory = ProductFactory.fake().buildAccessory();
        
        System.out.println("3. VARIETY: Created " + component.getType() + 
                         ", " + computer.getType() + ", " + accessory.getType());
        
        // Benefit 4: Readability
        System.out.println("\n4. READABILITY:");
        System.out.println("   ProductFactory.createGamingMouse() <- Clear what this does!");
        System.out.println("   vs");
        System.out.println("   new Product(1, \"Component\", \"Gaming Mouse\", 49.99, 15, \"gaming_mouse.png\") <- Hard to read");
        
        assertEquals(10, quickProducts.length);
        assertTrue(mouse1.getName().equals(mouse2.getName()));
    }
} 
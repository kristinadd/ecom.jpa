package com.kristina.ecom;

import com.kristina.ecom.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class demonstrating how to use ProductFactory.
 * 
 * This shows different approaches to creating test objects:
 * 1. Simple factory methods (Rails-like)
 * 2. Builder pattern
 * 3. Faker integration
 * 4. Random data generation
 */
@DisplayName("ProductFactory Usage Examples")
class ProductFactoryTest {

    @Test
    @DisplayName("Should create products using simple factory methods (Rails-like)")
    void shouldCreateProductsUsingSimpleFactoryMethods() {
        // Act: Create products using simple factory methods
        Product gamingMouse = ProductFactory.createGamingMouse();
        Product laptop = ProductFactory.createLaptop();
        Product accessory = ProductFactory.createAccessory();

        // Assert: Verify products are created correctly
        assertEquals("Component", gamingMouse.getType());
        assertEquals("Gaming Mouse", gamingMouse.getName());
        assertEquals(49.99, gamingMouse.getPrice(), 0.001);

        assertEquals("Computer", laptop.getType());
        assertEquals("Gaming Laptop", laptop.getName());
        assertEquals(1299.99, laptop.getPrice(), 0.001);

        assertEquals("Accessory", accessory.getType());
        assertEquals("Mouse Pad", accessory.getName());
        assertEquals(19.99, accessory.getPrice(), 0.001);
    }

    @Test
    @DisplayName("Should create products using builder pattern")
    void shouldCreateProductsUsingBuilderPattern() {
        // Act: Create products using builder pattern
        Product customProduct = ProductFactory.builder()
                .withId(100)
                .withName("Custom Gaming Setup")
                .withPrice(1999.99)
                .withQuantity(3)
                .withImg("custom_setup.png")
                .build();

        Product expensiveComponent = ProductFactory.builder()
                .asComponent()
                .expensive()
                .inStock()
                .build();

        Product cheapAccessory = ProductFactory.builder()
                .asAccessory()
                .cheap()
                .outOfStock()
                .build();

        // Assert: Verify products are created correctly
        assertEquals(100, customProduct.getId());
        assertEquals("Custom Gaming Setup", customProduct.getName());
        assertEquals(1999.99, customProduct.getPrice(), 0.001);
        assertEquals(3, customProduct.getQuantity());

        assertEquals("Component", expensiveComponent.getType());
        assertEquals(999.99, expensiveComponent.getPrice(), 0.001);
        assertEquals(50, expensiveComponent.getQuantity());

        assertEquals("Accessory", cheapAccessory.getType());
        assertEquals(9.99, cheapAccessory.getPrice(), 0.001);
        assertEquals(0, cheapAccessory.getQuantity());
    }

    @Test
    @DisplayName("Should create products using faker for realistic data")
    void shouldCreateProductsUsingFaker() {
        // Act: Create products using faker
        Product randomProduct = ProductFactory.fake().build();
        Product component = ProductFactory.fake().buildComponent();
        Product computer = ProductFactory.fake().buildComputer();
        Product accessory = ProductFactory.fake().buildAccessory();

        // Assert: Verify products have realistic data
        assertNotNull(randomProduct.getName());
        assertTrue(randomProduct.getPrice() > 0);
        assertTrue(randomProduct.getQuantity() > 0);

        assertEquals("Component", component.getType());
        assertTrue(component.getPrice() >= 9.99 && component.getPrice() <= 1049.99);

        assertEquals("Computer", computer.getType());
        assertTrue(computer.getPrice() >= 500 && computer.getPrice() <= 2000);

        assertEquals("Accessory", accessory.getType());
        assertTrue(accessory.getPrice() >= 5 && accessory.getPrice() <= 55);
    }

    @Test
    @DisplayName("Should create products using random data for edge cases")
    void shouldCreateProductsUsingRandomData() {
        // Act: Create products using random data
        Product randomProduct = ProductFactory.random().build();
        Product productWithNulls = ProductFactory.random().buildWithNulls();
        Product productWithEmptyStrings = ProductFactory.random().buildWithEmptyStrings();
        Product productWithExtremeValues = ProductFactory.random().buildWithExtremeValues();

        // Assert: Verify random products are created
        assertNotNull(randomProduct);
        assertTrue(randomProduct.getId() > 0);
        assertNotNull(randomProduct.getName());

        assertNotNull(productWithNulls);
        assertTrue(productWithNulls.getId() > 0);
        // Other fields should be null/default

        assertEquals("", productWithEmptyStrings.getType());
        assertEquals("", productWithEmptyStrings.getName());
        assertEquals("", productWithEmptyStrings.getImg());

        assertEquals(Integer.MAX_VALUE, productWithExtremeValues.getId());
        assertEquals(Double.MAX_VALUE, productWithExtremeValues.getPrice(), 0.001);
        assertEquals(Integer.MAX_VALUE, productWithExtremeValues.getQuantity());
    }

    @Test
    @DisplayName("Should create multiple products of the same type")
    void shouldCreateMultipleProductsOfSameType() {
        // Act: Create multiple products
        Product[] components = ProductFactory.createMultiple("component", 3);
        Product[] computers = ProductFactory.createMultiple("computer", 2);

        // Assert: Verify multiple products are created correctly
        assertEquals(3, components.length);
        assertEquals(2, computers.length);

        for (int i = 0; i < components.length; i++) {
            assertEquals("Component", components[i].getType());
            assertEquals(i + 1, components[i].getId());
        }

        for (int i = 0; i < computers.length; i++) {
            assertEquals("Computer", computers[i].getType());
            assertEquals(i + 1, computers[i].getId());
        }
    }

    @Test
    @DisplayName("Should create products with different stock levels")
    void shouldCreateProductsWithDifferentStockLevels() {
        // Act: Create products with different stock levels
        Product inStock = ProductFactory.createInStock();
        Product outOfStock = ProductFactory.createOutOfStock();
        Product lowStock = ProductFactory.createLowStock();

        // Assert: Verify stock levels are correct
        assertEquals(50, inStock.getQuantity());
        assertEquals(0, outOfStock.getQuantity());
        assertEquals(2, lowStock.getQuantity());
    }

    @Test
    @DisplayName("Should create products with different price ranges")
    void shouldCreateProductsWithDifferentPriceRanges() {
        // Act: Create products with different price ranges
        Product expensive = ProductFactory.createExpensive();
        Product cheap = ProductFactory.createCheap();

        // Assert: Verify price ranges are correct
        assertEquals(999.99, expensive.getPrice(), 0.001);
        assertEquals(9.99, cheap.getPrice(), 0.001);
    }

    @Test
    @DisplayName("Should create product list for testing collections")
    void shouldCreateProductListForTestingCollections() {
        // Act: Create a list of products
        Product[] productList = ProductFactory.createProductList(5);

        // Assert: Verify list is created correctly
        assertEquals(5, productList.length);
        
        for (int i = 0; i < productList.length; i++) {
            assertNotNull(productList[i]);
            assertEquals(i + 1, productList[i].getId());
            assertNotNull(productList[i].getName());
            assertTrue(productList[i].getPrice() > 0);
        }
    }

    @Test
    @DisplayName("Should create products by type using factory method")
    void shouldCreateProductsByType() {
        // Act: Create products by type
        Product component = ProductFactory.createByType("component");
        Product computer = ProductFactory.createByType("computer");
        Product accessory = ProductFactory.createByType("accessory");
        Product unknown = ProductFactory.createByType("unknown");

        // Assert: Verify products are created with correct types
        assertEquals("Component", component.getType());
        assertEquals("Computer", computer.getType());
        assertEquals("Accessory", accessory.getType());
        assertEquals("Component", unknown.getType()); // Default fallback
    }
} 
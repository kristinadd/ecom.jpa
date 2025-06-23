package com.kristina.ecom;

import com.kristina.ecom.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Simplified Product tests using ProductFactory.
 * 
 * This demonstrates how factories make tests:
 * - More readable and maintainable
 * - Less repetitive
 * - Easier to modify test data
 * - More focused on behavior rather than setup
 */
@DisplayName("Product Tests with Factory")
class ProductTestWithFactory {

    // Test fixture using factory
    private Product testProduct;

    @BeforeEach
    void setUp() {
        // Much cleaner setup using factory
        testProduct = ProductFactory.createGamingMouse();
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create product with all parameters")
        void shouldCreateProductWithAllParameters() {
            // Act: Using factory instead of manual construction
            Product product = ProductFactory.create();

            // Assert: Verify all fields are set correctly
            assertEquals(1, product.getId(), "Product ID should match");
            assertEquals("Component", product.getType(), "Product type should match");
            assertEquals("Test Product", product.getName(), "Product name should match");
            assertEquals(49.99, product.getPrice(), 0.001, "Product price should match");
            assertEquals(10, product.getQuantity(), "Product quantity should match");
            assertEquals("test.png", product.getImg(), "Product image should match");
        }

        @Test
        @DisplayName("Should create product with default constructor")
        void shouldCreateProductWithDefaultConstructor() {
            // Act: Create product using default constructor
            Product product = new Product();

            // Assert: Verify default values
            assertEquals(0, product.getId(), "Default ID should be 0");
            assertNull(product.getType(), "Default type should be null");
            assertNull(product.getName(), "Default name should be null");
            assertEquals(0.0, product.getPrice(), 0.001, "Default price should be 0.0");
            assertEquals(0, product.getQuantity(), "Default quantity should be 0");
            assertNull(product.getImg(), "Default image should be null");
        }

        @Test
        @DisplayName("Should create different product types")
        void shouldCreateDifferentProductTypes() {
            // Act: Using factory methods for different types
            Product component = ProductFactory.createComponent();
            Product computer = ProductFactory.createLaptop();
            Product accessory = ProductFactory.createAccessory();

            // Assert: Verify different types are created correctly
            assertEquals("Component", component.getType());
            assertEquals("Computer", computer.getType());
            assertEquals("Accessory", accessory.getType());
        }
    }

    @Nested
    @DisplayName("Getter and Setter Tests")
    class GetterSetterTests {

        @Test
        @DisplayName("Should get and set all fields correctly")
        void shouldGetAndSetAllFieldsCorrectly() {
            // Arrange: Using factory for initial product
            Product product = ProductFactory.create();

            // Act: Set new values
            product.setId(999);
            product.setType("Computer");
            product.setName("Custom Laptop");
            product.setPrice(1499.99);
            product.setQuantity(5);
            product.setImg("laptop.png");

            // Assert: Verify all values were set correctly
            assertEquals(999, product.getId(), "ID should be updated");
            assertEquals("Computer", product.getType(), "Type should be updated");
            assertEquals("Custom Laptop", product.getName(), "Name should be updated");
            assertEquals(1499.99, product.getPrice(), 0.001, "Price should be updated");
            assertEquals(5, product.getQuantity(), "Quantity should be updated");
            assertEquals("laptop.png", product.getImg(), "Image should be updated");
        }

        @Test
        @DisplayName("Should handle edge cases for quantity")
        void shouldHandleEdgeCasesForQuantity() {
            // Act: Test different quantity scenarios using factory
            Product inStock = ProductFactory.createInStock();
            Product outOfStock = ProductFactory.createOutOfStock();
            Product lowStock = ProductFactory.createLowStock();

            // Assert: Verify different stock levels
            assertTrue(inStock.getQuantity() > 0, "In stock product should have quantity > 0");
            assertEquals(0, outOfStock.getQuantity(), "Out of stock product should have quantity 0");
            assertTrue(lowStock.getQuantity() > 0 && lowStock.getQuantity() <= 5, "Low stock should be between 1-5");
        }

        @Test
        @DisplayName("Should handle edge cases for price")
        void shouldHandleEdgeCasesForPrice() {
            // Act: Test different price scenarios using factory
            Product expensive = ProductFactory.createExpensive();
            Product cheap = ProductFactory.createCheap();

            // Assert: Verify different price ranges
            assertTrue(expensive.getPrice() > 500, "Expensive product should be > $500");
            assertTrue(cheap.getPrice() < 50, "Cheap product should be < $50");
        }
    }

    @Nested
    @DisplayName("Builder Pattern Tests")
    class BuilderPatternTests {

        @Test
        @DisplayName("Should create custom product using builder")
        void shouldCreateCustomProductUsingBuilder() {
            // Act: Using builder pattern for flexible creation
            Product customProduct = ProductFactory.builder()
                    .withId(123)
                    .withName("Custom Gaming Setup")
                    .withPrice(2499.99)
                    .withQuantity(2)
                    .asComputer()
                    .build();

            // Assert: Verify custom product
            assertEquals(123, customProduct.getId());
            assertEquals("Custom Gaming Setup", customProduct.getName());
            assertEquals(2499.99, customProduct.getPrice(), 0.001);
            assertEquals(2, customProduct.getQuantity());
            assertEquals("Computer", customProduct.getType());
        }

        @Test
        @DisplayName("Should create products with different characteristics")
        void shouldCreateProductsWithDifferentCharacteristics() {
            // Act: Using builder with descriptive methods
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

            // Assert: Verify characteristics
            assertEquals("Component", expensiveComponent.getType());
            assertEquals(999.99, expensiveComponent.getPrice(), 0.001);
            assertEquals(50, expensiveComponent.getQuantity());

            assertEquals("Accessory", cheapAccessory.getType());
            assertEquals(9.99, cheapAccessory.getPrice(), 0.001);
            assertEquals(0, cheapAccessory.getQuantity());
        }
    }

    @Nested
    @DisplayName("Faker Integration Tests")
    class FakerIntegrationTests {

        @Test
        @DisplayName("Should create realistic test data")
        void shouldCreateRealisticTestData() {
            // Act: Using faker for realistic data
            Product randomProduct = ProductFactory.fake().build();
            Product component = ProductFactory.fake().buildComponent();
            Product computer = ProductFactory.fake().buildComputer();
            Product accessory = ProductFactory.fake().buildAccessory();

            // Assert: Verify realistic data
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
    }

    @Nested
    @DisplayName("Collection Tests")
    class CollectionTests {

        @Test
        @DisplayName("Should create multiple products for testing")
        void shouldCreateMultipleProductsForTesting() {
            // Act: Using factory to create multiple products
            Product[] products = ProductFactory.createProductList(5);
            Product[] components = ProductFactory.createMultiple("component", 3);

            // Assert: Verify multiple products
            assertEquals(5, products.length);
            assertEquals(3, components.length);

            for (Product product : products) {
                assertNotNull(product);
                assertNotNull(product.getName());
                assertTrue(product.getPrice() > 0);
            }

            for (Product component : components) {
                assertEquals("Component", component.getType());
            }
        }
    }

    @Nested
    @DisplayName("Edge Cases Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("Should handle extreme values")
        void shouldHandleExtremeValues() {
            // Act: Using random builder for edge cases
            Product extremeProduct = ProductFactory.random().buildWithExtremeValues();
            Product nullProduct = ProductFactory.random().buildWithNulls();
            Product emptyProduct = ProductFactory.random().buildWithEmptyStrings();

            // Assert: Verify edge cases are handled
            assertEquals(Integer.MAX_VALUE, extremeProduct.getId());
            assertEquals(Double.MAX_VALUE, extremeProduct.getPrice(), 0.001);
            assertEquals(Integer.MAX_VALUE, extremeProduct.getQuantity());

            assertNotNull(nullProduct);
            assertTrue(nullProduct.getId() > 0);

            assertEquals("", emptyProduct.getType());
            assertEquals("", emptyProduct.getName());
            assertEquals("", emptyProduct.getImg());
        }
    }

    @Nested
    @DisplayName("toString and equals Tests")
    class ToStringAndEqualsTests {

        @Test
        @DisplayName("Should return formatted string representation")
        void shouldReturnFormattedStringRepresentation() {
            // Act: Using factory for consistent test data
            Product product = ProductFactory.createGamingMouse();
            String result = product.toString();

            // Assert: Verify string contains all information
            assertTrue(result.contains("📍Product:"), "Should contain product header");
            assertTrue(result.contains("id: 1"), "Should contain ID");
            assertTrue(result.contains("type: Component"), "Should contain type");
            assertTrue(result.contains("name: Gaming Mouse"), "Should contain name");
            assertTrue(result.contains("price: 49.99"), "Should contain price");
            assertTrue(result.contains("quantity: 15"), "Should contain quantity");
            assertTrue(result.contains("img: gaming_mouse.png"), "Should contain image");
        }

        @Test
        @DisplayName("Should compare products by ID")
        void shouldCompareProductsById() {
            // Act: Using factory to create products with same ID
            Product product1 = ProductFactory.createGamingMouse();
            Product product2 = ProductFactory.builder()
                    .withId(1)
                    .withName("Different Name")
                    .withPrice(99.99)
                    .build();

            // Assert: Verify equality comparison
            assertTrue(product1.equals(product2), "Products with same ID should be equal");
            assertTrue(product2.equals(product1), "Equality should be symmetric");
            assertTrue(product1.equals(product1), "Product should equal itself");
        }
    }
} 
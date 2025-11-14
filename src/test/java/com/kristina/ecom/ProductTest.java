package com.kristina.ecom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kristina.ecom.pms.domain.Product;

import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive automated tests for the Product class.
 * 
 * This test class demonstrates:
 * - Unit testing principles (Arrange-Act-Assert)
 * - Test organization with @Nested classes
 * - Different assertion types
 * - Edge cases and boundary testing
 * - Test naming conventions
 */
@DisplayName("Product Class Tests")
class ProductTest {

    // Test data constants - makes tests more readable and maintainable
    private static final int VALID_ID = 1;
    private static final String VALID_TYPE = "Component";
    private static final String VALID_NAME = "Gaming Mouse";
    private static final double VALID_PRICE = 49.99;
    private static final int VALID_QUANTITY = 10;
    private static final String VALID_IMG = "mouse.png";

    // Test fixture - object used across multiple tests
    private Product testProduct;

    /**
     * Setup method that runs before each test.
     * This ensures each test starts with a clean, known state.
     */
    @BeforeEach
    void setUp() {
        // Arrange: Create a fresh Product instance for each test
        testProduct = new Product(VALID_ID, VALID_TYPE, VALID_NAME, VALID_PRICE, VALID_QUANTITY, VALID_IMG);
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create product with all parameters")
        void shouldCreateProductWithAllParameters() {
            // Act: Create product using full constructor
            Product product = new Product(VALID_ID, VALID_TYPE, VALID_NAME, VALID_PRICE, VALID_QUANTITY, VALID_IMG);

            // Assert: Verify all fields are set correctly
            assertEquals(VALID_ID, product.getId(), "Product ID should match");
            assertEquals(VALID_TYPE, product.getType(), "Product type should match");
            assertEquals(VALID_NAME, product.getName(), "Product name should match");
            assertEquals(VALID_PRICE, product.getPrice(), 0.001, "Product price should match");
            assertEquals(VALID_QUANTITY, product.getQuantity(), "Product quantity should match");
            assertEquals(VALID_IMG, product.getImg(), "Product image should match");
        }

        @Test
        @DisplayName("Should create product with default constructor")
        void shouldCreateProductWithDefaultConstructor() {
            // Act: Create product using default constructor
            Product product = new Product();

            // Assert: Verify default values (all should be default/empty)
            assertEquals(0, product.getId(), "Default ID should be 0");
            assertNull(product.getType(), "Default type should be null");
            assertNull(product.getName(), "Default name should be null");
            assertEquals(0.0, product.getPrice(), 0.001, "Default price should be 0.0");
            assertEquals(0, product.getQuantity(), "Default quantity should be 0");
            assertNull(product.getImg(), "Default image should be null");
        }

        @Test
        @DisplayName("Should create product with type, name, price, and image")
        void shouldCreateProductWithTypeNamePriceAndImage() {
            // Act: Create product using constructor with 4 parameters
            Product product = new Product(VALID_TYPE, VALID_NAME, VALID_PRICE, VALID_IMG);

            // Assert: Verify fields are set correctly (ID defaults to 0, quantity to 0)
            assertEquals(0, product.getId(), "ID should default to 0");
            assertEquals(VALID_TYPE, product.getType(), "Type should match");
            assertEquals(VALID_NAME, product.getName(), "Name should match");
            assertEquals(VALID_PRICE, product.getPrice(), 0.001, "Price should match");
            assertEquals(0, product.getQuantity(), "Quantity should default to 0");
            assertEquals(VALID_IMG, product.getImg(), "Image should match");
        }

        @Test
        @DisplayName("Should create product with type, name, price, and quantity")
        void shouldCreateProductWithTypeNamePriceAndQuantity() {
            // Act: Create product using constructor with 4 parameters (type, name, price, quantity)
            Product product = new Product(VALID_TYPE, VALID_NAME, VALID_PRICE, VALID_QUANTITY);

            // Assert: Verify fields are set correctly (ID defaults to 0, img to empty string)
            assertEquals(0, product.getId(), "ID should default to 0");
            assertEquals(VALID_TYPE, product.getType(), "Type should match");
            assertEquals(VALID_NAME, product.getName(), "Name should match");
            assertEquals(VALID_PRICE, product.getPrice(), 0.001, "Price should match");
            assertEquals(VALID_QUANTITY, product.getQuantity(), "Quantity should match");
            assertEquals("", product.getImg(), "Image should default to empty string");
        }
    }

    @Nested
    @DisplayName("Getter and Setter Tests")
    class GetterSetterTests {

        @Test
        @DisplayName("Should get and set ID correctly")
        void shouldGetAndSetIdCorrectly() {
            // Arrange: New ID value
            int newId = 999;

            // Act: Set new ID
            testProduct.setId(newId);

            // Assert: Verify ID was set and can be retrieved
            assertEquals(newId, testProduct.getId(), "ID should be updated");
        }

        @Test
        @DisplayName("Should get and set type correctly")
        void shouldGetAndSetTypeCorrectly() {
            // Arrange: New type value
            String newType = "Computer";

            // Act: Set new type
            testProduct.setType(newType);

            // Assert: Verify type was set and can be retrieved
            assertEquals(newType, testProduct.getType(), "Type should be updated");
        }

        @Test
        @DisplayName("Should get and set name correctly")
        void shouldGetAndSetNameCorrectly() {
            // Arrange: New name value
            String newName = "Gaming Keyboard";

            // Act: Set new name
            testProduct.setName(newName);

            // Assert: Verify name was set and can be retrieved
            assertEquals(newName, testProduct.getName(), "Name should be updated");
        }

        @Test
        @DisplayName("Should get and set price correctly")
        void shouldGetAndSetPriceCorrectly() {
            // Arrange: New price value
            double newPrice = 129.99;

            // Act: Set new price
            testProduct.setPrice(newPrice);

            // Assert: Verify price was set and can be retrieved
            assertEquals(newPrice, testProduct.getPrice(), 0.001, "Price should be updated");
        }

        @Test
        @DisplayName("Should get and set quantity correctly")
        void shouldGetAndSetQuantityCorrectly() {
            // Arrange: New quantity value
            int newQuantity = 25;

            // Act: Set new quantity
            testProduct.setQuantity(newQuantity);

            // Assert: Verify quantity was set and can be retrieved
            assertEquals(newQuantity, testProduct.getQuantity(), "Quantity should be updated");
        }

        @Test
        @DisplayName("Should get and set image correctly")
        void shouldGetAndSetImageCorrectly() {
            // Arrange: New image value
            String newImg = "keyboard.png";

            // Act: Set new image
            testProduct.setImg(newImg);

            // Assert: Verify image was set and can be retrieved
            assertEquals(newImg, testProduct.getImg(), "Image should be updated");
        }

        @Test
        @DisplayName("Should handle negative quantity")
        void shouldHandleNegativeQuantity() {
            // Arrange: Negative quantity value
            int negativeQuantity = -5;

            // Act: Set negative quantity
            testProduct.setQuantity(negativeQuantity);

            // Assert: Verify negative quantity is accepted (business logic decision)
            assertEquals(negativeQuantity, testProduct.getQuantity(), "Negative quantity should be allowed");
        }

        @Test
        @DisplayName("Should handle zero price")
        void shouldHandleZeroPrice() {
            // Arrange: Zero price value
            double zeroPrice = 0.0;

            // Act: Set zero price
            testProduct.setPrice(zeroPrice);

            // Assert: Verify zero price is accepted
            assertEquals(zeroPrice, testProduct.getPrice(), 0.001, "Zero price should be allowed");
        }
    }

    @Nested
    @DisplayName("toString Method Tests")
    class ToStringTests {

        @Test
        @DisplayName("Should return formatted string representation")
        void shouldReturnFormattedStringRepresentation() {
            // Act: Get string representation
            String result = testProduct.toString();

            // Assert: Verify string contains all product information
            assertTrue(result.contains("📍Product:"), "Should contain product header");
            assertTrue(result.contains("id: " + VALID_ID), "Should contain ID");
            assertTrue(result.contains("type: " + VALID_TYPE), "Should contain type");
            assertTrue(result.contains("name: " + VALID_NAME), "Should contain name");
            assertTrue(result.contains("price: " + String.format("%.2f", VALID_PRICE)), "Should contain formatted price");
            assertTrue(result.contains("quantity: " + VALID_QUANTITY), "Should contain quantity");
            assertTrue(result.contains("img: " + VALID_IMG), "Should contain image");
        }

        @Test
        @DisplayName("Should handle null values in toString")
        void shouldHandleNullValuesInToString() {
            // Arrange: Create product with null values
            Product productWithNulls = new Product();
            productWithNulls.setId(1);
            productWithNulls.setName("Test Product");

            // Act: Get string representation
            String result = productWithNulls.toString();

            // Assert: Verify string handles null values gracefully
            assertTrue(result.contains("📍Product:"), "Should contain product header");
            assertTrue(result.contains("id: 1"), "Should contain ID");
            assertTrue(result.contains("name: Test Product"), "Should contain name");
            // Note: toString will show "null" for null values, which is acceptable
        }
    }

    @Nested
    @DisplayName("equals Method Tests")
    class EqualsTests {

        @Test
        @DisplayName("Should return true for products with same ID")
        void shouldReturnTrueForProductsWithSameId() {
            // Arrange: Create another product with same ID but different other fields
            Product otherProduct = new Product(VALID_ID, "Computer", "Different Name", 99.99, 5, "different.png");

            // Act: Compare products
            boolean result = testProduct.equals(otherProduct);

            // Assert: Should be equal because they have the same ID
            assertTrue(result, "Products with same ID should be equal");
        }

        @Test
        @DisplayName("Should return false for products with different ID")
        void shouldReturnFalseForProductsWithDifferentId() {
            // Arrange: Create another product with different ID
            Product otherProduct = new Product(999, VALID_TYPE, VALID_NAME, VALID_PRICE, VALID_QUANTITY, VALID_IMG);

            // Act: Compare products
            boolean result = testProduct.equals(otherProduct);

            // Assert: Should not be equal because they have different IDs
            assertFalse(result, "Products with different IDs should not be equal");
        }

        @Test
        @DisplayName("Should return false when comparing with null")
        void shouldReturnFalseWhenComparingWithNull() {
            // Act: Compare with null
            boolean result = testProduct.equals(null);

            // Assert: Should return false
            assertFalse(result, "Product should not equal null");
        }

        @Test
        @DisplayName("Should return false when comparing with different object type")
        void shouldReturnFalseWhenComparingWithDifferentObjectType() {
            // Arrange: Create a different object type
            String stringObject = "This is a string";

            // Act: Compare with different object type
            boolean result = testProduct.equals(stringObject);

            // Assert: Should return false
            assertFalse(result, "Product should not equal different object type");
        }

        @Test
        @DisplayName("Should return true when comparing with itself")
        void shouldReturnTrueWhenComparingWithItself() {
            // Act: Compare product with itself
            boolean result = testProduct.equals(testProduct);

            // Assert: Should return true (reflexive property)
            assertTrue(result, "Product should equal itself");
        }
    }

    @Nested
    @DisplayName("clone Method Tests")
    class CloneTests {

        @Test
        @DisplayName("Should create a copy of the product")
        void shouldCreateACopyOfTheProduct() throws CloneNotSupportedException {
            // Act: Clone the product
            Product clonedProduct = (Product) testProduct.clone();

            // Assert: Verify clone is a separate object with same values
            assertNotSame(testProduct, clonedProduct, "Clone should be a different object");
            assertEquals(testProduct.getId(), clonedProduct.getId(), "Clone should have same ID");
            assertEquals(testProduct.getType(), clonedProduct.getType(), "Clone should have same type");
            assertEquals(testProduct.getName(), clonedProduct.getName(), "Clone should have same name");
            assertEquals(testProduct.getPrice(), clonedProduct.getPrice(), 0.001, "Clone should have same price");
            assertEquals(testProduct.getQuantity(), clonedProduct.getQuantity(), "Clone should have same quantity");
            assertEquals(testProduct.getImg(), clonedProduct.getImg(), "Clone should have same image");
        }

        @Test
        @DisplayName("Should allow modifying clone without affecting original")
        void shouldAllowModifyingCloneWithoutAffectingOriginal() throws CloneNotSupportedException {
            // Arrange: Clone the product
            Product clonedProduct = (Product) testProduct.clone();
            int originalId = testProduct.getId();
            String originalName = testProduct.getName();

            // Act: Modify the clone
            clonedProduct.setId(999);
            clonedProduct.setName("Modified Name");

            // Assert: Original should remain unchanged
            assertEquals(originalId, testProduct.getId(), "Original ID should remain unchanged");
            assertEquals(originalName, testProduct.getName(), "Original name should remain unchanged");
            
            // Clone should have new values
            assertEquals(999, clonedProduct.getId(), "Clone ID should be modified");
            assertEquals("Modified Name", clonedProduct.getName(), "Clone name should be modified");
        }
    }

    @Nested
    @DisplayName("Edge Cases and Boundary Tests")
    class EdgeCasesAndBoundaryTests {

        @Test
        @DisplayName("Should handle very large numbers")
        void shouldHandleVeryLargeNumbers() {
            // Arrange: Very large values
            int largeId = Integer.MAX_VALUE;
            double largePrice = Double.MAX_VALUE;
            int largeQuantity = Integer.MAX_VALUE;

            // Act: Set large values
            testProduct.setId(largeId);
            testProduct.setPrice(largePrice);
            testProduct.setQuantity(largeQuantity);

            // Assert: Verify large values are handled correctly
            assertEquals(largeId, testProduct.getId(), "Should handle large ID");
            assertEquals(largePrice, testProduct.getPrice(), 0.001, "Should handle large price");
            assertEquals(largeQuantity, testProduct.getQuantity(), "Should handle large quantity");
        }

        @Test
        @DisplayName("Should handle empty strings")
        void shouldHandleEmptyStrings() {
            // Arrange: Empty string values
            String emptyString = "";

            // Act: Set empty strings
            testProduct.setType(emptyString);
            testProduct.setName(emptyString);
            testProduct.setImg(emptyString);

            // Assert: Verify empty strings are handled correctly
            assertEquals(emptyString, testProduct.getType(), "Should handle empty type");
            assertEquals(emptyString, testProduct.getName(), "Should handle empty name");
            assertEquals(emptyString, testProduct.getImg(), "Should handle empty image");
        }

        @Test
        @DisplayName("Should handle special characters in strings")
        void shouldHandleSpecialCharactersInStrings() {
            // Arrange: Strings with special characters
            String specialType = "Component & Accessory";
            String specialName = "Gaming Mouse (RGB) - Special Edition!";
            String specialImg = "mouse_rgb_special_edition.png";

            // Act: Set strings with special characters
            testProduct.setType(specialType);
            testProduct.setName(specialName);
            testProduct.setImg(specialImg);

            // Assert: Verify special characters are handled correctly
            assertEquals(specialType, testProduct.getType(), "Should handle special characters in type");
            assertEquals(specialName, testProduct.getName(), "Should handle special characters in name");
            assertEquals(specialImg, testProduct.getImg(), "Should handle special characters in image");
        }
    }
} 
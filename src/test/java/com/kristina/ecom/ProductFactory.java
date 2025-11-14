package com.kristina.ecom;

import java.util.Random;

import com.kristina.ecom.pms.domain.Product;

/**
 * Factory class for creating Product test objects.
 * 
 * This factory provides multiple approaches:
 * 1. Simple factory methods with predefined data
 * 2. Builder pattern for flexible object creation
 * 3. Faker integration for realistic test data
 * 4. Random data generation for edge case testing
 * 
 * Usage examples:
 * - ProductFactory.createGamingMouse()
 * - ProductFactory.builder().withName("Custom Product").build()
 * - ProductFactory.fake().build()
 * - ProductFactory.random().build()
 */
public class ProductFactory {

    // Predefined test data constants
    private static final String[] PRODUCT_TYPES = {"Component", "Computer", "Accessory"};
    private static final String[] COMPONENT_NAMES = {
        "Gaming Mouse", "Mechanical Keyboard", "Gaming Headset", "Webcam", "Microphone",
        "Graphics Card", "Processor", "RAM", "SSD", "Power Supply"
    };
    private static final String[] COMPUTER_NAMES = {
        "Gaming Desktop", "Workstation", "Laptop", "All-in-One", "Mini PC"
    };
    private static final String[] ACCESSORY_NAMES = {
        "Mouse Pad", "Cable", "Adapter", "Stand", "Case"
    };
    private static final double[] PRICE_RANGES = {9.99, 29.99, 49.99, 99.99, 199.99, 499.99, 999.99};
    
    private static final Random random = new Random();

    // ========================================
    // SIMPLE FACTORY METHODS (Rails-like)
    // ========================================

    /**
     * Creates a default product with reasonable test data
     */
    public static Product create() {
        return new Product(1, "Component", "Test Product", 49.99, 10, "test.png");
    }

    /**
     * Creates a gaming mouse product
     */
    public static Product createGamingMouse() {
        return new Product(1, "Component", "Gaming Mouse", 49.99, 15, "gaming_mouse.png");
    }

    /**
     * Creates a gaming keyboard product
     */
    public static Product createGamingKeyboard() {
        return new Product(2, "Component", "Mechanical Keyboard", 129.99, 8, "keyboard.png");
    }

    /**
     * Creates a laptop product
     */
    public static Product createLaptop() {
        return new Product(3, "Computer", "Gaming Laptop", 1299.99, 5, "laptop.png");
    }

    /**
     * Creates a component product
     */
    public static Product createComponent() {
        return new Product(4, "Component", "Graphics Card", 499.99, 3, "gpu.png");
    }

    /**
     * Creates an accessory product
     */
    public static Product createAccessory() {
        return new Product(5, "Accessory", "Mouse Pad", 19.99, 25, "mousepad.png");
    }

    /**
     * Creates a product with specific type
     */
    public static Product createByType(String type) {
        switch (type.toLowerCase()) {
            case "component":
                return createComponent();
            case "computer":
                return createLaptop();
            case "accessory":
                return createAccessory();
            default:
                return create();
        }
    }

    /**
     * Creates multiple products of the same type
     */
    public static Product[] createMultiple(String type, int count) {
        Product[] products = new Product[count];
        for (int i = 0; i < count; i++) {
            Product product = createByType(type);
            product.setId(i + 1);
            products[i] = product;
        }
        return products;
    }

    // ========================================
    // BUILDER PATTERN (Flexible creation)
    // ========================================

    /**
     * Returns a builder for creating custom products
     */
    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    /**
     * Builder class for creating products with custom attributes
     */
    public static class ProductBuilder {
        private int id = 1;
        private String type = "Component";
        private String name = "Test Product";
        private double price = 49.99;
        private int quantity = 10;
        private String img = "test.png";

        public ProductBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public ProductBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder withPrice(double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductBuilder withImg(String img) {
            this.img = img;
            return this;
        }

        public ProductBuilder asComponent() {
            this.type = "Component";
            return this;
        }

        public ProductBuilder asComputer() {
            this.type = "Computer";
            return this;
        }

        public ProductBuilder asAccessory() {
            this.type = "Accessory";
            return this;
        }

        public ProductBuilder expensive() {
            this.price = 999.99;
            return this;
        }

        public ProductBuilder cheap() {
            this.price = 9.99;
            return this;
        }

        public ProductBuilder inStock() {
            this.quantity = 50;
            return this;
        }

        public ProductBuilder outOfStock() {
            this.quantity = 0;
            return this;
        }

        public ProductBuilder lowStock() {
            this.quantity = 2;
            return this;
        }

        public Product build() {
            return new Product(id, type, name, price, quantity, img);
        }
    }

    // ========================================
    // FAKER INTEGRATION (Realistic data)
    // ========================================

    /**
     * Returns a faker builder for creating realistic test data
     */
    public static FakerBuilder fake() {
        return new FakerBuilder();
    }

    /**
     * Faker builder for realistic test data
     */
    public static class FakerBuilder {
        private int id = random.nextInt(1000) + 1;

        public FakerBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public Product build() {
            String type = PRODUCT_TYPES[random.nextInt(PRODUCT_TYPES.length)];
            String name = generateRealisticName(type);
            double price = generateRealisticPrice(type);
            int quantity = random.nextInt(100) + 1;
            String img = generateImageName(name);

            return new Product(id, type, name, price, quantity, img);
        }

        public Product buildComponent() {
            String name = COMPONENT_NAMES[random.nextInt(COMPONENT_NAMES.length)];
            double price = generateRealisticPrice("Component");
            int quantity = random.nextInt(50) + 1;
            String img = generateImageName(name);

            return new Product(id, "Component", name, price, quantity, img);
        }

        public Product buildComputer() {
            String name = COMPUTER_NAMES[random.nextInt(COMPUTER_NAMES.length)];
            double price = generateRealisticPrice("Computer");
            int quantity = random.nextInt(20) + 1;
            String img = generateImageName(name);

            return new Product(id, "Computer", name, price, quantity, img);
        }

        public Product buildAccessory() {
            String name = ACCESSORY_NAMES[random.nextInt(ACCESSORY_NAMES.length)];
            double price = generateRealisticPrice("Accessory");
            int quantity = random.nextInt(100) + 1;
            String img = generateImageName(name);

            return new Product(id, "Accessory", name, price, quantity, img);
        }

        private String generateRealisticName(String type) {
            switch (type) {
                case "Component":
                    return COMPONENT_NAMES[random.nextInt(COMPONENT_NAMES.length)];
                case "Computer":
                    return COMPUTER_NAMES[random.nextInt(COMPUTER_NAMES.length)];
                case "Accessory":
                    return ACCESSORY_NAMES[random.nextInt(ACCESSORY_NAMES.length)];
                default:
                    return "Generic " + type;
            }
        }

        private double generateRealisticPrice(String type) {
            switch (type) {
                case "Component":
                    return PRICE_RANGES[random.nextInt(PRICE_RANGES.length)] + random.nextDouble() * 50;
                case "Computer":
                    return 500 + random.nextDouble() * 1500; // $500-$2000
                case "Accessory":
                    return 5 + random.nextDouble() * 50; // $5-$55
                default:
                    return PRICE_RANGES[random.nextInt(PRICE_RANGES.length)];
            }
        }

        private String generateImageName(String productName) {
            return productName.toLowerCase()
                    .replaceAll("[^a-z0-9]", "_")
                    .replaceAll("_+", "_")
                    .replaceAll("^_|_$", "") + ".png";
        }
    }

    // ========================================
    // RANDOM DATA GENERATOR (Edge cases)
    // ========================================

    /**
     * Returns a random builder for edge case testing
     */
    public static RandomBuilder random() {
        return new RandomBuilder();
    }

    /**
     * Random builder for edge case testing
     */
    public static class RandomBuilder {
        public Product build() {
            return new Product(
                random.nextInt(Integer.MAX_VALUE),
                generateRandomString(10),
                generateRandomString(20),
                random.nextDouble() * 10000,
                random.nextInt(1000),
                generateRandomString(15) + ".png"
            );
        }

        public Product buildWithNulls() {
            Product product = new Product();
            product.setId(random.nextInt(1000));
            // Leave other fields as null/default
            return product;
        }

        public Product buildWithEmptyStrings() {
            return new Product(
                random.nextInt(1000),
                "",
                "",
                random.nextDouble() * 1000,
                random.nextInt(100),
                ""
            );
        }

        public Product buildWithExtremeValues() {
            return new Product(
                Integer.MAX_VALUE,
                "Extreme Product",
                "Extreme Name",
                Double.MAX_VALUE,
                Integer.MAX_VALUE,
                "extreme.png"
            );
        }

        private String generateRandomString(int length) {
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append(chars.charAt(random.nextInt(chars.length())));
            }
            return sb.toString();
        }
    }

    // ========================================
    // CONVENIENCE METHODS
    // ========================================

    /**
     * Creates a list of products for testing collections
     */
    public static Product[] createProductList(int count) {
        Product[] products = new Product[count];
        for (int i = 0; i < count; i++) {
            products[i] = fake().withId(i + 1).build();
        }
        return products;
    }

    /**
     * Creates products with different stock levels
     */
    public static Product createInStock() {
        return builder().inStock().build();
    }

    public static Product createOutOfStock() {
        return builder().outOfStock().build();
    }

    public static Product createLowStock() {
        return builder().lowStock().build();
    }

    /**
     * Creates products with different price ranges
     */
    public static Product createExpensive() {
        return builder().expensive().build();
    }

    public static Product createCheap() {
        return builder().cheap().build();
    }
} 
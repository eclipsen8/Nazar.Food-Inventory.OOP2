import java.util.ArrayList;
import java.util.List;

class Product {
    private final int productId;
    private final String name;
    private final double price;
    private int quantity;

    public Product(int productId, String name, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double calculateTotalValue() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "Product[ID: " + productId + ", Name: " + name + ", Price: " + price + ", Quantity: " + quantity + "]";
    }
}

class Supplier {
    private final int supplierId;
    private final String name;
    private final String contactInfo;
    private final List<Product> suppliedProducts;

    public Supplier(int supplierId, String name, String contactInfo) {
        this.supplierId = supplierId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.suppliedProducts = new ArrayList<>();
    }

    public void addProduct(Product product) {
        suppliedProducts.add(product);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Supplier[ID: " + supplierId + ", Name: " + name + ", Contact Info: " + contactInfo + "]\n");
        sb.append("Supplied Products:\n");
        for (Product product : suppliedProducts) {
            sb.append("  ").append(product).append("\n");
        }
        return sb.toString();
    }
}

class Inventory {
    private final List<Product> products;

    public Inventory() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(int productId) {
        products.removeIf(product -> product.getProductId() == productId);
    }

    public double calculateInventoryValue() {
        return products.stream().mapToDouble(Product::calculateTotalValue).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Inventory:\n");
        for (Product product : products) {
            sb.append(product).append("\n");
        }
        return sb.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product(1, "Ice Cream", 500.0, 20);
        Product product2 = new Product(2, "Pik", 500.0, 30);
        Product product3 = new Product(3, "Banana", 170.0, 25);
        Product product4 = new Product(4, "Orange", 180.0, 40);
        Product product5 = new Product(5, "Apple", 150.0, 35);

        Supplier supplier1 = new Supplier(1, "FoodSupplier1", "contact@foodsupplier1.com");
        supplier1.addProduct(product1);
        supplier1.addProduct(product2);

        Supplier supplier2 = new Supplier(2, "FoodSupplier2", "info@foodsupplier2.com");
        supplier2.addProduct(product3);
        supplier2.addProduct(product4);
        supplier2.addProduct(product5);

        Inventory inventory = new Inventory();
        inventory.addProduct(product1);
        inventory.addProduct(product2);
        inventory.addProduct(product3);
        inventory.addProduct(product4);
        inventory.addProduct(product5);

        System.out.println("Initial Inventory:");
        System.out.println(inventory);
        System.out.println("Total Inventory Value: " + inventory.calculateInventoryValue() + "\n");

        System.out.println("Supplier Details:");
        System.out.println(supplier1);
        System.out.println(supplier2);

        product1.setQuantity(18);
        System.out.println("Updated Product: " + product1);
        System.out.println("Total Inventory Value: " + inventory.calculateInventoryValue() + "\n");

        inventory.removeProduct(2);
        System.out.println("Inventory after removing product with ID 2:");
        System.out.println(inventory);
    }
}

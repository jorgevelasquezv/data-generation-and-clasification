package com.poli.pojo;

import java.io.Serializable;
import java.util.UUID;

/**
 * Represents a product sold, including the quantity sold and the product information.
 */
public class ProductSold implements Serializable {
    
    private static final long serialVersionUID = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    int soldQuantity;
    Product product;

    /**
     * Gets the quantity of the product sold.
     * 
     * @return The quantity of the product sold.
     */
    public int getSoldQuantity() {
        return soldQuantity;
    }

    /**
     * Sets the quantity of the product sold.
     * 
     * @param soldQuantity The quantity of the product sold.
     * @return The updated ProductSold object.
     */
    public ProductSold setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
        return this;
    }

    /**
     * Gets the product information.
     * 
     * @return The product information.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product information.
     * 
     * @param product The product information.
     * @return The updated ProductSold object.
     */
    public ProductSold setProduct(Product product) {
        this.product = product;
        return this;
    }

    /**
     * Returns a string representation of the ProductSold object.
     *
     * @return The string representation of the ProductSold object.
     */
    @Override
    public String toString() {
        return "ProductSold{" +
                "soldQuantity=" + soldQuantity +
                ", product=" + product +
                '}';
    }
}

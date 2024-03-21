package com.poli.pojo;

import java.util.Objects;

/**
 * The Product class represents a product in a store.
 * It contains information about the product's ID, name, and unit price.
 */
public class Product {
    private String productId;
    private String productName;
    private double unitPrice;

    /**
     * Returns the ID of the product.
     *
     * @return the product ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Sets the ID of the product.
     *
     * @param productId the product ID to set
     * @return the updated Product object
     */
    public Product setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    /**
     * Returns the name of the product.
     *
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the name of the product.
     *
     * @param productName the product name to set
     * @return the updated Product object
     */
    public Product setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    /**
     * Returns the unit price of the product.
     *
     * @return the unit price
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Sets the unit price of the product.
     *
     * @param unitPrice the unit price to set
     * @return the updated Product object
     */
    public Product setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }
    
    /**
     * Returns a string representation of the product.
     *
     * @return a string representation of the product
     */
    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }

    /**
     * Checks if this product is equal to another object based on the productId.
     *
     * @param obj the object to compare with
     * @return true if the products are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Product otherProduct = (Product) obj;
        return productId.equals(otherProduct.productId);
    }

    /**
     * Returns the hash code value for the product.
     *
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

}

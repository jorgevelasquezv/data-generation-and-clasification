package com.poli.pojo;

/**
 * The ProductReport class represents a report for a product, including the product name and the total number of units sold.
 */
public class ProductReport {
    private String productName;
    private double totalSold;

    /**
     * Gets the name of the product.
     * 
     * @return The name of the product.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the name of the product.
     * 
     * @param productName The name of the product.
     * @return The ProductReport object.
     */
    public ProductReport setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    /**
     * Gets the total number of units sold for the product.
     * 
     * @return The total number of units sold.
     */
    public double getTotalSold() {
        return totalSold;
    }

    /**
     * Sets the total number of units sold for the product.
     * 
     * @param totalSold The total number of units sold.
     * @return The ProductReport object.
     */
    public ProductReport setTotalSold(double totalSold) {
        this.totalSold = totalSold;
        return this;
    }
}

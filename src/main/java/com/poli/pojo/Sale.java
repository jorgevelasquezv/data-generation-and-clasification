package com.poli.pojo;

import java.util.List;

/**
 * The Sale class represents a sale made by a salesman.
 * It contains information about the salesman and the products sold.
 */
public class Sale {
    private Salesman salesman;
    private List<ProductSold> soldProducts;

    /**
     * Returns the salesman who made the sale.
     * 
     * @return the salesman who made the sale
     */
    public Salesman getSalesman() {
        return salesman;
    }

    /**
     * Sets the salesman who made the sale.
     * 
     * @param salesman the salesman who made the sale
     */
    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    /**
     * Returns the list of products sold in the sale.
     * 
     * @return the list of products sold in the sale
     */
    public List<ProductSold> getSoldProducts() {
        return soldProducts;
    }

    /**
     * Sets the list of products sold in the sale.
     * 
     * @param soldProducts the list of products sold in the sale
     */
    public void setSoldProducts(List<ProductSold> soldProducts) {
        this.soldProducts = soldProducts;
    }

    /**
     * Returns a string representation of the Sale object.
     *
     * @return a string representation of the Sale object
     */
    @Override
    public String toString() {
        return "Sale{" +
                "salesman=" + salesman +
                ", soldProducts=" + soldProducts +
                '}';
    }
}

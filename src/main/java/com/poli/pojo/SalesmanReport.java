package com.poli.pojo;

import java.io.Serializable;
import java.util.UUID;

/**
 * The SalesmanReport class represents a report for a salesman, containing their full name and total sales.
 */
public class SalesmanReport implements Serializable {
    private static final long serialVersionUID = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    private String fullName;
    private int totalSales;

    /**
     * Gets the full name of the salesman.
     * 
     * @return The full name of the salesman.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the salesman.
     * 
     * @param fullName The full name of the salesman.
     * @return The SalesmanReport object.
     */
    public SalesmanReport setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * Gets the total sales of the salesman.
     * 
     * @return The total sales of the salesman.
     */
    public int getTotalSales() {
        return totalSales;
    }

    /**
     * Sets the total sales of the salesman.
     * 
     * @param totalSales The total sales of the salesman.
     * @return The SalesmanReport object.
     */
    public SalesmanReport setTotalSales(int totalSales) {
        this.totalSales = totalSales;
        return this;
    }

    /**
     * Returns a string representation of the SalesmanReport object.
     *
     * @return The string representation of the SalesmanReport object.
     */
    @Override
    public String toString() {
        return "SalesmanReport{" +
                "fullName='" + fullName + '\'' +
                ", totalSales=" + totalSales +
                '}';
    }
}

package com.poli;

import java.util.List;
import java.util.logging.Logger;

import com.poli.pojo.Product;
import com.poli.pojo.Sale;
import com.poli.pojo.Salesman;
import com.poli.service.FileManager;

/**
 * The Main class is the entry point of the application.
 * It reads information from files, processes the data, and writes reports.
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * The main method is the entry point of the application.
     * It reads information from files, processes the data, and writes reports.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();

        // Read information about salesmen from a file
        List<Salesman> salesmen = fileManager.readInformationSalesmanFile("files/info/salesmen_info.csv");

        // Read information about available products from a file
        List<Product> products = fileManager.readFileWithInformationOnAvailableProducts("files/info/products.csv");

        // Read sales information from files and generate sales objects
        List<Sale> sales = fileManager.readSalesInformationFile("files/salesmen", products, salesmen);

        try {
            // Write a report with information about salesmen
            fileManager.writeSalesMenReport(sales, "files/reports/salesmen_report.csv");
            LOGGER.info("The salesmen report was written successfully!");
        } catch (Exception e) {
            LOGGER.severe("An error occurred while writing the salesmen report. " + e.getMessage());
        }

        try {
            // Write a report with information about products
            fileManager.writeProductsReport(sales, products, "files/reports/products_report.csv");
            LOGGER.info("The products report was written successfully!");
        } catch (Exception e) {
            LOGGER.severe("An error occurred while writing the products report. " + e.getMessage());
        }
    }
}

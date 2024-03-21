package com.poli;

import java.util.List;
import java.util.logging.Logger;

import com.poli.pojo.Product;
import com.poli.pojo.Sale;
import com.poli.pojo.Salesman;
import com.poli.service.FileManager;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        List<Salesman> salesmen = fileManager.readInformationSalesmanFile("files/info/salesmen_info.csv");

        List<Product> products = fileManager.readFileWithInformationOnAvailableProducts("files/info/products.csv");

        List<Sale> sales = fileManager.readSalesInformationFile("files/salesmen", products, salesmen);

        try {
            fileManager.writeSalesMenReport(sales, "files/reports/salesmen_report.csv");
            LOGGER.info("The salesmen report was written successfully!");
        } catch (Exception e) {
            LOGGER.severe("An error occurred while writing the salesmen report " + e.getMessage());
        }

        try {
            fileManager.writeProductsReport(sales, products, "files/reports/products_report.csv");
            LOGGER.info("The products report was written successfully!");
        } catch (Exception e) {
            LOGGER.severe("An error occurred while writing the products report " + e.getMessage());
        }
    }
}
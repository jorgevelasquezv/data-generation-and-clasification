package com.poli.service;

import com.poli.pojo.Product;
import com.poli.pojo.ProductReport;
import com.poli.pojo.ProductSold;
import com.poli.pojo.Sale;
import com.poli.pojo.Salesman;
import com.poli.pojo.SalesmanReport;

import java.util.logging.Logger;
import java.util.logging.Level;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * The FileManager class contains methods to read and write information from and
 * to files.
 */
public class FileManager {
    private static final Logger LOGGER = Logger.getLogger(FileManager.class.getName());

    /**
     * Reads information from a sales men file and returns a list of Salesman
     * objects.
     *
     * @param filePath the path of the file to be read
     * @return a list of Salesman objects containing the information read from the
     *         file
     */
    public List<Salesman> readInformationSalesmanFile(String filePath) {
        List<Salesman> salesMen = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String lineOfFile;
            while ((lineOfFile = br.readLine()) != null) {
                String[] fragments = lineOfFile.split(";");
                if (fragments.length == 4) {
                    Salesman salesman = new Salesman();
                    salesman.setDocumentType(fragments[0]);
                    salesman.setDocumentNumber(Long.parseLong(fragments[1]));
                    salesman.setFirstName(fragments[2]);
                    salesman.setLastName(fragments[3]);
                    salesMen.add(salesman);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "{0} file not found", filePath);
        }
        return salesMen;
    }

    /**
     * Reads information from a products file and returns a list of Product objects.
     *
     * @param filePath the path of the file to be read
     * @return a list of Product objects containing the information read from the
     *         file
     */
    public List<Product> readFileWithInformationOnAvailableProducts(String filePath) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String lineOfFile;
            while ((lineOfFile = br.readLine()) != null) {
                String[] fragments = lineOfFile.split(";");
                if (fragments.length == 3) {
                    Product product = new Product();
                    product.setProductId(fragments[0]);
                    product.setProductName(fragments[1]);
                    product.setUnitPrice(Double.parseDouble(fragments[2]));
                    products.add(product);
                }
            }
        } catch (IOException e) {
            LOGGER.severe(filePath + " file not found");
        }
        return products;
    }

    /**
     * Reads information from a sales file and returns a list of Sale objects.
     *
     * @param folderPath the path of the folder containing the files to be read
     * @param products   a list of Product objects containing the information of the
     *                   available products
     * @return a list of Sale objects containing the information read from the files
     */
    public List<Sale> readSalesInformationFile(String folderPath, List<Product> products, List<Salesman> salesmen) {
        List<Sale> sales = new ArrayList<>();
        File folder = new File(folderPath);
        if (!folder.exists()) {
            LOGGER.log(Level.SEVERE, "{0} folder not found", folderPath);
            return sales;
        }
        if (folder.listFiles() == null) {
            LOGGER.severe("No files found in the folder");
            return sales;
        }
        if (products == null || products.isEmpty()) {
            LOGGER.severe("The list of products is null or empty");
            return sales;
        }
        if (salesmen == null || salesmen.isEmpty()) {
            LOGGER.severe("The list of salesmen is null or empty");
            return sales;
        }
        for (File file : folder.listFiles()) {
            processFile(file, products, salesmen, sales);
        }
        return sales.stream().filter(sale -> sale.getSoldProducts() != null && !sale.getSoldProducts().isEmpty()
                && sale.getSalesman() != null).toList();
    }

    /**
     * Processes a file and extracts data to populate the given lists of products,
     * salesmen, and sales.
     *
     * @param file     The file to be processed.
     * @param products The list of products to populate with data from the file.
     * @param salesmen The list of salesmen to populate with data from the file.
     * @param sales    The list of sales to populate with data from the file.
     */
    private void processFile(File file, List<Product> products, List<Salesman> salesmen, List<Sale> sales) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;
            Sale sale = new Sale();
            List<ProductSold> productsSold = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] fragments = line.split(";");
                if (fragments.length == 2) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        sale.setSalesman(getSalesman(fragments, salesmen));
                    } else {
                        productsSold.add(getProductSold(fragments, products));
                    }
                }
                sale.setSoldProducts(productsSold);
            }
            sales.add(sale);
        } catch (IOException e) {
            LOGGER.severe(file.getName() + " file not found");
        }
    }

    /**
     * Represents a salesman.
     */
    private Salesman getSalesman(String[] fragments, List<Salesman> salesmen) {
        return salesmen.stream()
                .filter(salesman -> salesman.getDocumentNumber() == Long.parseLong(fragments[1])).findFirst()
                .orElse(null);
    }

    /**
     * Represents a product sold, including the product itself and the quantity
     * sold.
     */
    private ProductSold getProductSold(String[] fragments, List<Product> products) {
        return new ProductSold().setProduct(getProduct(fragments[0], products))
                .setSoldQuantity(Integer.parseInt(fragments[1]));
    }

    /**
     * Represents a product.
     */
    private Product getProduct(String productId, List<Product> products) {
        return products.stream().filter(product -> product.getProductId().equals(productId)).findFirst().orElse(null);
    }

    /**
     * Writes a salesmen report to a file.
     * 
     * @param sales    the list of sales
     * @param filePath the path of the file to write the report to
     */
    public void writeSalesMenReport(List<Sale> sales, String filePath) {
        if (sales == null || sales.isEmpty()) {
            throw new IllegalArgumentException("The list of sales is null or empty");
        }

        List<SalesmanReport> salesmanReports = new ArrayList<>();
        sales.stream().map(Sale::getSalesman).distinct().forEach(salesman -> {
            SalesmanReport salesmanReport = new SalesmanReport();
            salesmanReport.setFullName(salesman.getFirstName() + " " + salesman.getLastName());
            salesmanReport.setTotalSales((int) calculateCollection(salesman, sales));
            salesmanReports.add(salesmanReport);
        });

        salesmanReports.sort((s1, s2) -> s2.getTotalSales() - s1.getTotalSales());

        createFolderIfNotExist(filePath);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (SalesmanReport salesmanReport : salesmanReports) {
                bw.write(salesmanReport.getFullName() + ";" + salesmanReport.getTotalSales());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates the total collection amount for a given salesman based on the
     * sales data.
     *
     * @param salesman The salesman for whom the collection amount needs to be
     *                 calculated.
     * @param sales    The list of sales data.
     * @return The total collection amount for the given salesman.
     */
    private double calculateCollection(Salesman salesman, List<Sale> sales) {
        return sales.stream().filter(sale -> sale.getSalesman().equals(salesman))
                .mapToDouble(sale -> sale.getSoldProducts().stream()
                        .mapToDouble(
                                productSold -> productSold.getProduct().getUnitPrice() * productSold.getSoldQuantity())
                        .sum())
                .sum();
    }

    /**
     * Writes a report of products sold to a file.
     *
     * @param productsSold The list of products sold.
     * @param filePath     The path of the file to write the report to.
     */
    public void writeProductsReport(List<Sale> sales, List<Product> products, String filePath) {
        if (sales == null || sales.isEmpty()) {
            throw new IllegalArgumentException("The list of sales is null or empty");
        }

        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("The list of products is null or empty");
        }

        List<ProductSold> productsSold = generateProductSoldList(sales, products);
        List<ProductReport> productsReport = new ArrayList<>();
        productsSold.stream().map(productSold -> {
            ProductReport productReport = new ProductReport();
            productReport.setProductName(productSold.getProduct().getProductName());
            productReport.setTotalSold(
                    Math.round(productSold.getSoldQuantity() * productSold.getProduct().getUnitPrice() * 100.0)
                            / 100.0);
            return productReport;
        }).distinct().forEach(productsReport::add);

        productsReport.sort((p1, p2) -> (int) (p2.getTotalSold() - p1.getTotalSold()));

        createFolderIfNotExist(filePath);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (ProductReport productReport : productsReport) {
                bw.write(productReport.getProductName() + ";" + productReport.getTotalSold());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a folder if it does not exist.
     *
     * @param filePath The path of the folder to be created.
     */
    private void createFolderIfNotExist(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
    }

    /**
     * Generates a list of ProductSold objects by extracting the sold products from
     * each sale in the given list of sales.
     * The method sums the quantities of products with the same ID and generates a
     * new list.
     *
     * @param sales    The list of Sale objects.
     * @param products The list of Product objects.
     * @return A list of ProductSold objects with summed quantities for products
     *         with the same ID.
     */
    public List<ProductSold> generateProductSoldList(List<Sale> sales, List<Product> products) {
        List<ProductSold> productSoldList = new ArrayList<>();
        Map<String, Integer> productQuantityMap = new HashMap<>();

        for (Sale sale : sales) {
            for (ProductSold productSold : sale.getSoldProducts()) {
                String productId = productSold.getProduct().getProductId();
                int quantity = productSold.getSoldQuantity();

                productQuantityMap.put(productId, productQuantityMap.getOrDefault(productId, 0) + quantity);
            }
        }

        for (Map.Entry<String, Integer> entry : productQuantityMap.entrySet()) {
            String productId = entry.getKey();
            int quantity = entry.getValue();

            Product product = getProduct(productId, products);

            if (product != null) {
                ProductSold productSold = new ProductSold();
                productSold.setProduct(product);
                productSold.setSoldQuantity(quantity);
                productSoldList.add(productSold);
            }
        }

        return productSoldList;
    }

}

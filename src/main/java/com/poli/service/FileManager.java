package com.poli.service;

import com.poli.pojo.Product;
import com.poli.pojo.ProductReport;
import com.poli.pojo.ProductSold;
import com.poli.pojo.Sale;
import com.poli.pojo.Salesman;
import com.poli.pojo.SalesmanReport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * The FileManager class contains methods to read and write information from and to files.
 */
public class FileManager {
    /**
     * Reads information from a sales men file and returns a list of Salesman objects.
     *
     * @param filePath the path of the file to be read
     * @return a list of Salesman objects containing the information read from the file
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
            e.printStackTrace();
        }
        return salesMen;
    }

    /**
     * Reads information from a products file and returns a list of Product objects.
     *
     * @param filePath the path of the file to be read
     * @return a list of Product objects containing the information read from the file
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
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Reads information from a sales file and returns a list of Sale objects.
     *
     * @param folderPath the path of the folder containing the files to be read
     * @param products a list of Product objects containing the information of the available products
     * @return a list of Sale objects containing the information read from the files
     */
    public List<Sale> readSalesInformationFile(String folderPath, List<Product> products) {
        List<Sale> sales = new ArrayList<>();
        File folder = new File(folderPath);
        for (File file : folder.listFiles()) {
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
                            sale.setSalesman(getSalesman(fragments));
                        } else {
                            productsSold.add(getProductSold(fragments, products));
                        }
                    }
                }
                sales.add(sale);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sales;
    }


    /**
     * Represents a salesman.
     */
    private Salesman getSalesman(String[] fragments) {
        return new Salesman().setDocumentType(fragments[0]).setDocumentNumber(Long.parseLong(fragments[1]));
    }

    /**
     * Represents a product sold, including the product itself and the quantity sold.
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
        List<SalesmanReport> salesmanReports = new ArrayList<>();
        sales.stream().map(Sale::getSalesman).distinct().forEach(salesman -> {
            SalesmanReport salesmanReport = new SalesmanReport();
            salesmanReport.setFullName(salesman.getFirstName() + " " + salesman.getLastName());
            salesmanReport.setTotalSales((int) calculateCollection(salesman, sales));
            salesmanReports.add(salesmanReport);
        });

        salesmanReports.sort((s1, s2) -> s2.getTotalSales() - s1.getTotalSales());

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
     * Calculates the total collection amount for a given salesman based on the sales data.
     *
     * @param salesman The salesman for whom the collection amount needs to be calculated.
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
     * @param filePath The path of the file to write the report to.
     */
    public void writeProductsReport(List<ProductSold> productsSold, String filePath) {
        List<ProductReport> productsReport = new ArrayList<>();
        productsSold.stream().map(productSold -> {
            ProductReport productReport = new ProductReport();
            productReport.setProductName(productSold.getProduct().getProductName());
            productReport.setTotalSold(productSold.getSoldQuantity() * productSold.getProduct().getUnitPrice());
            return productReport;
        }).distinct().forEach(productsReport::add);

        productsReport.sort((p1, p2) -> (int) (p2.getTotalSold() - p1.getTotalSold()));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (ProductReport productReport : productsReport) {
                bw.write(productReport.getProductName()+ ";" + productReport.getTotalSold());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


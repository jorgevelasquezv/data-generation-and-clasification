package com.poli.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import com.poli.pojo.Salesman;

/**
 * The GeneratorFilesTest class is responsible for generating files with sales
 * and product data.
 */
public class GeneratorFilesTest {
    private static final Logger logger = Logger.getLogger(GeneratorFilesTest.class.getName());
    private final Random random = new Random();

    private static final String[] FIRST_NAMES = { "Juan", "Maria", "Carlos", "Ana", "David", "Sofia", "Jose", "Laura",
            "Francisco", "Carmen" };
    private static final String[] LAST_NAMES = { "Gomez", "Lopez", "Martinez", "Garcia", "Rodriguez", "Perez",
            "Sanchez", "Torres", "Ramirez", "Castro" };

    /**
     * Creates a file with sales data for a specific salesperson.
     * 
     * @param randomSalesCount The number of random sales to generate.
     * @param name             The name of the salesperson.
     * @param id               The ID of the salesperson.
     */
    public void createSalesMenFile(int randomSalesCount, String name, long id) {
        File directory = createDirectory();
        try (FileWriter fileWriter = new FileWriter(directory + "/salesman_" + id + ".txt")) {

            fileWriter.write(name + ";" + id + "\n");

            for (int i = 0; i < randomSalesCount; i++) {
                int productId = random.nextInt(100) + 1;
                int quantity = random.nextInt(10) + 1;
                fileWriter.write(productId + ";" + quantity + "\n");
            }

            logger.info("File of sales for the vendor created successfully");
        } catch (IOException e) {
            logger.severe("An error occurred while creating the file of sales for the vendor" + e.getMessage());
        }
    }

    /**
     * Creates a file with product data.
     * 
     * @param productsCount The number of products to generate.
     */
    public void createProductsFile(int productsCount) {
        File directory = createDirectory();
        try (FileWriter fileWriter = new FileWriter(directory + "/products.txt")) {
            for (int i = 0; i < productsCount; i++) {
                int productId = i + 1;
                String productName = "Product-" + productId;
                double productPrice = 10 + (150 - 10) * random.nextDouble();
                fileWriter.write(productId + ";" + productName + ";" + productPrice + "\n");
            }
            logger.info("File of products created successfully");
        } catch (IOException e) {
            logger.severe("An error occurred while creating the file of products" + e.getMessage());
        }
    }

    /**
     * Creates a file with salesman information.
     * 
     * @param salesmanCount The number of salesmen to generate.
     */
    public List<Salesman> createSalesmanInfoFile(int salesmanCount) {
        File directory = createDirectory();
        try (FileWriter fileWriter = new FileWriter(directory + "/salesmen_info.txt")) {
            List<Salesman> salesmen = new ArrayList<>();
            for (int i = 0; i < salesmanCount; i++) {
                Salesman salesman = new Salesman();
                String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
                String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
                String documentType = "CC";
                Long documentNumber = Long.valueOf(10000000L + random.nextInt(90000000));
                fileWriter.write(documentType + ";" + documentNumber + ";" + firstName + ";" + lastName + "\n");
                salesman.setFirstName(firstName).setLastName(lastName).setDocumentType(documentType)
                        .setDocumentNumber(documentNumber);
                salesmen.add(salesman);
            }
            logger.info("File of information of salesman created successfully");
            return salesmen;
        } catch (IOException e) {
            logger.severe("An error occurred while creating the file of information of salesman: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Creates a folder and returns a File object representing the folder.
     *
     * @return A File object representing the created folder.
     */
    private File createDirectory() {
        File folder = new File("files");
        if (!folder.exists()) {
            folder.mkdir();
        }
        return folder;
    }

    /**
     * Returns the random number generator used by this class.
     *
     * @return the random number generator
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Returns an array of first names.
     *
     * @return an array of first names
     */
    public String[] getFirstNames() {
        return FIRST_NAMES;
    }

    /**
     * Returns an array of last names.
     *
     * @return an array of last names
     */
    public String[] getLastNames() {
        return LAST_NAMES;
    }

}

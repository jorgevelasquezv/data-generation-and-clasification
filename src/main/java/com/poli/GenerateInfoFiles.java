package com.poli;

import java.util.List;
import java.util.logging.Logger;

import com.poli.pojo.Salesman;
import com.poli.service.GeneratorFilesTest;

/**
 * This class is responsible for generating information files.
 * It creates salesman information files, products files, and salesmen files.
 */
public class GenerateInfoFiles {
    private static final Logger logger = Logger.getLogger(GenerateInfoFiles.class.getName());

    /**
     * The main method of the GenerateInfoFiles class.
     * It creates salesman information files, products files, and salesmen files.
     * It logs the success or failure of file generation.
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        GeneratorFilesTest generator = new GeneratorFilesTest();

        try {
            List<Salesman> salesMen = generator.createSalesmanInfoFile(10);

            generator.createProductsFile(50);

            for (Salesman salesman : salesMen) {
                String fullName = salesman.getFirstName() + " " + salesman.getLastName();
                generator.createSalesMenFile(10, fullName, salesman.getDocumentNumber());
            }

            logger.info("Files generated successfully!");
        } catch (Exception e) {
            logger.severe("An error occurred while creating files: " + e.getMessage());
        }
    }
}

package com.poli.pojo;

import java.util.Objects;

/**
 * The Salesman class represents a salesman in a system.
 * It contains information about the salesman's document type, document number,
 * first name, and last name.
 */
public class Salesman {
    private String documentType;
    private Long documentNumber;
    private String firstName;
    private String lastName;

    /**
     * Returns the document type of the salesman.
     * 
     * @return the document type of the salesman
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * Sets the document type of the salesman.
     * 
     * @param documentType the document type to set
     * @return the Salesman object
     */
    public Salesman setDocumentType(String documentType) {
        this.documentType = documentType;
        return this;
    }

    /**
     * Returns the document number of the salesman.
     * 
     * @return the document number of the salesman
     */
    public Long getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Sets the document number of the salesman.
     * 
     * @param documentNumber the document number to set
     * @return the Salesman object
     */
    public Salesman setDocumentNumber(Long documentNumber) {
        this.documentNumber = documentNumber;
        return this;
    }

    /**
     * Returns the first name of the salesman.
     * 
     * @return the first name of the salesman
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the salesman.
     * 
     * @param firstName the first name to set
     * @return the Salesman object
     */
    public Salesman setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Returns the last name of the salesman.
     * 
     * @return the last name of the salesman
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the salesman.
     * 
     * @param lastName the last name to set
     * @return the Salesman object
     */
    public Salesman setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Checks if this Salesman object is equal to another object.
     * Two Salesman objects are considered equal if their document number and
     * document type are equal.
     * 
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Salesman other = (Salesman) obj;
        return documentNumber.equals(other.documentNumber) && documentType.equals(other.documentType);
    }

    /**
     * Returns a hash code value for the Salesman object. The hash code is calculated based on the documentNumber and documentType properties.
     *
     * @return the hash code value for the Salesman object
     */
    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, documentType);
    }

    /**
     * Returns a string representation of the Salesman object.
     *
     * @return a string representation of the Salesman object
     */
    @Override
    public String toString() {
        return "Salesman{" +
                "documentType='" + documentType + '\'' +
                ", documentNumber=" + documentNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

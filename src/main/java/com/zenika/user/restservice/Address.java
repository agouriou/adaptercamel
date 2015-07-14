
package com.zenika.user.restservice;

public class Address {

    protected String street;
    protected String town;
    protected int postcode;

    /**
     * Obtient la valeur de la propriété street.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * Définit la valeur de la propriété street.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Obtient la valeur de la propriété town.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTown() {
        return town;
    }

    /**
     * Définit la valeur de la propriété town.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTown(String value) {
        this.town = value;
    }

    /**
     * Obtient la valeur de la propriété postcode.
     * 
     */
    public int getPostcode() {
        return postcode;
    }

    /**
     * Définit la valeur de la propriété postcode.
     * 
     */
    public void setPostcode(int value) {
        this.postcode = value;
    }

}

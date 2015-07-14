package com.zenika.user.restservice;

/**
 * Created by armel on 10/07/15.
 */
public class FlatUser {

    protected int id;
    protected String name;
    protected int age;
    protected int addressZipCode;
    protected String addressTown;
    protected String addressStreet;

    public FlatUser(){

    }

    public FlatUser(int id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * Obtient la valeur de la propriété id.
     *
     */
    public int getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     *
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété name.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Définit la valeur de la propriété name.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtient la valeur de la propriété age.
     *
     */
    public int getAge() {
        return age;
    }

    /**
     * Définit la valeur de la propriété age.
     *
     */
    public void setAge(int value) {
        this.age = value;
    }


    public int getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(int addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public String getAddressTown() {
        return addressTown;
    }

    public void setAddressTown(String addressTown) {
        this.addressTown = addressTown;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CreateOrder;


public class Address {
    
     private String country;
    private String town;
    private String postalCode;
    private String street;
    private String housenr;
    private String addition;

    public Address(String country, String town, String postalCode, String street, String housenr, String addition) {
        this.country = country;
        this.town = town;
        this.postalCode = postalCode;
        this.street = street;
        this.housenr = housenr;
        this.addition = addition;
    }

    @Override
    public String toString() {
        return  country + ", " + town + ", " + postalCode + ", " + street + ", " + housenr + ", " + addition ;
    }
    
}

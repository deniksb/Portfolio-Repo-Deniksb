/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CreateOrder;

import java.time.LocalDate;

/**
 *
 * @author Denislav Berberov – 3863158 – deniksb
 * {@code 432801@student.fontys.nl}
 */
public class Order {
     private Address pickupAddress;
   private Address dropoffAddress;
   private int ordernr;
   private LocalDate date;
   private String product;
   private ProductType productType;
   private String process;
   private int receiptnr;
   private String weight;
   private double volume;
   private double pricePerTon;

    public Order(Address pickupAddress, Address dropoffAddress, String product, ProductType productType, String weight, LocalDate date) {
        this.pickupAddress = pickupAddress;
        this.dropoffAddress = dropoffAddress;
        this.ordernr = ordernr;
        this.product = product;
        this.productType = productType;
        this.weight = weight;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" + "pickupAddress=" + pickupAddress + ", dropoffAddress=" + dropoffAddress + ", ordernr=" + ordernr + ", date=" + date + ", product=" + product + ", productType=" + productType + ", process=" + process + ", receiptnr=" + receiptnr + ", weight=" + weight + ", volume=" + volume + ", pricePerTon=" + pricePerTon + '}';
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public void setReceiptnr(int receiptnr) {
        this.receiptnr = receiptnr;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setPricePerTon(double pricePerTon) {
        this.pricePerTon = pricePerTon;
    }



    public Address getPickupAddress() {
        return pickupAddress;
    }

    public Address getDropoffAddress() {
        return dropoffAddress;
    }

    public int getOrdernr() {
        return ordernr;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getProduct() {
        return product;
    }

    public ProductType getProductType() {
        return productType;
    }

    public String getProcess() {
        return process;
    }

    public int getReceiptnr() {
        return receiptnr;
    }

    public String getWeight() {
        return weight;
    }

    public double getVolume() {
        return volume;
    }

    public double getPricePerTon() {
        return pricePerTon;
    }
    
    
   
    
}

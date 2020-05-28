/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CreateOrder;

/**
 *
 * @author Denislav Berberov – 3863158 – deniksb
 * {@code 432801@student.fontys.nl}
 */
public enum ProductType {
    
     HAZARDOUS("HAZARDOUS"),
    NONHAZARDOUS("NONHAZARDOUS"),
    FOOD("FOOD");
    
    private String label;

    private ProductType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
    
}

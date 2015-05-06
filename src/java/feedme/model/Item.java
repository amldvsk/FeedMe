/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author NadavBismuth
 */
public class Item {
    
    private int itemID;
    private String itemName;
    private double itemPrice;
    private String itemDescription;
    private String itemImagePath;
    private int quantity;
    protected JSONObject itemObject;

    public Item(String itemName, double itemPrice, String itemDescription, String itemImagePath) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.itemImagePath = itemImagePath;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemImagePath() {
        return itemImagePath;
    }

    public void setItemImagePath(String itemImagePath) {
        this.itemImagePath = itemImagePath;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void increaseQunatity()
    {
        this.quantity++;
    }
    
    public void decreaseQuantity()
    {
        if(this.quantity > 0)
        {
            this.quantity--;
        }
        else
        {
            System.out.println("Fuck Off");
        }
    }
    
    public JSONObject toJson() throws JSONException {
        itemObject = new JSONObject();
        itemObject.put("itemID", getItemID());
        itemObject.put("itemPrice", getItemID());
        itemObject.put("itemDescription", getItemID());
        itemObject.put("itemImagePath", getItemID());
        itemObject.put("quantity", getItemID());
        return itemObject;
    }
    
    
}

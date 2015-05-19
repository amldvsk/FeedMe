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
    private String itemName = null;
    private double itemPrice = 0;
    private String itemDescription = null;
    private String itemImagePath = null;
    private int quantity = 0;
    private int restId;
    protected JSONObject itemObject;

    public Item(String itemName, double itemPrice, String itemDescription, String itemImagePath) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.itemImagePath = itemImagePath;
    }
    public Item(int itemID, String itemName, double itemPrice, int quantity) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
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

    public int getRestId() {
        return restId;
    }

    public void setRestId(int restId) {
        this.restId = restId;
    }
    
    
    
    public JSONObject toJson() throws JSONException {
        itemObject = new JSONObject();
        itemObject.put("itemID", getItemID());
        itemObject.put("itemName", getItemName());
        itemObject.put("itemPrice", getItemPrice());
        itemObject.put("itemDescription", getItemDescription());
        itemObject.put("itemImagePath", getItemImagePath());
        itemObject.put("quantity", getQuantity());
        itemObject.put("rest_id", getRestId());
        return itemObject;
    }
    
    
}

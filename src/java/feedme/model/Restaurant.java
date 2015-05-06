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
public class Restaurant {
    
    private String name;
    private String phone;
    private String logo;
    private String street;
    private String streetNum;
    private String city;
    private int deliveryPrice;
    private int minOrder;
    private String estimatedTimeDel;
    private int dbid;
    protected JSONObject resturentObject;
    

    public Restaurant(String name, String phone, String logo, String street, String streetNum, String city, int deliveryPrice, int minOrder, String estimatedTimeDel) {
        this.name = name;
        this.phone = phone;
        this.logo = logo;
        this.street = street;
        this.streetNum = streetNum;
        this.city = city;
        this.deliveryPrice = deliveryPrice;
        this.minOrder = minOrder;
        this.estimatedTimeDel = estimatedTimeDel;
        
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public int getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(int minOrder) {
        this.minOrder = minOrder;
    }

    public String getEstimatedTimeDel() {
        return estimatedTimeDel;
    }

    public void setEstimatedTimeDel(String estimatedTimeDel) {
        this.estimatedTimeDel = estimatedTimeDel;
    }
    
    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }
    
    
    private JSONObject toJson() throws JSONException {
        resturentObject = new JSONObject();
        resturentObject.put("name", getName());
        resturentObject.put("phone", getPhone());
        resturentObject.put("logo", getLogo());
        resturentObject.put("street", getStreet());
        resturentObject.put("streetNum", getStreetNum());
        resturentObject.put("city", getCity());
        resturentObject.put("deliveryPrice", getDeliveryPrice());
        resturentObject.put("minOrder", getMinOrder());
        resturentObject.put("estimatedTimeDel", getEstimatedTimeDel());
        resturentObject.put("dbid", getDbid());
        return resturentObject;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author NadavBismuth
 */
public class Order {
    
    private int orderId;
    private int orderCustomerId;
    private Timestamp orderDateAndTime;
    private HashMap<HashMapKey , Item > restItemsMap ;
    private String CustomerFullName;
    private String CustomerPhonenum;
    private String CustomerAdress;
    private int status;
    protected JSONObject orederObject;
   

    public Order(int orderCustomerId, String CustomerFullName, String CustomerPhonenum ,String custmerAddress) {
        this.orderCustomerId = orderCustomerId;
        this.CustomerFullName = CustomerFullName;
        this.CustomerPhonenum = CustomerPhonenum;
        this.CustomerAdress = custmerAddress;
        restItemsMap  = new  HashMap<>();
    }
    
    public Order()
    {
        restItemsMap  = new  HashMap<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderCustomerId() {
        return orderCustomerId;
    }

    public void setOrderCustomerId(int orderCustomerId) {
        this.orderCustomerId = orderCustomerId;
    }

    public HashMap<HashMapKey, Item> getRestItemsMap() {
        return restItemsMap;
    }

    public void setRestItemsMap(HashMap<HashMapKey, Item> restItemsMap) {
        this.restItemsMap = restItemsMap;
    }

    public String getCustomerFullName() {
        return CustomerFullName;
    }

    public void setCustomerFullName(String CustomerFullName) {
        this.CustomerFullName = CustomerFullName;
    }

    public String getCustomerPhonenum() {
        return CustomerPhonenum;
    }

    public Timestamp getOrderDateAndTime() {
        return orderDateAndTime;
    }

    public void setOrderDateAndTime(Timestamp orderDateAndTime) {
        this.orderDateAndTime = orderDateAndTime;
    }

    public void setCustomerPhonenum(String CustomerPhonenum) {
        this.CustomerPhonenum = CustomerPhonenum;
    }

    public String getCustomerAdress() {
        return CustomerAdress;
    }

    public void setCustomerAdress(String CustomerAdress) {
        this.CustomerAdress = CustomerAdress;
    }

   
     public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public Double calcSum() {
        Double sum = 0.0;
        for( Item t : getRestItemsMap().values() ) {
            sum += (t.getItemPrice() * t.getQuantity());
        }
        return round(sum, 2);
    }
    
    
    public  double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", orderCustomerId=" + orderCustomerId  + ", CustomerFullName=" + CustomerFullName + ", CustomerPhonenum=" + CustomerPhonenum + ", CustomerAdress=" + CustomerAdress + ", status=" + status +'}';
    }
    
    
    public JSONObject toJson() throws JSONException {
        
        orederObject = new JSONObject();
        orederObject.put("orderId", getOrderId());
        orederObject.put("orderCustomerId", getOrderId());
        orederObject.put("CustomerFullName", getCustomerFullName());
        orederObject.put("CustomerPhonenum", getCustomerPhonenum());
        orederObject.put("CustomerAdress", getCustomerAdress());
        orederObject.put("status", getStatus());
        orederObject.put("order_sum", calcSum());
        orederObject.put("restItemsMap", new JSONArray());
        JSONArray restItemsArray = orederObject.getJSONArray("restItemsMap");
        for( Item item : getRestItemsMap().values() ) {
            restItemsArray.put(item.toJson());
        }
        return orederObject;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author NadavBismuth
 */
public class Order {
    
    private int orderId;
    private int orderCustomerId;
    private HashMap<Map<Integer , Integer> , Item > restItemsMap ;
    private String CustomerFullName;
    private String CustomerPhonenum;
    private String CustomerAdress;

    public Order(int orderCustomerId, String CustomerFullName, String CustomerPhonenum) {
        this.orderCustomerId = orderCustomerId;
        this.CustomerFullName = CustomerFullName;
        this.CustomerPhonenum = CustomerPhonenum;
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

    public HashMap<Map<Integer , Integer>, Item> getRestItemsMap() {
        return restItemsMap;
    }

    public void setRestItemsMap(HashMap<Map<Integer , Integer>, Item> restItemsMap) {
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

    public void setCustomerPhonenum(String CustomerPhonenum) {
        this.CustomerPhonenum = CustomerPhonenum;
    }

    public String getCustomerAdress() {
        return CustomerAdress;
    }

    public void setCustomerAdress(String CustomerAdress) {
        this.CustomerAdress = CustomerAdress;
    }

   
    
    
    
    
}

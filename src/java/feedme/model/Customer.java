/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author NadavBismuth
 */
public class Customer extends User{

    private Date bDay;
    private String street;
    private String houseNum;
    private String apartNum;
    private String city;
    protected JSONObject theUser;
    
    public Customer(String firstName, String lastName, String userName,  String phone, String email, int role
    ,Date bDay , String street , String houseNum, String apartNum ,String city) {
        super(firstName, lastName, userName, phone, email, role);
        this.bDay = bDay;
        this.street = street;
        this.houseNum = houseNum;
        this.apartNum = apartNum;
        this.city = city;
    }

    public Date getbDay() {
        return bDay;
    }

    public void setbDay(Date bDay) {
        this.bDay = bDay;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public String getApartNum() {
        return apartNum;
    }

    public void setApartNum(String apartNum) {
        this.apartNum = apartNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public String getFullAdress()
    {
        String fullAdd;
        fullAdd = this.getApartNum() + "/" + this.getHouseNum() + " , " + this.getStreet() + " , " + this.getCity();
        return fullAdd;
    }
    @Override
    public JSONObject toJson() throws JSONException {
        super.toJson();
        jsonUser.put("date", bDay);
        jsonUser.put("street", street);
        jsonUser.put("houseNum", street);
        jsonUser.put("apartNum", street);
        jsonUser.put("street", street);
        jsonUser.put("city", street);
        return jsonUser;
    }
    
}

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
public class User {
    
    private String firstName;
    private String lastName;
    private String userName;
    private String phone;
    private String email;
    private int role;
    private int dbId;
    protected JSONObject jsonUser;
    
    public User(String firstName, String lastName, String userName, String phone, String email, int role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.role = role;
       
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }
   

    public String getFullName()
    {
        return getFirstName() + " " + getLastName();
    }
    
    
   public JSONObject toJson() throws JSONException {
       jsonUser = new JSONObject();
       jsonUser.put("User", new JSONObject());
       JSONObject theUser = jsonUser.getJSONObject("User");
       theUser.put("firstName", getFirstName());
       theUser.put("lastName", getLastName());
       theUser.put("userName", getUserName());
       theUser.put("phone", getPhone());
       theUser.put("email", getEmail());
       theUser.put("role", getRole());
       theUser.put("role", getDbId());
       return jsonUser;
   }

    
}

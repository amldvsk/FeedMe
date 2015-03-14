/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

/**
 *
 * @author NadavBismuth
 */
public class User {
    
    private String firstName;

    public User(String firstName, String lastName, String userName, String pw, String phone, String email, int role, int dbId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.pw = pw;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.dbId = dbId;
    }
    private String lastName;
    private String userName;
    private String pw;
    private String phone;
    private String email;
    private int role;
    private int dbId;
}

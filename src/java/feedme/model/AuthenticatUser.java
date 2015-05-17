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
public class AuthenticatUser {
    
    private int userId;
    private String userFirstName;
    private String userLastName;
    private byte[] encrypRole;

    public AuthenticatUser(int userId, String userFirstName, String userLastName, byte[] encrypRole) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.encrypRole = encrypRole;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public byte[] getEncrypRole() {
        return encrypRole;
    }

    public void setEncrypRole(byte[] encrypRole) {
        this.encrypRole = encrypRole;
    }
    
    
    
}

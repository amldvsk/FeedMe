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
    private boolean loginResult = false;
    private int managerRestId;

    public boolean isLoginResult() {
        return loginResult;
    }

    public void setLoginResult(boolean loginResult) {
        this.loginResult = loginResult;
    }
  

    public AuthenticatUser(int userId, String userFirstName, String userLastName, byte[] encrypRole, boolean loginResult) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.encrypRole = encrypRole;
        this.loginResult = loginResult;
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

    public int getManagerRestId() {
        return managerRestId;
    }

    public void setManagerRestId(int managerRestId) {
        this.managerRestId = managerRestId;
    }
    
    
    
}

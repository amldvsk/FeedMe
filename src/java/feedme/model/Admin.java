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
public class Admin extends User {

    public Admin(String firstName, String lastName, String userName, String pw, String phone, String email, int role, int dbId) {
        super(firstName, lastName, userName, pw, phone, email, role, dbId);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

/**
 *
 * @author adi
 */
public class HashMapKey {
    private int firstNumber;
    private int secondNumber;

    public HashMapKey(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof HashMapKey)) {
            return false;
        }

        HashMapKey otherKey = (HashMapKey) object;
        return this.firstNumber == otherKey.firstNumber && this.secondNumber == otherKey.secondNumber;
    }

    @Override
    public int hashCode() {
        int result = 17; // any prime number
        result = 31 * result + Integer.valueOf(this.firstNumber).hashCode();
        result = 31 * result + Integer.valueOf(this.secondNumber).hashCode();
        return result;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }
    
    
    
    
}

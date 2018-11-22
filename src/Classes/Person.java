package Classes;

import java.math.BigInteger;

public class Person {
    String name;
    BigInteger ID;
    boolean gender;
    String email;

    public Person(){
    }
    public Person(String name, BigInteger ID, boolean gender, String email) {
        this.name = name;
        this.ID = ID;
        this.gender = gender;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getID() {
        return ID;
    }

    public void setID(BigInteger ID) {
        this.ID = ID;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

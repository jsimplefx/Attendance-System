package Classes;

import java.math.BigInteger;

public class Student {
    private String name; // do i even need to comment this.
    private BigInteger ID; // student ID
    private boolean present; // today's attendance state
    private int past; // number of times student was absent
    private String Excuse; // excuse for last time student was absent

    // a constructor with default excuse message
    public Student(String name, BigInteger ID, boolean present, int past) {
        this.name = name;
        this.ID = ID;
        this.present = present;
        this.past = past;
        this.Excuse = "Not Provided";
    }

    // a constructor with last excuse
    public Student(String name, BigInteger ID, boolean present, int past, String excuse) {
        this.name = name;
        this.ID = ID;
        this.present = present;
        this.past = past;
        Excuse = excuse;
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

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public int getPast() {
        return past;
    }

    public void setPast(int past) {
        this.past = past;
    }

    public String getExcuse() {
        return Excuse;
    }

    public void setExcuse(String excuse) {
        this.Excuse = excuse;
    }
}

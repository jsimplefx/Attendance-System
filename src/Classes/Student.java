package Classes;

public class Student extends Person{
    private boolean present; // today's attendance state
    private int past; // number of times student was absent
    private String Excuse; // excuse for last time student was absent

    // a constructor with default excuse message
    public Student(String name, int ID, boolean present, int past) {
        this.name = name;
        this.ID = ID;
        this.present = present;
        this.past = past;
        this.Excuse = "Not Provided";
    }

    // a constructor with last excuse
    public Student(String name, int ID, boolean present, int past, String excuse) {
        this.name = name;
        this.ID = ID;
        this.present = present;
        this.past = past;
        Excuse = excuse;
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

package Classes;

public class Student extends Person{
    private boolean present; // today's attendance state
    private int past; // number of times student was absent
    // a constructor with default excuse message
    public Student(int ID, String name, String gender, String mail, int past) {
        this.name = name;
        this.ID = ID;
        this.gender = gender;
        this.email = mail;
        this.past = past;
    }

    // a constructor with last excuse
    public Student(String name, int ID, boolean present, int past) {
        this.name = name;
        this.ID = ID;
        this.present = present;
        this.past = past;
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

}

package Classes;

public class Student extends Person{
    private String absences; // number of times student was absent
    private String excuse;
    private String present;
    private String bar_status;
    // a constructor for the students list page
    public Student(int ID, String name, String gender, String mail, String absences, String bar_status) {
        this.name = name;
        this.ID = ID;
        this.gender = gender;
        this.email = mail;
        this.absences = absences;
        this.bar_status = bar_status;
    }

    // a constructor with last excuse
    public Student( int ID, String name, String excuse, String present) {
        this.name = name;
        this.ID = ID;
        this.present = present;
        this.excuse = excuse;
    }

    public String getAbsences() {
        return absences;
    }

    public void setAbsences(String absences) {
        this.absences = absences;
    }

    public String getExcuse() {
        return excuse;
    }

    public void setExcuse(String excuse) {
        this.excuse = excuse;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public String getBar_status() {
        return bar_status;
    }

    public void setBar_status(String bar_status) {
        this.bar_status = bar_status;
    }
}

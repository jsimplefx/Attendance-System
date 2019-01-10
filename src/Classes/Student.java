package Classes;

public class Student extends Person{
    private String absences; // number of times student was absent
    private String excuse;
    private String present;
    private String bar_status;
    private String subjects;
    // a constructor for the students list page
    public Student(int ID, String name, String gender, String mail, String absences, String bar_status, String subjects) {
        this.name = name;
        this.ID = ID;
        this.gender = gender;
        this.email = mail;
        this.absences = absences;
        this.bar_status = bar_status;
        this.subjects = subjects;
    }

    // a constructor with last excuse
    public Student( int ID, String name, String excuse, String present, String subjects) {
        this.name = name;
        this.ID = ID;
        this.present = present;
        this.excuse = excuse;
        this.subjects = subjects;
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

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }
}

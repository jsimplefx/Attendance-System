package Classes;

public class Student extends Person{
    private String absences; // number of times student was absent
    private String excuse;
    private String present;
    // a constructor for the students list page
    public Student(int ID, String name, String gender, String mail, String absences) {
        this.name = name;
        this.ID = ID;
        this.gender = gender;
        this.email = mail;
        this.absences = absences;
    }



    // a constructor with last excuse
    public Student( int ID, String name, String excuse, String present) {
        this.name = name;
        this.ID = ID;
        this.present = present;
        this.excuse = excuse;
    }

}

package Classes;

public class Teacher extends Person{
    private String Pass; // login password
    private String XP; // experience (dunno why i called it XP)
    private String subjects;

    public Teacher() {
    }

    public Teacher(String name, String pass, int ID, String gender, String email, String subjects, String XP, long phone) {
        this.name = name;
        Pass = pass;
        this.ID = ID;
        this.gender = gender;
        this.email = email;
        this.XP = XP;
        this.subjects = subjects;
        this.phone = phone;
    }

    public String getXP() {
        return XP;
    }

    public String getSubjects() {
        return subjects;
    }
}

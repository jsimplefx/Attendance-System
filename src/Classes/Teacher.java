package Classes;

import javafx.scene.image.Image;

public class Teacher extends Person{
    private String Pass; // login password
    private String XP; // experience (dunno why i called it XP)
    private Image portrait;
    private String[] subjects;

    public Teacher() {
    }

    public Teacher(String name, String pass, int ID, boolean gender, String email, String[] subjects, String XP) {
        this.name = name;
        Pass = pass;
        this.ID = ID;
        this.gender = gender;
        this.email = email;
        this.XP = XP;
        this.subjects = subjects;
        this.portrait = new Image("resources/default.png");
    }

    public Teacher(String name, String pass, int ID, boolean gender, String email, String XP, String[] subjects,  Image portrait) {
        this.name = name;
        Pass = pass;
        this.ID = ID;
        this.gender = gender;
        this.email = email;
        this.XP = XP;
        this.portrait = portrait;
    }



    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getXP() {
        return XP;
    }

    public void setXP(String XP) {
        this.XP = XP;
    }

    public Image getPortrait() {
        return portrait;
    }

    public void setPortrait(Image portrait) {
        this.portrait = portrait;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }
}

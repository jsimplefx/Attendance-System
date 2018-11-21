package Classes;

import javafx.scene.image.Image;

public class Teacher {
    private String name; //
    private String Pass; // login password
    private int ID; // login and genera ID
    private boolean gender; // 0 is male and 1 is female
    private String email;
    private String XP; // experience (dunno why i called it XP)
    private Image portrait;

    public Teacher() {
    }

    public Teacher(String name, String pass, int ID, boolean gender, String email, String XP) {
        this.name = name;
        Pass = pass;
        this.ID = ID;
        this.gender = gender;
        this.email = email;
        this.XP = XP;
        this.portrait = new Image("resources/default.png");
    }

    public Teacher(String name, String pass, int ID, boolean gender, String email, String XP, Image portrait) {
        this.name = name;
        Pass = pass;
        this.ID = ID;
        this.gender = gender;
        this.email = email;
        this.XP = XP;
        this.portrait = portrait;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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
}

package models;

public class User {
    private static int id = 3;
    private String name;
    private String surname;
    private boolean gender;

    public User (String name, String surname, boolean gender){
        id += 1;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return id + ": " + name + " " + surname + " - " + (gender ? "male" : "female");
    }
}

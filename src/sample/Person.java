package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Person {
    private String name;
    private ContactInfo myContact;
    private String position;
    private Image picture;
    private String salary;
    private ArrayList<String> notes;

    public Person(String name, ContactInfo myContact, String position, String salary, Image picture){
        this.name = name;
        this. myContact = myContact;
        this.position = position;
        this.salary = salary;
        try{
            this.picture=picture;
        }
        catch(Exception e) {
            System.out.println(e);
            this.picture = new Image("/flower");

        }
        notes= new ArrayList<>();
    }

    public Person(String name, ContactInfo myContact, String position, String salary){
        this.name = name;
        this.myContact = myContact;
        this.position = position;
        this.salary = salary;
        notes= new ArrayList<>();

    }

    public Person(){
        notes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactInfo getMyContact() {
        return myContact;
    }

    public void setMyContact(ContactInfo myContact) {
        this.myContact = myContact;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Image getPicture() {
        return picture;
    }

    public void setPicture(Image picture) {
        this.picture = picture;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }


    public void addNote(String note){
        notes.add(note);
    }

//    public String toString(){
//        System.out.println("Name: "+ name);
//        System.out.println("Contact Information: " + myContact.toString());
//        System.out.println("Position: " + position);
//        System.out.println("Salary");
//    }

}

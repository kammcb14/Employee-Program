package sample;

public class ContactInfo {
    private String number;
    private String email;
    private String address;

    //Constructor
    public ContactInfo(String phoneNumber, String email, String street, String city, String state, String zip){
        this.email = email;
        number = phoneNumber;
        this.address = street + " " + city + " " + state + " " + zip;
    }

    //Get and Set methods
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




}

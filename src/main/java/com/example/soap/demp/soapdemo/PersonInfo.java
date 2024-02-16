package com.example.soap.demp.soapdemo;



import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "personInfo")
public class PersonInfo {

    private String firstName;
    private String lastName;
    // Add more fields as needed

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}


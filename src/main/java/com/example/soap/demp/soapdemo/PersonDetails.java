package com.example.soap.demp.soapdemo;



import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "personDetails")
public class PersonDetails {

    private String name;
    // Add more fields as needed

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


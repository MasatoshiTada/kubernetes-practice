package com.example.ui.web.form;

import com.example.ui.persistence.entity.Customer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CustomerForm {

    private String firstName;

    private String lastName;

    private String email;

    @DateTimeFormat(pattern = "uuuu-MM-dd")
    private LocalDate birthday;

    public CustomerForm() {}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Customer convertToEntity() {
        return new Customer(firstName, lastName, email, birthday);
    }
}

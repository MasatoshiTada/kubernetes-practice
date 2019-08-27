package com.example.api.web.request;

import com.example.api.persistence.entity.Customer;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CustomerRequest {

    @NotBlank
    @Length(min = 1, max = 32)
    private String firstName;

    @NotBlank
    @Length(min = 1, max = 32)
    private String lastName;

    @NotBlank
    @Length(min = 1, max = 128)
    private String email;

    @NotNull
    @DateTimeFormat(pattern = "uuuu-MM-dd")
    private LocalDate birthday;

    public CustomerRequest() {}

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

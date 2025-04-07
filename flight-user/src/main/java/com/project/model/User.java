package com.project.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true, length = 10)
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate dob;
    private String role = "USER"; //by default


    public User(Long id, String mobileNumber, String firstName, String lastName, String password, String email, Gender gender, LocalDate dob, String role) {
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.role = role;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

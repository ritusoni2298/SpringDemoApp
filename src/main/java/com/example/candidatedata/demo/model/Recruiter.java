package com.example.candidatedata.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

@Entity
@Table(name="recruiters")
public class Recruiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String firstName;

    @NotNull
    @NotEmpty
    @NotBlank
    private String LastName;

    @Column(unique = true)
    private String email;

    public Recruiter(){

    }

    public Recruiter(@NotNull @NotEmpty @NotBlank String firstName, @NotNull @NotEmpty @NotBlank String lastName, String email) {
        this.firstName = firstName;
        LastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidate")
//    Set<Candidate> candidates=new HashSet<>(); ==> if we want candidates data when recruiter is fetched

}

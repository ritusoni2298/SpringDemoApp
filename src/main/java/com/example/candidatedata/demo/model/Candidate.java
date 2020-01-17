package com.example.candidatedata.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="candidates",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
            @UniqueConstraint(columnNames = {"candidate_id"})
    })
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value={"createdAt","updatedAt"},
        allowGetters = true
)

public class Candidate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @NotEmpty
    private String firstName;

    @NotBlank
    @NotNull
    @NotEmpty
    private String lastName;

    @NotBlank
    @NotNull
    @NotEmpty
    private String email;

    private String reference;

    private String phoneno;

    @Column(nullable = false,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="candidate_id",nullable = false)
    @JsonIgnore
    private Recruiter recruiter;

    public Candidate(@NotBlank @NotEmpty String firstName, @NotBlank @NotEmpty String lastName, @NotBlank @NotEmpty String email, String reference, String phoneno, Date createAt, Date updatedAt, Recruiter recruiter) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.reference = reference;
        this.phoneno = phoneno;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.recruiter = recruiter;
    }

    public Candidate(){

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }
}

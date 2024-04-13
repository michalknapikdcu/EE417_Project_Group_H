package app.sensors;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

/*
 * @author Conor McCarthy
 */

// Entity class replicates values stored in contact table in DB
// 
@Entity
// Contact table name in DB
@Table(name = "contact")
public class ContactEntity {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Column for each row in table
    @Column(name = "firstname") // Exact name of row
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "contactnumber")
    private String contactNumber;

    @Column(name = "country")
    private String country;

    @Column(name = "subject")
    private String subject;

    // Empty Constructors
    public ContactEntity() {
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

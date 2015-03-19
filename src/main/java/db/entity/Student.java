package db.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by boro on 18.03.15.
 */
@Entity
@Table(name = "Students")
public class Student implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    public Student(int id, String lastname, String firstname) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public Student(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }
    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }
}

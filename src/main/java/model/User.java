package model;

/**
 * Created by boro on 23.03.15.
 */
public class User implements Model {
    private int id;
    private String firstname;
    private String lastname;
    private int age;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public User() {

    }

    public User(String lastname, String firstname, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }
}

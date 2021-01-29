package entity;

import java.io.Serializable;

public class User implements Entity, Serializable {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isBanned;
    private Role role;
    private long phoneNumber;
    private String email;
    private int id;
    private static int currentID;

    public User(String login, String password, String firstName, String lastName, String email, Role role) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.id = ++currentID;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return getFirstName() + " " + getLastName();
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCurrentID() {
        return currentID;
    }

    public static void setCurrentID(int currentID) {
        User.currentID = currentID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isBanned=" + isBanned +
                ", role=" + role +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}

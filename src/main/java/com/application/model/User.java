package com.application.model;

import java.time.LocalDate;
import java.util.List;

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String telegramTag;
    private String faculty;
    private String group;
    private String email;
    private String phoneNumber;
    private LocalDate birthday;
    private LocalDate admissionDay;
    private UserRole userRole;
    private String password;
    private String login;
    private List<TaskUser> userTasks;
    public long getId() {
        return id;
    }
    public void setId(long id) { this.id = id; }
    public String getFirstName() {
        return firstName;
    }
    public void setFirst_name(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLast_name(String lastName) {
        this.lastName = lastName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getTelegramTag() {
        return telegramTag;
    }
    public void setTelegramTag(String telegramTag) {
        this.telegramTag = telegramTag;
    }
    public String getFaculty() {
        return faculty;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public LocalDate getAdmissionDay() {
        return admissionDay;
    }
    public void setAdmissionDay(LocalDate admissionDay) {
        this.admissionDay = admissionDay;
    }
    public UserRole getRole()
    {
        return userRole;
    }
    public void setRole(UserRole userRole)
    {
        this.userRole = userRole;
    }
    public String getPassword() {
        return password;
    }
    public void  setPassword(String password)
    {
        this.password = password;
    }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public List<TaskUser> getUserTasks() { return userTasks; }
    public void setUserTasks(List<TaskUser> userTasks) { this.userTasks = userTasks;}
    public User(long id, String login, String password, String firstName, String lastName, String middleName, String telegramTag, String faculty, String group, String email, String phoneNumber, LocalDate birthday, LocalDate admissionDay, UserRole userRole) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.telegramTag = telegramTag;
        this.faculty = faculty;
        this.group = group;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.admissionDay = admissionDay;
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", telegramTag='" + telegramTag + '\'' +
                ", faculty='" + faculty + '\'' +
                ", group='" + group + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthday=" + birthday +
                ", admissionDay=" + admissionDay +
                ", userRole=" + userRole +
                '}';
    }
}

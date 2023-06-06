package com.application.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "telegram_tag")
    private String telegramTag;
    private String faculty;
    @Column(name = "`group`")
    private String group;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "day_of_birth")
    private Date birthday;
    @Column(name = "day_of_admission")
    private Date admissionDay;
    @ManyToOne
    @JoinColumn(name="user_role_id", nullable=false)
    private UserRole userRole;
    private String password;
    private String login;
    @OneToMany(mappedBy="user")
    private List<TaskUser> userTasks;
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
    public String getFullName()
    {
        return firstName + " " + middleName + " " + lastName;
    }
}

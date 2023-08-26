package io.nilmani.management.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user_detail")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name="custom-id",strategy = "io.nilmani.management.customId.CustomIdGenerator")
    private String id;
    private  String name;
    @Column(unique = true)
    private  String email;
    private  String password;
    private  String gender;
    private  long mobileNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }
}

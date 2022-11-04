package com.example.qlsv.model;

import java.io.Serializable;

public class Student implements Serializable {

    private String masv;
    private String name;
    private String lop;
    private String email;
    private String phone;
    private String password;

    public Student() {
        this.masv = masv;
        this.name = name;
        this.lop = lop;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

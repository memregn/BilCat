package com.beyza.bilcat;

public class User {
    private String nametxt;
    private String phonetxt;
    private String emailtxt;

    public User(){

    }
    public User(String fullname,String phone, String email){
        this.nametxt =fullname;
        this.emailtxt = email;
        this.phonetxt = phone;
    }

    public String getEmailtxt() {
        return emailtxt;
    }

    public String getNametxt() {
        return nametxt;
    }

    public String getPhonetxt() {
        return phonetxt;
    }
}

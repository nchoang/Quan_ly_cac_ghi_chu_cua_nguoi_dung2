package com.example.model;

public class usermodel {
    private String iduser;
    private String pass;

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "usermodel{" +
                "iduser='" + iduser + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}

package com.jinwoo.ably.src.signup.models;

import com.google.gson.annotations.SerializedName;

public class SignUpBody {

    @SerializedName("userType")
    private String userType;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private  String password;

    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phone;

    @SerializedName("dateOfBirth")
    private String dateOfBirth;

    @SerializedName("AgreeOnService")
    private String AgreeOnService;

    @SerializedName("AgreeOnPrivate")
    private String AgreeOnPrivate;

    public SignUpBody(String userType, String email, String password, String name, String phone, String dateOfBirth, String AgreeOnService, String AgreeOnPrivate) {
        this.userType = userType;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.AgreeOnService = AgreeOnService;
        this.AgreeOnPrivate = AgreeOnPrivate;
    }
}

package com.example.societies.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private long id;
    private String username;
    private String name;
    private String password;
    private int age;
    private String gender;
    private String address;
    private String phone;
}

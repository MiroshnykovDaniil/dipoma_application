package com.diploma.application.user;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String name;
    private String password;

}

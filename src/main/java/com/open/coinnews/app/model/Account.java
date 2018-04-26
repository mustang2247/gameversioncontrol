package com.open.coinnews.app.model;

import javax.persistence.*;

/**
 * 用户
 */
@Entity
@Table(name = "t_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    private String password;

    @Column(name = "real_name")
    private String realName;
}

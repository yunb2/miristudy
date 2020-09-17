package me.yunb.miristudy.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String name;

    @Column
    private String password;

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}

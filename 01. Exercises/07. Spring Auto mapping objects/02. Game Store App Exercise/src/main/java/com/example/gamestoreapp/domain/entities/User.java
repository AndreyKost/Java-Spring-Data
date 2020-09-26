package com.example.gamestoreapp.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
    private String email;
    private String fullName;
    private String password;
    private Role role;

    @ManyToMany(cascade= CascadeType.ALL)
    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    private List<Game> games;

    public User() {
    }

    @Column(name = "email",unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "fullName")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

package com.cashRegister.model;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private Role roleName;

    public User(String login, String password, String name, String surname, Role roleName) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.roleName = roleName;
    }

    public User(int id, String login, String password, String name, String surname, Role roleName) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Role getRoleName() {
        return roleName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setRoleName(Role roleName) {
        this.roleName = roleName;
    }
}

package com.cashRegister.model;

import java.util.Objects;

/**
 * This class simulates a user
 */
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

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && login.equals(user.login) && password.equals(user.password) && name.equals(user.name) && surname.equals(user.surname) && roleName.equals(user.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name, surname, roleName);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", roleName=" + roleName +
                '}';
    }
}

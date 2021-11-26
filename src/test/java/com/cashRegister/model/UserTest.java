package com.cashRegister.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User userWithOutId;
    private User userFull;
    private final Role firstRole = new Role("Tester");

    @Before
    public void BeforeTest() {
        userFull = new User(4, "root", "tt333s!", "Ivan", "Petrov", firstRole);
        userWithOutId = new User("task", "htty67ui", "Petr", "Ivanov", new Role("Gamer"));
    }

    @Test
    public void getIdUserFull() {
        assertEquals(userFull.getId(), 4);
    }

    @Test
    public void getIdUserWithOutId() {
        assertEquals(userWithOutId.getId(), 0);
    }

    @Test
    public void getLoginUserFull() {
        assertEquals(userFull.getLogin(), "root");
    }

    @Test
    public void getPasswordUserFull() {
        assertEquals(userFull.getPassword(), "tt333s!");
    }

    @Test
    public void getNameUserFull() {
        assertEquals(userFull.getName(), "Ivan");
    }

    @Test
    public void getSurnameUserFull() {
        assertEquals(userFull.getSurname(), "Petrov");
    }

    @Test
    public void getRoleUserFull() {
        assertEquals(userFull.getRoleName(), firstRole);
    }

    @Test
    public void setIdUserFull() {
        int newId = 78;
        userFull.setId(newId);
        assertEquals(userFull.getId(), newId);
    }

    @Test
    public void setLoginUserFull() {
        String newLogin = "tax";
        userFull.setLogin(newLogin);
        assertEquals(userFull.getLogin(), newLogin);
    }

    @Test
    public void setPasswordUserFull() {
        String newPassword = "pass123";
        userFull.setPassword(newPassword);
        assertEquals(userFull.getPassword(), newPassword);
    }

    @Test
    public void setNameUserFull() {
        String newName = "Fara";
        userFull.setName(newName);
        assertEquals(userFull.getName(), newName);
    }

    @Test
    public void setSurnameUserFull() {
        String newSurname = "Trump";
        userFull.setSurname(newSurname);
        assertEquals(userFull.getSurname(), newSurname);
    }

    @Test
    public void setRoleUserFull() {
        Role newRole = new Role("Developer");
        userFull.setRoleName(newRole);
        assertEquals(userFull.getRoleName(), newRole);
    }

    @Test
    public void equalCheckFull() {
        assertEquals(userFull, new User(4, "root", "tt333s!", "Ivan", "Petrov", firstRole));
    }

    @Test
    public void falseEqualseCheckFull() {
        assertNotEquals(userFull, new User(5, "root", "tt333s!", "Ivan", "Petrov", firstRole));
        assertNotEquals(userFull, new User(4, "r", "tt333s!", "Ivan", "Petrov", firstRole));
        assertNotEquals(userFull, new User(4, "root", null, "Ivan", "Petrov", firstRole));
        assertNotEquals(userFull, new User(4, "root", "tt333s!", "U123", "Petrov", firstRole));
        assertNotEquals(userFull, new User(4, "root", "tt333s!", "Ivan", null, firstRole));
        assertNotEquals(userFull, new User(4, "root", "tt333s!", "Ivan", "Petrov", null));
    }

    @Test
    public void hashCheckFull() {
        User newUser = new User(4, "root", "tt333s!", "Ivan", "Petrov", firstRole);
        assertEquals(userFull.hashCode(), newUser.hashCode());
    }

    @Test
    public void falseHashCheckFull() {
        User newUser = new User(5, "root", "tt333s!", "Ivan", "Petrov", firstRole);
        assertNotEquals(userFull.hashCode(), newUser.hashCode());
    }

    @Test
    public void toStringCheckFull() {
        assertEquals(userFull.toString(), "User{id=4, login='root', password='tt333s!', name='Ivan', surname='Petrov', roleName=Role{id=0, name='Tester'}}");
    }
}

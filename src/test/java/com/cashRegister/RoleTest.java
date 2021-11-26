package com.cashRegister;

import com.cashRegister.model.Role;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoleTest {

    private Role roleWithId;
    private Role roleOnlyWithName;

    @Before
    public void beforeTest() {
        roleOnlyWithName = new Role("Tester");
        roleWithId = new Role(5, "Gamer");
    }

    @Test
    public void getIdRoleWithId() {
        final int res = 5;
        assertEquals(roleWithId.getId(), res);
    }

    @Test
    public void getIdRoleOnlyWithName() {
        assertEquals(roleOnlyWithName.getId(), 0);
    }

    @Test
    public void getNameRoleOnlyWithName() {
        assertEquals(roleOnlyWithName.getName(), "Tester");
    }

    @Test
    public void getNameRoleWithId() {
        assertEquals(roleWithId.getName(), "Gamer");
    }

    @Test
    public void setNameRoleOnlyWithName() {
        String nameResult = "Developer";
        roleOnlyWithName.setName(nameResult);
        assertEquals(roleOnlyWithName.getName(), nameResult);
    }

    @Test
    public void setNameRoleWithId() {
        String nameResult = "Current";
        roleWithId.setName(nameResult);
        assertEquals(roleWithId.getName(), nameResult);
    }

    @Test
    public void equalsRoleWithId() {
        assertEquals(roleWithId, new Role(5, "Gamer"));
    }

    @Test
    public void equalsRoleOnlyWithName() {
        assertEquals(roleOnlyWithName, new Role("Tester"));
    }

    @Test
    public void notEqualsRoleWithId() {
        assertNotEquals(roleWithId, new Role(6, "Parent"));
        assertNotEquals(roleWithId, new Role("Gamer"));
        assertNotEquals(roleWithId, new Role(0,"Gamer"));
        assertNotEquals(roleWithId, new Role(null));
        assertNotEquals(roleWithId, new Role(""));
        assertNotEquals(roleWithId, new Role(5, null));
    }

    @Test
    public void notEqualsRoleOnlyWithName() {
        assertNotEquals(roleOnlyWithName, new Role(6, "Tester"));
        assertNotEquals(roleOnlyWithName, new Role("Parent"));
        assertNotEquals(roleWithId, new Role(0,"Tester"));
        assertNotEquals(roleWithId, new Role(null));
        assertNotEquals(roleWithId, new Role(""));
    }

    @Test
    public void hashRoleWithId () {
        Role testRole = new Role(5, "Gamer");
        assertEquals(testRole.hashCode() , roleWithId.hashCode());
    }

    @Test
    public void hashRoleOnlyWithName () {
        Role testRole = new Role("Tester");
        assertEquals(testRole.hashCode() , roleOnlyWithName.hashCode());
    }

    @Test
    public void falseHashRoleWithId () {
        Role testRole = new Role(6, "Gamer");
        assertFalse(testRole.hashCode() == roleWithId.hashCode());
    }

    @Test
    public void falseHashRoleOnlyWithName () {
        Role testRole = new Role("tester");
        assertFalse(testRole.hashCode() == roleOnlyWithName.hashCode());
    }

    @Test
    public void toStringRoleWithId() {
        assertEquals(roleWithId.toString(), "Role{id=5, name='Gamer'}");
    }

    @Test
    public void toStringRoleOnlyWithName() {
        assertEquals(roleOnlyWithName.toString(), "Role{id=0, name='Tester'}");
    }

    @Test
    public void falseToString() {
        assertNotEquals(roleWithId.toString(), "Role{id=7, name='Gamer'}");
    }
}

package com.cashRegister.repository;

import com.cashRegister.exception.RoleNotFoundException;
import com.cashRegister.model.Role;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RoleRepositoryTest {

    RoleRepository roleRepository;

    @Before
    public void beforeStart() throws SQLException {
        roleRepository = mock(RoleRepository.class);
    }

    @Test
    public void getAllRoles() {
        when(roleRepository.getAllRoles()).thenCallRealMethod();
        List<Role> roles = roleRepository.getAllRoles();
        assertTrue(roles.size() > 0);
    }

    @Test
    public void getRoleByName() throws RoleNotFoundException {
        String roleName = "Casher";
        when(roleRepository.getAllRoles()).thenCallRealMethod();
        when(roleRepository.getRoleByName(roleName)).thenCallRealMethod();
        Role currentRole = roleRepository.getRoleByName(roleName);
        assertEquals(currentRole.getName(), roleName);
    }

    @Test(expected = RoleNotFoundException.class)
    public void falseGetRoleByName() throws RoleNotFoundException {
        String roleName = "Test";
        when(roleRepository.getAllRoles()).thenCallRealMethod();
        when(roleRepository.getRoleByName(roleName)).thenCallRealMethod();
        Role currentRole = roleRepository.getRoleByName(roleName);
    }
}

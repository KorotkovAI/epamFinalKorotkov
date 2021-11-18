package com.cashRegister.repository;

import com.cashRegister.DbManager;
import com.cashRegister.exception.RoleNotFoundException;
import com.cashRegister.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    private static final String SELECT_ALL_ROLES = "SELECT * FROM roles;";

    private static RoleRepository roleRepository = null;

    public synchronized static RoleRepository getRoleRepository() {
        if (roleRepository == null) {
            roleRepository = new RoleRepository();
        }
        return roleRepository;
    }

    public Role getRoleByName(String name) throws RoleNotFoundException {
        List<Role> roles = getAllRoles();
        for (Role role : roles) {
            if (role.getName().equals(name)) {
                return role;
            }
        }
        throw new RoleNotFoundException("role with NAME " + name + " not found");
    }


    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        try {
            Connection connection = DbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROLES);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int roleId = rs.getInt("id");
                String roleName = rs.getString("name");
                roles.add(new Role(roleId, roleName));
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        return roles;
    }
}

package com.cashRegister.repository;

import com.cashRegister.model.Role;
import com.cashRegister.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    private static final String SELECT_ALL_ROLES = "SELECT * FROM roles;";

    public Role getRoleByName (Connection con, String name) throws Exception {
        List<Role> roles = getAllRoles(con);
        //roles.stream().forEach(System.out::println);
        for (Role role: roles) {
            if (role.getName().equals(name)) {
                return role;
            }
        }
        throw new Exception("role is null");
    }


    public List<Role> getAllRoles(Connection con) {
        List <Role> roles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_ROLES);
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

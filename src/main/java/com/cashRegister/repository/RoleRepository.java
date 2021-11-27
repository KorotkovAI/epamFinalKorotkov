package com.cashRegister.repository;

import com.cashRegister.DbManager;
import com.cashRegister.exception.RoleNotFoundException;
import com.cashRegister.model.Role;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    public static final String SELECT_ALL_ROLES = "SELECT * FROM roles;";

    private static RoleRepository roleRepository = null;

    private static final Logger log = LogManager.getLogger(RoleRepository.class);

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
        log.log(Level.ERROR, "role with NAME " + name + " not found");
        throw new RoleNotFoundException("role with NAME " + name + " not found");
    }


    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DbManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_ROLES);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int roleId = rs.getInt("id");
                String roleName = rs.getString("name");
                roles.add(new Role(roleId, roleName));
            }
        } catch (SQLException e2) {
            log.log(Level.ERROR, e2);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.log(Level.ERROR, e);
            }
        }
        return roles;
    }
}

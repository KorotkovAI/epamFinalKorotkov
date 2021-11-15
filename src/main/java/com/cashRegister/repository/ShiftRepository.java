package com.cashRegister.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShiftRepository {

    private static final String CLOSE_SHIFT = "SELECT id FROM shifts WHERE isOpen = 1;";

    private static ShiftRepository shiftRepository;

    public synchronized static ShiftRepository getShiftRepository() {
        if (shiftRepository == null) {
            shiftRepository = new ShiftRepository();
        }
        return shiftRepository;
    }


    public int firstOpenShift() {
        int idFirstOpenShift = 0;
        try (Connection connection = DbManager.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(CLOSE_SHIFT);
            ResultSet rs = preparedStatement.executeQuery();
            idFirstOpenShift = rs.getInt("id");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return idFirstOpenShift;
    }
    ////////////////////////////
    public boolean closeShift(int idShift) {
        return true;
    }
}

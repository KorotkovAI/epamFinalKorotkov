package com.cashRegister.repository;

import com.cashRegister.DbManager;
import com.cashRegister.exception.ShiftNotFoundException;
import com.cashRegister.model.Shift;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShiftRepository {

    private static final String ALL_OPEN_SHIFTS = "SELECT * FROM shifts WHERE isOpen = 1;";
    private static final String CLOSE_SHIFT = "UPDATE shifts SET isOpen = 0, closeTime = ? WHERE id = ?;";
    private static final String ALL_SHIFTS = "SELECT * FROM shifts;";

    private static ShiftRepository shiftRepository;

    public synchronized static ShiftRepository getShiftRepository() {
        if (shiftRepository == null) {
            shiftRepository = new ShiftRepository();
        }
        return shiftRepository;
    }


    public Shift firstOpenShift() {
        Shift currentShift = null;
        try {
            Connection connection = DbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ALL_OPEN_SHIFTS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idOpenShift = rs.getInt("id");
                boolean isOpenShft = (rs.getInt("isOpen") == 1) ? true : false;
                Timestamp openTime = rs.getTimestamp("openTime");
                Timestamp closeTime = rs.getTimestamp("closeTime");
                currentShift = new Shift(idOpenShift, isOpenShft, openTime, closeTime);
                break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return currentShift;
    }

    public Shift getShiftById(int idShift) throws ShiftNotFoundException {
        List<Shift> shiftList = getAllShifts();
        for (Shift shift : shiftList) {
            if (shift.getId() == idShift) {
                return shift;
            }
        }
        throw new ShiftNotFoundException("shift with ID " + idShift + " not found");
    }

    public List<Shift> getAllShifts() {
        List<Shift> shifts = new ArrayList<>();
        Shift currentShift = null;

        try {
            Connection connection = DbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ALL_SHIFTS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idOpenShift = rs.getInt("id");
                boolean isOpenShft = (rs.getInt("isOpen") == 1) ? true : false;
                Timestamp openTime = rs.getTimestamp("openTime");
                Timestamp closeTime = rs.getTimestamp("closeTime");
                currentShift = new Shift(idOpenShift, isOpenShft, openTime, closeTime);
                shifts.add(currentShift);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shifts;
    }

    public boolean closeShift(int idShift) throws IllegalAccessException, ShiftNotFoundException {
        if (idShift > 0) {
            Shift currentShift = getShiftById(idShift);

            if (!currentShift.isOpen()) {
                return false;
            }
            try {
                Connection connection = DbManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(CLOSE_SHIFT);
                preparedStatement.setString(1, String.valueOf(new Timestamp(System.currentTimeMillis())));
                preparedStatement.setInt(2, currentShift.getId());
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalAccessException("wrong idShift");
    }
}

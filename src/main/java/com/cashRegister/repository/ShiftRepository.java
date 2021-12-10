package com.cashRegister.repository;

import com.cashRegister.DbManager;
import com.cashRegister.exception.ShiftNotFoundException;
import com.cashRegister.model.Shift;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class works with shifts
 */
public class ShiftRepository {

    private static final String ALL_OPEN_SHIFTS = "SELECT * FROM shifts WHERE isOpen = 1;";
    private static final String CLOSE_SHIFT = "UPDATE shifts SET isOpen = 0, closeTime = ? WHERE id = ?;";
    private static final String ALL_SHIFTS = "SELECT * FROM shifts;";
    private static final String OPEN_SHIFT = "INSERT INTO shifts SET isOpen = 1, openTime = ?;";

    private static ShiftRepository shiftRepository;

    private static final Logger log = LogManager.getLogger(ShiftRepository.class);

    public synchronized static ShiftRepository getShiftRepository() {
        if (shiftRepository == null) {
            shiftRepository = new ShiftRepository();
        }
        return shiftRepository;
    }


    public Shift firstOpenShift() {
        Shift currentShift = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DbManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(ALL_OPEN_SHIFTS);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idOpenShift = rs.getInt("id");
                boolean isOpenShft = (rs.getInt("isOpen") == 1) ? true : false;
                Timestamp openTime = rs.getTimestamp("openTime");
                Timestamp closeTime = rs.getTimestamp("closeTime");
                currentShift = new Shift(idOpenShift, isOpenShft, openTime, closeTime);
                break;
            }
        } catch (SQLException throwables) {
            log.log(Level.ERROR, throwables);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.log(Level.ERROR, e);
            }
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
        log.log(Level.ERROR, "shift with ID " + idShift + " not found");
        throw new ShiftNotFoundException("shift with ID " + idShift + " not found");
    }

    public List<Shift> getAllShifts() {
        List<Shift> shifts = new ArrayList<>();
        Shift currentShift = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DbManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(ALL_SHIFTS);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idOpenShift = rs.getInt("id");
                boolean isOpenShft = (rs.getInt("isOpen") == 1) ? true : false;
                Timestamp openTime = rs.getTimestamp("openTime");
                Timestamp closeTime = rs.getTimestamp("closeTime");
                currentShift = new Shift(idOpenShift, isOpenShft, openTime, closeTime);
                shifts.add(currentShift);
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.log(Level.ERROR, e);
            }
        }
        return shifts;
    }

    public boolean closeShift(int idShift) throws IllegalAccessException, ShiftNotFoundException {
        if (idShift > 0) {
            Shift currentShift = getShiftById(idShift);
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            if (!currentShift.isOpen()) {
                return false;
            }
            try {
                connection = DbManager.getInstance().getConnection();
                preparedStatement = connection.prepareStatement(CLOSE_SHIFT);
                preparedStatement.setString(1, String.valueOf(new Timestamp(System.currentTimeMillis())));
                preparedStatement.setInt(2, currentShift.getId());
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                log.log(Level.ERROR, e);
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.log(Level.ERROR, e);
                }
            }
        }
        throw new IllegalArgumentException("wrong idShift");
    }

    public boolean openShift() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DbManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(OPEN_SHIFT);
            preparedStatement.setString(1, String.valueOf(new Timestamp(System.currentTimeMillis())));
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            log.log(Level.ERROR, throwables);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.log(Level.ERROR, e);
            }
        }
        return false;
    }
}

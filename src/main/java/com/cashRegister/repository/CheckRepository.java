package com.cashRegister.repository;

import com.cashRegister.DbManager;
import com.cashRegister.model.Check;
import com.cashRegister.model.Shift;
import com.cashRegister.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckRepository {

    private static CheckRepository checkRepository;
    private static ShiftRepository shiftRepository;

    private static final String ALL_CHECKS_THIS_SHIFT = "SELECT * FROM checks WHERE users_id = ? AND shifts_id = ?;";


    public synchronized static CheckRepository getCheckRepository() {
        if (checkRepository == null) {
            checkRepository = new CheckRepository();
        }
        return checkRepository;
    }

    public CheckRepository() {
        shiftRepository = ShiftRepository.getShiftRepository();
    }

    public List<Check> getAllChecksCurrentCasherOpenShift(User user) {
        List<Check> checks = new ArrayList<>();
        Shift openshift = shiftRepository.firstOpenShift();

        try {
            Connection connection = DbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ALL_CHECKS_THIS_SHIFT);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, openshift.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idCheck = rs.getInt("id");
                double checkSum = rs.getDouble("CheckSum");
                Timestamp checkTime = rs.getTimestamp("CheckTme");
                boolean isReturned = (rs.getInt("isReturned") == 1) ? true : false;
                checks.add(new Check(idCheck, checkSum, checkTime, isReturned, openshift, user));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return checks;
    }
}

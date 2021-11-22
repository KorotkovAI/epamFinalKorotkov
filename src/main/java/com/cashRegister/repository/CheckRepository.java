package com.cashRegister.repository;

import com.cashRegister.DbManager;
import com.cashRegister.exception.CheckReturnedException;
import com.cashRegister.exception.ShiftNotFoundException;
import com.cashRegister.model.Check;
import com.cashRegister.model.Goods;
import com.cashRegister.model.Shift;
import com.cashRegister.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CheckRepository {

    private static CheckRepository checkRepository;
    private static ShiftRepository shiftRepository;
    private static CheckGoodsRepository checkGoodsRepository;
    private static UserRepository userRepository;

    private static final String ALL_CHECKS_THIS_SHIFT = "SELECT * FROM checks WHERE users_id = ? AND shifts_id = ?;";
    private static final String ADD_CHECK = "INSERT INTO checks SET checksum = ?, checktime = ?, isreturned = 0, shifts_id = ?, users_id = ?;";
    private static final String RETURN_CHECK = "UPDATE checks SET isReturned = 1 WHERE id = ?;";
    private static final String SELECT_CURRENT_CHECK = "SELECT * FROM checks WHERE id = ?;";

    public synchronized static CheckRepository getCheckRepository() {
        if (checkRepository == null) {
            checkRepository = new CheckRepository();
        }
        return checkRepository;
    }

    public CheckRepository() {
        shiftRepository = ShiftRepository.getShiftRepository();
        checkGoodsRepository = CheckGoodsRepository.getCheckGoodsRepository();
        userRepository = UserRepository.getUserRepository();
    }

    public List<Check> getAllChecksCurrentCasherOpenShift(User user) {
        if (user != null) {
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
                    Timestamp checkTime = rs.getTimestamp("CheckTime");
                    boolean isReturned = (rs.getInt("isReturned") == 1) ? true : false;
                    checks.add(new Check(idCheck, checkSum, checkTime, isReturned, openshift, user));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return checks;
        }
        throw new NullPointerException("user cannot be null");
    }

    public int createCheck(List<Goods> goodsList, Shift shift, User user) {

        int primarykey = 0;

        if (!goodsList.isEmpty() || goodsList != null || shift != null || user != null) {
            double cheksum = 0.0;

            for (Goods goods : goodsList) {
                cheksum += goods.getAmount() * goods.getPrice();
            }

            try {
                Connection connection = DbManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(ADD_CHECK, new String[]{"id"});
                preparedStatement.setDouble(1, cheksum);
                preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.setInt(3, shift.getId());
                preparedStatement.setInt(4, user.getId());
                if (preparedStatement.executeUpdate() > 0) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        primarykey = generatedKeys.getInt(1);
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return primarykey;
    }

    public Check getCurrentCheck(int checkId) {
        Check currentCheck = null;
        System.out.println("get in method getCurrent check");
        try {
            Connection connection = DbManager.getConnection();
            PreparedStatement pstm = connection.prepareStatement(SELECT_CURRENT_CHECK);
            pstm.setInt(1, checkId);
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                double checkSum = resultSet.getDouble("CheckSum");
                Timestamp checkTime = resultSet.getTimestamp("CheckTime");
                boolean isReturned = (resultSet.getInt("isReturned") == 1) ? true : false;
                int shiftId = resultSet.getInt("Shifts_id");
                int usersId = resultSet.getInt("Users_id");
                currentCheck = new Check(checkId, checkSum, checkTime, isReturned,
                        shiftRepository.getShiftById(shiftId), userRepository.getCurrentUser(usersId));
                System.out.println(currentCheck.getId() + "id check");
                break;
            }
        } catch (SQLException | ShiftNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return currentCheck;
    }

    public boolean checkReturn(int checkId) throws CheckReturnedException {

        if (checkId > 0) {
            System.out.println("get into method");
            Check currentCheck = getCurrentCheck(checkId);
            System.out.println(currentCheck + "currentcheck");
            if (!currentCheck.isReturned()) {
                try {
                    System.out.println("check not returned");
                    Connection connection = DbManager.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(RETURN_CHECK);
                    preparedStatement.setInt(1, checkId);
                    preparedStatement.execute();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                throw new CheckReturnedException("check already returned");
            }
        }
        return false;
    }

}

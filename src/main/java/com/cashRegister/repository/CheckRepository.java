package com.cashRegister.repository;

import com.cashRegister.DbManager;
import com.cashRegister.exception.CheckReturnedException;
import com.cashRegister.exception.ShiftNotFoundException;
import com.cashRegister.model.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CheckRepository {

    private static CheckRepository checkRepository;
    private static ShiftRepository shiftRepository;
    private static CheckGoodsRepository checkGoodsRepository;
    private static UserRepository userRepository;

    private static final Logger log = LogManager.getLogger(CheckRepository.class);

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
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet rs = null;

            try {
                connection = DbManager.getInstance().getConnection();
                preparedStatement = connection.prepareStatement(ALL_CHECKS_THIS_SHIFT);
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setInt(2, openshift.getId());
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    int idCheck = rs.getInt("id");
                    double checkSum = rs.getDouble("CheckSum");
                    Timestamp checkTime = rs.getTimestamp("CheckTime");
                    boolean isReturned = (rs.getInt("isReturned") == 1) ? true : false;
                    checks.add(new Check(idCheck, checkSum, checkTime, isReturned, openshift, user));
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
            return checks;
        }
        log.log(Level.ERROR, "user cannot be null");
        throw new NullPointerException("user cannot be null");
    }

    public int createCheck(List<Goods> goodsList, Shift shift, User user) {

        int primarykey = 0;

        if (!goodsList.isEmpty() || goodsList != null || shift != null || user != null) {
            double cheksum = 0.0;

            for (Goods goods : goodsList) {
                cheksum += goods.getAmount() * goods.getPrice();
            }

            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = DbManager.getInstance().getConnection();
                preparedStatement = connection.prepareStatement(ADD_CHECK, new String[]{"id"});
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
                log.log(Level.ERROR, throwables);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.log(Level.ERROR, e);
                }
            }
        }
        return primarykey;
    }

    public Check getCurrentCheck(int checkId) {
        Check currentCheck = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;

        if (checkId > 0) {
            try {
                connection = DbManager.getInstance().getConnection();
                pstm = connection.prepareStatement(SELECT_CURRENT_CHECK);
                pstm.setInt(1, checkId);
                resultSet = pstm.executeQuery();
                while (resultSet.next()) {
                    double checkSum = resultSet.getDouble("CheckSum");
                    Timestamp checkTime = resultSet.getTimestamp("CheckTime");
                    boolean isReturned = (resultSet.getInt("isReturned") == 1) ? true : false;
                    int shiftId = resultSet.getInt("Shifts_id");
                    int usersId = resultSet.getInt("Users_id");
                    currentCheck = new Check(checkId, checkSum, checkTime, isReturned,
                            shiftRepository.getShiftById(shiftId), userRepository.getCurrentUser(usersId));
                    break;
                }
            } catch (SQLException | ShiftNotFoundException throwables) {
                log.log(Level.ERROR, throwables);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.log(Level.ERROR, e);
                }
            }
            return currentCheck;
        } else {
            throw new IllegalArgumentException("Check id can not be less then 0");
        }
    }

    public boolean checkReturn(int checkId) throws CheckReturnedException {

        if (checkId > 0) {
            Check currentCheck = getCurrentCheck(checkId);

            if (!currentCheck.isReturned()) {
                Connection connection = null;
                PreparedStatement preparedStatement = null;

                try {
                    connection = DbManager.getInstance().getConnection();
                    preparedStatement = connection.prepareStatement(RETURN_CHECK);
                    preparedStatement.setInt(1, checkId);
                    preparedStatement.execute();
                    return true;
                } catch (SQLException e) {
                    log.log(Level.ERROR, e);
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        log.log(Level.ERROR, e);
                    }
                }
            } else {
                throw new CheckReturnedException("check already returned");
            }
        }
        return false;
    }
}

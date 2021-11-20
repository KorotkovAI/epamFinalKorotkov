package com.cashRegister.repository;

import com.cashRegister.DbManager;
import com.cashRegister.model.Goods;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CheckGoodsRepository {

    private static CheckGoodsRepository checkGoodsRepository;

    private String ADD_CHECKGOODS = "INSERT INTO checkgoods SET idgoods = %d, namegoods = '%s', amountgoods = %d, pricegoods = %s, checks_id = %d;";

    public synchronized static CheckGoodsRepository getCheckGoodsRepository() {
        if (checkGoodsRepository == null) {
            checkGoodsRepository = new CheckGoodsRepository();
        }
        return checkGoodsRepository;
    }

    public boolean addCheckGoodsList(List<Goods> goodsList, int checkId) {
        if (!goodsList.isEmpty() || goodsList != null || checkId > 0) {

            try {
                Connection connection = DbManager.getConnection();
                Statement stmt = connection.createStatement();
                for (Goods goods : goodsList) {
                    String currentBatch = String.format(ADD_CHECKGOODS, goods.getId(), goods.getName(),
                            goods.getAmount(), goods.getPrice(), checkId);
                    stmt.addBatch(currentBatch);
                }
                stmt.executeBatch();
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return true;
        }
        return false;
    }
}

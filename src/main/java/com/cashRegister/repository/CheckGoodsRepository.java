package com.cashRegister.repository;

import com.cashRegister.DbManager;
import com.cashRegister.model.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckGoodsRepository {

    private static CheckGoodsRepository checkGoodsRepository;

    private String ADD_CHECKGOODS = "INSERT INTO checkgoods SET idgoods = %d, namegoods = '%s', amountgoods = %d, pricegoods = %s, checks_id = %d;";
    private static final String ALL_GOODS_FOR_RETURN = "SELECT * FROM checkgoods WHERE checks_id = ?;";

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

    public List<Goods> returnCheckGoods(int checkId) {
        List<Goods> goodsForReturn = new ArrayList<>();

        if (checkId >0) {
            try {
                Connection connection =DbManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(ALL_GOODS_FOR_RETURN);
                preparedStatement.setInt(1,checkId);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    int goodsId = rs.getInt("idGoods");
                    String goodsName = rs.getString("nameGoods");
                    int goodsAmount = rs.getInt("amountGoods");
                    double goodsPrice = rs.getDouble("priceGoods");
                    goodsForReturn.add(new Goods(goodsId, goodsName, goodsAmount, goodsPrice));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return goodsForReturn;
    }
}

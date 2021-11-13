package com.cashRegister.repository;

import com.cashRegister.model.Goods;
import com.cashRegister.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsRepository {
    private static final String SELECT_ALL_GOODS = "SELECT * FROM goods;";

    private static GoodsRepository goodsRepository = null;

    public synchronized static GoodsRepository getGoodsRepository() {
        if (goodsRepository == null) {
            goodsRepository = new GoodsRepository();
        }
        return goodsRepository;
    }

    public List<Goods> getAllGoods() {
        List<Goods> goods = new ArrayList<>();
        try {
            Connection connection = DbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GOODS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int goodsId = rs.getInt("id");
                String goodsName = rs.getString("name");
                int goodsAmount = rs.getInt("amount");
                double goodsPrice = rs.getDouble("price");
                goods.add(new Goods(goodsId,goodsName, goodsAmount, goodsPrice));
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        return goods;
    }

}

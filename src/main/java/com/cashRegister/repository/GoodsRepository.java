package com.cashRegister.repository;

import com.cashRegister.exception.GoodsNotFoundException;
import com.cashRegister.model.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsRepository {
    private static final String SELECT_ALL_GOODS = "SELECT * FROM goods;";
    private static final String UPDATE_GOODS = "UPDATE goods SET name = ?, amount = ?, price = ? WHERE id = ?;";
    private static final String DELETE_GOODS = "DELETE FROM goods WHERE id = ?;";
    private static final String ADD_GOODS = "INSERT INTO goods SET name = ?, amount = ?, price = ?;";

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
                goods.add(new Goods(goodsId, goodsName, goodsAmount, goodsPrice));
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        return goods;
    }

    public Goods getGoodsById(int id) throws GoodsNotFoundException {
        List<Goods> goodsList = getAllGoods();
        for (Goods goods : goodsList) {
            if (goods.getId() == id) {
                return goods;
            }
        }
        throw new GoodsNotFoundException("goods with ID " + id + " not found");
    }

    public Goods getGoodsByName(String name) throws GoodsNotFoundException {
        List<Goods> goodsList = getAllGoods();
        for (Goods goods : goodsList) {
            if (goods.getName().equals(name)) {
                return goods;
            }
        }
        throw new GoodsNotFoundException("goods with name " + name + " not found");
    }

    public boolean update(Goods newGoods) throws GoodsNotFoundException {
        List<Goods> goodsList = getAllGoods();
        Goods oldGoods = goodsList.get(newGoods.getId());

        if (oldGoods != null) {
            if (oldGoods.equals(newGoods)) {
                return false;
            }

            try {
                Connection connection = DbManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GOODS);
                preparedStatement.setString(1, newGoods.getName());
                preparedStatement.setInt(2, newGoods.getAmount());
                preparedStatement.setDouble(3, newGoods.getPrice());
                preparedStatement.setInt(4, newGoods.getId());
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return false;
    }

    public boolean deleteByName(String goodsName) throws SQLException {
        List<Goods> goodsList = goodsRepository.getAllGoods();
        Goods goods = goodsList.stream().filter(x -> x.getName().equals(goodsName)).findFirst().get();
        int newParam = goods.getId();
        if (newParam != -1) {
            Connection connection = DbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GOODS);
            preparedStatement.setInt(1, newParam);
            preparedStatement.executeUpdate();
            return true;
        }
        return false;
    }

    public boolean addGoods(Goods goods) {
        if (goods != null) {
            List<Goods> goodsList = getAllGoods();
            boolean status = goodsList.stream().anyMatch(x -> x.getName().equals(goods.getName()));
            if (!status) {
                try {
                    Connection connection = DbManager.getConnection();
                    PreparedStatement preparedStatement = null;
                    preparedStatement = connection.prepareStatement(ADD_GOODS);
                    preparedStatement.setString(1, goods.getName());
                    preparedStatement.setInt(2, goods.getAmount());
                    preparedStatement.setDouble(3, goods.getPrice());
                    preparedStatement.executeUpdate();
                    System.out.println("000000000000");
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}

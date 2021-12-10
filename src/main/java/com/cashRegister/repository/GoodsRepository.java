package com.cashRegister.repository;

import com.cashRegister.DbManager;
import com.cashRegister.exception.GoodsNotFoundException;
import com.cashRegister.model.Goods;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class works with goods
 */
public class GoodsRepository {
    private static final String SELECT_ALL_GOODS = "SELECT * FROM goods;";
    private static final String UPDATE_GOODS = "UPDATE goods SET name = ?, amount = ?, price = ? WHERE id = ?;";
    private static final String DELETE_GOODS = "DELETE FROM goods WHERE id = ?;";
    private static final String ADD_GOODS = "INSERT INTO goods SET name = ?, amount = ?, price = ?;";
    private String DELETE_FROM_STORE = "UPDATE goods SET amount = %d WHERE id = %d;";

    private static GoodsRepository goodsRepository = null;

    private static final Logger log = LogManager.getLogger(GoodsRepository.class);


    public synchronized static GoodsRepository getGoodsRepository() {
        if (goodsRepository == null) {
            goodsRepository = new GoodsRepository();
        }
        return goodsRepository;
    }

    public List<Goods> getAllGoods() {
        List<Goods> goods = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DbManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_GOODS);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int goodsId = rs.getInt("id");
                String goodsName = rs.getString("name");
                int goodsAmount = rs.getInt("amount");
                double goodsPrice = rs.getDouble("price");
                goods.add(new Goods(goodsId, goodsName, goodsAmount, goodsPrice));
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        log.log(Level.ERROR, "goods with ID " + id + " not found");
        throw new GoodsNotFoundException("goods with ID " + id + " not found");
    }

    public Goods getGoodsByName(String name) throws GoodsNotFoundException {
        List<Goods> goodsList = getAllGoods();
        for (Goods goods : goodsList) {
            if (goods.getName().equals(name)) {
                return goods;
            }
        }
        log.log(Level.ERROR, "goods with name " + name + " not found");
        throw new GoodsNotFoundException("goods with name " + name + " not found");
    }

    public boolean update(Goods newGoods) throws GoodsNotFoundException {
        List<Goods> goodsList = getAllGoods();
        Goods oldGoods = goodsList.stream().filter(x -> x.getId() == newGoods.getId()).findFirst().orElse(null);

        if (oldGoods != null) {
            if (oldGoods.equals(newGoods)) {
                log.log(Level.ERROR, "equals goods");
                return false;
            }
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = DbManager.getInstance().getConnection();
                preparedStatement = connection.prepareStatement(UPDATE_GOODS);
                preparedStatement.setString(1, newGoods.getName());
                preparedStatement.setInt(2, newGoods.getAmount());
                preparedStatement.setDouble(3, newGoods.getPrice());
                preparedStatement.setInt(4, newGoods.getId());
                preparedStatement.executeUpdate();
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
        }
        return false;
    }

    public boolean deleteByName(String goodsName) {
        List<Goods> goodsList = goodsRepository.getAllGoods();
        Goods goods = goodsList.stream().filter(x -> x.getName().equals(goodsName)).findFirst().get();
        int newParam = goods.getId();

        if (newParam != -1) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = DbManager.getInstance().getConnection();
                preparedStatement = connection.prepareStatement(DELETE_GOODS);
                preparedStatement.setInt(1, newParam);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                log.log(Level.ERROR, e);
            }
            return true;
        }
        return false;
    }

    public boolean addGoods(Goods goods) {
        if (goods != null) {
            List<Goods> goodsList = getAllGoods();
            boolean status = goodsList.stream().anyMatch(x -> x.getName().equals(goods.getName()));

            if (!status) {
                Connection connection = null;
                PreparedStatement preparedStatement = null;

                try {
                    connection = DbManager.getInstance().getConnection();
                    preparedStatement = connection.prepareStatement(ADD_GOODS);
                    preparedStatement.setString(1, goods.getName());
                    preparedStatement.setInt(2, goods.getAmount());
                    preparedStatement.setDouble(3, goods.getPrice());
                    preparedStatement.executeUpdate();
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
            }
        }
        return false;
    }

    public boolean deleteGoodsForCheck(List<Goods> goodsList) {
        Connection connection = null;
        Statement statement = null;
        Statement stmtRollBack = null;

        try {
            connection = DbManager.getInstance().getConnection();
            statement = connection.createStatement();
            List<Goods> oldGoods = getAllGoods();
            for (Goods goods : goodsList) {
                if (goods.getId() > 0 || goods.getAmount() > 0) {
                    int oldAmount = oldGoods.stream().filter(x -> x.getName().equals(goods.getName())).findFirst().get().getAmount();
                    int newGoodsAmount = oldAmount - goods.getAmount();
                    if (newGoodsAmount < 0) {
                        statement.cancel();
                        return false;
                    }
                    String currentBatch = String.format(DELETE_FROM_STORE, newGoodsAmount, goods.getId());
                    statement.addBatch(currentBatch);
                } else {
                    statement.cancel();
                    return false;
                }
            }

            statement.executeBatch();

            boolean checkIsMinus = false;
            List<Goods> goodsUpdatedList = getAllGoods();

            for (Goods goodsFromCheck : goodsList) {
                int amountfromList = goodsUpdatedList.stream().filter(x -> x.getName().equals(goodsFromCheck.getName())).
                        findFirst().get().getAmount();
                if (amountfromList < 0) {
                    checkIsMinus = true;
                    break;
                }
            }

            if (checkIsMinus) {
                stmtRollBack = connection.createStatement();
                for (Goods goodsForRollback : goodsList) {
                    String rollbackBatch = String.format(DELETE_FROM_STORE,
                            oldGoods.get(goodsForRollback.getId()).getAmount(), goodsForRollback.getId());
                    stmtRollBack.addBatch(rollbackBatch);
                }
                stmtRollBack.executeBatch();
                return false;
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
        return true;
    }

    public boolean returnGoods(List<Goods> goodsList) {
        if (goodsList != null && !goodsList.isEmpty()) {
            List<Goods> oldgoods = getAllGoods();
            Connection connection = null;
            Statement statement = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = DbManager.getInstance().getConnection();
                statement = connection.createStatement();

                for (Goods goods : goodsList) {
                    Goods oldGoodsForUpdate = oldgoods.stream().filter(x -> x.getId() == goods.getId()).findFirst().orElse(null);
                    if (oldGoodsForUpdate != null) {
                        int newAmount = oldGoodsForUpdate.getAmount() + goods.getAmount();
                        String currentBatch = String.format(DELETE_FROM_STORE, newAmount, goods.getId());
                        statement.addBatch(currentBatch);
                    } else {
                        preparedStatement = connection.prepareStatement(ADD_GOODS);
                        preparedStatement.setString(1, goods.getName());
                        preparedStatement.setInt(2, goods.getAmount());
                        preparedStatement.setDouble(3, goods.getPrice());
                        preparedStatement.executeUpdate();
                    }
                }
                statement.executeBatch();
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
        }
        return false;
    }
}

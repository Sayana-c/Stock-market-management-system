package com.stockmarket.dao;

import com.stockmarket.entity.Stock;

public interface StockDao {
    void saveStock(Stock stock);
    Stock getStock(int id);
    void updateStock(Stock stock);
    void deleteStock(int id);
}


package com.stockmarket.main;

import com.stockmarket.dao.StockDao;
import com.stockmarket.dao.StockDaoImpl;
import com.stockmarket.entity.Stock;

public class StockApp {
    public static void main(String[] args) {
        StockDao stockDao = new StockDaoImpl();

        Stock stock1 = new Stock("TCS", "Tata Consultancy Services", 3500.50);
        stockDao.saveStock(stock1);

        Stock fetchedStock = stockDao.getStock(stock1.getId());
        System.out.println("Fetched Stock: " + fetchedStock);

        fetchedStock.setPrice(3600.75);
        stockDao.updateStock(fetchedStock);

        // stockDao.deleteStock(fetchedStock.getId());
    }
}


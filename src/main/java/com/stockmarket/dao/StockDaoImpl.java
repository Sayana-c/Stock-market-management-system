package com.stockmarket.dao;

import com.stockmarket.entity.Stock;
import com.stockmarket.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StockDaoImpl implements StockDao {

    @Override
    public void saveStock(Stock stock) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(stock);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Stock getStock(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Stock.class, id);
        }
    }

    @Override
    public void updateStock(Stock stock) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(stock);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStock(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Stock stock = session.get(Stock.class, id);
            if (stock != null) session.delete(stock);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}

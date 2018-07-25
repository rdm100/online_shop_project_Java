package db;

import models.Basket;
import models.Customer;
import models.Order;
import models.Product;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBProduct {


    private static Session session;

    public static void saveProducts(Basket basket){
        for(Product product: basket.getProducts()){
            DBHelper.save(product);
        }
    }

    public static List<Product> productsFromSearch(String name) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Product> products = null;
        try {
            Criteria cr = session.createCriteria(Product.class);
            cr.add(Restrictions.ilike("name",   name +"%"));
            products = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }
}


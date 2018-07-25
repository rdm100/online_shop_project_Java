package db;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import models.Basket;
import models.Customer;
import models.Order;
import models.Product;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBOrder {

    private static Session session;

    public static List<Product> findProductsInOrder(Order order) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Product> foundProducts = null;
        try {
            Criteria cr = session.createCriteria(Product.class);
            cr.createAlias("orders", "order");
            cr.add(Restrictions.eq("order.id", order.getId()));
            foundProducts = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return foundProducts;
    }

}





package db;

import models.Customer;
import models.Order;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBCustomer {

    private static Session session;

    public static List<Order> AllOrdersBelongingToACustomer(Customer customer) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Order> orders = null;
        try {
            Criteria cr = session.createCriteria(Order.class);
            cr.add(Restrictions.eq("customer", customer));
            orders = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return orders;
    }

    public static Customer findCustomerByUsername(String username) {
        session = HibernateUtil.getSessionFactory().openSession();
        Customer foundCustomer = null;
        try {
            Criteria cr = session.createCriteria(Customer.class);
            cr.add(Restrictions.eq("username", username));
            foundCustomer = (Customer)cr.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return foundCustomer;
    }
}

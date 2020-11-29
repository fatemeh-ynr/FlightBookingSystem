package repository.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {


    private SessionFactory sessionFactory;
    private Class<T> entityClass;
    private String entityClassName;


    public GenericDaoImpl() {
        sessionFactory = DBConnection.getSessionFactory();
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        entityClassName = entityClass.getSimpleName();
        System.out.println("entityClassName: " + entityClassName);
    }

    @Override
    public int create(T t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (Integer) session.save(t);
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public T read(PK id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        T t = session.get(entityClass, id);
        transaction.commit();
        session.close();
        return t;
    }

    @Override
    public T update(T t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        T updatedObject = (T) session.merge(t);
        transaction.commit();
        session.close();
        return updatedObject;
    }

    @Override
    public void delete(T t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(t);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteById(PK id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session = sessionFactory.getCurrentSession();
        T t = (T) session.get(entityClass, id);
        session.delete(t);
        session.flush();
        transaction.commit();
        session.close();
    }

    @Override
    public List<T> selectAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from " + entityClassName);
        List list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
}

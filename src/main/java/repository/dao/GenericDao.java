package repository.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {
    int create(T t);
    T read(PK id);
    T update(T t);
    void delete(T t);
    void deleteById(PK id);
    List<T> selectAll();
}

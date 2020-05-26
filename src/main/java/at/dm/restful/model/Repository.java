package at.dm.restful.model;

import java.util.List;

public interface Repository<T> {
    public List<T> findAll();
    public T findOne(int id);
    public int create(T entity); // returns last insert id
}

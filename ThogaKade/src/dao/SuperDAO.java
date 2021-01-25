package dao;


import entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

public interface SuperDAO<T extends SuperEntity,ID> extends Serializable {
    boolean save(T t) throws Exception;
    boolean update(T t) throws Exception;
    boolean delete(ID id) throws Exception;
    T get(ID id) throws Exception;
    List<T> getAll() throws Exception;
}

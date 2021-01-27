package org.itstep.controller.domain.dao;

import java.util.List;

public interface GenericDao<T, ID> {
    void save(T data);
    T findById(ID id);
    List<T> findAll();
    void delete(T data);
    T update(T data);
}

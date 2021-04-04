package org.itstep.controller.domain.dao.impl;

import org.itstep.aspect.Cached;
import org.itstep.controller.domain.dao.TaskDao;
import org.itstep.controller.domain.entity.Task;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class TaskDaoImpl implements TaskDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void save(Task data) {
//        try {
            //entityManager.getTransaction().begin();
            entityManager.persist(data);
//            entityManager.getTransaction().commit();
//        } catch (Throwable ex) {
//            entityManager.getTransaction().rollback();
//        }
    }

    @Override
    @Cached
    public Task findById(Integer integer) {
        Task task = entityManager.find(Task.class, integer);
        System.out.println("Find Id: " + task);
        return task;
    }

    @Override
    @Cached
    public List<Task> findAll() {
        return entityManager.createQuery("FROM Task", Task.class).getResultList();
    }

    @Override
    public void delete(Task data) {
        System.out.println("delete: " + data);
        entityManager.remove((Object) entityManager.merge(data));
    }

    @Override
    public Task update(Task data) {
        return entityManager.merge(data);
    }

}

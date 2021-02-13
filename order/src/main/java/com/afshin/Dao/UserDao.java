package com.afshin.Dao;

import com.afshin.Entity.*;
import com.afshin.General.GeneralFunc;
import com.afshin.General.Myentitymanager;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * @Project order
 * @Author Afshin Parhizkari
 * @Date 2020 - 12 - 23
 * @Time 11:36 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:
 */
public class UserDao {
    public UserDao() {}

    EntityManager entityManager = Myentitymanager.getEntityManager();
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    public List<User> findall() {
        try {
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> u = criteriaQuery.from(User.class);
            criteriaQuery.select(u);
            Query q = entityManager.createQuery(criteriaQuery);
            List<User> users=q.getResultList();
            GeneralFunc.logger.info("{}.{}|Try: All are Fetched",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName());
            return users;
        }catch (Exception e){
            GeneralFunc.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public User findbyid(Integer userid) {
        try {
            User user=entityManager.find(User.class, userid);
            GeneralFunc.logger.info("{}.{}|Try: ID {} is Fetched",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),user.getIdusers());
            return user;
        }catch (Exception e){
            GeneralFunc.logger.error("{}.{}|Exception: {}",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
            e.printStackTrace();
            return null;
        }    }
    public User login(String userName) {
        try {
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> u = criteriaQuery.from(User.class);
            criteriaQuery.select(u);
            criteriaQuery.where(criteriaBuilder.equal(u.get("username"),userName));
            Query q = entityManager.createQuery(criteriaQuery);
            User user= (User) q.getResultList().get(0);
            GeneralFunc.logger.info("{}.{}|Try: userName {} is Fetched",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),user.getUsername());
            return user;
        }catch (Exception e){
            GeneralFunc.logger.error("{}.{}|Exception: {}",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    //ExecuteUpdate
    public void insert(User user){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            GeneralFunc.logger.info("{}.{}|Try: Inserted",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        }catch(Exception e){
            GeneralFunc.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
            e.printStackTrace();
        }
    }
    public void update(User user){
        try {
            entityManager.getTransaction().begin();
            user.setUsername(user.getUsername());
            user.setPassword(user.getPassword());
            user.setEmployeeid(user.getEmployeeid());
            entityManager.getTransaction().commit();
            GeneralFunc.logger.info("{}.{}|Try: Updated",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        }catch(Exception e){
            GeneralFunc.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
            e.printStackTrace();
        }
    }
    public void delete(User user){
        try{
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
            GeneralFunc.logger.info("{}.{}|Try: Deleted",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        }catch(Exception e){
            GeneralFunc.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
            e.printStackTrace();
        }
    }
}

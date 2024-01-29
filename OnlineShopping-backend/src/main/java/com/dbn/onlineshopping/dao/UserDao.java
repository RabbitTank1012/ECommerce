package com.dbn.onlineshopping.dao;

import com.dbn.onlineshopping.domain.entity.User;
import com.dbn.onlineshopping.domain.entity.UserResult;
import com.dbn.onlineshopping.exception.UserSaveFailedException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
@Repository
public class UserDao {
    @Autowired
    SessionFactory sessionFactory;
    private String userid;
    private String role;

    public void setRole(String role){
        this.role = role;
    }

    public void setUserid(String userid){
        this.userid =userid;
    }
    public List<User> getAllUsers(){
        Session session;
        List<User> userList = null;
        List<UserResult> userResults =null;

        try{
            session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root);
            userList = session.createQuery(cq).getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (userList.isEmpty()) ? null : userList;
    }

    public List<UserResult> getAllUsers2(){
        Session session;
        List<User> userList = null;
        List<UserResult> userResults =null;

        try{
            session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UserResult> cq = cb.createQuery(UserResult.class);
            Root<User> root = cq.from(User.class);
            cq.multiselect(root.get("user_id"),root.get("username"),root.get("password")
                    ,root.get("email"),root.get("role"));


            userResults = session.createQuery(cq).getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (userResults.isEmpty()) ? null : userResults;
    }

    public User getUserById(int id){
        Session session;
        Optional<User> user = null;
        try{
            session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            Predicate predicate = cb.equal(root.get("user_id"), id);
            cq.select(root).where(predicate);
            user = session.createQuery(cq).uniqueResultOptional();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return (user.isPresent())? user.get() : null;
    }

    public UserResult getUserById2(int id){
        Session session;
        Optional<User> user = null;
        Optional<UserResult> userResult = null;
        try{
            session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UserResult> cq = cb.createQuery(UserResult.class);
            Root<User> root = cq.from(User.class);
            cq.multiselect(root.get("user_id"),root.get("username"),root.get("password"),
                    root.get("email"),root.get("role"));

            Predicate predicate = cb.equal(root.get("user_id"), id);
            cq.where(predicate);
            userResult = session.createQuery(cq).uniqueResultOptional();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return (userResult.isPresent())?userResult.get() : null;
    }

    public String addUser(User user){
        Session session;
        String response ="aa";
        try{
            session = sessionFactory.getCurrentSession();
           if(getUserByname(user.getUsername()) == null){
               session.saveOrUpdate(user);
               response = "New user has been added.";
           }
           else
               response = "User exists, have another try.";


        }
        catch(Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public void somethingWentWrong () throws UserSaveFailedException {
        throw new UserSaveFailedException("Something went wrong, rolling back");
    }

    public void deleteById(User user){
        Session session;
        try{
            session = sessionFactory.getCurrentSession();
            session.delete(user);
        }
        catch (Exception e){
            throw e;
        }
    }

    public void updateStatusById(int userid, boolean status){

        Session session;
        try{
            session = sessionFactory.getCurrentSession();
            User user = session.get(User.class, userid);
            session.saveOrUpdate(user);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public User getUserByname(String username){
        Session session;
        Optional<User> user = null;
        try{
            session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            Predicate predicate = cb.equal(root.get("username"), username);
            cq.select(root).where(predicate);

            user = session.createQuery(cq).uniqueResultOptional();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return (user.isPresent())? user.get() : null;
    }

}

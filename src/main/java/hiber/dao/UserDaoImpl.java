package hiber.dao;


import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

   private HibernateTemplate hibernateTemplate;

   @Autowired
   public void setSessionFactory(SessionFactory sessionFactory) {
      this.hibernateTemplate = new HibernateTemplate(sessionFactory);
   }
   @Override
   public void add(User user) {
      hibernateTemplate.save(user);
   }

   @Override
   public List<User> listUsers() {
      return hibernateTemplate.execute(session -> session.createQuery("from User", User.class).getResultList());
   }

//   @Override
//   public void add(User user) {
//      sessionFactory.getCurrentSession().save(user);
//   }
//
//   @Override
//   @SuppressWarnings("unchecked")
//   public List<User> listUsers() {
//      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
//      return query.getResultList();
//   }

//   @Override
//   @SuppressWarnings("unchecked")
//   public User getUserByCar(String model, int series) {
//      String hql = "FROM User user where Car.model = :model AND Car.series = :series";
//      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
//      query.setParameter("model", model).setParameter("series", series);
//      return query.setMaxResults(1).getSingleResult();
//   }
@Override
@SuppressWarnings("unchecked")
public User getUserByCar(String model, int series) {
   String hql = "FROM User user WHERE user.car.model = :model AND user.car.series = :series";
   List<User> users = (List<User>) hibernateTemplate.execute(session -> {
      Query query = session.createQuery(hql);
      query.setParameter("model", model);
      query.setParameter("series", series);
      query.setMaxResults(1);
      return query.list();
   });
   return !users.isEmpty() ? users.get(0) : null;
}

}

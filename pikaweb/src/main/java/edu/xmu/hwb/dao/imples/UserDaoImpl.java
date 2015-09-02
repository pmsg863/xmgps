package edu.xmu.hwb.dao.imples;

import edu.xmu.hwb.dao.UserDaoI;
import edu.xmu.hwb.domain.GpsUser;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Herrfe on 14-5-23.
 */

@Repository("userDao")
public class UserDaoImpl implements UserDaoI {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Serializable saveUser(GpsUser user) {
        return this.sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public GpsUser getUser(String email) {
        String hql = ("from GpsUser where email = ? ") ;
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);

        List<GpsUser> result = query.setString(0, email).list();

        if (result==null)
            return null;
        else
            return result.get(0);
    }

    @Override
    public List<GpsUser> getUsers() {
        String hql = "from GpsUser" ;

        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
}

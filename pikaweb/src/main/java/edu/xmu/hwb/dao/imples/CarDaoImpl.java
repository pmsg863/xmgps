package edu.xmu.hwb.dao.imples;

import edu.xmu.hwb.dao.CarDaoI;
import edu.xmu.hwb.domain.CarInfo;
import edu.xmu.hwb.domain.GpsUser;
import edu.xmu.hwb.domain.HistoryPositionTEntity;
import edu.xmu.hwb.domain.RealPositionTEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pmsg863 on 14-5-26.
 */
@Repository("carDao")
public class CarDaoImpl implements CarDaoI {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Serializable saveCarInfo(CarInfo carInfo) {
        return this.sessionFactory.getCurrentSession().save(carInfo);
    }

    @Override
    public void removeCarInfo(CarInfo carInfo) {
        this.sessionFactory.getCurrentSession().delete(carInfo);
    }

    @Override
    public List<CarInfo> getCars(GpsUser user) {
        String hql = ("from CarInfo where user = ? ") ;
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);

        List<CarInfo> result = query.setEntity(0,user).list();

        return result;
    }

    @Override
    public List<RealPositionTEntity> getLastPosition( List<String> cars) {
        String hql = ("from RealPositionTEntity where carnumber in (:carnos) ") ;
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);

        return query.setParameterList("carnos", cars).list();
    }

    @Override
    public List<HistoryPositionTEntity> getHisPosition(String carno, String starttime, String endtime) {
        String hql = ("from HistoryPositionTEntity where carnumber = :carnumber and gpstime > :starttime and gpstime < :endtime order by gpstime") ;
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);

        query.setMaxResults(3000) ;
        List result = query
                .setParameter("carnumber", carno)
                .setParameter("starttime", starttime)
                .setParameter("endtime", endtime).list();

        return result;
    }
}

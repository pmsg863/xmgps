package edu.xmu.hwb.service.imples;

import edu.xmu.hwb.dao.CarDaoI;
import edu.xmu.hwb.domain.CarInfo;
import edu.xmu.hwb.domain.GpsUser;
import edu.xmu.hwb.domain.HistoryPositionTEntity;
import edu.xmu.hwb.domain.RealPositionTEntity;
import edu.xmu.hwb.service.CarServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pmsg863 on 14-5-26.
 */
@Repository("carService")
public class CarServiceImpl implements CarServiceI {

    @Autowired
    CarDaoI dao ;


    @Override
    public Serializable saveCarInfo(CarInfo carInfo) {
        return dao.saveCarInfo(carInfo);
    }

    @Override
    public void removeCarInfo(CarInfo carInfo) {
        dao.removeCarInfo(carInfo);
    }

    @Override
    public List<CarInfo> getCars(GpsUser user) {
        return dao.getCars(user);
    }

    @Override
    public List<RealPositionTEntity> getLastPosition( List<String> cars) {
        return dao.getLastPosition(cars);
    }

    @Override
    public List<HistoryPositionTEntity> getHisPosition(String carno, String starttime, String endtime) {
        return dao.getHisPosition(carno,starttime,endtime);
    }
}

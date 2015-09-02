package edu.xmu.hwb.dao;

import edu.xmu.hwb.domain.CarInfo;
import edu.xmu.hwb.domain.GpsUser;
import edu.xmu.hwb.domain.HistoryPositionTEntity;
import edu.xmu.hwb.domain.RealPositionTEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pmsg863 on 14-5-26.
 */
public interface CarDaoI {
    public Serializable saveCarInfo(CarInfo carInfo);
    public void removeCarInfo(CarInfo carInfo);
    public List<CarInfo> getCars(GpsUser user);
    public List<RealPositionTEntity> getLastPosition(List<String> cars );
    public List<HistoryPositionTEntity> getHisPosition(String carno,String starttime,String endtime );
}

package edu.xmu.hwb.controller;

import edu.xmu.hwb.domain.CarInfo;
import edu.xmu.hwb.domain.GpsUser;
import edu.xmu.hwb.domain.HistoryPositionTEntity;
import edu.xmu.hwb.domain.RealPositionTEntity;
import edu.xmu.hwb.service.CarServiceI;
import edu.xmu.hwb.util.RequestAck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pmsg863 on 14-5-26.
 */
@Controller
@RequestMapping("/cars")
public class CarContoller {
    @Autowired
    private CarServiceI carService;

    @ResponseBody
    @RequestMapping(value = "position", method = {RequestMethod.POST, RequestMethod.GET})
    public List<RealPositionTEntity> getUser(HttpServletRequest request) {
        List<String> carnos = new LinkedList<String>();

        List<CarInfo> cars = this.getCars(request);
        for (CarInfo car : cars) {
            carnos.add(car.getCarno());
        }
        return  carService.getLastPosition(carnos);
    }

    @ResponseBody
    @RequestMapping(value = "getCars", method = {RequestMethod.POST, RequestMethod.GET})
    public List<CarInfo> getCars(HttpServletRequest request) {
        GpsUser userinfo = (GpsUser)request.getSession().getAttribute("userinfo");
        if (userinfo == null)
            return null;
        else
            return carService.getCars(userinfo);
    }

    @ResponseBody
    @RequestMapping(value = "hisposition", method = {RequestMethod.POST, RequestMethod.GET} ,produces="text/plain;charset=UTF-8")
    public List<HistoryPositionTEntity> getHisPosition(@RequestParam String carno,@RequestParam String starttime,@RequestParam String endtime,HttpServletRequest request) {
        GpsUser userinfo = (GpsUser)request.getSession().getAttribute("userinfo");
        if (userinfo == null)
            return null;
        else
            return carService.getHisPosition(carno,starttime,endtime);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = "application/json")
    public
    @ResponseBody
    RequestAck saveCarInfo(@RequestBody CarInfo carInfo, HttpServletRequest request) {
        GpsUser userinfo = (GpsUser) request.getSession().getAttribute("userinfo");
        if (userinfo == null)
            return new RequestAck("001");
        else {
            carInfo.setUser(userinfo);
            Serializable serializable = carService.saveCarInfo(carInfo);
            return new RequestAck("000").setReturnObject(serializable);
        }
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST, consumes = "application/json")
    public
    @ResponseBody
    RequestAck removeCarInfo(@RequestBody CarInfo carInfo, HttpServletRequest request) {
        GpsUser userinfo = (GpsUser) request.getSession().getAttribute("userinfo");
        if (userinfo == null)
            return new RequestAck("001");
        else {
            carInfo.setUser(userinfo);
            carService.removeCarInfo(carInfo);
            return new RequestAck("000");
        }
    }
}

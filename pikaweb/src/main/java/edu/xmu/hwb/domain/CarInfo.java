package edu.xmu.hwb.domain;

import javax.persistence.*;

/**
 * Created by pmsg863 on 14-5-26.
 */
@Entity
@Table(name = "car_info", schema = "")
public class CarInfo {

    @Id
    @Column(name = "car_id")
    //自增序列
    @GeneratedValue(strategy=GenerationType.IDENTITY,generator="seq_car_id")
    @SequenceGenerator(name="seq_car_id",sequenceName="seq_car_id")
    public long getCarid() {
        return carid;
    }

    public void setCarid(Long carid) {
        this.carid = carid;
    }

    @ManyToOne
    public GpsUser getUser() {
        return user;
    }

    public void setUser(GpsUser userid) {
        this.user = userid;
    }

    @Basic
    @Column(name = "car_no" ,unique=true)
    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }

    @Basic
    @Column(name = "car_type")
    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }

    private long carid;
    private GpsUser user;
    private String carno;
    private String cartype;


}

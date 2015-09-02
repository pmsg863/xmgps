package edu.xmu.hwb.domain;

import javax.persistence.*;

/**
 * Created by pmsg863 on 14-5-26.
 */
@Entity
@Table(name = "history_position_t", schema = "", catalog = "gps")
public class HistoryPositionTEntity {
    private int id;
    private String imei;
    private String carnumber;
    private byte location;
    private String gpstime;
    private String systime;
    private long latitude;
    private long longtitude;
    private int high;
    private int speed;
    private int direction;
    private int mileage;
    private int gpstype;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IMEI")
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Basic
    @Column(name = "carnumber")
    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    @Basic
    @Column(name = "location")
    public byte getLocation() {
        return location;
    }

    public void setLocation(byte location) {
        this.location = location;
    }

    @Basic
    @Column(name = "gpstime")
    public String getGpstime() {
        return gpstime;
    }

    public void setGpstime(String gpstime) {
        this.gpstime = gpstime;
    }

    @Basic
    @Column(name = "systime")
    public String getSystime() {
        return systime;
    }

    public void setSystime(String systime) {
        this.systime = systime;
    }

    @Basic
    @Column(name = "latitude")
    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "longtitude")
    public long getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(long longtitude) {
        this.longtitude = longtitude;
    }

    @Basic
    @Column(name = "high")
    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    @Basic
    @Column(name = "speed")
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Basic
    @Column(name = "direction")
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Basic
    @Column(name = "mileage")
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Basic
    @Column(name = "gpstype")
    public int getGpstype() {
        return gpstype;
    }

    public void setGpstype(int gpstype) {
        this.gpstype = gpstype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistoryPositionTEntity that = (HistoryPositionTEntity) o;

        if (direction != that.direction) return false;
        if (gpstype != that.gpstype) return false;
        if (high != that.high) return false;
        if (id != that.id) return false;
        if (latitude != that.latitude) return false;
        if (location != that.location) return false;
        if (longtitude != that.longtitude) return false;
        if (mileage != that.mileage) return false;
        if (speed != that.speed) return false;
        if (carnumber != null ? !carnumber.equals(that.carnumber) : that.carnumber != null) return false;
        if (gpstime != null ? !gpstime.equals(that.gpstime) : that.gpstime != null) return false;
        if (imei != null ? !imei.equals(that.imei) : that.imei != null) return false;
        if (systime != null ? !systime.equals(that.systime) : that.systime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (imei != null ? imei.hashCode() : 0);
        result = 31 * result + (carnumber != null ? carnumber.hashCode() : 0);
        result = 31 * result + (int) location;
        result = 31 * result + (gpstime != null ? gpstime.hashCode() : 0);
        result = 31 * result + (systime != null ? systime.hashCode() : 0);
        result = 31 * result + (int) (latitude ^ (latitude >>> 32));
        result = 31 * result + (int) (longtitude ^ (longtitude >>> 32));
        result = 31 * result + high;
        result = 31 * result + speed;
        result = 31 * result + direction;
        result = 31 * result + mileage;
        result = 31 * result + gpstype;
        return result;
    }
}

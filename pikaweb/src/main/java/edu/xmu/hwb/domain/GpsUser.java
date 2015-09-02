package edu.xmu.hwb.domain;

import javax.persistence.*;

/**
 * Created by Herrfe on 14-5-23.
 */
@Entity
@Table(name = "gps_user", schema = "")
public class GpsUser {
    @Id
    @Column(name = "user_id")
    //自增序列
    @GeneratedValue(strategy=GenerationType.IDENTITY,generator="seq_gpsuser_id")
    @SequenceGenerator(name="seq_gpsuser_id",sequenceName="seq_gpsuser_id")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Basic
    @Column(name = "password")
    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
    @Basic
    @Column(name = "mobile")
    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }
    @Basic
    @Column(name = "user_group")
    public int getUsergroup() {
        return usergroup;
    }

    public void setUsergroup(int usergroup) {
        this.usergroup = usergroup;
    }
    @Basic
    @Column(name = "user_name")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Basic
    @Column(name = "user_address")
    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }


    public GpsUser(String email, String username, String useraddress, int usergroup) {
        this.email = email;
        this.username = username;
        this.useraddress = useraddress;
        this.usergroup = usergroup;
    }

    public GpsUser() {
    }

    int userid;
    String email;
    char[] password;
    long mobile;
    int usergroup;
    String username;
    String useraddress;

    public GpsUser copy() {
        return new GpsUser(this.email,this.username,this.useraddress,this.usergroup);
    }

    public GpsUser nopUser() {
        return new GpsUser();
    }

}

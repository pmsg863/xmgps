package edu.xmu.hwb.dao;

import edu.xmu.hwb.domain.GpsUser;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Herrfe on 14-5-23.
 */
public interface UserDaoI {
    public Serializable saveUser(GpsUser user);
    public GpsUser getUser(String email);
    public List<GpsUser> getUsers( );
}

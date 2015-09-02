package edu.xmu.hwb.service;

import edu.xmu.hwb.domain.GpsUser;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Herrfe on 14-5-23.
 */
public interface UserServiceI {

    public Serializable saveUser(GpsUser user);

    public GpsUser getUser(String email);

    public List<GpsUser> getUsers( );
}

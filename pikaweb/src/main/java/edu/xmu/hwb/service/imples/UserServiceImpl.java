package edu.xmu.hwb.service.imples;

import edu.xmu.hwb.dao.UserDaoI;
import edu.xmu.hwb.domain.GpsUser;
import edu.xmu.hwb.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Herrfe on 14-5-23.
 */
@Service("userService")
public class UserServiceImpl implements UserServiceI{

    @Autowired
    UserDaoI dao ;

    @Override
    public Serializable saveUser(GpsUser user) {
        return dao.saveUser(user);
    }

    @Override
    public GpsUser getUser(String email) {
        return dao.getUser(email);
    }

    @Override
    public List<GpsUser> getUsers() {
        return dao.getUsers();
    }
}

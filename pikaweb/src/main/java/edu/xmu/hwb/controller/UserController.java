package edu.xmu.hwb.controller;

import edu.xmu.hwb.domain.GpsUser;
import edu.xmu.hwb.service.UserServiceI;
import edu.xmu.hwb.util.RequestAck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Herrfe on 14-5-23.
 */
@Controller
@RequestMapping("/users")
public class UserController {
    private UserServiceI userService;

    @Autowired
    public void setUserService(UserServiceI userService) {
        this.userService = userService;
    }

    /**
     * 查询用户          todo 密码的加密
     */
    @ResponseBody
    @RequestMapping(value = "getUser", method = RequestMethod.GET)
    public GpsUser getUser(@RequestParam String email,@RequestParam String password, HttpServletRequest request) {
        GpsUser dbuser = userService.getUser(email);
        if (String.valueOf( dbuser.getPassword() )
                .equals( password )
               ){
            request.getSession().setAttribute("userinfo",dbuser);
            return dbuser.copy();
        }
        else
            return dbuser.nopUser();
    }

    @ResponseBody
    @RequestMapping(value = "getUsers", method = RequestMethod.GET)
    public List<GpsUser> getUsers(HttpServletRequest request) {
        return userService.getUsers();
    }

    /**
     * 增加用户
     */
    @RequestMapping(value = "save", method = RequestMethod.POST ,consumes = "application/json")
    public @ResponseBody RequestAck saveUser(@RequestBody GpsUser user, HttpServletRequest request) {
        userService.saveUser(user);
        return new RequestAck("000");
    }
}

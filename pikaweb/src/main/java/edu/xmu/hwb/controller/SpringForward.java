package edu.xmu.hwb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Herrfe on 14-5-26.
 */
@Controller
@RequestMapping("/spring")
public class SpringForward {
    @RequestMapping(value = "redirect", method = RequestMethod.GET)
    public String redirect(String page){
        return page;
    }
}

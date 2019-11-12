package com.yalogs.redisclient.controller.curd;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApplicationContestBeansController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/beans")
    @ResponseBody
    public String[] beans() {
        return applicationContext.getBeanDefinitionNames();
    }
}

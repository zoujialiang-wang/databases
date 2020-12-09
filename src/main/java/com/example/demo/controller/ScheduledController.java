package com.example.demo.controller;


import com.example.demo.commen.ScheduledTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author zou18846936743
 * @Date 2020/12/9 下午7:21
 * @Version 1.0
 */
@RestController
@RequestMapping
public class ScheduledController {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Autowired
    private ScheduledTasks scheduledTasks;

    @RequestMapping("/schedledTest")
    public void test(@RequestParam String name) {
        scheduledTasks.start(() -> System.out.println(Thread.currentThread().getName() + "执行的时间是：  " + dateFormat.format(new Date())), new CronTrigger("0/1 * * * * ?"), name);

    }

    @RequestMapping("/stopSchedled")
    public void stopSchedled(@RequestParam String name) {
        scheduledTasks.stop(name);
    }

}

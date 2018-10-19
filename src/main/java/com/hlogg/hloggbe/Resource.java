package com.hlogg.hloggbe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Resource {

    private ActivityRepo activityRepo = new ActivityRepo();

    @RequestMapping("/activities/all")
    public List<Activity> getAllActivities(){

        ArrayList<Activity> activities = activityRepo.getAllActivities();

        return activities;
    }
}

package com.hlogg.hloggbe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
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

    @RequestMapping("/activities/getFirst")
}
package com.hlogg.hloggbe;

import org.springframework.context.annotation.Bean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityRepo {

    public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";
    public static final String DATABASE_NAME = "hlogg";
    public static final String TABLE_NAME_ACTIVITY_RECORDS = "activity_records";

    public ActivityRepo() {
    }

    public ArrayList<Activity> getAllActivities(){

        List<String> activitiesList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL+DATABASE_NAME);

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM activities";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                activitiesList.add(resultSet.getString(2));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException sql_e) {
            System.out.println("Got the following sql exception: " + sql_e.toString());
        }



        ArrayList<Activity> activities = new ArrayList<>();

        Activity activity1 = new Activity(1, "Skate");
        Activity activity2 = new Activity(2, "333");
        Activity activity3 = new Activity(3, "Koreansk");

        activities.add(activity1);
        activities.add(activity2);
        activities.add(activity3);

        return activities;
    }
}

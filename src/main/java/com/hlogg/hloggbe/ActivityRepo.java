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

        ArrayList<Activity> activitiesList = new ArrayList<>();

        try {
                Connection connection = DriverManager.getConnection(DATABASE_URL+DATABASE_NAME);

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM activities";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){

                Activity activity = new Activity(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );
                activitiesList.add(activity);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException sql_e) {
            System.out.println("Got the following sql exception: " + sql_e.toString());
        }


        return activitiesList;
    }

    public Activity getActivityById(int activityId){
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL+DATABASE_NAME);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM activities WHERE activity_id = " + activityId + ";";
            ResultSet resultSet = statement.executeQuery(query);

            Activity activity = new Activity();

            while(resultSet.next()){
                activity.setActivityId(resultSet.getInt(1));
                activity.setActivityDescription(resultSet.getString(2));
            }

            resultSet.close();
            statement.close();
            connection.close();

            return activity;

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return null;
    }


}

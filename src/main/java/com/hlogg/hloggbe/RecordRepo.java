package com.hlogg.hloggbe;

import org.apache.tomcat.jni.Local;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;

public class RecordRepo {

//    public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";
    public static final String DATABASE_URL = System.getenv("JDBC_DATABASE_URL");
    public static final String DATABASE_USER_NAME = "hlogg";
    public static final String TABLE_NAME_ACTIVITY_RECORDS = "activity_records";


    public Record saveNewRecord(Record record){


        int week = record.getDate().get(WeekFields.ISO.weekOfYear());

        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            String sql = String.format("INSERT INTO records (date, week, activity_id)" +
                    "VALUES (?,?,?);");

            LocalDate date = record.getDate();
            Date dateFormatted = Date.valueOf(date);

            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setDate(1, dateFormatted);
            preparedStatement.setInt(2, week);
            preparedStatement.setInt(3, record.getActivity().getActivityId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("SAVING NEW RECORD FAILED");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    Record savedRecord = this.getRecordById(generatedKeys.getInt(1));
                    preparedStatement.close();
                    connection.close();
                    return savedRecord;
                }
                else {
                    preparedStatement.close();
                    connection.close();
                    throw new SQLException("CREATING RECORD FAILED, no ID obtained.");
                }
            }


        } catch (SQLException e) {
            System.out.println(" SOMETHING WENT WRONG WHILE TRYING TO SAVE A NEW RECORD");
            System.out.println(e.toString());
            return null;
        }
    }

    public ArrayList<Record> getAllRecords(){

        ActivityRepo activityRepo = new ActivityRepo();

        ArrayList<Record> records = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM records;";
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()){
                Record record = new Record();
                record.setId(resultSet.getInt(1));
                record.setDate(resultSet.getDate(2).toLocalDate());
                record.setWeek(resultSet.getInt(3));

                Activity activity = activityRepo.getActivityById(resultSet.getInt(4));
                record.setActivity(activity);

                records.add(record);
            }

        } catch (SQLException e) {
            System.out.println("SOMETHING FAILED WHILE GETTING ALL RECORDS");
            System.out.println(e.toString());
        }

        return records;
    }

    public Record getRecordById(int recordId){

        Record record = new Record();
        ActivityRepo activityRepo = new ActivityRepo();

        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM records WHERE record_id = %d", recordId);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                record.setId(resultSet.getInt(1));

                record.setDate(resultSet.getDate(2).toLocalDate());

                record.setWeek(record.getDate().get(WeekFields.ISO.weekOfYear()));

                Activity activity = activityRepo.getActivityById(resultSet.getInt(4));
                record.setActivity(activity);
            }

            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println("DET VAR DENNE JA" + e.toString());
        }

        System.out.println("Get record by ID: " + record.getId());

        return record;
    }

    public boolean deleteRecordById(int record_id) {

        System.out.println("Will try to delete record with id: " + record_id);

        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM records WHERE records.record_id = " + record_id + ";";
            final int i = statement.executeUpdate(sql);

            statement.close();
            connection.close();

            System.out.println(i + " records with record_id " + record_id + " deleted.");

            if (i == 1){
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {

            return false;
        }
    }
}

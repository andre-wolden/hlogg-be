package com.hlogg.hloggbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.logging.Logger;

@SpringBootApplication
public class HloggBeApplication {

	public static final String DATABASE_URL_POSTGRES = "jdbc:postgresql://localhost:5432/postgres";
	public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";
	public static final String DATABASE_NAME = "hlogg";
	public static final String TABLE_NAME_records = "records";


	public static void main(String[] args) {
		SpringApplication.run(HloggBeApplication.class, args);

		boolean dbIsCreated = createDbIfNotExist();
		System.out.println("dbIsCreated: " + dbIsCreated);

		boolean recordsTableIfNotExist = createRecordsTableIfNotExist();
		boolean activitiesTableIfNotExist = createActivitiesTableIfNotExist();
		boolean usersTableIfNotExist = createUsersTableIfNotExist();

//		boolean tablesIsCreated = createTablesIfNotExist();

        boolean activitiesIsInserted = insertActivities();
	}

	private static boolean createDbIfNotExist() {
		Logger logger = Logger.getLogger("db-logger");

		try {

			logger.info("Trying to create database with name --hlogg--. If it already exists, then it's all good..");

			Connection connection = DriverManager.getConnection(DATABASE_URL_POSTGRES, "woldena", "");
			Statement statement = connection.createStatement();
			String sql = String.format("CREATE DATABASE %s;", DATABASE_NAME);
			statement.executeUpdate(sql);
			System.out.println("hlogg database has successfully been created");
			statement.close();
			connection.close();
			return true;

		} catch (SQLException sql_e){
			if (sql_e.getMessage().equals("ERROR: database \"hlogg\" already exists")){
				logger.info(sql_e.getMessage().toString());
				return true;
			} else {
				logger.info(sql_e.getMessage());
				return false;
			}
		}
	}

	private static boolean createActivitiesTableIfNotExist(){
		Logger createTablesIfNotExistLogger = Logger.getLogger("createTable");
		try {
			createTablesIfNotExistLogger.info("Creating activity table");

			Connection connection = DriverManager.getConnection((DATABASE_URL+DATABASE_NAME));

			Statement statement = connection.createStatement();
			String sql = "CREATE TABLE activities (" +
					"activity_id SERIAL PRIMARY KEY," +
					"activity_name VARCHAR(50) NOT NULL UNIQUE" +
					");";

			statement.execute(sql);
			statement.close();

			connection.close();

		} catch (SQLException sql_e){
			System.out.println(sql_e.getMessage().toString());
		}
		return true;

	}

	private static boolean createRecordsTableIfNotExist(){
		Logger createTablesIfNotExistLogger = Logger.getLogger("createTablesIfNotExistLogger");
		try {
			createTablesIfNotExistLogger.info("Creating records table");

			Connection connection = DriverManager.getConnection((DATABASE_URL+DATABASE_NAME));


			Statement statement = connection.createStatement();

			String sql = "CREATE TABLE records (" +
					"record_id SERIAL PRIMARY KEY," +
					"date DATE," +
					"week INTEGER," +
					"activity_id INTEGER REFERENCES activities(activity_id)" +
					");";

			statement.execute(sql);
			statement.close();

			connection.close();

		} catch (SQLException sql_e){
			System.out.println(sql_e.getMessage().toString());
		}
		return true;
	}

	private static boolean createUsersTableIfNotExist(){
		Logger createTablesIfNotExistLogger = Logger.getLogger("createTablesIfNotExistLogger");
		try {
			createTablesIfNotExistLogger.info("Creating tables if they don't already exist...");

			Connection connection = DriverManager.getConnection((DATABASE_URL+DATABASE_NAME));

			Statement statement = connection.createStatement();
			String sql = "CREATE TABLE users (" +
					"user_id SERIAL PRIMARY KEY," +
					"user_name VARCHAR(50) NOT NULL UNIQUE," +
					"password VARCHAR(50) NOT NULL UNIQUE" +
					");";

			statement.execute(sql);

			statement.close();
			connection.close();
		} catch (SQLException sql_e){
			System.out.println(sql_e.getMessage().toString());
		}
		return true;
	}



	private static boolean insertActivities(){

        Logger insertActivitiesLogger = Logger.getLogger("insertActivitiesLogger");

        boolean activitiesTableIsEmpty = false;

        try {
            insertActivitiesLogger.info("Inserting activities into activities table");
            Connection connection = DriverManager.getConnection(DATABASE_URL+DATABASE_NAME);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM activities";
            ResultSet resultSet = statement.executeQuery(sql);

            activitiesTableIsEmpty = !resultSet.next();

            statement.close();
            connection.close();

        } catch (SQLException sql_e) {
            insertActivitiesLogger.info(sql_e.toString());
        }

        if (activitiesTableIsEmpty){
            try {
                insertActivitiesLogger.info("Inserting activities into activities table");
                Connection connection = DriverManager.getConnection(DATABASE_URL+DATABASE_NAME);
                Statement statement = connection.createStatement();
                String sql = "INSERT INTO activities " +
                        "(activity_name)" +
                        "VALUES " +
                        "('333')," +
                        "('Nørd')," +
                        "('Piano')," +
                        "('Koreansk')," +
                        "('Skate')," +
                        "('Trening')," +
                        "('FysMat')," +
                        "('MTG')" +
                        ";";
                statement.execute(sql);
                statement.close();
                connection.close();

                return true;

            } catch (SQLException sql_e) {
                insertActivitiesLogger.info(sql_e.toString());

                return false;
            }
        }

        System.out.println("Activities table is not empty, so not putting in the basics listed above...");

        return true;
    }
}

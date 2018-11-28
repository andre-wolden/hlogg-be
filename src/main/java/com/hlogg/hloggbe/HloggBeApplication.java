package com.hlogg.hloggbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URISyntaxException;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Collections;
import java.util.logging.Logger;

@SpringBootApplication
public class HloggBeApplication {

	public static final String DATABASE_URL_POSTGRES = "jdbc:postgresql://localhost:5432/postgres";
//	public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";
	public static final String DATABASE_URL = System.getenv("JDBC_DATABASE_URL");
	public static final String DATABASE_NAME = "hlogg";
	public static final String TABLE_NAME_records = "records";


	public static void main(String[] args) {

		SpringApplication.run(HloggBeApplication.class, args);

		boolean dbIsCreated = createDbIfNotExist();
		System.out.println("dbIsCreated: " + dbIsCreated);
		boolean tablesIsCreated = createTablesIfNotExist();
        boolean activitiesIsInserted = insertActivities();
	}

	private static Connection getConnection() throws URISyntaxException, SQLException {
		String dbUrl = System.getenv("JDBC_DATABASE_URL");
		return DriverManager.getConnection(dbUrl);
	}

	private static boolean createDbIfNotExist() {
		Logger logger = Logger.getLogger("db-logger");

		try {

			logger.info("Trying to create database with name --hlogg--. If it already exists, then it's all good..");

			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			String sql = String.format("CREATE DATABASE %s;", DATABASE_NAME);
			statement.executeUpdate(sql);
			System.out.println("hlogg database has successfully been created");
			statement.close();
			connection.close();
			return true;

		} catch (SQLException sql_e ){
			if (sql_e.getMessage().equals("ERROR: database \"hlogg\" already exists")){
				logger.info(sql_e.getMessage().toString());
				return true;
			} else {
				logger.info(sql_e.getMessage());
				return false;
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static boolean createTablesIfNotExist(){

		Logger createTablesIfNotExistLogger = Logger.getLogger("createTablesIfNotExistLogger");

		try {
			createTablesIfNotExistLogger.info("Creating tables if they don't already exist...");

			Connection connection = getConnection();

			Statement statement1 = connection.createStatement();
			String sql1 = "CREATE TABLE activities (" +
                    "activity_id SERIAL PRIMARY KEY," +
                    "activity_name VARCHAR(50) NOT NULL UNIQUE" +
                    ");";

			statement1.execute(sql1);
			statement1.close();


			Statement statement2 = connection.createStatement();

			String sql2 = "CREATE TABLE records (" +
					"record_id SERIAL PRIMARY KEY," +
					"date DATE," +
					"week INTEGER," +
					"activity_id INTEGER REFERENCES activities(activity_id)" +
					");";

			statement2.execute(sql2);

			statement2.close();
			connection.close();

		} catch (SQLException sql_e ){
			System.out.println(sql_e.getMessage().toString());
		} catch (URISyntaxException e){
			return false;
		}
		return true;
	}

	private static boolean insertActivities(){

        Logger insertActivitiesLogger = Logger.getLogger("insertActivitiesLogger");

        boolean activitiesTableIsEmpty = false;

        try {
            insertActivitiesLogger.info("Inserting activities into activities table");
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM activities";
            ResultSet resultSet = statement.executeQuery(sql);

            activitiesTableIsEmpty = !resultSet.next();

            statement.close();
            connection.close();

        } catch (SQLException sql_e) {
            insertActivitiesLogger.info(sql_e.toString());
        } catch (URISyntaxException e) {
			e.printStackTrace();
		}

		if (activitiesTableIsEmpty){
            try {
                insertActivitiesLogger.info("Inserting activities into activities table");
                Connection connection = DriverManager.getConnection(DATABASE_URL);
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

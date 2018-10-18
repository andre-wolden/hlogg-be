package com.hlogg.hloggbe;

import java.sql.*;
import java.util.logging.Logger;

public class LoffBladeRepo {


    public LoffBlade getFirst(){

        LoffBlade loffBlade = new LoffBlade();
        loffBlade.setText("First LoffBlade");
        loffBlade.setId(2);

        return loffBlade;
    }


    public static String readBlades(){

        String loffBladesString = "";

        Logger logger = Logger.getLogger("app-logger");

        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres");

            stmt = conn.createStatement();
            String sql = "SELECT * FROM loffblade";
            ResultSet rs = stmt.executeQuery(sql);

            logger.warning("ResultSet:");
            logger.warning(rs.toString());

            while(rs.next()){
                int id = rs.getInt(1);
                String bladeType = rs.getString(2);
                loffBladesString += bladeType.toString();
                System.out.println("id: " + id + ". bladeType: " + bladeType);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException sqle){
            logger.warning("sql exception:");
            logger.warning(sqle.toString());

        } catch (Exception e){
            logger.warning("other exception:");
            logger.warning(e.toString());
        } finally {
            try{
                if(stmt!=null)
                    stmt.close();
            } catch (SQLException se2){
                logger.warning(se2.toString());
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

        return loffBladesString;
    }
}

package org.ksolves;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Filereader implements Runnable {
    private static final Logger log = LogManager.getLogger(Filereader.class);
    private final String[] line;
    Connection connection = DbConnector.connection();
    int batchsize = 10000;
    public Filereader(String[] line) {
        this.line = line;
    }

    @Override
    public void run() {

        try {
            PreparedStatement st = connection.prepareStatement("Insert into csv values(?,?,?,?,?,?,?,?,?,?)");
            st.setInt(1, Integer.valueOf(line[0]));

            for (int i = 1; i < 10; i++) {
                if (line[i] != null) {
                    st.setString(i + 1, line[i]);
                } else {
                    System.out.println("error in " + line);
                }
            }
            st.executeUpdate();
            st.close();
            connection.close();
            log.info("Line executed to database");
        } catch (Exception e) {
            log.error("error in File reader \n");
        }
    }
}


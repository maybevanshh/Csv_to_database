package org.ksolves;

import org.junit.Test;
import org.junit.gen5.api.Assertions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class File_processorTest {
    Connection connection = DbConnector.connection();
    String[] st = {"2003","test","test","test","test","test","test","test","test","test"};
    @Test
    public void file_process() {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO CSV VALUES(?,?,?,?,?,?,?,?,?,?)");
                ps.setInt(1, Integer.valueOf(st[0]));
                for (int i = 0; i < 10; i++) {
                    ps.setString(i + 1, st[i]);
                }
                ps.executeUpdate();
                ps.close();
                PreparedStatement ps2 = connection.prepareStatement("SELECT * from csv where industry_name_nzsioc='test'");
                ResultSet rs = ps2.executeQuery();
                Assertions.assertTrue(rs.next());


            }catch (Exception ignore){            }
    }
}
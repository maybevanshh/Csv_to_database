import org.junit.gen5.api.Assertions;
import org.junit.gen5.api.DisplayName;
import org.junit.gen5.api.Test;
import org.ksolves.DbConnector;
import org.ksolves.Filereader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {
    Connection connection = DbConnector.connection();

    @Test
    @DisplayName("Connection working with Database and table exits.")
    void classtest() throws SQLException {
        PreparedStatement st = connection.prepareStatement("SELECT EXISTS (SELECT FROM pg_tables WHERE tablename  = 'csv');");
        ResultSet rs = st.executeQuery();
        Assertions.assertTrue(rs.next());
        System.out.println("class exists");
    }

    @Test
    @DisplayName("Testing input in database")
    void inputtest() throws SQLException {
        String[] str = {"2003","test","test","test","test","test","test","test","test","test"};
        Filereader fr = new Filereader(str);
        fr.run();
        PreparedStatement st = connection.prepareStatement("Select year from csv where industry_name_nzsioc='test'");
        ResultSet rs = st.executeQuery();
        Assertions.assertTrue(rs.next());
        System.out.println("input success");
    }

    public static void main(String[] args) {
        test a = new test();
        try {
            a.classtest();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            a.inputtest();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

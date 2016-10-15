package pt.uc.dei.wsvd.bench.tpcw.versions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import pt.uc.dei.wsvd.bench.Database;

/**
 * WS - Vulnerability Detection Tools Benchmark
 * TPC - C Benchmark Services
 * #WebServiceOperation
 *
 *
 * @author nmsa@dei.uc.pt
 */
public class GetPassword_VxA {

    public String getPassword(String C_UNAME) {
        String passwd = null;
        Connection con = Database.pickConnection();
        try {
            // Prepare SQL
            PreparedStatement stmt = 
                            Database.pickConnection().prepareStatement("SELECT c_passwd FROM tpcw_customer WHERE c_uname = ?");
            // Set parameter
            stmt.setString(1, C_UNAME);
            
            ResultSet rs = stmt.executeQuery();
            // Results
            rs.next();
            passwd = rs.getString("c_passwd");
            rs.close();
            stmt.close();
            con.commit();
        } catch (java.lang.Exception ex) {
            //ex.printStackTrace();
        } finally {
            Database.relaseConnection(con);
        }
        return passwd;
    }
}

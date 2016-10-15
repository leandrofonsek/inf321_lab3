package pt.uc.dei.wsvd.bench.tpcw.versions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pt.uc.dei.wsvd.bench.Database;
import pt.uc.dei.wsvd.bench.tpcw.object.Book;

/**
 * WS - Vulnerability Detection Tools Benchmark
 * TPC - C Benchmark Services
 * #WebServiceOperation
 *
 *
 * @author nmsa@dei.uc.pt
 */
public class DoAuthorSearch_VxA {

    public List<Book> doAuthorSearch(String search_key) {
        List<Book> books = new ArrayList<Book>();
        Connection con = Database.pickConnection();
        try {
            // Prepare SQL
            Statement statement = Database.createStatement(con);
            // Set parameter
//            statement.setString(1, );
            ResultSet rs = statement.executeQuery("SELECT * FROM tpcw_author, tpcw_item WHERE tpcw_item.i_a_id = tpcw_author.a_id AND tpcw_author.a_lname LIKE '" + search_key + "%' AND ROWNUM <= 50 ORDER BY tpcw_item.i_title ");
            // Results
            while (rs.next()) {
                books.add(new Book(rs));
            }
            rs.close();
            statement.close();
            con.commit();
        } catch (java.lang.Exception ex) {
            //ex.printStackTrace();
        } finally {
            Database.relaseConnection(con);
        }
        return books;
    }
}

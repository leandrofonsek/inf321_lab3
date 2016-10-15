package pt.uc.dei.wsvd.bench.tpcapp.versions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pt.uc.dei.wsvd.bench.tpcapp.input.ProductDetailInput;
import pt.uc.dei.wsvd.bench.tpcapp.output.ProductDetailOutput;
import pt.uc.dei.wsvd.bench.tpcapp.output.ProductDetailPojo;
import pt.uc.dei.wsvd.bench.Database;

/**
 * WS - Vulnerability Detection Tools Benchmark
 * TPC - APP Benchmark Services
 *
 *
 * #WebServiceOperation
 *
 * @author cnl@dei.uc.pt
 * @author nmsa@dei.uc.pt
 */
public class ProductDetail_VxA {

    public ProductDetailOutput process(ProductDetailInput input) throws Exception {
        //TODO:Uncoment to use validator pt.uc.dei.wsvd.bench.tpcapp.validator.InputValidator.getInstance().validate(input);
        ProductDetailOutput pdo = new ProductDetailOutput();
        Connection conn = Database.pickConnection();
        try {
            if (conn != null) {
                pdo = getData(conn, input);
            }

            conn.commit();
        } catch (SQLException e) {
            throw e;
        } finally {
            Database.relaseConnection(conn);
        }
//        if (pdo != null) {
//            System.out.println("********# PDO: " + pdo.getDetailedProductsList());
        return pdo;
    }

    private ProductDetailOutput getData(Connection conn, ProductDetailInput input) throws SQLException {
        List<Long> list = input.getItemIds();
        ProductDetailOutput pdo = new ProductDetailOutput();
        String sql = "Select i_id, i_title, a_fname, a_lname, i_pub_date, i_publisher, "
                + " i_subject, i_desc, i_cost, i_srp, i_avail, i_isbn,"
                + " i_page, i_dimensions, i_image" + " from item, author"
                + " where a_id = i_a_id and i_id = ";
        Statement stmt = Database.createStatement(conn);
        if (list != null) {
            // try
            // {
            int tam = list.size();
            List<ProductDetailPojo> dpl = new ArrayList<ProductDetailPojo>();
            for (int i = 0; i < tam; ++i) {
                Long l = list.get(i);
                // System.out.println("\n"+sql+l);
                // ResultSet rs= db.executeQuery(sql, list);
                ResultSet rs = stmt.executeQuery(sql + l);
                while (rs.next()) {
                    long i_id = rs.getLong("i_id");
                    String i_title = rs.getString("i_title");
                    String a_fname = rs.getString("a_fname");
                    String a_lname = rs.getString("a_lname");
                    Date i_pub_date = rs.getDate("i_pub_date");
                    String i_publisher = rs.getString("i_publisher");
                    String i_subject = rs.getString("i_subject");
                    String i_desc = rs.getString("i_desc");
                    String i_cost = rs.getString("i_cost");
                    String i_srp = rs.getString("i_srp");
                    Date i_avail = rs.getDate("i_avail");
                    String i_isbn = rs.getString("i_isbn");
                    String i_page = rs.getString("i_page");
                    String i_dimensions = rs.getString("i_dimensions");
                    String i_image = rs.getString("i_image");

                    ProductDetailPojo pdp = new ProductDetailPojo();
                    pdp.setItemId(i_id);
                    pdp.setItemTitle(i_title);
                    pdp.setAuthorFName(a_fname);
                    pdp.setAuthorLName(a_lname);
                    pdp.setItemPubDate(i_pub_date);
                    pdp.setItemPublisher(i_publisher);
                    pdp.setItemSubject(i_subject);
                    pdp.setItemDesc(i_desc);
                    pdp.setItemCost(i_cost);
                    pdp.setItemSrp(i_srp);
                    pdp.setItemAvail(i_avail.toString());
                    pdp.setItemIsbn(i_isbn);
                    pdp.setItemPage(i_page);
                    pdp.setItemDimensions(i_dimensions);
                    pdp.setImageBytes(i_image.getBytes());

                    dpl.add(pdp);
                }
                if (rs != null) {
                    rs.close();
                }
            }
            pdo.setDetailedProductsList(dpl);
            // }
            // catch (SQLException e)
            // {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }
        } else {
            System.out.println("Source List is null!!!");
        }
        stmt.close();
        /*
         * percorrer o result set criar uma lista com os dados do resultset
         */
        return (pdo);
    }
}

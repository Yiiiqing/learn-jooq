package zone.yiqing.learnjooq;

import static zone.yiqing.learnjooq.generated.Tables.*;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.*;

/**
 * @author yiqing.zhang, {@literal <yiqing.zhang@leyantech.com>}
 * @date 2021-06-29.
 */
public class Main {

  public static void main(String[] args) {
    String userName = "root";
    String password = "123456";
    String url = "jdbc:mysql://localhost:3306/library";

    // Connection is the only JDBC resource that we need
    // PreparedStatement and ResultSet are handled by jOOQ, internally
    try (Connection conn = DriverManager.getConnection(url, userName, password)) {
      // ...
      DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
      Result<Record> result = create.select().from(AUTHOR).fetch();
      for (Record r : result) {
        Integer id = r.getValue(AUTHOR.ID);
        String firstName = r.getValue(AUTHOR.FIRST_NAME);
        String lastName = r.getValue(AUTHOR.LAST_NAME);
        System.out.println("ID: " + id + " first name: " + firstName + " last name: " + lastName);
      }
    }
    // For the sake of this tutorial, let's keep exception handling simple
    catch (Exception e) {
      e.printStackTrace();
    }
  }

}

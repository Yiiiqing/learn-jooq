package zone.yiqing.learnjooq.config;

import static zone.yiqing.learnjooq.generated.Tables.AUTHOR;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author yiqing.zhang, {@literal <yiqing.zhang@leyantech.com>}
 * @date 2021-06-29.
 */
public class DslContextConfig {
  public static String userName = "root";
  public static String password = "123456";
  public static String url = "jdbc:mysql://localhost:3306/library";

  public static DSLContext getDslContext(){
    try{
      Connection conn = DriverManager.getConnection(url, userName, password);
      DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
      return create;
    }catch (SQLException e){
      e.printStackTrace();
    }
    return null;
  }
}
